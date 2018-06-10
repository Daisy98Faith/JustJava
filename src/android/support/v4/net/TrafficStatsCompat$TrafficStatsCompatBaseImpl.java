// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.net;

import android.net.TrafficStats;
import android.os.ParcelFileDescriptor;
import java.net.DatagramSocket;
import java.net.SocketException;

// Referenced classes of package android.support.v4.net:
//            TrafficStatsCompat, DatagramSocketWrapper

static class 
{

    public void tagDatagramSocket(DatagramSocket datagramsocket)
        throws SocketException
    {
        ParcelFileDescriptor parcelfiledescriptor = ParcelFileDescriptor.fromDatagramSocket(datagramsocket);
        TrafficStats.tagSocket(new DatagramSocketWrapper(datagramsocket, parcelfiledescriptor.getFileDescriptor()));
        parcelfiledescriptor.detachFd();
    }

    public void untagDatagramSocket(DatagramSocket datagramsocket)
        throws SocketException
    {
        ParcelFileDescriptor parcelfiledescriptor = ParcelFileDescriptor.fromDatagramSocket(datagramsocket);
        TrafficStats.untagSocket(new DatagramSocketWrapper(datagramsocket, parcelfiledescriptor.getFileDescriptor()));
        parcelfiledescriptor.detachFd();
    }

    ()
    {
    }
}
