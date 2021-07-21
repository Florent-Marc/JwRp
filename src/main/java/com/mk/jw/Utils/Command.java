package com.mk.jw.Utils;

import com.mk.jw.jw;
import com.mk.jw.listener.Network;
import com.mk.jw.network.PacketSetSpeed;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nullable;
import java.util.List;


public class Command extends CommandBase {

    @Override
    public String getName() {
        return "jw";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/jw <subcommand> [<args>]";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayer target = getPlayer(sender.getName());
        if(args.length==3){
            if(args[0].matches("setwalkspeed")) {
                Network.network.sendToServer(new PacketSetSpeed(Integer.parseInt(args[2]),target.getEntityId(),"walk"));
                target.sendMessage(new TextComponentString("[Server]Ton walkspeed à été set à "+args[2]+" !"));
            }
            if(args[0].matches("setflyspeed")) {
                Network.network.sendTo(new PacketSetSpeed(Float.parseFloat(args[2]),target.getEntityId(),"fly"), (EntityPlayerMP) target);
                target.sendMessage(new TextComponentString("[Server]Ton flyspeed a ete set a "+args[2]+" !"));
            }
            if(args[0].matches("region")) {
                if(args[1].matches("setsecondpoint")){
                    Rg.setSecondpoint(target);
                    target.sendMessage(new TextComponentString("[Server]Second point de la zone set !"));
                }
                if(args[1].matches("setfirstpoint")){
                    Rg.setfirstpoint(target);
                    target.sendMessage(new TextComponentString("[Server]Premier point de la zone set !"));
                }
            }
        }
        if(args.length==4){
            if(args[0].matches("region")) {
                if(args[1].matches("setregion")){
                    Rg.addregion(args[2]);
                }
                if(args[1].matches("removeregion")){
                    Rg.removeregion(args[2]);
                }
            }
        }
    }
    public EntityPlayerMP getPlayer(String name) {
        return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(name);
    }
    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1){
            return getListOfStringsMatchingLastWord(args, "region","resetPlayer","info");
        }
        if(args[0].matches("setwalkspeed")||args[0].matches("setflyspeed")||args[0].matches("resetplayer")){
            return getListOfStringsMatchingLastWord(args, FMLCommonHandler.instance().getMinecraftServerInstance().getOnlinePlayerNames());
        }
        if(args[0].matches("region")){
            return getListOfStringsMatchingLastWord(args, "setsecondpoint","setfirstpoint","setregion","removeregion","info");
        }
        return null;

    }
}
