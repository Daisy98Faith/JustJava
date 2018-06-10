// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            ViewGroupUtilsApi18, ViewGroupUtilsApi14, ViewGroupUtilsImpl, ViewGroupOverlayImpl

class ViewGroupUtils
{

    private static final ViewGroupUtilsImpl IMPL;

    ViewGroupUtils()
    {
    }

    static ViewGroupOverlayImpl getOverlay(ViewGroup viewgroup)
    {
        return IMPL.getOverlay(viewgroup);
    }

    static void suppressLayout(ViewGroup viewgroup, boolean flag)
    {
        IMPL.suppressLayout(viewgroup, flag);
    }

    static 
    {
        if (android.os.Build.VERSION.SDK_INT >= 18)
        {
            IMPL = new ViewGroupUtilsApi18();
        } else
        {
            IMPL = new ViewGroupUtilsApi14();
        }
    }
}
