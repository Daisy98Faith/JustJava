// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.NotificationManager;
import android.content.Context;

// Referenced classes of package android.support.v4.app:
//            NotificationManagerCompat, NotificationManagerCompatKitKat

static class A extends A
{

    public boolean areNotificationsEnabled(Context context, NotificationManager notificationmanager)
    {
        return NotificationManagerCompatKitKat.areNotificationsEnabled(context);
    }

    A()
    {
    }
}
