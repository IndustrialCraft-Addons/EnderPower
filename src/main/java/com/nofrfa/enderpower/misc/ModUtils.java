package com.nofrfa.enderpower.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.BlockStructure;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.world.BlockEvent;

public class ModUtils {

    public static final Item.ToolMaterial ToolMaterial_univ =
            EnumHelper.addToolMaterial(
                    "enderpower:tool", //name
                    4, //HarvestLevel
                    5712, //MaxUses
                    40.0F, //Efficiency
                    6.0F, //Damage
                    30 //Enchantability
            );
    public static final Item.ToolMaterial ToolMaterial_sword =
            EnumHelper.addToolMaterial(
                    "enderpower:sword", //name
                    4, //HarvestLevel
                    2856, //MaxUses
                    1.0F, //Efficiency
                    13.0F, //Damage
                    30 //Enchantability
            );

    public static String getStringFromNumber(double number) {
        String returnString = "0";
        if (number <= 1000) {
            returnString = String.format("%.0f", number);
        } else if (number >= 10E2D && number < 10E5D) {
            returnString = String.format("%.2fK", number /10E2D);

        } else if (number >= 10E5D && number < 10E8D) {
            returnString = String.format("%.2fM", number /10E5D);

        } else if (number >= 10E8D && number < 10E11D) {
            returnString = String.format("%.2fG", number /10E8D);

        } else if (number >= 10E11D && number < 10E14D) {
            returnString = String.format("%.2fT", number /10E11D);

        } else if (number >= 10E14D && number < 10E17D) {
            returnString = String.format("%.2fP", number /10E14D);

        } else if (number >= 10E17D && number < 10E20D) {
            returnString = String.format("%.2fE", number /10E17D);

        } else if (number >= 10E20D && number < 10E23D) {
            returnString = String.format("%.2fZ", number /10E20D);

        } else if (number >= 10E23D && number < 10E26D) {
            returnString = String.format("%.2fY", number /10E23D);

        }
        return returnString;
    }

    public static boolean getEqualsItems(ItemStack root, Item...items) {
        boolean ret = false;
        for(Item item1 : items) {
            if(root.isItemEqualIgnoreDurability(is(item1)))
                ret = true;
        }

        return ret;
    }

    public static boolean hasMode(ItemStack stack1, String nbtKey) {
        assert false;
        return stack1.hasTagCompound() && stack1.getTagCompound().hasKey(nbtKey) && stack1.getTagCompound().getInteger(nbtKey) != 0;
    }

    public static void changeMode(EntityPlayer player, EnumHand hand, String nbtKey, int maxModes) {
        ItemStack item = player.getHeldItem(hand);
        assert false;
        if(item.hasTagCompound()) {
            if (item.getTagCompound().hasKey(nbtKey)) {
                int nowSize = item.getTagCompound().getInteger(nbtKey);
                item.getTagCompound().setInteger(nbtKey, (nowSize + 1) % maxModes);
            }
        } else {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger(nbtKey, 1);

            item.setTagCompound(nbt);
        }
        new ActionResult<>(EnumActionResult.SUCCESS, item);
    }

    public static void blockDestroyer(ItemStack stack, World world, BlockPos pos, EntityLivingBase entityLiving, String nameHarvestTool, int plusDigSize, String nbtKey) {
        if(!(entityLiving instanceof EntityPlayer) || world.isRemote)
            return;

        assert false;
        if(!stack.hasTagCompound() && !stack.getTagCompound().hasKey(nbtKey))
            return;

        if(stack.getTagCompound().getInteger(nbtKey) == 0)
            return;

        EntityPlayer player = (EntityPlayer) entityLiving;
        EnumFacing facing = entityLiving.getHorizontalFacing();

        if(entityLiving.rotationPitch < -45.0F)     facing = EnumFacing.UP;
        else if(entityLiving.rotationPitch > 45.0F) facing = EnumFacing.DOWN;

        boolean yAxis = facing.getAxis() == EnumFacing.Axis.Y;
        boolean xAxis = facing.getAxis() == EnumFacing.Axis.X;

        for(int index = -plusDigSize; index <= plusDigSize; ++index) {
            for (int index1 = -plusDigSize; index1 <= plusDigSize; ++index1) {
                if (index == 0 && index1 == 0) continue;

                BlockPos pos1;
                if(yAxis)       pos1 = pos.add(index, 0, index1);
                else if(xAxis)  pos1 = pos.add(0, index, index1);
                else            pos1 = pos.add(index, index1, 0);

                IBlockState state1 = world.getBlockState(pos1);
                if(state1.getBlockHardness(world, pos1) > 0.0F) {
                    BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(world, pos1, state1, player);
                    MinecraftForge.EVENT_BUS.post(event);
                    if(!event.isCanceled()) {
                        Block block = state1.getBlock();
                        if(!block.getHarvestTool(state1).equalsIgnoreCase(nameHarvestTool))
                            continue;

                        if((block instanceof BlockCommandBlock || block instanceof BlockStructure) && !player.canUseCommandBlock()) {
                            world.notifyBlockUpdate(pos1, state1, state1, 3);
                            continue;
                        }

                        if(stack.getTagCompound().getInteger(nbtKey) != 2) {
                            goHarvestDestroy(stack, block, world, player, pos1, state1);
                        } else {
                            boolean hasTile = block.hasTileEntity(state1);
                            if(hasTile) {
                                player.sendMessage(new TextComponentString(String.format("%s%s [x:%s | y:%s | z:%s]", I18n.format("chatinfo.tag"), "TileEntity Detected", pos1.getX(), pos1.getY(), pos1.getZ())));
                            } else {
                                goHarvestDestroy(stack, block, world, player, pos1, state1);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void goHarvestDestroy(ItemStack stack1, Block block1, World world1, EntityPlayer player1, BlockPos pos2, IBlockState state2) {
        boolean harvest = block1.canHarvestBlock(world1, pos2, player1);
        boolean destroy = block1.removedByPlayer(state2, world1, pos2, player1, harvest);
        if(harvest && destroy) {
            block1.harvestBlock(world1, player1, pos2, state2, null, stack1);
            block1.onBlockDestroyedByPlayer(world1, pos2, state2);
            stack1.damageItem(1, player1);
        }
    }

    public static ItemStack is(Item item0) {
        return new ItemStack(item0);
    }
}
