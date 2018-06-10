// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.AlarmManager;
import android.app.PendingIntent;

// Referenced classes of package android.support.v4.app:
//            AlarmManagerCompatApi21, AlarmManagerCompatApi23, AlarmManagerCompatKitKat

public final class AlarmManagerCompat
{

    private AlarmManagerCompat()
    {
    }

    public static void setAlarmClock(AlarmManager alarmmanager, long l, PendingIntent pendingintent, PendingIntent pendingintent1)
    {
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            AlarmManagerCompatApi21.setAlarmClock(alarmmanager, l, pendingintent, pendingintent1);
            return;
        } else
        {
            setExact(alarmmanager, 0, l, pendingintent1);
            return;
        }
    }

    public static void setAndAllowWhileIdle(AlarmManager alarmmanager, int i, long l, PendingIntent pendingintent)
    {
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            AlarmManagerCompatApi23.setAndAllowWhileIdle(alarmmanager, i, l, pendingintent);
            return;
        } else
        {
            alarmmanager.set(i, l, pendingintent);
            return;
        }
    }

    public static void setExact(AlarmManager alarmmanager, int i, long l, PendingIntent pendingintent)
    {
        if (android.os.Build.VERSION.SDK_INT >= 19)
        {
            AlarmManagerCompatKitKat.setExact(alarmmanager, i, l, pendingintent);
            return;
        } else
        {
            alarmmanager.set(i, l, pendingintent);
            return;
        }
    }

    public static void setExactAndAllowWhileIdle(AlarmManager alarmmanager, int i, long l, PendingIntent pendingintent)
    {
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            AlarmManagerCompatApi23.setExactAndAllowWhileIdle(alarmmanager, i, l, pendingintent);
            return;
        } else
        {
            setExact(alarmmanager, i, l, pendingintent);
            return;
        }
    }
}
