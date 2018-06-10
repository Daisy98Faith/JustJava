// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;


// Referenced classes of package android.support.v7.widget:
//            ViewBoundsCheck

static class mBoundFlags
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

    ()
    {
        mBoundFlags = 0;
    }
}
