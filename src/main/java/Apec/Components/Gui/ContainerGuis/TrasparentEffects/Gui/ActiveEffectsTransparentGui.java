package Apec.Components.Gui.ContainerGuis.TrasparentEffects.Gui;

import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.IInventory;

public class ActiveEffectsTransparentGui extends GuiChest {


    public ActiveEffectsTransparentGui(IInventory upperInv, IInventory lowerInv)
    {
        super(upperInv, lowerInv);
    }

    // No drawing should happen

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

    }

    @Override
    public void drawBackground(int tint) {

    }
}
