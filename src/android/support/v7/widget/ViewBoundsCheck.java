// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;
import java.lang.annotation.Annotation;

class ViewBoundsCheck
{
    static class BoundFlags
    {

        int mBoundFlags;
        int mChildEnd;
        int mChildStart;
        int mRvEnd;
        int mRvStart;

        void addFlags(int i)
        {
            mBoundFlags = mBoundFlags | i;
        }

        boolean boundsMatch()
        {
            while ((mBoundFlags & 7) != 0 && (mBoundFlags & compare(mChildStart, mRvStart) << 0) == 0 || (mBoundFlags & 0x70) != 0 && (mBoundFlags & compare(mChildStart, mRvEnd) << 4) == 0 || (mBoundFlags & 0x700) != 0 && (mBoundFlags & compare(mChildEnd, mRvStart) << 8) == 0 || (mBoundFlags & 0x7000) != 0 && (mBoundFlags & compare(mChildEnd, mRvEnd) << 12) == 0) 
            {
                return false;
            }
            return true;
        }

        int compare(int i, int j)
        {
            if (i > j)
            {
                return 1;
            }
            return i != j ? 4 : 2;
        }

        void resetFlags()
        {
            mBoundFlags = 0;
        }

        void setBounds(int i, int j, int k, int l)
        {
            mRvStart = i;
            mRvEnd = j;
            mChildStart = k;
            mChildEnd = l;
        }

        void setFlags(int i, int j)
        {
            mBoundFlags = mBoundFlags & ~j | i & j;
        }

        BoundFlags()
        {
            mBoundFlags = 0;
        }
    }

    static interface Callback
    {

        public abstract View getChildAt(int i);

        public abstract int getChildCount();

        public abstract int getChildEnd(View view);

        public abstract int getChildStart(View view);

        public abstract View getParent();

        public abstract int getParentEnd();

        public abstract int getParentStart();
    }

    public static interface ViewBounds
        extends Annotation
    {
    }


    static final int CVE_PVE_POS = 12;
    static final int CVE_PVS_POS = 8;
    static final int CVS_PVE_POS = 4;
    static final int CVS_PVS_POS = 0;
    static final int EQ = 2;
    static final int FLAG_CVE_EQ_PVE = 8192;
    static final int FLAG_CVE_EQ_PVS = 512;
    static final int FLAG_CVE_GT_PVE = 4096;
    static final int FLAG_CVE_GT_PVS = 256;
    static final int FLAG_CVE_LT_PVE = 16384;
    static final int FLAG_CVE_LT_PVS = 1024;
    static final int FLAG_CVS_EQ_PVE = 32;
    static final int FLAG_CVS_EQ_PVS = 2;
    static final int FLAG_CVS_GT_PVE = 16;
    static final int FLAG_CVS_GT_PVS = 1;
    static final int FLAG_CVS_LT_PVE = 64;
    static final int FLAG_CVS_LT_PVS = 4;
    static final int GT = 1;
    static final int LT = 4;
    static final int MASK = 7;
    BoundFlags mBoundFlags;
    final Callback mCallback;

    ViewBoundsCheck(Callback callback)
    {
        mCallback = callback;
        mBoundFlags = new BoundFlags();
    }

    View findOneViewWithinBoundFlags(int i, int j, int k, int l)
    {
        int i1 = mCallback.getParentStart();
        int j1 = mCallback.getParentEnd();
        View view;
        byte byte0;
        if (j > i)
        {
            byte0 = 1;
        } else
        {
            byte0 = -1;
        }
        View view1;
        for (view = null; i != j; view = view1)
        {
            View view2 = mCallback.getChildAt(i);
            int k1 = mCallback.getChildStart(view2);
            int l1 = mCallback.getChildEnd(view2);
            mBoundFlags.setBounds(i1, j1, k1, l1);
            if (k != 0)
            {
                mBoundFlags.resetFlags();
                mBoundFlags.addFlags(k);
                if (mBoundFlags.boundsMatch())
                {
                    return view2;
                }
            }
            view1 = view;
            if (l != 0)
            {
                mBoundFlags.resetFlags();
                mBoundFlags.addFlags(l);
                view1 = view;
                if (mBoundFlags.boundsMatch())
                {
                    view1 = view2;
                }
            }
            i += byte0;
        }

        return view;
    }

    boolean isViewWithinBoundFlags(View view, int i)
    {
        mBoundFlags.setBounds(mCallback.getParentStart(), mCallback.getParentEnd(), mCallback.getChildStart(view), mCallback.getChildEnd(view));
        if (i != 0)
        {
            mBoundFlags.resetFlags();
            mBoundFlags.addFlags(i);
            return mBoundFlags.boundsMatch();
        } else
        {
            return false;
        }
    }
}
