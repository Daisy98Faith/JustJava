// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Activity;
import android.net.Uri;

class ActivityCompatApi22
{

    ActivityCompatApi22()
    {
    }

    public static Uri getReferrer(Activity activity)
    {
        return activity.getReferrer();
    }
}
