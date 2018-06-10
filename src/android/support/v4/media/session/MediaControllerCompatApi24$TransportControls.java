// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;

// Referenced classes of package android.support.v4.media.session:
//            MediaControllerCompatApi24

public static class  extends 
{

    public static void prepare(Object obj)
    {
        ((android.media.session.rtControls)obj).rtControls();
    }

    public static void prepareFromMediaId(Object obj, String s, Bundle bundle)
    {
        ((android.media.session.rtControls)obj).MediaId(s, bundle);
    }

    public static void prepareFromSearch(Object obj, String s, Bundle bundle)
    {
        ((android.media.session.MediaId)obj).Search(s, bundle);
    }

    public static void prepareFromUri(Object obj, Uri uri, Bundle bundle)
    {
        ((android.media.session.Search)obj).Uri(uri, bundle);
    }

    public ()
    {
    }
}
