// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.Context;
import android.os.Bundle;
import android.service.media.MediaBrowserService;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompatApi21

static class mServiceProxy extends MediaBrowserService
{

    final ildren mServiceProxy;

    public android.service.media.or onGetRoot(String s, int i, Bundle bundle)
    {
        s = mServiceProxy.t(s, i, bundle);
        if (s == null)
        {
            return null;
        } else
        {
            return new android.service.media.Root(((t) (s)).t, ((t) (s)).t);
        }
    }

    public void onLoadChildren(String s, android.service.media.or or)
    {
        mServiceProxy.ildren(s, new ildren(or));
    }

    (Context context,  )
    {
        attachBaseContext(context);
        mServiceProxy = ;
    }
}
