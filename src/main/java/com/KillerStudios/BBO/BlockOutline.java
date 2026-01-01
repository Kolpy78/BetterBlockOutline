package com.KillerStudios.BBO;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import org.lwjgl.opengl.GL11;

public class BlockOutline {
    @SubscribeEvent
    public void DrawBlockHighlightEvent(DrawBlockHighlightEvent event){
        event.setCanceled(true);
        if (event.target.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK){
            int x = event.target.blockX;
            int y = event.target.blockY;
            int z = event.target.blockZ;
            float partialTick = event.partialTicks;
            BlockOutlineRender(x, y, z, partialTick, event.player, event.player.worldObj);
        }
    }
    public void BlockOutlineRender(int x, int y, int z, float partialTick, EntityPlayer player, World world){
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glLineWidth(5.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(false);
        float f1 = 0.002F;
        Block block = world.getBlock(x, y, z);
        if (block != null) {
            if (block.getMaterial() != Material.air){
                block.setBlockBoundsBasedOnState(world, x, y, z);
                double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double) partialTick;
                double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double) partialTick;
                double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double) partialTick;
                RenderGlobal.drawOutlinedBoundingBox(block.getSelectedBoundingBoxFromPool(world, x, y, z).expand((double)f1,(double)f1,(double)f1).getOffsetBoundingBox(-d0, -d1, -d2), -1);
                GL11.glDepthMask(true);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glDisable(GL11.GL_BLEND);
            }else {
                FMLLog.severe("null block!");
            }
        }
        GL11.glPopMatrix();
    }
}
