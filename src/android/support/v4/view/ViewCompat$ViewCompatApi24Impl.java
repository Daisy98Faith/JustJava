// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.content.ClipData;
import android.view.PointerIcon;
import android.view.View;

// Referenced classes of package android.support.v4.view:
//            ViewCompat, PointerIconCompat

static class  extends 
{

    public void cancelDragAndDrop(View view)
    {
        view.cancelDragAndDrop();
    }

    public void dispatchFinishTemporaryDetach(View view)
    {
        view.dispatchFinishTemporaryDetach();
    }

    public void dispatchStartTemporaryDetach(View view)
    {
        view.dispatchStartTemporaryDetach();
    }

    public void setPointerIcon(View view, PointerIconCompat pointericoncompat)
    {
        if (pointericoncompat != null)
        {
            pointericoncompat = ((PointerIconCompat) (pointericoncompat.getPointerIcon()));
        } else
        {
            pointericoncompat = null;
        }
        view.setPointerIcon((PointerIcon)(PointerIcon)pointericoncompat);
    }

    public boolean startDragAndDrop(View view, ClipData clipdata, android.view.tApi24Impl tapi24impl, Object obj, int i)
    {
        return view.startDragAndDrop(clipdata, tapi24impl, obj, i);
    }

    public void updateDragShadow(View view, android.view.tApi24Impl tapi24impl)
    {
        view.updateDragShadow(tapi24impl);
    }

    ()
    {
    }
}
