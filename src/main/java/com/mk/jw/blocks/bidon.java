package com.mk.jw.blocks;


import com.mk.jw.jw;
import fr.dynamx.common.items.DynamXItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


import java.util.List;


public class bidon extends DynamXItem {

    public static final String STATE_FUEL = "FUEL";

    public bidon(String modid, String itemName, String model) {
        super(modid, itemName, model);
        this.setCreativeTab(jw.juraTabs);
        this.setMaxStackSize(1);

    }

    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Litre restant : "+getfuel(stack));
        super.addInformation(stack, world, tooltip, flagIn);
    }

    @SideOnly(Side.CLIENT)
    public static int getfuel(ItemStack stack){
        int fuel = 100;
        NBTTagCompound nbt;
        if(!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        nbt = stack.getTagCompound();

        fuel=nbt.getInteger(STATE_FUEL);

        return fuel;
    }


    @SideOnly(Side.CLIENT)
    public static void setfuel(ItemStack stack, int fuel){
        NBTTagCompound nbt;
        if(!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        nbt = stack.getTagCompound();

        nbt.setInteger(STATE_FUEL, fuel);
    }

}
