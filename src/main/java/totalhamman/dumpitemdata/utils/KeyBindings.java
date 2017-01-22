package totalhamman.dumpitemdata.utils;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyBindings {

    public static KeyBinding itemKey;
    public static KeyBinding blckKey;

    public static void init() {
        itemKey = new KeyBinding("key.itemdump", Keyboard.KEY_C, "key.categories.dumpitemdata");
        blckKey = new KeyBinding("key.blckdump", Keyboard.KEY_V, "key.categories.dumpitemdata");
        ClientRegistry.registerKeyBinding(itemKey);
        ClientRegistry.registerKeyBinding(blckKey);
    }
}
