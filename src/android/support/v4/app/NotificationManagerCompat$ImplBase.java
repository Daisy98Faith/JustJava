// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

// Referenced classes of package android.support.v4.app:
//            NotificationManagerCompat

static class 
    implements 
{

    public boolean areNotificationsEnabled(Context context, NotificationManager notificationmanager)
    {
        return true;
    }

    public void cancelNotification(NotificationManager notificationmanager, String s, int i)
    {
        notificationmanager.cancel(s, i);
    }

    public int getImportance(NotificationManager notificationmanager)
    {
        return -1000;
    }

    public void postNotification(NotificationManager notificationmanager, String s, int i, Notification notification)
    {
        notificationmanager.notify(s, i, notification);
    }

    ()
    {
    }
}
