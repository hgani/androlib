package com.gani.lib.ui.layout;

import android.content.Context;
import android.widget.LinearLayout;

public class HorizontalLayout extends AbstractLinearLayout {
    public HorizontalLayout(Context context){
        super(context);
        this.setOrientation(LinearLayout.HORIZONTAL);
    }
}
