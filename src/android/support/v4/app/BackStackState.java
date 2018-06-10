// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            BackStackRecord, Fragment, FragmentManagerImpl

final class BackStackState
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BackStackState createFromParcel(Parcel parcel)
        {
            return new BackStackState(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BackStackState[] newArray(int i)
        {
            return new BackStackState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    };
    final boolean mAllowOptimization;
    final int mBreadCrumbShortTitleRes;
    final CharSequence mBreadCrumbShortTitleText;
    final int mBreadCrumbTitleRes;
    final CharSequence mBreadCrumbTitleText;
    final int mIndex;
    final String mName;
    final int mOps[];
    final ArrayList mSharedElementSourceNames;
    final ArrayList mSharedElementTargetNames;
    final int mTransition;
    final int mTransitionStyle;

    public BackStackState(Parcel parcel)
    {
        mOps = parcel.createIntArray();
        mTransition = parcel.readInt();
        mTransitionStyle = parcel.readInt();
        mName = parcel.readString();
        mIndex = parcel.readInt();
        mBreadCrumbTitleRes = parcel.readInt();
        mBreadCrumbTitleText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mBreadCrumbShortTitleRes = parcel.readInt();
        mBreadCrumbShortTitleText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mSharedElementSourceNames = parcel.createStringArrayList();
        mSharedElementTargetNames = parcel.createStringArrayList();
        boolean flag;
        if (parcel.readInt() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mAllowOptimization = flag;
    }

    public BackStackState(BackStackRecord backstackrecord)
    {
        int k = backstackrecord.mOps.size();
        mOps = new int[k * 6];
        if (!backstackrecord.mAddToBackStack)
        {
            throw new IllegalStateException("Not on back stack");
        }
        int i = 0;
        int j = 0;
        while (i < k) 
        {
            BackStackRecord.Op op = (BackStackRecord.Op)backstackrecord.mOps.get(i);
            int ai[] = mOps;
            int l = j + 1;
            ai[j] = op.cmd;
            ai = mOps;
            int i1 = l + 1;
            if (op.fragment != null)
            {
                j = op.fragment.mIndex;
            } else
            {
                j = -1;
            }
            ai[l] = j;
            ai = mOps;
            j = i1 + 1;
            ai[i1] = op.enterAnim;
            ai = mOps;
            l = j + 1;
            ai[j] = op.exitAnim;
            ai = mOps;
            i1 = l + 1;
            ai[l] = op.popEnterAnim;
            ai = mOps;
            j = i1 + 1;
            ai[i1] = op.popExitAnim;
            i++;
        }
        mTransition = backstackrecord.mTransition;
        mTransitionStyle = backstackrecord.mTransitionStyle;
        mName = backstackrecord.mName;
        mIndex = backstackrecord.mIndex;
        mBreadCrumbTitleRes = backstackrecord.mBreadCrumbTitleRes;
        mBreadCrumbTitleText = backstackrecord.mBreadCrumbTitleText;
        mBreadCrumbShortTitleRes = backstackrecord.mBreadCrumbShortTitleRes;
        mBreadCrumbShortTitleText = backstackrecord.mBreadCrumbShortTitleText;
        mSharedElementSourceNames = backstackrecord.mSharedElementSourceNames;
        mSharedElementTargetNames = backstackrecord.mSharedElementTargetNames;
        mAllowOptimization = backstackrecord.mAllowOptimization;
    }

    public int describeContents()
    {
        return 0;
    }

    public BackStackRecord instantiate(FragmentManagerImpl fragmentmanagerimpl)
    {
        BackStackRecord backstackrecord = new BackStackRecord(fragmentmanagerimpl);
        int j = 0;
        int i = 0;
        while (j < mOps.length) 
        {
            BackStackRecord.Op op = new BackStackRecord.Op();
            int ai[] = mOps;
            int k = j + 1;
            op.cmd = ai[j];
            if (FragmentManagerImpl.DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder()).append("Instantiate ").append(backstackrecord).append(" op #").append(i).append(" base fragment #").append(mOps[k]).toString());
            }
            ai = mOps;
            j = k + 1;
            k = ai[k];
            if (k >= 0)
            {
                op.fragment = (Fragment)fragmentmanagerimpl.mActive.get(k);
            } else
            {
                op.fragment = null;
            }
            ai = mOps;
            k = j + 1;
            op.enterAnim = ai[j];
            ai = mOps;
            j = k + 1;
            op.exitAnim = ai[k];
            ai = mOps;
            k = j + 1;
            op.popEnterAnim = ai[j];
            ai = mOps;
            j = k + 1;
            op.popExitAnim = ai[k];
            backstackrecord.mEnterAnim = op.enterAnim;
            backstackrecord.mExitAnim = op.exitAnim;
            backstackrecord.mPopEnterAnim = op.popEnterAnim;
            backstackrecord.mPopExitAnim = op.popExitAnim;
            backstackrecord.addOp(op);
            i++;
        }
        backstackrecord.mTransition = mTransition;
        backstackrecord.mTransitionStyle = mTransitionStyle;
        backstackrecord.mName = mName;
        backstackrecord.mIndex = mIndex;
        backstackrecord.mAddToBackStack = true;
        backstackrecord.mBreadCrumbTitleRes = mBreadCrumbTitleRes;
        backstackrecord.mBreadCrumbTitleText = mBreadCrumbTitleText;
        backstackrecord.mBreadCrumbShortTitleRes = mBreadCrumbShortTitleRes;
        backstackrecord.mBreadCrumbShortTitleText = mBreadCrumbShortTitleText;
        backstackrecord.mSharedElementSourceNames = mSharedElementSourceNames;
        backstackrecord.mSharedElementTargetNames = mSharedElementTargetNames;
        backstackrecord.mAllowOptimization = mAllowOptimization;
        backstackrecord.bumpBackStackNesting(1);
        return backstackrecord;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        i = 0;
        parcel.writeIntArray(mOps);
        parcel.writeInt(mTransition);
        parcel.writeInt(mTransitionStyle);
        parcel.writeString(mName);
        parcel.writeInt(mIndex);
        parcel.writeInt(mBreadCrumbTitleRes);
        TextUtils.writeToParcel(mBreadCrumbTitleText, parcel, 0);
        parcel.writeInt(mBreadCrumbShortTitleRes);
        TextUtils.writeToParcel(mBreadCrumbShortTitleText, parcel, 0);
        parcel.writeStringList(mSharedElementSourceNames);
        parcel.writeStringList(mSharedElementTargetNames);
        if (mAllowOptimization)
        {
            i = 1;
        }
        parcel.writeInt(i);
    }

}
