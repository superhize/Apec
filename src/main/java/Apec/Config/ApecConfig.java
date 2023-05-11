package Apec.Config;

import Apec.ApecMain;
import Apec.ComponentId;
import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.KeyBind;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.core.OneKeyBind;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import org.lwjgl.input.Keyboard;

public class ApecConfig extends Config {

    public ApecConfig(){
        super(new Mod("Apec", ModType.HUD, "/assets/apec/gui/logos/apec.png"), "apecConfig.json");
        initialize();

        registerKeyBind(keyToggleApec, () -> {
            ApecMain.getComponent(ComponentId.GUI_MODIFIER).Toggle();
            apecEnabled = ApecMain.getComponent(ComponentId.GUI_MODIFIER).getEnableState();
        });

        addListener("apecEnabled", () -> ApecMain.getComponent(ComponentId.GUI_MODIFIER).Toggle());
    }

    @Switch(name = "Enable Apec", description = "Completly enable or disable Apec")
    public static boolean apecEnabled = true;

    @KeyBind(name = "Toggle Apec", description = "Key used to toggle apec so you don't need to open oneconfig gui eachtime")
    public static OneKeyBind keyToggleApec = new OneKeyBind(Keyboard.KEY_RCONTROL);

}
