// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;


// Referenced classes of package android.support.v4.view:
//            WindowInsetsCompat, WindowInsetsCompatApi20

private static class  extends 
{

    public WindowInsetsCompat consumeSystemWindowInsets(Object obj)
    {
        return new WindowInsetsCompat(WindowInsetsCompatApi20.consumeSystemWindowInsets(obj));
    }

    public Object getSourceWindowInsets(Object obj)
    {
        return WindowInsetsCompatApi20.getSourceWindowInsets(obj);
    }

    public int getSystemWindowInsetBottom(Object obj)
    {
        return WindowInsetsCompatApi20.getSystemWindowInsetBottom(obj);
    }

    public int getSystemWindowInsetLeft(Object obj)
    {
        return WindowInsetsCompatApi20.getSystemWindowInsetLeft(obj);
    }

    public int getSystemWindowInsetRight(Object obj)
    {
        return WindowInsetsCompatApi20.getSystemWindowInsetRight(obj);
    }

    public int getSystemWindowInsetTop(Object obj)
    {
        return WindowInsetsCompatApi20.getSystemWindowInsetTop(obj);
    }

    public boolean hasInsets(Object obj)
    {
        return WindowInsetsCompatApi20.hasInsets(obj);
    }

    public boolean hasSystemWindowInsets(Object obj)
    {
        return WindowInsetsCompatApi20.hasSystemWindowInsets(obj);
    }

    public boolean isRound(Object obj)
    {
        return WindowInsetsCompatApi20.isRound(obj);
    }

    public WindowInsetsCompat replaceSystemWindowInsets(Object obj, int i, int j, int k, int l)
    {
        return new WindowInsetsCompat(WindowInsetsCompatApi20.replaceSystemWindowInsets(obj, i, j, k, l));
    }

    ()
    {
    }
}
