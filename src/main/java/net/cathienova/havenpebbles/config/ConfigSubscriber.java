package net.cathienova.havenpebbles.config;

import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import net.cathienova.havenpebbles.HavenPebbles;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@EventBusSubscriber(modid = HavenPebbles.MODID)
public class ConfigSubscriber {
    @SubscribeEvent
    public static void onModConfigEvent(final ModConfigEvent event) {
        if (!(event instanceof ModConfigEvent.Loading || event instanceof ModConfigEvent.Reloading)) {
            return;
        }

        if (event.getConfig().getType() == ModConfig.Type.CLIENT) {
            HavenPebblesConfig.bakeClient();
        }

        if (event.getConfig().getType() == ModConfig.Type.SERVER) {
            if (event instanceof ModConfigEvent.Loading && HavenPebbles.server_config.takeDefaultsCreated()) {
                IConfigSpec.ILoadedConfig loadedConfig = event.getConfig().getLoadedConfig();
                if (loadedConfig != null) {
                    writeServerConfig(event.getConfig(), loadedConfig.config());
                }
            }

            HavenPebblesConfig.bakeServer();
        }
    }

    private static void writeServerConfig(
            ModConfig config,
            UnmodifiableCommentedConfig root
    ) {
        Path path = config.getFullPath();
        if (path == null) {
            return;
        }

        String formatted = renderServerConfig(root);

        try {
            String current = Files.readString(path, StandardCharsets.UTF_8);
            if (!current.equals(formatted)) {
                Files.writeString(path, formatted, StandardCharsets.UTF_8);
            }
        } catch (IOException exception) {
            HavenPebbles.LOGGER.warn("Could not format server config '{}': {}", path, exception.getMessage());
        }
    }

    private static String renderServerConfig(UnmodifiableCommentedConfig root) {
        StringBuilder output = new StringBuilder();

        appendComment(output, root.getComment("pebbleEffects"), 0);
        output.append("[pebbleEffects]\r\n");

        Object pebbleEffectsValue = root.getRaw("pebbleEffects");
        if (pebbleEffectsValue instanceof UnmodifiableConfig pebbleEffects) {
            for (UnmodifiableConfig.Entry entry : pebbleEffects.entrySet()) {
                output.append("    ")
                        .append(key(entry.getKey()))
                        .append(" = ")
                        .append(quoted(stringValue(entry.getValue(), "default")))
                        .append("\r\n");
            }
        }

        output.append("\r\n");

        appendComment(output, root.getComment("pebbleEffectTypes"), 0);
        output.append("[pebbleEffectTypes]\r\n");

        Object effectTypesValue = root.getRaw("pebbleEffectTypes");
        if (effectTypesValue instanceof UnmodifiableConfig effectTypes) {
            for (UnmodifiableConfig.Entry entry : effectTypes.entrySet()) {
                if (!(entry.getValue() instanceof UnmodifiableCommentedConfig effectType)) {
                    continue;
                }

                output.append("\r\n");
                output.append("    [pebbleEffectTypes.")
                        .append(key(entry.getKey()))
                        .append("]\r\n");

                appendComment(output, effectType.getComment("type"), 8);

                String selection = stringValue(effectType.getRaw("type"), "all");
                output.append("        type = ")
                        .append(quoted(selection))
                        .append("\r\n");

                appendComment(output, effectType.getComment("effects"), 8);

                output.append("        effects = [\r\n");

                Object effectsValue = effectType.getRaw("effects");
                if (effectsValue instanceof List<?> effects) {
                    appendRows(output, effects, 12);
                }

                output.append("        ]\r\n");
            }
        }

        Object gatheringValue = root.getRaw("gathering");
        if (gatheringValue instanceof UnmodifiableConfig gathering) {
            for (UnmodifiableConfig.Entry entry : gathering.entrySet()) {
                if (!(entry.getValue() instanceof UnmodifiableCommentedConfig gatheringEntry)) {
                    continue;
                }

                output.append("\r\n");
                output.append("[gathering.")
                        .append(key(entry.getKey()))
                        .append("]\r\n");

                appendComment(output, gatheringEntry.getComment("dimensions"), 4);
                appendStringList(output, "dimensions", gatheringEntry.getRaw("dimensions"));

                appendComment(output, gatheringEntry.getComment("useBlocks"), 4);
                appendStringList(output, "useBlocks", gatheringEntry.getRaw("useBlocks"));

                appendComment(output, gatheringEntry.getComment("outputs"), 4);
                output.append("    outputs = [\r\n");

                Object outputsValue = gatheringEntry.getRaw("outputs");
                if (outputsValue instanceof List<?> outputs) {
                    appendRows(output, outputs, 8);
                }

                output.append("    ]\r\n");
            }
        }

        return output.toString();
    }

    private static void appendComment(
            StringBuilder output,
            String comment,
            int indentation
    ) {
        if (comment == null || comment.isBlank()) {
            return;
        }

        String spaces = " ".repeat(indentation);
        String normalized = comment
                .replace("\r\n", "\n")
                .replace('\r', '\n');

        for (String line : normalized.split("\n")) {
            output.append(spaces)
                    .append("#")
                    .append(line)
                    .append("\r\n");
        }
    }

    private static void appendRows(
            StringBuilder output,
            List<?> values,
            int indentation
    ) {
        String spaces = " ".repeat(indentation);

        for (int index = 0; index < values.size(); index++) {
            Object value = values.get(index);
            if (!(value instanceof List<?> row)) {
                continue;
            }

            output.append(spaces)
                    .append(formatRow(row));

            if (index < values.size() - 1) {
                output.append(",");
            }

            output.append("\r\n");
        }
    }

    private static void appendStringList(
            StringBuilder output,
            String name,
            Object configuredValue
    ) {
        output.append("    ")
                .append(name)
                .append(" = [\r\n");

        if (configuredValue instanceof List<?> values) {
            for (int index = 0; index < values.size(); index++) {
                output.append("        ")
                        .append(quoted(String.valueOf(values.get(index))));

                if (index < values.size() - 1) {
                    output.append(",");
                }

                output.append("\r\n");
            }
        }

        output.append("    ]\r\n");
    }

    private static String formatRow(List<?> values) {
        StringBuilder row = new StringBuilder("[");

        for (int index = 0; index < values.size(); index++) {
            if (index > 0) {
                row.append(", ");
            }

            Object value = values.get(index);
            if (value instanceof String text) {
                row.append(quoted(text));
            } else {
                row.append(value);
            }
        }

        return row.append("]").toString();
    }

    private static String stringValue(Object value, String fallback) {
        return value instanceof String text && !text.isBlank()
                ? text.trim()
                : fallback;
    }

    private static String key(String value) {
        if (value.matches("[A-Za-z0-9_-]+")) {
            return value;
        }

        return quoted(value);
    }

    private static String quoted(String value) {
        return "\"" + value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\r", "\\r")
                .replace("\n", "\\n") + "\"";
    }
}