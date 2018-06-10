// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.graphics.Paint;
import android.view.Display;
import android.view.View;

// Referenced classes of package android.support.v4.view:
//            ViewCompat

static class  extends 
{

    public Display getDisplay(View view)
    {
        return view.getDisplay();
    }

    public int getLabelFor(View view)
    {
        return view.getLabelFor();
    }

    public int getLayoutDirection(View view)
    {
        return view.getLayoutDirection();
    }

    public int getPaddingEnd(View view)
    {
        return view.getPaddingEnd();
    }

    public int getPaddingStart(View view)
    {
        return view.getPaddingStart();
    }

    public int getWindowSystemUiVisibility(View view)
    {
        return view.getWindowSystemUiVisibility();
    }

    public boolean isPaddingRelative(View view)
    {
        return view.isPaddingRelative();
    }

    public void setLabelFor(View view, int i)
    {
        view.setLabelFor(i);
    }

    public void setLayerPaint(View view, Paint paint)
    {
        view.setLayerPaint(paint);
    }

    public void setLayoutDirection(View view, int i)
    {
        view.setLayoutDirection(i);
    }

    public void setPaddingRelative(View view, int i, int j, int k, int l)
    {
        view.setPaddingRelative(i, j, k, l);
    }

    ()
    {
    }
}
