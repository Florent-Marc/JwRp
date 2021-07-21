package com.mk.jw.network;

import com.mk.jw.Utils.Profils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.nio.charset.StandardCharsets;

public class PacketUpdateClient implements IMessage {

    String prenom,nom,sex,age;
    int player,rib,cb;
    double argent;

    public PacketUpdateClient(){}

    public PacketUpdateClient(EntityPlayer player, String nom, String prenom, String sex, String age,Double Argent) {
        this.player =player.getEntityId();

        this.prenom=nom;
        this.nom=prenom;
        this.sex=sex;
        this.age=age;

        this.rib=rib;
        this.cb=cb;

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
    public static class handler implements IMessageHandler<PacketUpdateClient,IMessage> {
        @Override
        public IMessage onMessage(PacketUpdateClient m, MessageContext ctx) {
            Profils.load(m.prenom,m.nom,m.sex,m.age,m.rib,m.cb,m.argent);
            return null;
        }
    }
}
