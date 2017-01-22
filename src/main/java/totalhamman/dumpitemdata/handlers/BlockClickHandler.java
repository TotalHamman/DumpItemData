package totalhamman.dumpitemdata.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import totalhamman.dumpitemdata.CmdDumpBlockData;
import totalhamman.dumpitemdata.CmdDumpItemData;

public class BlockClickHandler {
	
	@SubscribeEvent
	public void onBlockRightClick(PlayerInteractEvent e) {
		if(!e.getEntityPlayer().getEntityWorld().isRemote) {
			if(CmdDumpBlockData.getWaitForBlockClick(e.getEntityPlayer().getUniqueID())) {
			    StringBuilder sb = new StringBuilder();
				Block blc = e.getWorld().getBlockState(e.getPos()).getBlock();
				if(blc == null) {
					return;
				}

                int meta = e.getWorld().getBlockState(e.getPos()).getBlock().getMetaFromState(e.getWorld().getBlockState(e.getPos()));
                ItemStack s = new ItemStack(blc, 1, meta);

                sb.append("BlockID: " + blc.getRegistryName().toString() + "(" + Item.getIdFromItem(s.getItem()) + ")\n");
                sb.append("Block Name: " + blc.getUnlocalizedName() + " = " + blc.getLocalizedName() + "\n");
                sb.append("BlockState: " + e.getWorld().getBlockState(e.getPos()) + "\n");
                sb.append("Metadata: " + meta + "\n");
                sb.append("XYZ: " + e.getPos().toString() + "\n");
                sb.append("Lightlevel: " + "block: " + e.getWorld().getLight(e.getPos()) + ", sky: " + e.getWorld().getLightFor(EnumSkyBlock.SKY, e.getPos()) + "\n");
                if(blc.hasTileEntity(e.getWorld().getBlockState(e.getPos()))) {
                    TileEntity te = e.getWorld().getTileEntity(e.getPos());
                    if(te != null) {
                        NBTTagCompound nbt = new NBTTagCompound();
                        te.writeToNBT(nbt);
                        sb.append("NBT: {" + CmdDumpItemData.getNBTString(nbt, 1) + "}");
                    }
                }
                sb.append("\n");
				
				System.out.println("\n" + sb.toString());
				
				e.setCanceled(true);
			}
		}
	}
}
