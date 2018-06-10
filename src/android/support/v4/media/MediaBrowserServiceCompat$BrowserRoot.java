// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserServiceCompat

public static final class mExtras
{

    public static final String EXTRA_OFFLINE = "android.service.media.extra.OFFLINE";
    public static final String EXTRA_RECENT = "android.service.media.extra.RECENT";
    public static final String EXTRA_SUGGESTED = "android.service.media.extra.SUGGESTED";
    public static final String EXTRA_SUGGESTION_KEYWORDS = "android.service.media.extra.SUGGESTION_KEYWORDS";
    private final Bundle mExtras;
    private final String mRootId;

    public Bundle getExtras()
    {
        return mExtras;
    }

    public String getRootId()
    {
        return mRootId;
    }

    public (String s, Bundle bundle)
    {
        if (s == null)
        {
            throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead.");
        } else
        {
            mRootId = s;
            mExtras = bundle;
            return;
        }
    }
}
