package com.mk.jw.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketSetSpeed implements IMessage{
    private String type;
    private float speed;
    private int target;

    public PacketSetSpeed(){
    }

    public PacketSetSpeed(float speed, int player,String type) {
        this.speed = speed;
        this.target = player;
        this.type = type;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.type= ByteBufUtils.readUTF8String(buf);
        this.speed = buf.readFloat();
        this.target = buf.readInt();

    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.type);
        buf.writeFloat(this.speed);
        buf.writeInt(this.target);
    }

    public static class handler implements IMessageHandler<PacketSetSpeed, IMessage> {
        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(PacketSetSpeed message, MessageContext ctx) {
            EntityPlayer p = Minecraft.getMinecraft().player;
            if(message.type.equalsIgnoreCase("fly")){
                if (p != null) {
                    p.capabilities.setFlySpeed(message.speed);

                }
            }
            if(message.type.equalsIgnoreCase("walk")){
                if (p != null) {
                    p.capabilities.setPlayerWalkSpeed(message.speed);
                }
            }
            return null;
        }
    }
}
