// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.BundleCompat;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat, MediaBrowserCompat

private final class this._cls0 extends Handler
{

    private final pl mServiceBinderImpl;
    final MediaBrowserServiceCompat this$0;

    public void handleMessage(Message message)
    {
        Bundle bundle = message.getData();
        switch (message.what)
        {
        default:
            Log.w("MBServiceCompat", (new StringBuilder()).append("Unhandled message: ").append(message).append("\n  Service version: ").append(1).append("\n  Client version: ").append(message.arg1).toString());
            return;

        case 1: // '\001'
            mServiceBinderImpl.connect(bundle.getString("data_package_name"), bundle.getInt("data_calling_uid"), bundle.getBundle("data_root_hints"), new sCompat(MediaBrowserServiceCompat.this, message.replyTo));
            return;

        case 2: // '\002'
            mServiceBinderImpl.disconnect(new sCompat(MediaBrowserServiceCompat.this, message.replyTo));
            return;

        case 3: // '\003'
            mServiceBinderImpl.addSubscription(bundle.getString("data_media_item_id"), BundleCompat.getBinder(bundle, "data_callback_token"), bundle.getBundle("data_options"), new sCompat(MediaBrowserServiceCompat.this, message.replyTo));
            return;

        case 4: // '\004'
            mServiceBinderImpl.removeSubscription(bundle.getString("data_media_item_id"), BundleCompat.getBinder(bundle, "data_callback_token"), new sCompat(MediaBrowserServiceCompat.this, message.replyTo));
            return;

        case 5: // '\005'
            mServiceBinderImpl.getMediaItem(bundle.getString("data_media_item_id"), (ResultReceiver)bundle.getParcelable("data_result_receiver"), new sCompat(MediaBrowserServiceCompat.this, message.replyTo));
            return;

        case 6: // '\006'
            mServiceBinderImpl.registerCallbacks(new sCompat(MediaBrowserServiceCompat.this, message.replyTo), bundle.getBundle("data_root_hints"));
            return;

        case 7: // '\007'
            mServiceBinderImpl.unregisterCallbacks(new sCompat(MediaBrowserServiceCompat.this, message.replyTo));
            return;

        case 8: // '\b'
            mServiceBinderImpl.search(bundle.getString("data_search_query"), bundle.getBundle("data_search_extras"), (ResultReceiver)bundle.getParcelable("data_result_receiver"), new sCompat(MediaBrowserServiceCompat.this, message.replyTo));
            break;
        }
    }

    public void postOrRun(Runnable runnable)
    {
        if (Thread.currentThread() == getLooper().getThread())
        {
            runnable.run();
            return;
        } else
        {
            post(runnable);
            return;
        }
    }

    public boolean sendMessageAtTime(Message message, long l)
    {
        Bundle bundle = message.getData();
        bundle.setClassLoader(android/support/v4/media/MediaBrowserCompat.getClassLoader());
        bundle.putInt("data_calling_uid", Binder.getCallingUid());
        return super.sendMessageAtTime(message, l);
    }

    pl()
    {
        this$0 = MediaBrowserServiceCompat.this;
        super();
        mServiceBinderImpl = new pl(MediaBrowserServiceCompat.this);
    }
}
