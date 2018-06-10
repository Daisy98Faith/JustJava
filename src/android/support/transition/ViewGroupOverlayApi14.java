// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            ViewOverlayApi14, ViewGroupOverlayImpl

class ViewGroupOverlayApi14 extends ViewOverlayApi14
    implements ViewGroupOverlayImpl
{

    ViewGroupOverlayApi14(Context context, ViewGroup viewgroup, View view)
    {
        super(context, viewgroup, view);
    }

    static ViewGroupOverlayApi14 createFrom(ViewGroup viewgroup)
    {
        return (ViewGroupOverlayApi14)ViewOverlayApi14.createFrom(viewgroup);
    }

    public void add(View view)
    {
        mOverlayViewGroup.add(view);
    }

    public void remove(View view)
    {
        mOverlayViewGroup.remove(view);
    }
}
