// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import java.util.concurrent.ArrayBlockingQueue;

// Referenced classes of package android.support.v4.view:
//            AsyncLayoutInflater

private static class mRequestPool extends Thread
{

    private static final start sInstance;
    private ArrayBlockingQueue mQueue;
    private android.support.v4.util.start mRequestPool;

    public static mRequestPool getInstance()
    {
        return sInstance;
    }

    public void enqueue( )
    {
        try
        {
            mQueue.put();
            return;
        }
        // Misplaced declaration of an exception variable
        catch ( )
        {
            throw new RuntimeException("Failed to enqueue async inflate request", );
        }
    }

    public  obtainRequest()
    {
         1 = ()mRequestPool.mRequestPool();
          = 1;
        if (1 == null)
        {
             = new ();
        }
        return ;
    }

    public void releaseRequest( )
    {
        .callback = null;
        .inflater = null;
        .parent = null;
        .resid = 0;
        .view = null;
        mRequestPool.mRequestPool();
    }

    public void run()
    {
_L2:
          = ()mQueue.take();
        InterruptedException interruptedexception;
        try
        {
            .view = .inflater.mInflater.inflate(.resid, .parent, false);
        }
        catch (RuntimeException runtimeexception)
        {
            Log.w("AsyncLayoutInflater", "Failed to inflate resource in the background! Retrying on the UI thread", runtimeexception);
        }
        Message.obtain(.inflater.mHandler, 0, ).sendToTarget();
        continue; /* Loop/switch isn't completed */
        interruptedexception;
        Log.w("AsyncLayoutInflater", interruptedexception);
        if (true) goto _L2; else goto _L1
_L1:
    }

    static 
    {
        sInstance = new <init>();
        sInstance.start();
    }

    private ()
    {
        mQueue = new ArrayBlockingQueue(10);
        mRequestPool = new android.support.v4.util.mRequestPool(10);
    }
}
