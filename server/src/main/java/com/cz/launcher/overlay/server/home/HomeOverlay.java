package com.cz.launcher.overlay.server.home;

import android.content.Context;
import android.os.Bundle;

import com.cz.launcher.overlay.library.component.OverlayComponent;
import com.cz.launcher.overlay.server.R;

/**
 * @author Created by cz
 * @date 11/20/20 2:09 PM
 * @email bingo110@126.com
 */
public class HomeOverlay extends OverlayComponent {

    public HomeOverlay(Context context, int theme) {
        super(context, theme);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.component_home);
    }
}