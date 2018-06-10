// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.util.Map;

// Referenced classes of package android.support.v4.app:
//            RemoteInput

static class 
    implements 
{

    public void addDataResultToIntent(RemoteInput remoteinput, Intent intent, Map map)
    {
        Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
    }

    public void addResultsToIntent(RemoteInput aremoteinput[], Intent intent, Bundle bundle)
    {
        Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
    }

    public Map getDataResultsFromIntent(Intent intent, String s)
    {
        Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
        return null;
    }

    public Bundle getResultsFromIntent(Intent intent)
    {
        Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
        return null;
    }

    ()
    {
    }
}
