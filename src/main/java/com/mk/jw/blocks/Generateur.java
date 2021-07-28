package com.mk.jw.blocks;

import com.jme3.math.Vector3f;
import com.mk.jw.blocks.tile.TEGenerateur;
import com.mk.jw.jw;
import fr.dynamx.common.blocks.DynamXBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jline.utils.Log;

import javax.annotation.Nullable;


public class Generateur extends DynamXBlock  {

    public Generateur(Material material, String modid, String blockName, String model) {
        super(material, modid, blockName, model);
        this.setCreativeTab(jw.juraTabs);
        this.blockObjectInfo.setTranslation(new Vector3f(0.0F, -1.5F, 0.0F));
    }


    public int getHarvestLevel(IBlockState state) {
        return 2;
    }


    @Nullable
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TEGenerateur(this.blockObjectInfo);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        boolean hasCanister = playerIn.getHeldItemMainhand().getItem() == jw.bidon;
        if (!worldIn.isRemote) {
            if (hasCanister) {
                TEGenerateur te = (TEGenerateur) worldIn.getTileEntity(pos);
                te.addfuel(te.getFuel() + 10);
                bidon.setfuel(playerIn.getHeldItemMainhand(), bidon.getfuel(playerIn.getHeldItemMainhand()) - 10);
            }
        }else{
            if(!hasCanister){
                Log.info("open frame");
                playerIn.openGui(jw.INSTANCE,10,worldIn, pos.getX(),pos.getY(),pos.getZ());
            }
        }

        return true;
    }
}
