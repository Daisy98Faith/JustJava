// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.net;

import android.net.TrafficStats;
import android.os.ParcelFileDescriptor;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

// Referenced classes of package android.support.v4.net:
//            DatagramSocketWrapper

public final class TrafficStatsCompat
{
    static class TrafficStatsCompatApi24Impl extends TrafficStatsCompatBaseImpl
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

        TrafficStatsCompatApi24Impl()
        {
        }
    }

    static class TrafficStatsCompatBaseImpl
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

        TrafficStatsCompatBaseImpl()
        {
        }
    }


    private static final TrafficStatsCompatBaseImpl IMPL;

    private TrafficStatsCompat()
    {
    }

    public static void clearThreadStatsTag()
    {
        TrafficStats.clearThreadStatsTag();
    }

    public static int getThreadStatsTag()
    {
        return TrafficStats.getThreadStatsTag();
    }

    public static void incrementOperationCount(int i)
    {
        TrafficStats.incrementOperationCount(i);
    }

    public static void incrementOperationCount(int i, int j)
    {
        TrafficStats.incrementOperationCount(i, j);
    }

    public static void setThreadStatsTag(int i)
    {
        TrafficStats.setThreadStatsTag(i);
    }

    public static void tagDatagramSocket(DatagramSocket datagramsocket)
        throws SocketException
    {
        IMPL.tagDatagramSocket(datagramsocket);
    }

    public static void tagSocket(Socket socket)
        throws SocketException
    {
        TrafficStats.tagSocket(socket);
    }

    public static void untagDatagramSocket(DatagramSocket datagramsocket)
        throws SocketException
    {
        IMPL.untagDatagramSocket(datagramsocket);
    }

    public static void untagSocket(Socket socket)
        throws SocketException
    {
        TrafficStats.untagSocket(socket);
    }

    static 
    {
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            IMPL = new TrafficStatsCompatApi24Impl();
        } else
        {
            IMPL = new TrafficStatsCompatBaseImpl();
        }
    }
}
