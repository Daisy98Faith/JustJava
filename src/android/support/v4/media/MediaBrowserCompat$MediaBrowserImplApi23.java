// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat, MediaBrowserCompatApi23

static class <init> extends <init>
{

    public void getItem(String s, <init> <init>1)
    {
        if (mServiceBinderWrapper == null)
        {
            MediaBrowserCompatApi23.getItem(mBrowserObj, s, <init>1.backObj);
            return;
        } else
        {
            super.getItem(s, <init>1);
            return;
        }
    }

    public (Context context, ComponentName componentname,  , Bundle bundle)
    {
        super(context, componentname, , bundle);
    }
}
