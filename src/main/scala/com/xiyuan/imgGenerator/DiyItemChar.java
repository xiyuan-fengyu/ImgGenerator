package com.xiyuan.imgGenerator;

import java.awt.*;

/**
 * Created by xiyuan_fengyu on 2016/8/9.
 */
public class DiyItemChar extends DiyItem {

    public final char c;

    public final int size;

    public final int centerX;

    public final int centerY;

    public final int rotation;

    public final Color color;

    public DiyItemChar(char c, int size, int centerX, int centerY, int rotation, Color color) {
        this(c, size, centerX, centerY, rotation, color, false);
    }

    public DiyItemChar(char c, int size, int centerX, int centerY, int rotation, Color color, boolean random) {
        if (!random) {
            this.c = c;
            this.size = size;
            this.centerX = centerX;
            this.centerY = centerY;
            this.rotation = rotation;
        }
        else {
            this.c = c;
            this.size = size + RandomUtil.randomBetween(-4, 4);
            this.centerX = centerX + RandomUtil.randomBetween(-4, 4);
            this.centerY = centerY + RandomUtil.randomBetween(-4, 4);
            this.rotation = rotation + RandomUtil.randomBetween(-10, 10);
        }

        if (color != null) {
            this.color = color;
        }
        else {
            this.color = Color.decode(RandomUtil.randomColorStr());
        }
    }

}
