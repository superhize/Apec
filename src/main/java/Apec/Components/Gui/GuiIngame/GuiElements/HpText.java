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

public class HpText extends TextComponent {

    public HpText () {
        super(GUIComponentID.HP_TEXT);
    }

    int stringWidth = 0;

    @Override
    public void draw(DataExtractor.PlayerStats ps, DataExtractor.ScoreBoardData sd,DataExtractor.OtherData od, ScaledResolution sr,boolean editingMode) {
        super.draw(ps,sd,od,sr,editingMode);
        GlStateManager.pushMatrix();
        if (ApecMain.Instance.settingsManager.getSettingState(SettingID.HP_TEXT)) {
            GlStateManager.scale(scale, scale, scale);

            boolean showAP = ApecMain.Instance.settingsManager.getSettingState(SettingID.SHOW_ABSORPTION_BAR);

            Vector2f StatBar = ApecUtils.scalarMultiply(getCurrentAnchorPoint(),oneOverScale);

            int addedHp = ps.Hp + ps.Ap;
            String HPString = (!showAP && ps.Ap != 0 ? "\u00a7e" + addedHp + "\u00a7r" : ps.Hp) + "/" + ps.BaseHp + " HP" + (ps.HealDuration != 0 ? " +" + ps.HealDuration +"/s " + ps.HealDurationTicker : "");
            ApecUtils.drawThiccBorderString(HPString, (int) (StatBar.x - mc.fontRendererObj.getStringWidth(HPString)), (int) (StatBar.y - 10), 0xd10808);
            stringWidth = mc.fontRendererObj.getStringWidth(HPString);


            if (ps.Ap != 0 && showAP) {
                String APString = ps.Ap + "/" + ps.BaseAp + " AP";
                ApecUtils.drawThiccBorderString(APString, (int) (StatBar.x - 5 - mc.fontRendererObj.getStringWidth(APString) - mc.fontRendererObj.getStringWidth(HPString)), (int) (StatBar.y - 10), 0x1966AD);
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
