package com.xiyuan.imgGenerator;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by xiyuan_fengyu on 2016/8/9.
 */
public class DiyInfo {

    public final ArrayList<DiyItem> items;

    public final int width;

    public final int height;

    public final Color background;

    public DiyInfo(int width, int height, Color background) {
        items = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.background = background;
    }

}
