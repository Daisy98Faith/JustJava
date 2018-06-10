// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.NotificationManager;

class NotificationManagerCompatApi24
{

    NotificationManagerCompatApi24()
    {
    }

    public static boolean areNotificationsEnabled(NotificationManager notificationmanager)
    {
        return notificationmanager.areNotificationsEnabled();
    }

    public static int getImportance(NotificationManager notificationmanager)
    {
        return notificationmanager.getImportance();
    }
}
