// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;

// Referenced classes of package android.support.v4.app:
//            BaseFragmentActivityApi14

abstract class BaseFragmentActivityJB extends BaseFragmentActivityApi14
{

    boolean mStartedActivityFromFragment;

    BaseFragmentActivityJB()
    {
    }

    public void startActivityForResult(Intent intent, int i, Bundle bundle)
    {
        if (!mStartedActivityFromFragment && i != -1)
        {
            checkForValidRequestCode(i);
        }
        super.startActivityForResult(intent, i, bundle);
    }

    public void startIntentSenderForResult(IntentSender intentsender, int i, Intent intent, int j, int k, int l, Bundle bundle)
        throws android.content.IntentSender.SendIntentException
    {
        if (!mStartedIntentSenderFromFragment && i != -1)
        {
            checkForValidRequestCode(i);
        }
        super.startIntentSenderForResult(intentsender, i, intent, j, k, l, bundle);
    }
}
