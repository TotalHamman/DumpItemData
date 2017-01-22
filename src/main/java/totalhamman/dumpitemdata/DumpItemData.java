package totalhamman.dumpitemdata;

import net.minecraft.command.CommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import totalhamman.dumpitemdata.handlers.BlockClickHandler;
import totalhamman.dumpitemdata.handlers.KeyBindingsHandler;
import totalhamman.dumpitemdata.network.PacketHandler;
import totalhamman.dumpitemdata.utils.KeyBindings;


@Mod(modid = "dumpitemdata", name = "DumpItemData", version = "1.0")
public class DumpItemData {

	public static final Logger log = LogManager.getLogger("dumpitemdata");
	public static final boolean debugOn = false;

	@EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        if (debugOn) DumpItemData.log.info("Register Packet Handler");
        PacketHandler.registerMessages("dumpitemdata");
    }

	@EventHandler
	public void init(FMLInitializationEvent e) {
        if (debugOn) DumpItemData.log.info("Register / Init Block Click Handler");
		MinecraftForge.EVENT_BUS.register(new BlockClickHandler());

        if (debugOn) DumpItemData.log.info("Register / Init Key Bindings Handler");
        MinecraftForge.EVENT_BUS.register(new KeyBindingsHandler());
        KeyBindings.init();
	}

	@EventHandler
	public void serverAboutToStart(FMLServerAboutToStartEvent e) {
		CommandHandler ch = (CommandHandler)e.getServer().getCommandManager();
		
		//if("true".equalsIgnoreCase(System.getProperty("enableDumpItemDataCommand", "false"))) {
		ch.registerCommand(new CmdDumpItemData());
		ch.registerCommand(new CmdDumpBlockData());
		//}
		
	}
}
