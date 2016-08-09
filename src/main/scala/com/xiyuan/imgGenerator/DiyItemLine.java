package com.xiyuan.imgGenerator;

import java.awt.*;

/**
 * Created by xiyuan_fengyu on 2016/8/9.
 */
public class DiyItemLine extends DiyItem {

    public final int startX;

    public final int startY;

    public final int endX;

    public final int endY;

    public final Color color;

    public DiyItemLine(int startX, int startY, int endX, int endY, Color color) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        if (color != null) {
            this.color = color;
        }
        else {
            this.color = Color.decode(RandomUtil.randomColorStr());
        }
    }
}
