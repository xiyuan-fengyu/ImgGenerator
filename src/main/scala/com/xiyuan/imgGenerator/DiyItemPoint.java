package com.xiyuan.imgGenerator;

import java.awt.*;

/**
 * Created by xiyuan_fengyu on 2016/8/9.
 */
public class DiyItemPoint extends DiyItem {

    public final int size;

    public final int centerX;

    public final int centerY;

    public final Color color;

    public DiyItemPoint(int size, int centerX, int centerY) {
        this(size, centerX, centerY, null);
    }

    public DiyItemPoint(int size, int centerX, int centerY, Color color) {
        this.size = size;
        this.centerX = centerX;
        this.centerY = centerY;
        if (color != null) {
            this.color = color;
        }
        else {
            this.color = Color.decode(RandomUtil.randomColorStr());
        }
    }
}
