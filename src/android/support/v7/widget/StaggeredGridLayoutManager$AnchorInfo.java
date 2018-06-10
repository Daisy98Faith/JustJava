// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import java.util.Arrays;

// Referenced classes of package android.support.v7.widget:
//            StaggeredGridLayoutManager, OrientationHelper

class reset
{

    boolean mInvalidateOffsets;
    boolean mLayoutFromEnd;
    int mOffset;
    int mPosition;
    int mSpanReferenceLines[];
    boolean mValid;
    final StaggeredGridLayoutManager this$0;

    void assignCoordinateFromPadding()
    {
        int i;
        if (mLayoutFromEnd)
        {
            i = mPrimaryOrientation.getEndAfterPadding();
        } else
        {
            i = mPrimaryOrientation.getStartAfterPadding();
        }
        mOffset = i;
    }

    void assignCoordinateFromPadding(int i)
    {
        if (mLayoutFromEnd)
        {
            mOffset = mPrimaryOrientation.getEndAfterPadding() - i;
            return;
        } else
        {
            mOffset = mPrimaryOrientation.getStartAfterPadding() + i;
            return;
        }
    }

    void reset()
    {
        mPosition = -1;
        mOffset = 0x80000000;
        mLayoutFromEnd = false;
        mInvalidateOffsets = false;
        mValid = false;
        if (mSpanReferenceLines != null)
        {
            Arrays.fill(mSpanReferenceLines, -1);
        }
    }

    void saveSpanReferenceLines(mSpanReferenceLines amspanreferencelines[])
    {
        int j = amspanreferencelines.length;
        if (mSpanReferenceLines == null || mSpanReferenceLines.length < j)
        {
            mSpanReferenceLines = new int[mSpans.length];
        }
        for (int i = 0; i < j; i++)
        {
            mSpanReferenceLines[i] = amspanreferencelines[i].rtLine(0x80000000);
        }

    }

    ()
    {
        this$0 = StaggeredGridLayoutManager.this;
        super();
        reset();
    }
}
