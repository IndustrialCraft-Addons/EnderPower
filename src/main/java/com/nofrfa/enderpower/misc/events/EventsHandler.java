package com.nofrfa.enderpower.misc.events;

import com.nofrfa.enderpower.misc.registr.ItemsRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;
import java.util.SplittableRandom;

public class EventsHandler {
    @SubscribeEvent
    public void tntDetonated(ExplosionEvent.Detonate event) {
        World world = event.getWorld();
        if(!world.isRemote) {
            for(Object entity : event.getAffectedEntities()) {
                if(entity instanceof EntityItem) {
                    assert false;
                    if(((EntityItem) entity).getItem().isItemEqual(new ItemStack(ItemsRegistry.DUST_spadiy))) {
                        EntityItem entityToSpawn =
                                new EntityItem(
                                        world,
                                        ((EntityItem) entity).getPosition().getX(),
                                        ((EntityItem) entity).getPosition().getY(),
                                        ((EntityItem) entity).getPosition().getZ(),
                                        new ItemStack(ItemsRegistry.INGOT_spadiy, getRandomNumber(((EntityItem) entity).getItem().getCount()))
                                );
                        entityToSpawn.setGlowing(true);
                        world.spawnEntity(entityToSpawn);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onTarget(LivingSetAttackTargetEvent event) {
        if(event.getEntityLiving() instanceof EntityEnderman){
            EntityLiving entity = (EntityLiving) event.getEntityLiving();
            if(event.getTarget() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.getTarget();
                NonNullList<ItemStack> invPlayer = player.inventory.mainInventory;

                for(ItemStack item2 : invPlayer) {
                    assert false;
                    if(item2.isItemEqual(new ItemStack(ItemsRegistry.ITEM_erbi_amulet)))
                        entity.setAttackTarget(null);
                }
            }
        }
    }

    @SubscribeEvent
    public void shulkerdetecter(LivingAttackEvent event) {
       Entity entity = event.getEntity();
       if(entity instanceof EntityPlayer) {
           if(event.getSource().getTrueSource() instanceof EntityShulker) {
               NonNullList<ItemStack> invPlayer = ((EntityPlayer) entity).inventory.mainInventory;
               for (ItemStack item : invPlayer) {
                   assert false;
                   if (item.isItemEqualIgnoreDurability(new ItemStack(ItemsRegistry.ITEM_erbi_amulet))) {
                       event.setCanceled(true);
                       item.damageItem(1, event.getEntityLiving());
                       for (ItemStack item2 : invPlayer) {
                           if (item2.isItemEqual(new ItemStack(ItemsRegistry.ITEM_deterrent))) {
                               item2.setCount(0);
                               NBTTagCompound nbttt = new NBTTagCompound();
                               nbttt.setString("inside", "shulker_projectile");
                               ItemStack itemStack = new ItemStack(ItemsRegistry.ITEM_deterrent_filled);
                               itemStack.setTagCompound(nbttt);
                               ((EntityPlayer) entity).inventory.addItemStackToInventory(itemStack);
                               item.damageItem(1, event.getEntityLiving());
                               break;
                           }
                       }
                       break;
                   }
               }
           }
       }
    }

    @SubscribeEvent
    public void playerInteract(PlayerInteractEvent.EntityInteractSpecific event) {
        if(!event.getWorld().isRemote) {
            Entity entity = event.getTarget();
            ItemStack itemIs = event.getItemStack();
            if (entity instanceof EntityShulkerBullet) {
                assert false;
                if (itemIs.isItemEqual(new ItemStack(ItemsRegistry.ITEM_deterrent)) && event.getHand() == EnumHand.MAIN_HAND) {
                    EntityPlayer player = event.getEntityPlayer();
                    if (player.dimension == 1) {
                        NBTTagCompound inside = new NBTTagCompound();
                        inside.setString("inside", "shulker_projectile");

                        entity.setDead();
                        itemIs.setCount(itemIs.getCount() - 1);

                        ItemStack finalItem = new ItemStack(ItemsRegistry.ITEM_deterrent_filled);
                        finalItem.setTagCompound(inside);

                        player.inventory.addItemStackToInventory(finalItem);
                    } else
                        player.sendMessage(new TextComponentString(String.format("%s%s", I18n.format("chatinfo.tag"), I18n.format("event.fail"))));
                }
            }
        }
    }

    @SubscribeEvent
    public void entityDie(LivingDeathEvent event) {
        if(event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();

            assert false;
            if(!player.getHeldItem(player.getActiveHand()).isItemEqualIgnoreDurability(new ItemStack(ItemsRegistry.TOOL_spadiy_sword))) return;

            SplittableRandom random = new SplittableRandom();
            if(random.nextInt(1, 101) <= 40) return;

            EntityLivingBase entity = event.getEntityLiving();
            BlockPos pos;
            ItemStack stack;
            
            if(entity instanceof EntitySkeleton) {
                pos = getPosOf(entity);
                stack = new ItemStack(Items.SKULL, 1, 0);
            }
            else if(entity instanceof EntityZombie) {
                pos = getPosOf(entity);
                stack = new ItemStack(Items.SKULL, 1, 2);
            }
            else if(entity instanceof EntityCreeper) {
                pos = getPosOf(entity);
                stack = new ItemStack(Items.SKULL, 4, 2);
            }
            else if(entity instanceof EntityWitherSkeleton) {
                pos = getPosOf(entity);
                stack = new ItemStack(Items.SKULL, 1, 1);
            }
            else return;

            World world = event.getEntity().getEntityWorld();
            EntityItem entityToSpawn = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            world.spawnEntity(entityToSpawn);
        }
    }

    private BlockPos getPosOf(Entity entity1) {
        return entity1.getPosition();
    }

    private int getRandomNumber(int max_1) {
        return max_1 >= 3 ? new Random().nextInt(max_1) : max_1;
    }
}