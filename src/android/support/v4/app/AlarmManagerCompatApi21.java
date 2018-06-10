// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.AlarmManager;
import android.app.PendingIntent;

class AlarmManagerCompatApi21
{

    AlarmManagerCompatApi21()
    {
    }

    static void setAlarmClock(AlarmManager alarmmanager, long l, PendingIntent pendingintent, PendingIntent pendingintent1)
    {
        alarmmanager.setAlarmClock(new android.app.AlarmManager.AlarmClockInfo(l, pendingintent), pendingintent1);
    }
}
