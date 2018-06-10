// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.view.View;

// Referenced classes of package android.support.transition:
//            ViewUtilsApi18, ViewUtilsApi14, ViewUtilsImpl, ViewOverlayImpl, 
//            WindowIdImpl

class ViewUtils
{

    private static final ViewUtilsImpl IMPL;

    ViewUtils()
    {
    }

    static ViewOverlayImpl getOverlay(View view)
    {
        return IMPL.getOverlay(view);
    }

    static WindowIdImpl getWindowId(View view)
    {
        return IMPL.getWindowId(view);
    }

    static 
    {
        if (android.os.Build.VERSION.SDK_INT >= 18)
        {
            IMPL = new ViewUtilsApi18();
        } else
        {
            IMPL = new ViewUtilsApi14();
        }
    }
}
