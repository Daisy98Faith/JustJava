// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Service;

class ServiceCompatApi24
{

    ServiceCompatApi24()
    {
    }

    public static void stopForeground(Service service, int i)
    {
        service.stopForeground(i);
    }
}
