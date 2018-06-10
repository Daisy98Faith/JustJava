// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;

// Referenced classes of package android.support.v4.media.session:
//            MediaSessionCompatApi24

static class <init> extends <init>
{

    public void onPrepare()
    {
        ((<init>)mCallback).pare();
    }

    public void onPrepareFromMediaId(String s, Bundle bundle)
    {
        ((pare)mCallback).pareFromMediaId(s, bundle);
    }

    public void onPrepareFromSearch(String s, Bundle bundle)
    {
        ((pareFromMediaId)mCallback).pareFromSearch(s, bundle);
    }

    public void onPrepareFromUri(Uri uri, Bundle bundle)
    {
        ((pareFromSearch)mCallback).pareFromUri(uri, bundle);
    }

    public ( )
    {
        super();
    }
}
