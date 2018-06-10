// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import java.lang.ref.WeakReference;

// Referenced classes of package android.support.design.widget:
//            SnackbarManager

private static class duration
{

    final WeakReference callback;
    int duration;
    boolean paused;

    boolean isSnackbar(duration duration1)
    {
        return duration1 != null && callback.get() == duration1;
    }

    (int i,  )
    {
        callback = new WeakReference();
        duration = i;
    }
}
