// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content.res;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

// Referenced classes of package android.support.v4.content.res:
//            ConfigurationHelperJellybeanMr1

public final class ConfigurationHelper
{
    private static class ConfigurationHelperApi17Impl extends ConfigurationHelperBaseImpl
    {

        public int getDensityDpi(Resources resources)
        {
            return ConfigurationHelperJellybeanMr1.getDensityDpi(resources);
        }

        private ConfigurationHelperApi17Impl()
        {
        }

    }

    private static class ConfigurationHelperBaseImpl
    {

        public int getDensityDpi(Resources resources)
        {
            return resources.getDisplayMetrics().densityDpi;
        }

        private ConfigurationHelperBaseImpl()
        {
        }

    }


    private static final ConfigurationHelperBaseImpl IMPL;

    private ConfigurationHelper()
    {
    }

    public static int getDensityDpi(Resources resources)
    {
        return IMPL.getDensityDpi(resources);
    }

    public static int getScreenHeightDp(Resources resources)
    {
        return resources.getConfiguration().screenHeightDp;
    }

    public static int getScreenWidthDp(Resources resources)
    {
        return resources.getConfiguration().screenWidthDp;
    }

    public static int getSmallestScreenWidthDp(Resources resources)
    {
        return resources.getConfiguration().smallestScreenWidthDp;
    }

    static 
    {
        if (android.os.Build.VERSION.SDK_INT >= 17)
        {
            IMPL = new ConfigurationHelperApi17Impl();
        } else
        {
            IMPL = new ConfigurationHelperBaseImpl();
        }
    }
}
