// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

private class mCallbacks
    implements mCallbacks
{

    final Messenger mCallbacks;
    final MediaBrowserServiceCompat this$0;

    private void sendRequest(int i, Bundle bundle)
        throws RemoteException
    {
        Message message = Message.obtain();
        message.what = i;
        message.arg1 = 1;
        message.setData(bundle);
        mCallbacks.send(message);
    }

    public IBinder asBinder()
    {
        return mCallbacks.getBinder();
    }

    public void onConnect(String s, android.support.v4.media.session.ks ks, Bundle bundle)
        throws RemoteException
    {
        Bundle bundle1 = bundle;
        if (bundle == null)
        {
            bundle1 = new Bundle();
        }
        bundle1.putInt("extra_service_version", 1);
        bundle = new Bundle();
        bundle.putString("data_media_item_id", s);
        bundle.putParcelable("data_media_session_token", ks);
        bundle.putBundle("data_root_hints", bundle1);
        sendRequest(1, bundle);
    }

    public void onConnectFailed()
        throws RemoteException
    {
        sendRequest(2, null);
    }

    public void onLoadChildren(String s, List list, Bundle bundle)
        throws RemoteException
    {
        Bundle bundle1 = new Bundle();
        bundle1.putString("data_media_item_id", s);
        bundle1.putBundle("data_options", bundle);
        if (list != null)
        {
            if (list instanceof ArrayList)
            {
                s = (ArrayList)list;
            } else
            {
                s = new ArrayList(list);
            }
            bundle1.putParcelableArrayList("data_media_item_list", s);
        }
        sendRequest(3, bundle1);
    }

    (Messenger messenger)
    {
        this$0 = MediaBrowserServiceCompat.this;
        super();
        mCallbacks = messenger;
    }
}
