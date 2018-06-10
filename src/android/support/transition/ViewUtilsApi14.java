// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.view.View;

// Referenced classes of package android.support.transition:
//            ViewUtilsImpl, ViewOverlayApi14, WindowIdApi14, ViewOverlayImpl, 
//            WindowIdImpl

class ViewUtilsApi14
    implements ViewUtilsImpl
{

    ViewUtilsApi14()
    {
    }

    public ViewOverlayImpl getOverlay(View view)
    {
        return ViewOverlayApi14.createFrom(view);
    }

    public WindowIdImpl getWindowId(View view)
    {
        return new WindowIdApi14(view.getWindowToken());
    }
}
