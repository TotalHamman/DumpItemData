package totalhamman.dumpitemdata;

import net.minecraft.command.CommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;


@Mod(modid = "dumpitemdata", name = "DumpItemData", version = "1.0")
public class DumpItemData {

	@EventHandler
	public void init(FMLInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(new BlockClickHandler());
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
