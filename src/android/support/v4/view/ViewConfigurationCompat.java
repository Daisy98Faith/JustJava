// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.view.ViewConfiguration;

// Referenced classes of package android.support.v4.view:
//            ViewConfigurationCompatICS

public final class ViewConfigurationCompat
{
    static class BaseViewConfigurationVersionImpl
        implements ViewConfigurationVersionImpl
    {

        public boolean hasPermanentMenuKey(ViewConfiguration viewconfiguration)
        {
            return true;
        }

        BaseViewConfigurationVersionImpl()
        {
        }
    }

    static class HoneycombViewConfigurationVersionImpl extends BaseViewConfigurationVersionImpl
    {

        public boolean hasPermanentMenuKey(ViewConfiguration viewconfiguration)
        {
            return false;
        }

        HoneycombViewConfigurationVersionImpl()
        {
        }
    }

    static class IcsViewConfigurationVersionImpl extends HoneycombViewConfigurationVersionImpl
    {

        public boolean hasPermanentMenuKey(ViewConfiguration viewconfiguration)
        {
            return ViewConfigurationCompatICS.hasPermanentMenuKey(viewconfiguration);
        }

        IcsViewConfigurationVersionImpl()
        {
        }
    }

    static interface ViewConfigurationVersionImpl
    {

        public abstract boolean hasPermanentMenuKey(ViewConfiguration viewconfiguration);
    }


    static final ViewConfigurationVersionImpl IMPL;

    private ViewConfigurationCompat()
    {
    }

    public static int getScaledPagingTouchSlop(ViewConfiguration viewconfiguration)
    {
        return viewconfiguration.getScaledPagingTouchSlop();
    }

    public static boolean hasPermanentMenuKey(ViewConfiguration viewconfiguration)
    {
        return IMPL.hasPermanentMenuKey(viewconfiguration);
    }

    static 
    {
        if (android.os.Build.VERSION.SDK_INT >= 14)
        {
            IMPL = new IcsViewConfigurationVersionImpl();
        } else
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            IMPL = new HoneycombViewConfigurationVersionImpl();
        } else
        {
            IMPL = new BaseViewConfigurationVersionImpl();
        }
    }
}
