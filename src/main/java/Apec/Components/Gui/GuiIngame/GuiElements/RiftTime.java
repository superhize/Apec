package Apec.Components.Gui.GuiIngame.GuiElements;

import Apec.ApecMain;
import Apec.Utils.ApecUtils;
import Apec.Components.Gui.GuiIngame.GUIComponentID;
import Apec.Components.Gui.GuiIngame.TextComponent;
import Apec.DataInterpretation.DataExtractor;
import Apec.Settings.SettingID;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.util.vector.Vector2f;

public class RiftTime extends TextComponent {

    public RiftTime () {
        super(GUIComponentID.RIFT_TIME);
    }

    int stringWidth = 0;

    @Override
    public void draw(DataExtractor.PlayerStats ps, DataExtractor.ScoreBoardData sd,DataExtractor.OtherData od, ScaledResolution sr,boolean editingMode) {
        super.draw(ps,sd,od,sr,editingMode);
        GlStateManager.pushMatrix();
        if (ApecMain.Instance.settingsManager.getSettingState(SettingID.RIFT_TIME)) {
            DataExtractor de = new DataExtractor();
            boolean inRift = false;
            for(String line : de.getSidebarLines()){
                if(ApecUtils.removeAllCodes(line).toLowerCase().contains("rift dimensio")){ // why
                    inRift = true;
                }
            }

            if(inRift){
                GlStateManager.scale(scale, scale, scale);
                Vector2f StatBar = ApecUtils.scalarMultiply(getCurrentAnchorPoint(),oneOverScale);
                String timeString = ApecUtils.formatTime(String.valueOf(this.mc.thePlayer.experienceLevel));
                ApecUtils.drawThiccBorderString(timeString, (int) (StatBar.x - mc.fontRendererObj.getStringWidth(timeString)), (int) (StatBar.y - 10), 0xd10808);
                stringWidth = mc.fontRendererObj.getStringWidth(timeString);
            }
        }
        GlStateManager.popMatrix();
    }

    @Override
    public Vector2f getAnchorPointPosition() {
        return guiModifier.applyGlobalChanges(this,new Vector2f(g_sr.getScaledWidth() - 190 + 112 + 70, 15));
    }

    @Override
    public Vector2f getBoundingPoint() {
        return new Vector2f(-stringWidth*scale,-11*scale);
    }

}
