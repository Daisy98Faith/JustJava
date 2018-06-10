// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;

class ActivityCompatJB
{

    ActivityCompatJB()
    {
    }

    public static void finishAffinity(Activity activity)
    {
        activity.finishAffinity();
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i, Bundle bundle)
    {
        activity.startActivityForResult(intent, i, bundle);
    }

    public static void startIntentSenderForResult(Activity activity, IntentSender intentsender, int i, Intent intent, int j, int k, int l, Bundle bundle)
        throws android.content.IntentSender.SendIntentException
    {
        activity.startIntentSenderForResult(intentsender, i, intent, j, k, l, bundle);
    }
}
