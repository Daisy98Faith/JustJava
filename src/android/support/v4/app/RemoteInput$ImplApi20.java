// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.content.Intent;
import android.os.Bundle;
import java.util.Map;

// Referenced classes of package android.support.v4.app:
//            RemoteInput, RemoteInputCompatApi20

static class 
    implements 
{

    public void addDataResultToIntent(RemoteInput remoteinput, Intent intent, Map map)
    {
        RemoteInputCompatApi20.addDataResultToIntent(remoteinput, intent, map);
    }

    public void addResultsToIntent(RemoteInput aremoteinput[], Intent intent, Bundle bundle)
    {
        RemoteInputCompatApi20.addResultsToIntent(aremoteinput, intent, bundle);
    }

    public Map getDataResultsFromIntent(Intent intent, String s)
    {
        return RemoteInputCompatApi20.getDataResultsFromIntent(intent, s);
    }

    public Bundle getResultsFromIntent(Intent intent)
    {
        return RemoteInputCompatApi20.getResultsFromIntent(intent);
    }

    ()
    {
    }
}
