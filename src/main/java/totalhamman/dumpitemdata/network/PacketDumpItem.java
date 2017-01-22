package totalhamman.dumpitemdata.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import totalhamman.dumpitemdata.CmdDumpItemData;

import static totalhamman.dumpitemdata.utils.LogHelper.logHelper;

public class PacketDumpItem implements IMessage, IMessageHandler<PacketDumpItem, IMessage> {

    @Override
    public void fromBytes(ByteBuf buf) { }

    @Override
    public void toBytes(ByteBuf buf) { }

    public PacketDumpItem() { }

    @Override
    public IMessage onMessage(PacketDumpItem message, MessageContext context) {
        logHelper("onMessage dump item data");
        CmdDumpItemData.dumpItem(context.getServerHandler().playerEntity);
        return null;
    }
}