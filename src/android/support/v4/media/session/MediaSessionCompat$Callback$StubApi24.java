// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;

// Referenced classes of package android.support.v4.media.session:
//            MediaSessionCompat

private class <init> extends <init>
    implements <init>
{

    final romUri this$0;

    public void onPrepare()
    {
        <init>.this.<init>();
    }

    public void onPrepareFromMediaId(String s, Bundle bundle)
    {
        romMediaId(s, bundle);
    }

    public void onPrepareFromSearch(String s, Bundle bundle)
    {
        romSearch(s, bundle);
    }

    public void onPrepareFromUri(Uri uri, Bundle bundle)
    {
        romUri(uri, bundle);
    }

    ()
    {
        this$0 = this._cls0.this;
        super(<init>.this);
    }
}
