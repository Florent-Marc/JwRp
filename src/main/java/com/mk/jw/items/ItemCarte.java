package com.mk.jw.items;


import com.mk.jw.jw;
import fr.dynamx.common.items.DynamXItem;


public class ItemCarte extends DynamXItem {
    public ItemCarte(String modid, String itemName, String model) {
        super(modid, itemName, model);
        this.setCreativeTab(jw.juraTabs);
        this.setMaxStackSize(1);
    }
}
