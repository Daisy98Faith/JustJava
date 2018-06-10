// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat, MediaBrowserCompatUtils

private static class allback
{

    private final List mCallbacks = new ArrayList();
    private final List mOptionsList = new ArrayList();

    public allback getCallback(Bundle bundle)
    {
        for (int i = 0; i < mOptionsList.size(); i++)
        {
            if (MediaBrowserCompatUtils.areSameOptions((Bundle)mOptionsList.get(i), bundle))
            {
                return (allback)mCallbacks.get(i);
            }
        }

        return null;
    }

    public List getCallbacks()
    {
        return mCallbacks;
    }

    public List getOptionsList()
    {
        return mOptionsList;
    }

    public boolean isEmpty()
    {
        return mCallbacks.isEmpty();
    }

    public void putCallback(Bundle bundle, allback allback)
    {
        for (int i = 0; i < mOptionsList.size(); i++)
        {
            if (MediaBrowserCompatUtils.areSameOptions((Bundle)mOptionsList.get(i), bundle))
            {
                mCallbacks.set(i, allback);
                return;
            }
        }

        mCallbacks.add(allback);
        mOptionsList.add(bundle);
    }

    public allback()
    {
    }
}
