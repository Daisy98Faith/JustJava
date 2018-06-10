// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Service;

// Referenced classes of package android.support.v4.app:
//            ServiceCompat

static class 
    implements 
{

    public void stopForeground(Service service, int i)
    {
        boolean flag;
        if ((i & 1) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        service.stopForeground(flag);
    }

    ()
    {
    }
}
