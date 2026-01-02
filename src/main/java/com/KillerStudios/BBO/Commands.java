package com.KillerStudios.BBO;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

public class Commands extends CommandBase {

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
                    int Red = Integer.parseInt(args[1]);
                    int Blue = Integer.parseInt(args[2]);
                    int Green = Integer.parseInt(args[3]);
                    int Alpha = Integer.parseInt(args[4]);
                    if (Red <= 255 && Blue <= 255 && Green <= 255 && Alpha <= 255) {
                        float newRed = (float) Red / 255;
                        float newBlue = (float) Blue / 255;
                        float newGreen = (float) Green / 255;
                        float newAlpha = (float) Alpha / 255;
                        BlockOutline.setRGBA(newRed, newBlue, newGreen, newAlpha);
                    } else {
                        throw new WrongUsageException("Please type a number from 0-255!");
                    }
                } catch (NumberFormatException e) {
                    throw new WrongUsageException("Please type a number from 0-255!");
                }
            } else {
                throw new WrongUsageException("/BlockOutline color <Red|Green|Blue|Alpha> 46");
            }
        }
        if (args[0].equalsIgnoreCase("line")) {
            if (args.length == 2) {
                try {
                    int Thick = Integer.parseInt(args[1]);
                    if (Thick <= 15) {
                        BlockOutline.setLineThicc((float) Thick);
                    }
                } catch (NumberFormatException e) {
                    throw new WrongUsageException("Please type a number from 0-15!");
                }
            } else {
                throw new WrongUsageException("/BlockOutline line 0-255");
            }
        }
    }
}
