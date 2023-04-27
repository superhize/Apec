package Apec.Components.Gui.GuiIngame.GuiElements;

import Apec.ApecMain;
import Apec.Settings.SettingsManager;
import Apec.Utils.ApecUtils;
import Apec.Components.Gui.GuiIngame.GUIComponent;
import Apec.Components.Gui.GuiIngame.GUIComponentID;
import Apec.DataInterpretation.DataExtractor;
import Apec.Settings.SettingID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.util.vector.Vector2f;

public class MpBar extends GUIComponent {

    public MpBar() {
        super(GUIComponentID.MN_BAR);
    }

    @Override
    public void drawTex(DataExtractor.PlayerStats ps, DataExtractor.ScoreBoardData sd, DataExtractor.OtherData od, ScaledResolution sr,boolean editingMode) {
        super.drawTex(ps,sd,od,sr,editingMode);
        GlStateManager.pushMatrix();
        GlStateManager.scale(scale,scale,scale);
        if (ApecMain.Instance.settingsManager.getSettingState(SettingID.MP_BAR)) {
            GuiIngame gi = Minecraft.getMinecraft().ingameGUI;

            Vector2f StatBar = ApecUtils.scalarMultiply(getCurrentAnchorPoint(),oneOverScale);

            float mpFactor = ps.Mp > ps.BaseMp ? 1 :(float) ps.Mp / (float) ps.BaseMp;

            boolean showOp = ApecMain.Instance.settingsManager.getSettingState(SettingID.SHOW_OP_BAR);

            mc.renderEngine.bindTexture(new ResourceLocation(ApecMain.modId, "gui/statBars.png"));

            if (ps.Op != 0 && showOp) {

                float opFactor = ps.Op > ps.BaseOp ? 1 : (float)ps.Op / (float)ps.BaseOp; // for safety's sake

                gi.drawTexturedModalRect((int) StatBar.x, (int) StatBar.y, 0, 70, 182, 5);
                gi.drawTexturedModalRect((int) StatBar.x , (int) StatBar.y , 0, 75, (int)(opFactor * 49), 5);
                gi.drawTexturedModalRect((int) StatBar.x  + 51, (int) StatBar.y , 51, 75, (int) (mpFactor * 131f), 5);

            } else {
                gi.drawTexturedModalRect((int) StatBar.x, (int) StatBar.y, 0, 10, 182, 5);
                gi.drawTexturedModalRect((int) StatBar.x, (int) StatBar.y, 0, 15, (int) (mpFactor * 182f), 5);
            }
        }
        GlStateManager.popMatrix();
    }

    @Override
    public Vector2f getAnchorPointPosition() {
        return this.guiModifier.applyGlobalChanges(this,new Vector2f(g_sr.getScaledWidth() - 190, 34));
    }

    @Override
    public Vector2f getBoundingPoint() {
        return new Vector2f(182*scale,5*scale);
    }

}
