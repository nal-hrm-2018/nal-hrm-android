package com.dal.hrm_management.models;

import android.graphics.drawable.Drawable;

public class MenuModel {
    public String menuName;
    public Drawable drawable;
    public boolean hasChildren, isGroup;

    public MenuModel(String menuName, boolean isGroup, boolean hasChildren,Drawable drawable) {

        this.menuName = menuName;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
        this.drawable = drawable;

    }
}
