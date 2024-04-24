package com.monopolio.utils;

import javafx.scene.text.Font;

import java.io.InputStream;

public class FontUtils {
    private InputStream fontStream = getClass().getResourceAsStream("/fonts/LuckiestGuy-Regular.ttf");
    private Font font;

    public FontUtils(int size) {
        font = Font.loadFont(fontStream, size);
    }

    public void setSize(int size) {
        font = Font.loadFont(fontStream, size);
    }

    public Font getFont() {
        return font;
    }
}
