// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.net;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;

// Referenced classes of package android.support.v4.net:
//            DatagramSocketWrapper

private static class fd extends SocketImpl
{

    protected void accept(SocketImpl socketimpl)
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    protected int available()
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    protected void bind(InetAddress inetaddress, int i)
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    protected void close()
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    protected void connect(String s, int i)
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    protected void connect(InetAddress inetaddress, int i)
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    protected void connect(SocketAddress socketaddress, int i)
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    protected void create(boolean flag)
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    protected InputStream getInputStream()
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    public Object getOption(int i)
        throws SocketException
    {
        throw new UnsupportedOperationException();
    }

    protected OutputStream getOutputStream()
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    protected void listen(int i)
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    protected void sendUrgentData(int i)
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    public void setOption(int i, Object obj)
        throws SocketException
    {
        throw new UnsupportedOperationException();
    }

    public (DatagramSocket datagramsocket, FileDescriptor filedescriptor)
    {
        localport = datagramsocket.getLocalPort();
        fd = filedescriptor;
    }
}
