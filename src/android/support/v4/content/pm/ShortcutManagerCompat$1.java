// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content.pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;

// Referenced classes of package android.support.v4.content.pm:
//            ShortcutManagerCompat

static final class val.callback extends BroadcastReceiver
{

    final IntentSender val$callback;

    public void onReceive(Context context, Intent intent)
    {
        try
        {
            val$callback.sendIntent(context, 0, null, null, null);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return;
        }
    }

    (IntentSender intentsender)
    {
        val$callback = intentsender;
        super();
    }
}
