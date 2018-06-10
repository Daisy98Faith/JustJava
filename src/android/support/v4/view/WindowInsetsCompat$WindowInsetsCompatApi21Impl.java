// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.graphics.Rect;

// Referenced classes of package android.support.v4.view:
//            WindowInsetsCompat, WindowInsetsCompatApi21

private static class  extends 
{

    public WindowInsetsCompat consumeStableInsets(Object obj)
    {
        return new WindowInsetsCompat(WindowInsetsCompatApi21.consumeStableInsets(obj));
    }

    public int getStableInsetBottom(Object obj)
    {
        return WindowInsetsCompatApi21.getStableInsetBottom(obj);
    }

    public int getStableInsetLeft(Object obj)
    {
        return WindowInsetsCompatApi21.getStableInsetLeft(obj);
    }

    public int getStableInsetRight(Object obj)
    {
        return WindowInsetsCompatApi21.getStableInsetRight(obj);
    }

    public int getStableInsetTop(Object obj)
    {
        return WindowInsetsCompatApi21.getStableInsetTop(obj);
    }

    public boolean hasStableInsets(Object obj)
    {
        return WindowInsetsCompatApi21.hasStableInsets(obj);
    }

    public boolean isConsumed(Object obj)
    {
        return WindowInsetsCompatApi21.isConsumed(obj);
    }

    public WindowInsetsCompat replaceSystemWindowInsets(Object obj, Rect rect)
    {
        return new WindowInsetsCompat(WindowInsetsCompatApi21.replaceSystemWindowInsets(obj, rect));
    }

    ()
    {
    }
}
