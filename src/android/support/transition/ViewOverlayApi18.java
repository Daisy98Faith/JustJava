// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

// Referenced classes of package android.support.transition:
//            ViewOverlayImpl

class ViewOverlayApi18
    implements ViewOverlayImpl
{

    private final ViewOverlay mViewOverlay;

    ViewOverlayApi18(View view)
    {
        mViewOverlay = view.getOverlay();
    }

    public void add(Drawable drawable)
    {
        mViewOverlay.add(drawable);
    }

    public void clear()
    {
        mViewOverlay.clear();
    }

    public void remove(Drawable drawable)
    {
        mViewOverlay.remove(drawable);
    }
}
