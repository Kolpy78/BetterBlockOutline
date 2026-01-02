package com.KillerStudios.BBO;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraftforge.common.config.Configuration;

import java.util.List;

import static com.KillerStudios.BBO.Config.*;

public class Commands extends CommandBase {
    ;
    @Override
    public String getCommandName() {
        return "BlockOutline";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/BlockOutline <Red|Green|Blue|Alpha>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            throw new WrongUsageException("/BlockOutline <color|line>");
        }
        if (args[0].equalsIgnoreCase("color")) {
            if (args.length == 5) {
                try {
                    checkRGBAT(args);
                   } catch (NumberFormatException e) {
                    throw new WrongUsageException("Please type a number from 0-255!");
                }
            } else {
                throw new WrongUsageException("Please type a number from 0-255 for each color!");
            }
        }
        if (args[0].equalsIgnoreCase("line")) {
            if (args.length == 2) {
                try {
                    checkRGBAT(args);
                   } catch (NumberFormatException e) {
                    throw new WrongUsageException("Please type a number from 0-255!");
                }
            } else {
                throw new WrongUsageException("/BlockOutline color <Red|Green|Blue|Alpha>");
            }
        }
        if (args[0].equalsIgnoreCase("line")) {
            if (args.length == 2) {
                try {
                    checkRGBAT(args);
                } catch (NumberFormatException e) {
                    throw new WrongUsageException("Please type a number from 0-15!");
                }
            } else {
                throw new WrongUsageException("/BlockOutline line 0-15");
            }
        }
    }
    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, "color", "line") : null;
    }
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
    public void save2config(String[] args){
        if (args[0].equalsIgnoreCase("color")) {
            configuration.get(Configuration.CATEGORY_GENERAL, "Red", Red).set(Integer.parseInt(args[1]));
            configuration.get(Configuration.CATEGORY_GENERAL, "Green", Green).set(Integer.parseInt(args[2]));
            configuration.get(Configuration.CATEGORY_GENERAL, "Blue", Blue).set(Integer.parseInt(args[3]));
            configuration.get(Configuration.CATEGORY_GENERAL, "Alpha", Alpha).set(Integer.parseInt(args[4]));
        }
        if (args[0].equalsIgnoreCase("line")) {
            configuration.get(Configuration.CATEGORY_GENERAL, "Thickness", Thick).set(Integer.parseInt(args[1]));
        }
        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
    public void checkRGBAT(String[] args){
        if (args[0].equalsIgnoreCase("color")) {
            Red = Integer.parseInt(args[1]);
            Green = Integer.parseInt(args[2]);
            Blue = Integer.parseInt(args[3]);
            Alpha = Integer.parseInt(args[4]);
            if (Red <= 255 && Blue <= 255 && Green <= 255 && Alpha <= 255) {
                float newRed = (float) Red / 255;
                float newBlue = (float) Blue / 255;
                float newGreen = (float) Green / 255;
                float newAlpha = (float) Alpha / 255;
                save2config(args);
                BlockOutline.setRGBA(newRed, newBlue, newGreen, newAlpha);
            } else {
                throw new WrongUsageException("Please type a number from 0-255!");
            }
        }
        if (args[0].equalsIgnoreCase("line")) {
            Thick = Integer.parseInt(args[1]);
            if (Thick <= 15) {
                BlockOutline.setLineThicc((float) Thick);
                save2config(args);
            }
        }
    }
}
