package totalhamman.dumpitemdata.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
    public static SimpleNetworkWrapper INSTANCE;
    private static int packetId = 0;

    public static int nextID() {
        return packetId++;
    }

    public static void registerMessages(String channelName) {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);

        // Server side
        INSTANCE.registerMessage(PacketDumpItem.class, PacketDumpItem.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(PacketDumpBlck.class, PacketDumpBlck.class, nextID(), Side.SERVER);

        // Client side
    }
}