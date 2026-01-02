package com.KillerStudios.BBO;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static String greeting = "Hello World";
    public static int Red = 1;
    public static int Green = 1;
    public static int Blue = 1;
    public static int Alpha = 1;
    public static int Thick = 1;
    public static Configuration configuration;
    public static void synchronizeConfiguration(File configFile) {
        configuration = new Configuration(configFile);
        greeting = configuration.getString("greeting", Configuration.CATEGORY_GENERAL, greeting, "How shall I greet?");
        Red = configuration.getInt("Red", Configuration.CATEGORY_GENERAL, Red, 0, 255, "Value for red");
        Green = configuration.getInt("Green", Configuration.CATEGORY_GENERAL, Green, 0, 255, "Value for green");
        Blue = configuration.getInt("Blue", Configuration.CATEGORY_GENERAL, Blue, 0, 255, "Value for blue");
        Alpha = configuration.getInt("Alpha", Configuration.CATEGORY_GENERAL, Alpha, 0, 255, "Value for alpha (transparency)");
        Thick = configuration.getInt("Thickness", Configuration.CATEGORY_GENERAL, Thick, 0, 15, "Value for the outline thickness");
        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
