// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentManagerImpl, Fragment, FragmentManager

private class mFlags
    implements mFlags
{

    final int mFlags;
    final int mId;
    final String mName;
    final FragmentManagerImpl this$0;

    public boolean generateOps(ArrayList arraylist, ArrayList arraylist1)
    {
        if (mPrimaryNav != null && mId < 0 && mName == null)
        {
            FragmentManager fragmentmanager = mPrimaryNav.peekChildFragmentManager();
            if (fragmentmanager != null && fragmentmanager.popBackStackImmediate())
            {
                return false;
            }
        }
        return popBackStackState(arraylist, arraylist1, mName, mId, mFlags);
    }

    (String s, int i, int j)
    {
        this$0 = FragmentManagerImpl.this;
        super();
        mName = s;
        mId = i;
        mFlags = j;
    }
}
