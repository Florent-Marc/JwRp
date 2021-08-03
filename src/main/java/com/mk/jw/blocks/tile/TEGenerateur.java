package com.mk.jw.blocks.tile;

import com.mk.jw.blocks.TileEntitySyncClient;
import com.mk.jw.juralink.ElectricityLink;
import fr.dynamx.common.contentpack.type.objects.BlockObject;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;


public class TEGenerateur extends TileEntitySyncClient implements ITickable {

    private int fuel;
    private boolean burned;
    private int timeleft=0;
    private int kwh=0;
    private BlockObject b;
    private boolean upt = false;



    //time en tick
    public TEGenerateur(){
        super(null);
    }
    public TEGenerateur(BlockObject<?> blockObjectInfo) {
        super(blockObjectInfo);
        this.b = blockObjectInfo;
    }

    public void setKwh(int kwh) {
        this.kwh = kwh;
        sync();
        markDirty();
    }

    public int getCapacitor() {
        return 50;
    }

    public int getKwh() {
        return kwh;
    }

    public boolean hasFuel(){
        return getFuel() > 0;
    }
    public int getTimeburned() {
        return (40*30)*5;
    }
    public int getTimeleft() {
        return timeleft;
    }

    public void setTimeleft(int timeleft) {
        this.timeleft = timeleft;
        sync();
        markDirty();
    }

    public void burn(boolean burned) {
        this.burned = burned;
    }

    public boolean isburned() {
        return burned;
    }

    public void removeFuel() {
        setFuel(getFuel()-1);
        sync();
        markDirty();
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        fuel = tagCompound.getInteger("fuel");
        timeleft = tagCompound.getInteger("timeleft");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setInteger("fuel",fuel);
        tagCompound.setInteger("timeleft",timeleft);
        return tagCompound;
    }

    public void addfuel(int fuel) {
        this.fuel = this.fuel+fuel;
        sync();
        markDirty();
    }

    public int getFuel() {
        return this.fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
        sync();
        markDirty();
    }

    @Override
    public void update() {
        if(!hasFuel()){
            burn(false);
        }
        if(hasFuel()) {
            if (getTimeleft() == 0) {
                removeFuel();
                setTimeleft(getTimeburned());
            } else {
                setTimeleft(getTimeleft() - 1);
                burn(true);
            }
        }
        if(isburned()){
            setKwh(120);
            if(!this.upt){
                this.upt=true;
                ElectricityLink.addlist(this.pos);
                ElectricityLink.setProdkwh(ElectricityLink.getProdkwh()+getKwh());
                ElectricityLink.updatelist(this.pos,true);
            }
        }else{
            setKwh(0);
            if(this.upt){
                this.upt=false;
                ElectricityLink.setProdkwh(ElectricityLink.getProdkwh()-getKwh());
                ElectricityLink.updatelist(this.pos,false);
            }
        }
    }
}

