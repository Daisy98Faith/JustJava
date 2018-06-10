// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.net;

import android.net.TrafficStats;
import java.net.DatagramSocket;
import java.net.SocketException;

// Referenced classes of package android.support.v4.net:
//            TrafficStatsCompat

static class  extends 
{

    public void tagDatagramSocket(DatagramSocket datagramsocket)
        throws SocketException
    {
        TrafficStats.tagDatagramSocket(datagramsocket);
    }

    public void untagDatagramSocket(DatagramSocket datagramsocket)
        throws SocketException
    {
        TrafficStats.untagDatagramSocket(datagramsocket);
    }

    ()
    {
    }
}
