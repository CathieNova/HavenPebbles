package net.cathienova.havenpebbles.compat.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.cathienova.havenpebbles.HavenPebbles;
import net.cathienova.havenpebbles.compat.PebbleGatheringRecipe;
import net.cathienova.havenpebbles.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import java.util.Locale;

public class PebbleGatheringCategory implements IRecipeCategory<PebbleGatheringRecipe> {
    public static final IRecipeType<PebbleGatheringRecipe> TYPE = IRecipeType.create(
            HavenPebbles.MODID,
            "gathering",
            PebbleGatheringRecipe.class
    );

    private final IDrawable icon;
    private final IDrawable arrow;

    public PebbleGatheringCategory(IGuiHelper guiHelper) {
        icon = guiHelper.createDrawableItemStack(new ItemStack(ModItems.stone_pebble.get()));
        arrow = guiHelper.getRecipeArrow();
    }

    @Override
    public IRecipeType<PebbleGatheringRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.havenpebbles.gathering");
    }

    @Override
    public int getWidth() {
        return 176;
    }

    @Override
    public int getHeight() {
        return 88;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PebbleGatheringRecipe recipe, IFocusGroup focuses) {
        builder.addInputSlot(30, 45).add(new ItemStack(recipe.block()));
        builder.addOutputSlot(132, 45).add(recipe.result());
    }

    @Override
    public void draw(PebbleGatheringRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        Minecraft minecraft = Minecraft.getInstance();
        Component actionLineOne = Component.translatable("jei.havenpebbles.action_line_one");
        Component actionLineTwo = Component.translatable("jei.havenpebbles.action_line_two");

        arrow.draw(guiGraphics, 75, 45);
        guiGraphics.text(minecraft.font, Component.translatable("jei.havenpebbles.dimension", dimensionName(recipe.dimension())), 2, 2, 0xFF404040, false);
        guiGraphics.text(minecraft.font, actionLineOne, centeredX(minecraft, actionLineOne), 16, 0xFF404040, false);
        guiGraphics.text(minecraft.font, actionLineTwo, centeredX(minecraft, actionLineTwo), 27, 0xFF404040, false);
        guiGraphics.text(minecraft.font, Component.translatable("jei.havenpebbles.chance", formatPercent(recipe.chance())), 2, 77, 0xFF404040, false);
    }

    @Override
    public Identifier getIdentifier(PebbleGatheringRecipe recipe) {
        return recipe.id();
    }

    private static int centeredX(Minecraft minecraft, Component component) {
        return (176 - minecraft.font.width(component)) / 2;
    }

    private static Component dimensionName(String dimension) {
        if (dimension.equals("*")) {
            return Component.translatable("jei.havenpebbles.any_dimension");
        }

        Identifier dimensionId = Identifier.tryParse(dimension);
        if (dimensionId == null) {
            return Component.literal(dimension);
        }

        return Component.translatableWithFallback(
                dimensionId.toLanguageKey("dimension"),
                dimensionId.toString()
        );
    }

    private static String formatPercent(double value) {
        if (Math.abs(value - Math.rint(value)) < 0.0001D) {
            return String.format(Locale.ROOT, "%.0f", value);
        }
        return String.format(Locale.ROOT, "%.2f", value);
    }
}