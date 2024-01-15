package com.lapka.nonogramportal;

import java.util.Arrays;
import java.util.List;

public class Colors {
    public static List<String> colors = Arrays.asList("#000000", "#FF0000", "#00FF00", "#0000FF", "#FFFF00", "#FF00FF", "#FFA500", "#008080",
            "#FFD1DC", "#FFB6C1", "#FF69B4", "#FF6347", "#FF4500", "#FFD700", "#F0E68C", "#EEE8AA",
            "#98FB98", "#87CEEB", "#FFA07A", "#FF8C00", "#FF4500", "#FFD700", "#FF69B4", "#FF6347",
            "#87CEEB", "#00BFFF", "#32CD32", "#ADFF2F", "#FFDAB9", "#FFE4C4", "#FFDEAD", "#F0FFF0",
            "#E6E6FA", "#D8BFD8", "#DDA0DD", "#BA55D3", "#9370DB", "#8A2BE2"
    );
    public static int getId(String hex) {
        for (int i = 0; i < colors.size(); i++) {
            if (colors.get(i).equals(hex)) return i;
        }
        return -1;
    }
}
