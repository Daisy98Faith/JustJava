// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;


// Referenced classes of package android.support.design.widget:
//            Snackbar

public static class mBar.BaseCallback extends mBar.BaseCallback
{

    public static final int DISMISS_EVENT_ACTION = 1;
    public static final int DISMISS_EVENT_CONSECUTIVE = 4;
    public static final int DISMISS_EVENT_MANUAL = 3;
    public static final int DISMISS_EVENT_SWIPE = 0;
    public static final int DISMISS_EVENT_TIMEOUT = 2;

    public void onDismissed(Snackbar snackbar, int i)
    {
    }

    public volatile void onDismissed(Object obj, int i)
    {
        onDismissed((Snackbar)obj, i);
    }

    public void onShown(Snackbar snackbar)
    {
    }

    public volatile void onShown(Object obj)
    {
        onShown((Snackbar)obj);
    }

    public mBar.BaseCallback()
    {
    }
}
