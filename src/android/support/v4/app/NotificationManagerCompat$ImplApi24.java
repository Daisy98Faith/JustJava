// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.NotificationManager;
import android.content.Context;

// Referenced classes of package android.support.v4.app:
//            NotificationManagerCompat, NotificationManagerCompatApi24

static class  extends 
{

    public boolean areNotificationsEnabled(Context context, NotificationManager notificationmanager)
    {
        return NotificationManagerCompatApi24.areNotificationsEnabled(notificationmanager);
    }

    public int getImportance(NotificationManager notificationmanager)
    {
        return NotificationManagerCompatApi24.getImportance(notificationmanager);
    }

    ()
    {
    }
}
