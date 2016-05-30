package com.example.rhg.outsourcing.utils;

import android.util.Log;

import com.bigkoo.convenientbanner.ConvenientBanner;

/**
 * Created by remember on 2016/5/4.
 */
public class BannerController {
    ConvenientBanner convenientBanner;

    public void setConvenientBanner(ConvenientBanner convenientBanner) {
        this.convenientBanner = convenientBanner;
    }

    /*private static BannerController ourInstance = new BannerController();

    public static BannerController getInstance() {
        return ourInstance;
    }*/

    public BannerController() {
    }

    public void startBanner(long time) {
        if (convenientBanner != null && !convenientBanner.isTurning()) {
            convenientBanner.startTurning(time);
        }
    }

    public void stopBanner() {
        if (convenientBanner != null && convenientBanner.isTurning()) {
            convenientBanner.stopTurning();
        }
    }

}
