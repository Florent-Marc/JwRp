package com.mk.jw.blocks.block;

import com.jme3.math.Vector3f;
import com.mk.jw.items.ItemBidon;
import com.mk.jw.blocks.tile.TEGenerateur;
import com.mk.jw.juralink.ElectricityLink;
import com.mk.jw.jw;
import fr.dynamx.common.blocks.DynamXBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.jline.utils.Log;

import javax.annotation.Nullable;


public class BlockGenerateur extends DynamXBlock  {

    public BlockGenerateur(Material material, String modid, String blockName, String model) {
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
        ItemStack Canister = playerIn.getHeldItemMainhand();
        if (!worldIn.isRemote) {
            if (hasCanister) {
                TEGenerateur te = (TEGenerateur) worldIn.getTileEntity(pos);
                if(ItemBidon.getfuel(Canister)>=10) {
                    if ((te.getFuel() + 10) <= te.getCapacitor()) {
                        te.addfuel(10);
                        ItemBidon.setfuel(Canister, ItemBidon.getfuel(Canister) - 10);
                    }else{
                        playerIn.sendMessage(new TextComponentString("Geneteur full"));
                    }
                }else{
                    playerIn.sendMessage(new TextComponentString("Bidon pas assez rempli"));
                }
            }
        }else{
            if(!hasCanister){
                Log.info("open frame");
                playerIn.openGui(jw.INSTANCE,10,worldIn, pos.getX(),pos.getY(),pos.getZ());
            }
        }

        return true;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        ElectricityLink.addlist(pos);
    }

    @Override
    public void onPlayerDestroy(World worldIn, BlockPos pos, IBlockState state) {
        ElectricityLink.checklist(pos);
    }
}
