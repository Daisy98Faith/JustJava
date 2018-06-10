// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat, MediaBrowserCompatApi21, MediaBrowserCompatApi24

static class <init> extends <init>
{

    public void subscribe(String s, Bundle bundle, <init> <init>1)
    {
        if (bundle == null)
        {
            MediaBrowserCompatApi21.subscribe(mBrowserObj, s, ccess._mth200(<init>1));
            return;
        } else
        {
            MediaBrowserCompatApi24.subscribe(mBrowserObj, s, bundle, ccess._mth200(<init>1));
            return;
        }
    }

    public void unsubscribe(String s, ccess._cls200 _pcls200)
    {
        if (_pcls200 == null)
        {
            MediaBrowserCompatApi21.unsubscribe(mBrowserObj, s);
            return;
        } else
        {
            MediaBrowserCompatApi24.unsubscribe(mBrowserObj, s, ccess._mth200(_pcls200));
            return;
        }
    }

    public (Context context, ComponentName componentname,  , Bundle bundle)
    {
        super(context, componentname, , bundle);
    }
}
