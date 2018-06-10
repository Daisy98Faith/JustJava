// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.os.IBinder;

// Referenced classes of package android.support.transition:
//            WindowIdImpl

class WindowIdApi14
    implements WindowIdImpl
{

    private final IBinder mToken;

    WindowIdApi14(IBinder ibinder)
    {
        mToken = ibinder;
    }

    public boolean equals(Object obj)
    {
        return (obj instanceof WindowIdApi14) && ((WindowIdApi14)obj).mToken.equals(mToken);
    }
}
