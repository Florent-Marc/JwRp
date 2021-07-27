package com.mk.jw.Utils;

import com.mk.jw.jw;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class JuraTabs extends CreativeTabs {

    public JuraTabs(String label) {
        super(label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(jw.bidon);
    }

    @Override
    public CreativeTabs setNoTitle() {
        return super.setNoTitle();
    }

    @Override
    public boolean hasSearchBar()
    {
        return true;
    }
}
