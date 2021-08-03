package com.mk.jw.items;


import com.mk.jw.jw;
import fr.dynamx.common.items.DynamXItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;


import java.util.List;


public class ItemBidon extends DynamXItem {

    public static final String STATE_FUEL = "FUEL";

    public ItemBidon(String modid, String itemName, String model) {
        super(modid, itemName, model);
        this.setCreativeTab(jw.juraTabs);
        this.setMaxStackSize(1);

    }

    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Litre restant : "+getfuel(stack));
        super.addInformation(stack, world, tooltip, flagIn);
    }

    public static int getfuel(ItemStack stack){
        int fuel = 100;
        NBTTagCompound nbt;
        if(!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        nbt = stack.getTagCompound();

        fuel=nbt.getInteger(STATE_FUEL);

        return 100;
    }



    public static void setfuel(ItemStack stack, int fuel){
        NBTTagCompound nbt;
        if(!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        nbt = stack.getTagCompound();

        nbt.setInteger(STATE_FUEL, fuel);

    }

}
