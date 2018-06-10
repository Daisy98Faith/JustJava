// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.AlarmManager;
import android.app.PendingIntent;

class AlarmManagerCompatKitKat
{

    AlarmManagerCompatKitKat()
    {
    }

    static void setExact(AlarmManager alarmmanager, int i, long l, PendingIntent pendingintent)
    {
        alarmmanager.setExact(i, l, pendingintent);
    }
}
