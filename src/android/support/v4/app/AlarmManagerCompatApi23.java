// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.AlarmManager;
import android.app.PendingIntent;

class AlarmManagerCompatApi23
{

    AlarmManagerCompatApi23()
    {
    }

    static void setAndAllowWhileIdle(AlarmManager alarmmanager, int i, long l, PendingIntent pendingintent)
    {
        alarmmanager.setAndAllowWhileIdle(i, l, pendingintent);
    }

    static void setExactAndAllowWhileIdle(AlarmManager alarmmanager, int i, long l, PendingIntent pendingintent)
    {
        alarmmanager.setExactAndAllowWhileIdle(i, l, pendingintent);
    }
}
