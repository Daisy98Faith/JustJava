// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;

// Referenced classes of package android.support.v4.media.session:
//            MediaSessionCompatApi24

public static interface 
    extends 
{

    public abstract void onPrepare();

    public abstract void onPrepareFromMediaId(String s, Bundle bundle);

    public abstract void onPrepareFromSearch(String s, Bundle bundle);

    public abstract void onPrepareFromUri(Uri uri, Bundle bundle);
}
