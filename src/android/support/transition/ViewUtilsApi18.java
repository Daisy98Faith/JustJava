// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.view.View;

// Referenced classes of package android.support.transition:
//            ViewUtilsApi14, ViewOverlayApi18, WindowIdApi18, ViewOverlayImpl, 
//            WindowIdImpl

class ViewUtilsApi18 extends ViewUtilsApi14
{

    ViewUtilsApi18()
    {
    }

    public ViewOverlayImpl getOverlay(View view)
    {
        return new ViewOverlayApi18(view);
    }

    public WindowIdImpl getWindowId(View view)
    {
        return new WindowIdApi18(view);
    }
}
