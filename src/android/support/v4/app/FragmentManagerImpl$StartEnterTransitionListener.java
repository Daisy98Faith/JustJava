// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentManagerImpl, BackStackRecord, Fragment

static class mRecord
    implements mRecord
{

    private final boolean mIsBack;
    private int mNumPostponed;
    private final BackStackRecord mRecord;

    public void cancelTransaction()
    {
        FragmentManagerImpl.access$300(mRecord.mManager, mRecord, mIsBack, false, false);
    }

    public void completeTransaction()
    {
        boolean flag1 = false;
        FragmentManagerImpl fragmentmanagerimpl;
        boolean flag;
        int j;
        if (mNumPostponed > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        fragmentmanagerimpl = mRecord.mManager;
        j = fragmentmanagerimpl.mAdded.size();
        for (int i = 0; i < j; i++)
        {
            Fragment fragment = (Fragment)fragmentmanagerimpl.mAdded.get(i);
            fragment.setOnStartEnterTransitionListener(null);
            if (flag && fragment.isPostponed())
            {
                fragment.startPostponedEnterTransition();
            }
        }

        fragmentmanagerimpl = mRecord.mManager;
        BackStackRecord backstackrecord = mRecord;
        boolean flag2 = mIsBack;
        if (!flag)
        {
            flag1 = true;
        }
        FragmentManagerImpl.access$300(fragmentmanagerimpl, backstackrecord, flag2, flag1, true);
    }

    public boolean isReady()
    {
        return mNumPostponed == 0;
    }

    public void onStartEnterTransition()
    {
        mNumPostponed = mNumPostponed - 1;
        if (mNumPostponed != 0)
        {
            return;
        } else
        {
            FragmentManagerImpl.access$200(mRecord.mManager);
            return;
        }
    }

    public void startListening()
    {
        mNumPostponed = mNumPostponed + 1;
    }



    (BackStackRecord backstackrecord, boolean flag)
    {
        mIsBack = flag;
        mRecord = backstackrecord;
    }
}
