package totalhamman.dumpitemdata.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import totalhamman.dumpitemdata.CmdDumpBlockData;

import static totalhamman.dumpitemdata.utils.LogHelper.logHelper;

public class PacketDumpBlck implements IMessage, IMessageHandler<PacketDumpBlck, IMessage> {

    @Override
    public void fromBytes(ByteBuf buf) { }

    @Override
    public void toBytes(ByteBuf buf) { }

    public PacketDumpBlck() { }

    @Override
    public IMessage onMessage(PacketDumpBlck message, MessageContext context) {
        logHelper("onMessage dump block data ready, waiting on right-click");
        CmdDumpBlockData.putWaiting(context.getServerHandler().playerEntity);
        return null;
    }
}