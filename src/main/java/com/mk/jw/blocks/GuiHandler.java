package com.mk.jw.blocks;

import com.mk.jw.blocks.gui.GuiGenerateur;
import com.mk.jw.blocks.tile.TEGenerateur;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        if(ID == 10) {
            if (te instanceof TEGenerateur) {
                return new GuiGenerateur((TEGenerateur) te);
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        if(ID == 10) {
            if (te instanceof TEGenerateur) {
                return new GuiGenerateur((TEGenerateur) te);
            }
        }
        return null;
    }
}
