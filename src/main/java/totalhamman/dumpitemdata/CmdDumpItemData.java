package totalhamman.dumpitemdata;


import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.server.MinecraftServer;

import java.util.Iterator;

public class CmdDumpItemData extends CommandBase {
	
	private static final int MAX_RECURSION_DEPTH = 64;
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender cs) {
		return cs instanceof EntityPlayer && cs.canCommandSenderUseCommand(this.getRequiredPermissionLevel(), this.getCommandName());
	}

	@Override
	public String getCommandName() {
		return "dumpitemdata";
	}

	@Override
	public String getCommandUsage(ICommandSender cs) {
		return "/dumpitemdata";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender cs, String[] cmd) {
		if(cs instanceof EntityPlayer) {
			EntityPlayer plr = (EntityPlayer) cs;
			dumpItem(plr);
		}
	}

	public static void dumpItem(EntityPlayer plr) {
		ItemStack is = plr.inventory.getCurrentItem();
		if(is != null) {
			System.out.println("\n" +
					"ItemID: " + is.getUnlocalizedName() + " (" + Item.getIdFromItem(is.getItem()) + ")\n" +
					"Name: " + is.getItem().getUnlocalizedName() + " = " + is.getItem().getItemStackDisplayName(is) +
					(is.getTagCompound() != null && is.getTagCompound().hasKey("display", 10) ? ("\nDisplay Name: " + is.getDisplayName() + "\n") : "\n") +
					"Metadata: " + is.getItemDamage() + "\n" +
					"Stacksize: " + is.stackSize + "/" + is.getMaxStackSize() +
                    (is.getTagCompound() != null ? "\nNBT: {\n" + getNBTString(is.getTagCompound(), 1) + "}\n" : "\n")
			);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static String getNBTString(NBTTagCompound nbt, int ident) {
		if(nbt == null || ident > MAX_RECURSION_DEPTH) {
			return "";
		}
		
		String ident_s = getIdent(ident);
		
		StringBuilder sb = new StringBuilder();
		for(Iterator keys = nbt.getKeySet().iterator(); keys.hasNext();) {
			String key = (String) keys.next();
			sb.append(ident_s + key + ": ");
			
			NBTBase tag = nbt.getTag(key);
			byte id = tag.getId();
			switch(id) {
			case 0:
				sb.append("<end>");
				break;
			case 1:
				sb.append("byte:" + ((NBTTagByte)tag).getByte());
				break;
			case 2:
				sb.append("short:" + ((NBTTagShort)tag).getShort());
				break;
			case 3:
				sb.append("int:" + ((NBTTagInt)tag).getInt());
				break;
			case 4:
				sb.append("long:" + ((NBTTagLong)tag).getLong());
				break;
			case 5:
				sb.append("float:" + ((NBTTagFloat)tag).getFloat());
				break;
			case 6:
				sb.append("double:" + ((NBTTagDouble)tag).getDouble());
				break;
			case 7:
				sb.append("bytearray:" + byteArrayStr(((NBTTagByteArray)tag).getByteArray()));
				break;
			case 8:
				sb.append("string:\"" + ((NBTTagString)tag).getString() + "\"");
				break;
			case 9:
				sb.append("taglist-" + getTagTypeString(((NBTTagList)tag).getTagType()) + ":[\n" + getTagListString((NBTTagList) tag, ident + 1) + ident_s + "]");
				break;
			case 10:
				sb.append("compoundtag:{\n" + getNBTString((NBTTagCompound) tag, ident + 1) + ident_s + "}");
				break;
			case 11:
				sb.append("intarray:" + intArrayStr(((NBTTagIntArray)tag).getIntArray()));
				break;
			default:
				sb.append("<?>");
				break;
			}
			
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	@SuppressWarnings("rawtypes")
	public static String getTagListString(NBTTagList list, int ident) {
		if(list == null || ident > MAX_RECURSION_DEPTH) {
			return "";
		}
		
		String ident_s = getIdent(ident);
		
		int i = 0;
		StringBuilder sb = new StringBuilder();
		for(Iterator tags = list.tagList.iterator(); tags.hasNext();) {
			sb.append(ident_s + i + ": ");
			NBTBase tag = (NBTBase) tags.next();
			byte id = tag.getId();
			switch(id) {
				case 0:
					sb.append("<end>");
					break;
				case 1:
					sb.append("byte:" + ((NBTTagByte)tag).getByte());
					break;
				case 2:
					sb.append("short:" + ((NBTTagShort)tag).getShort());
					break;
				case 3:
					sb.append("int:" + ((NBTTagInt)tag).getInt());
					break;
				case 4:
					sb.append("long:" + ((NBTTagLong)tag).getLong());
					break;
				case 5:
					sb.append("float:" + ((NBTTagFloat)tag).getFloat());
					break;
				case 6:
					sb.append("double:" + ((NBTTagDouble)tag).getDouble());
					break;
				case 7:
					sb.append("bytearray:" + byteArrayStr(((NBTTagByteArray)tag).getByteArray()));
					break;
				case 8:
					sb.append("string:\"" + ((NBTTagString)tag).getString() + "\"");
					break;
				case 9:
					sb.append("taglist-" + getTagTypeString(((NBTTagList)tag).getTagType()) + ":[\n" + getTagListString((NBTTagList) tag, ident + 1) + ident_s + "]");
					break;
				case 10:
					sb.append("compoundtag:{\n" + getNBTString((NBTTagCompound) tag, ident + 1) + ident_s + "}");
					break;
				case 11:
					sb.append("intarray:" + intArrayStr(((NBTTagIntArray)tag).getIntArray()));
					break;
				default:
					sb.append("<?>");
					break;
			}
			
			i++;
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public static String getIdent(int ident) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < ident; i++) {
			sb.append('\t');
		}
		
		return sb.toString();
	}
	
	public static String getTagTypeString(int type) {
		switch(type) {
		case 0:
			return "<end>";
		case 1:
			return "byte";
		case 2:
			return "short";
		case 3:
			return "int";
		case 4:
			return "long";
		case 5:
			return "float";
		case 6:
			return "double";
		case 7:
			return "bytearray";
		case 8:
			return "string";
		case 9:
			return "taglist";
		case 10:
			return "compoundtag";
		case 11:
			return "intarray";
		}
		
		return "<?>";
	}
	
	public static String byteArrayStr(byte[] ba) {
		if(ba == null) {
			return "null";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0; i < ba.length; i++) {
			sb.append(ba[i]);
			if(i != ba.length - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	public static String intArrayStr(int[] ia) {
		if(ia == null) {
			return "null";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0; i < ia.length; i++) {
			sb.append(ia[i]);
			if(i != ia.length - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		
		return sb.toString();
	}

}
