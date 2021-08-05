package com.mk.jw.network;


import com.mk.jw.Utils.Utils;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.jline.utils.Log;

public class PacketGui implements IMessage {

    int fuel;

    public PacketGui(){}

    public PacketGui(int fuel) {
        this.fuel = fuel;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.fuel = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.fuel);
    }
    public static class handler implements IMessageHandler<PacketGui,IMessage> {
        @Override
        public IMessage onMessage(PacketGui m, MessageContext ctx) {
            Utils.fuelstate=m.fuel;
            Log.info("Packet machine reçu");
            return null;
        }
    }
}
