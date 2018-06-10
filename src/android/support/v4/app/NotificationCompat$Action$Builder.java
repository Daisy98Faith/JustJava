// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v4.app:
//            NotificationCompat, RemoteInput

public static final class GeneratedReplies
{

    private boolean mAllowGeneratedReplies;
    private final Bundle mExtras;
    private final int mIcon;
    private final PendingIntent mIntent;
    private ArrayList mRemoteInputs;
    private final CharSequence mTitle;

    public GeneratedReplies addExtras(Bundle bundle)
    {
        if (bundle != null)
        {
            mExtras.putAll(bundle);
        }
        return this;
    }

    public mExtras addRemoteInput(RemoteInput remoteinput)
    {
        if (mRemoteInputs == null)
        {
            mRemoteInputs = new ArrayList();
        }
        mRemoteInputs.add(remoteinput);
        return this;
    }

    public mRemoteInputs build()
    {
        RemoteInput aremoteinput[] = new ArrayList();
        RemoteInput aremoteinput1[] = new ArrayList();
        if (mRemoteInputs != null)
        {
            for (Iterator iterator = mRemoteInputs.iterator(); iterator.hasNext();)
            {
                RemoteInput remoteinput = (RemoteInput)iterator.next();
                if (remoteinput.isDataOnly())
                {
                    aremoteinput.add(remoteinput);
                } else
                {
                    aremoteinput1.add(remoteinput);
                }
            }

        }
        if (aremoteinput.isEmpty())
        {
            aremoteinput = null;
        } else
        {
            aremoteinput = (RemoteInput[])aremoteinput.toArray(new RemoteInput[aremoteinput.size()]);
        }
        if (aremoteinput1.isEmpty())
        {
            aremoteinput1 = null;
        } else
        {
            aremoteinput1 = (RemoteInput[])aremoteinput1.toArray(new RemoteInput[aremoteinput1.size()]);
        }
        return new mRemoteInputs(mIcon, mTitle, mIntent, mExtras, aremoteinput1, aremoteinput, mAllowGeneratedReplies);
    }

    public  extend( )
    {
        .extend(this);
        return this;
    }

    public Bundle getExtras()
    {
        return mExtras;
    }

    public mExtras setAllowGeneratedReplies(boolean flag)
    {
        mAllowGeneratedReplies = flag;
        return this;
    }

    public (int i, CharSequence charsequence, PendingIntent pendingintent)
    {
        this(i, charsequence, pendingintent, new Bundle(), null, true);
    }

    private <init>(int i, CharSequence charsequence, PendingIntent pendingintent, Bundle bundle, RemoteInput aremoteinput[], boolean flag)
    {
        mAllowGeneratedReplies = true;
        mIcon = i;
        mTitle = arSequenceLength(charsequence);
        mIntent = pendingintent;
        mExtras = bundle;
        if (aremoteinput == null)
        {
            charsequence = null;
        } else
        {
            charsequence = new ArrayList(Arrays.asList(aremoteinput));
        }
        mRemoteInputs = charsequence;
        mAllowGeneratedReplies = flag;
    }

    public mAllowGeneratedReplies(mAllowGeneratedReplies mallowgeneratedreplies)
    {
        this(mallowgeneratedreplies.<init>, mallowgeneratedreplies.<init>, mallowgeneratedreplies.tent, new Bundle(mallowgeneratedreplies.tent), mallowgeneratedreplies.eInputs(), mallowgeneratedreplies.GeneratedReplies());
    }
}
