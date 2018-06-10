// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.util.Log;
import android.view.View;
import java.util.List;

// Referenced classes of package android.support.v7.widget:
//            LinearLayoutManager

static class mScrapList
{

    static final int INVALID_LAYOUT = 0x80000000;
    static final int ITEM_DIRECTION_HEAD = -1;
    static final int ITEM_DIRECTION_TAIL = 1;
    static final int LAYOUT_END = 1;
    static final int LAYOUT_START = -1;
    static final int SCROLLING_OFFSET_NaN = 0x80000000;
    static final String TAG = "LLM#LayoutState";
    int mAvailable;
    int mCurrentPosition;
    int mExtra;
    boolean mInfinite;
    boolean mIsPreLayout;
    int mItemDirection;
    int mLastScrollDelta;
    int mLayoutDirection;
    int mOffset;
    boolean mRecycle;
    List mScrapList;
    int mScrollingOffset;

    private View nextViewFromScrapList()
    {
        int i;
        int j;
        j = mScrapList.size();
        i = 0;
_L3:
        View view;
        mScrapList mscraplist;
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        view = ((mScrapList)mScrapList.get(i)).;
        mscraplist = ()view.getLayoutParams();
          goto _L1
_L5:
        i++;
        if (true) goto _L3; else goto _L2
_L1:
        if (mscraplist.Removed() || mCurrentPosition != mscraplist.wLayoutPosition()) goto _L5; else goto _L4
_L4:
        assignPositionFromScrapList(view);
        return view;
_L2:
        return null;
    }

    public void assignPositionFromScrapList()
    {
        assignPositionFromScrapList(null);
    }

    public void assignPositionFromScrapList(View view)
    {
        view = nextViewInLimitedList(view);
        if (view == null)
        {
            mCurrentPosition = -1;
            return;
        } else
        {
            mCurrentPosition = ((mCurrentPosition)view.getLayoutParams()).wLayoutPosition();
            return;
        }
    }

    boolean hasMore(wLayoutPosition wlayoutposition)
    {
        return mCurrentPosition >= 0 && mCurrentPosition < wlayoutposition.mCurrentPosition();
    }

    void log()
    {
        Log.d("LLM#LayoutState", (new StringBuilder()).append("avail:").append(mAvailable).append(", ind:").append(mCurrentPosition).append(", dir:").append(mItemDirection).append(", offset:").append(mOffset).append(", layoutDir:").append(mLayoutDirection).toString());
    }

    View next(mLayoutDirection mlayoutdirection)
    {
        if (mScrapList != null)
        {
            return nextViewFromScrapList();
        } else
        {
            mlayoutdirection = mlayoutdirection.Position(mCurrentPosition);
            mCurrentPosition = mCurrentPosition + mItemDirection;
            return mlayoutdirection;
        }
    }

    public View nextViewInLimitedList(View view)
    {
        View view1;
        int i;
        int j;
        int i1;
        i1 = mScrapList.size();
        view1 = null;
        j = 0x7fffffff;
        i = 0;
_L2:
        View view2;
        View view3;
        mItemDirection mitemdirection;
        int k;
        view2 = view1;
        if (i >= i1)
        {
            break MISSING_BLOCK_LABEL_156;
        }
        view3 = ((mScrapList)mScrapList.get(i)).;
        mitemdirection = ()view3.getLayoutParams();
        view2 = view1;
        k = j;
        if (view3 != view)
        {
            if (!mitemdirection.Removed())
            {
                break; /* Loop/switch isn't completed */
            }
            k = j;
            view2 = view1;
        }
_L4:
        i++;
        view1 = view2;
        j = k;
        if (true) goto _L2; else goto _L1
_L1:
        int l;
        l = (mitemdirection.wLayoutPosition() - mCurrentPosition) * mItemDirection;
        view2 = view1;
        k = j;
        if (l < 0) goto _L4; else goto _L3
_L3:
        view2 = view1;
        k = j;
        if (l >= j) goto _L4; else goto _L5
_L5:
        view1 = view3;
        k = l;
        view2 = view1;
        if (l != 0) goto _L4; else goto _L6
_L6:
        view2 = view1;
        return view2;
    }

    ()
    {
        mRecycle = true;
        mExtra = 0;
        mIsPreLayout = false;
        mScrapList = null;
    }
}
