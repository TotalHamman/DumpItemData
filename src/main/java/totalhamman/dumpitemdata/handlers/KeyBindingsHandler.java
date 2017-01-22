package totalhamman.dumpitemdata.handlers;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import totalhamman.dumpitemdata.network.PacketDumpBlck;
import totalhamman.dumpitemdata.network.PacketDumpItem;
import totalhamman.dumpitemdata.network.PacketHandler;
import totalhamman.dumpitemdata.utils.KeyBindings;

import static totalhamman.dumpitemdata.utils.LogHelper.logHelper;

public class KeyBindingsHandler {
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBindings.itemKey.isPressed()) {
            logHelper("Item Key Pressed");
            PacketHandler.INSTANCE.sendToServer(new PacketDumpItem());
        }
        if (KeyBindings.blckKey.isPressed()) {
            logHelper("Block Key Pressed");
            PacketHandler.INSTANCE.sendToServer(new PacketDumpBlck());
        }
    }
}
