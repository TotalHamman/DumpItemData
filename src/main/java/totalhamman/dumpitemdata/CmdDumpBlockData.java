package totalhamman.dumpitemdata;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CmdDumpBlockData extends CommandBase {
	
	private static Map<UUID, Boolean> waiting = new HashMap<UUID, Boolean>();
	
	public static boolean getWaitForBlockClick(UUID plr) {
		if(waiting.get(plr) != null) {
			waiting.remove(plr);
			return true;
		}
		
		return false;
	}

	public static void putWaiting(EntityPlayer player) {
        if(!waiting.containsKey(player.getUniqueID())) {
            waiting.put(player.getUniqueID(), true);
        }
    }
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender cs) {
		return cs instanceof EntityPlayer && cs.canCommandSenderUseCommand(this.getRequiredPermissionLevel(), this.getCommandName());
	}

	@Override
	public String getCommandName() {
		return "dumpblockdata";
	}

	@Override
	public String getCommandUsage(ICommandSender cs) {
		return "/dumpblockdata";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender cs, String[] cmd) {
		if(cs instanceof EntityPlayer) {
			if(!waiting.containsKey(((EntityPlayer) cs).getUniqueID())) {
				waiting.put(((EntityPlayer) cs).getUniqueID(), true);
			}
		}
	}
}
