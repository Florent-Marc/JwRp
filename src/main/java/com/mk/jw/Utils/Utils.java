package com.mk.jw.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.compress.utils.IOUtils;
import org.jline.utils.Log;
import org.lwjgl.opengl.Display;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;

public class Utils {
    public static final String MODID = "jw";
    public static final String MODNAME = "jw";
    public static final String VERSION = "1.0";
    public static final String ChannelLog = "861605005378846750";
    public static final String ChannelModeration = "1.0";


    public static void seticon(){
        InputStream inputStream = null;
        InputStream inputStream1 = null;

        try {

            inputStream = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(MODID, "textures/icon_16x16.png")).getInputStream();
            inputStream1 = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(MODID, "textures/icon_32x32.png")).getInputStream();

            Display.setIcon(new ByteBuffer[] {readImageToBuffer(inputStream), readImageToBuffer(inputStream1)});
        } catch (IOException exception) {
            Log.error("Couldn't set icon", exception);

        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(inputStream1);
        }

    }
    public static ByteBuffer readImageToBuffer(InputStream imageStream) throws IOException
    {
        BufferedImage bufferedimage = ImageIO.read(imageStream);
        int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), null, 0, bufferedimage.getWidth());
        ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);

        for (int i : aint)
        {
            bytebuffer.putInt(i << 8 | i >> 24 & 255);
        }

        bytebuffer.flip();
        return bytebuffer;
    }

    public static HashMap<String, String> Rglist = new HashMap<String, String>();
    public HashMap<String,String> getRglist() {
        //Mineraux
        Rglist.put("Diamant", "1:0:0:10:0:0");
        Rglist.put("Or", "11:0:0:20:0:0");
        return Rglist;
    }

}
