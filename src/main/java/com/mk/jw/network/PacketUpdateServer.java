package com.mk.jw.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.nio.charset.StandardCharsets;

public class PacketUpdateServer implements IMessage {
    private String prenom,nom,sex,age;
    private int player,rib,cb;
    private double argent;

    public PacketUpdateServer(){}

    public PacketUpdateServer(EntityPlayer player,String nom, String prenom, String sex, String age,Double Argent) {
        this.player =player.getEntityId();


        this.prenom=prenom;
        this.nom=nom;
        this.sex=sex;
        this.age=age;
        this.argent=Argent;

    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.player = buf.readInt();

        this.nom = ByteBufUtils.readUTF8String(buf);
        this.prenom = ByteBufUtils.readUTF8String(buf);
        this.sex = ByteBufUtils.readUTF8String(buf);
        this.age = ByteBufUtils.readUTF8String(buf);

        this.rib = buf.readInt();
        this.cb = buf.readInt();

        this.argent = buf.readDouble();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.player);

        buf.writeCharSequence(this.nom, StandardCharsets.UTF_8);
        buf.writeCharSequence(this.prenom, StandardCharsets.UTF_8);
        buf.writeCharSequence(this.sex, StandardCharsets.UTF_8);
        buf.writeCharSequence(this.age, StandardCharsets.UTF_8);

        buf.writeInt(this.rib);
        buf.writeInt(this.cb);

        buf.writeDouble(this.argent);
    }

    public static class handler implements IMessageHandler<PacketUpdateServer,IMessage> {
        @Override
        public IMessage onMessage(PacketUpdateServer message, MessageContext ctx) {
            final Entity e = ctx.getServerHandler().player.world.getEntityByID(message.player);
            EntityPlayerMP p = (EntityPlayerMP) e;
            //MethodeBDD.setProfil(p, message.Snom, message.Sprenom, message.Ssex, message.Sage, message.Cnom, message.Cprenom, message.Csex, message.Cage, message.money);
            return null;
        }
    }
}
