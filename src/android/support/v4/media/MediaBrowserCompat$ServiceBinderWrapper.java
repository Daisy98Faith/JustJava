// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.app.BundleCompat;
import android.support.v4.os.ResultReceiver;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat

private static class mRootHints
{

    private Messenger mMessenger;
    private Bundle mRootHints;

    private void sendRequest(int i, Bundle bundle, Messenger messenger)
        throws RemoteException
    {
        Message message = Message.obtain();
        message.what = i;
        message.arg1 = 1;
        message.setData(bundle);
        message.replyTo = messenger;
        mMessenger.send(message);
    }

    void addSubscription(String s, IBinder ibinder, Bundle bundle, Messenger messenger)
        throws RemoteException
    {
        Bundle bundle1 = new Bundle();
        bundle1.putString("data_media_item_id", s);
        BundleCompat.putBinder(bundle1, "data_callback_token", ibinder);
        bundle1.putBundle("data_options", bundle);
        sendRequest(3, bundle1, messenger);
    }

    void connect(Context context, Messenger messenger)
        throws RemoteException
    {
        Bundle bundle = new Bundle();
        bundle.putString("data_package_name", context.getPackageName());
        bundle.putBundle("data_root_hints", mRootHints);
        sendRequest(1, bundle, messenger);
    }

    void disconnect(Messenger messenger)
        throws RemoteException
    {
        sendRequest(2, null, messenger);
    }

    void getMediaItem(String s, ResultReceiver resultreceiver, Messenger messenger)
        throws RemoteException
    {
        Bundle bundle = new Bundle();
        bundle.putString("data_media_item_id", s);
        bundle.putParcelable("data_result_receiver", resultreceiver);
        sendRequest(5, bundle, messenger);
    }

    void registerCallbackMessenger(Messenger messenger)
        throws RemoteException
    {
        Bundle bundle = new Bundle();
        bundle.putBundle("data_root_hints", mRootHints);
        sendRequest(6, bundle, messenger);
    }

    void removeSubscription(String s, IBinder ibinder, Messenger messenger)
        throws RemoteException
    {
        Bundle bundle = new Bundle();
        bundle.putString("data_media_item_id", s);
        BundleCompat.putBinder(bundle, "data_callback_token", ibinder);
        sendRequest(4, bundle, messenger);
    }

    void search(String s, Bundle bundle, ResultReceiver resultreceiver, Messenger messenger)
        throws RemoteException
    {
        Bundle bundle1 = new Bundle();
        bundle1.putString("data_search_query", s);
        bundle1.putBundle("data_search_extras", bundle);
        bundle1.putParcelable("data_result_receiver", resultreceiver);
        sendRequest(8, bundle1, messenger);
    }

    void unregisterCallbackMessenger(Messenger messenger)
        throws RemoteException
    {
        sendRequest(7, null, messenger);
    }

    public (IBinder ibinder, Bundle bundle)
    {
        mMessenger = new Messenger(ibinder);
        mRootHints = bundle;
    }
}
