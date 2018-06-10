// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;

// Referenced classes of package android.support.v4.app:
//            NotificationCompat

static final class n
    implements n.Factory
{

    public n build(int i, CharSequence charsequence, PendingIntent pendingintent, Bundle bundle, Input ainput[], Input ainput1[], boolean flag)
    {
        return new nit>(i, charsequence, pendingintent, bundle, (RemoteInput[])(RemoteInput[])ainput, (RemoteInput[])(RemoteInput[])ainput1, flag);
    }

    public nit>[] newArray(int i)
    {
        return new nit>[i];
    }

    public volatile n[] newArray(int i)
    {
        return newArray(i);
    }

    n()
    {
    }
}
