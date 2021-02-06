package com.yanisbft.geofindr.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class IconUtil {
    public static final Icon SEARCH = loadIcon("search");
    public static final Icon QUIZ = loadIcon("quiz");
    public static final Icon SETTINGS = loadIcon("settings");

    public static Icon loadIcon(String name) {
        try {
            InputStream inputStream = IconUtil.class.getResourceAsStream("/img/icons/" + name + ".png");
            Image image = ImageIO.read(inputStream).getScaledInstance(12, 12, Image.SCALE_DEFAULT);
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ImageIcon loadFlag(String name) {
        try {
            InputStream inputStream = IconUtil.class.getResourceAsStream("/img/flags/" + name + ".png");
            Image image = ImageIO.read(inputStream);
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
