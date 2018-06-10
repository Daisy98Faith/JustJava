// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.ComponentName;
import android.os.Bundle;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat

static interface ack
{

    public abstract void connect();

    public abstract void disconnect();

    public abstract Bundle getExtras();

    public abstract void getItem(String s, ack ack);

    public abstract String getRoot();

    public abstract ComponentName getServiceComponent();

    public abstract android.support.v4.media.session. getSessionToken();

    public abstract boolean isConnected();

    public abstract void search(String s, Bundle bundle, ack ack);

    public abstract void subscribe(String s, Bundle bundle, ack ack);

    public abstract void unsubscribe(String s, ack ack);
}
