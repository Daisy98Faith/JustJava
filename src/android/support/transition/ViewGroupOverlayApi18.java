// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

// Referenced classes of package android.support.transition:
//            ViewGroupOverlayImpl

class ViewGroupOverlayApi18
    implements ViewGroupOverlayImpl
{

    private final ViewGroupOverlay mViewGroupOverlay;

    ViewGroupOverlayApi18(ViewGroup viewgroup)
    {
        mViewGroupOverlay = viewgroup.getOverlay();
    }

    public void add(Drawable drawable)
    {
        mViewGroupOverlay.add(drawable);
    }

    public void add(View view)
    {
        mViewGroupOverlay.add(view);
    }

    public void clear()
    {
        mViewGroupOverlay.clear();
    }

    public void remove(Drawable drawable)
    {
        mViewGroupOverlay.remove(drawable);
    }

    public void remove(View view)
    {
        mViewGroupOverlay.remove(view);
    }
}
