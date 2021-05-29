package com.nofrfa.enderpower.misc.registr;

import com.nofrfa.enderpower.misc.Configs;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EnderPowerCommand extends CommandBase {
    public static final String
            NAME = "enderpower_give",
            USAGE = "/enderpower_give | ep_give <detter_shulker>",
            USAGE_ROOT = "/enderpower_give | ep_give <detter_shulker | detter_enderfish>",
            ALIAS = "ep_give";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return Configs.GeneralSettings.root_access ? USAGE_ROOT : USAGE;
    }

    @Override
    public List<String> getAliases() {
        List<String> aliases = new ArrayList<>();

        aliases.add(ALIAS);

        return aliases;
    }

    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if(args.length == 1) {
            if(Configs.GeneralSettings.root_access)
                return getListOfStringsMatchingLastWord(args, "detter_shulker", "detter_enderfish");
            else return getListOfStringsMatchingLastWord(args, "detter_shulker");
        }
        else return null;
    }

    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender instanceof EntityPlayer && server.getPlayerList().canSendCommands(((EntityPlayer) sender).getGameProfile());
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (sender instanceof EntityPlayer) {
            if (args.length != 1) {
                throw new WrongUsageException(this.getUsage(sender));
            }

            EntityPlayer player = getCommandSenderAsPlayer(sender);
            ItemStack mainHandItem = player.getHeldItemMainhand();

            if(args[0].equals("detter_shulker")) {
                assert false;
                if(mainHandItem.isItemEqual(new ItemStack(ItemsRegistry.ITEM_deterrent_filled)) || mainHandItem.isItemEqual(new ItemStack(ItemsRegistry.ITEM_deterrent))) {
                    if(!mainHandItem.hasTagCompound()) {
                        ItemStack finalItem = new ItemStack(ItemsRegistry.ITEM_deterrent_filled);

                        NBTTagCompound inside = new NBTTagCompound();
                        inside.setString("inside", "shulker_projectile");
                        finalItem.setTagCompound(inside);

                        mainHandItem.setCount(-1);

                        player.inventory.addItemStackToInventory(finalItem);
                        player.sendMessage(new TextComponentString(I18n.format("chatinfo.tag") + I18n.format("command.enderpower_give.success")));
                    } else {
                        player.sendMessage(new TextComponentString(I18n.format("chatinfo.tag") + I18n.format("command.enderpower_give.nbt.error")));
                    }
                } else {
                    player.sendMessage(new TextComponentString(I18n.format("chatinfo.tag") + I18n.format("command.enderpower_give.detter.error")));
                }
            }

            if(Configs.GeneralSettings.root_access) {
                if (args[0].equals("detter_enderfish")) {
                    if(mainHandItem.getUnlocalizedName().equals("item.deterrent_filled")) {
                        if(!mainHandItem.hasTagCompound()) {
                            assert false;
                            ItemStack finalItem = new ItemStack(ItemsRegistry.ITEM_deterrent_filled);

                            NBTTagCompound inside = new NBTTagCompound();
                            inside.setString("inside", "enderfish");
                            finalItem.setTagCompound(inside);

                            mainHandItem.setCount(-1);

                            player.inventory.addItemStackToInventory(finalItem);
                            player.sendMessage(new TextComponentString(String.format("%sEnderFish was artificially added to the §dDeterrent", I18n.format("chatinfo.tag"))));
                        } else {
                            player.sendMessage(new TextComponentString(String.format("%s%s", I18n.format("chatinfo.tag"), I18n.format("command.enderpower_give.nbt.error"))));
                        }
                    } else {
                        player.sendMessage(new TextComponentString(I18n.format("chatinfo.tag") + I18n.format("command.enderpower_give.detter.error")));
                    }
                }
            }
        }
    }
}
