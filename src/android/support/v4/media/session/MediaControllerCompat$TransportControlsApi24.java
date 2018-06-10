// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;

// Referenced classes of package android.support.v4.media.session:
//            MediaControllerCompat

static class <init> extends <init>
{

    public void prepare()
    {
        prepare(mControlsObj);
    }

    public void prepareFromMediaId(String s, Bundle bundle)
    {
        prepareFromMediaId(mControlsObj, s, bundle);
    }

    public void prepareFromSearch(String s, Bundle bundle)
    {
        prepareFromSearch(mControlsObj, s, bundle);
    }

    public void prepareFromUri(Uri uri, Bundle bundle)
    {
        prepareFromUri(mControlsObj, uri, bundle);
    }

    public (Object obj)
    {
        super(obj);
    }
}
