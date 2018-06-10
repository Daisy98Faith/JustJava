// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

// Referenced classes of package android.support.v7.widget:
//            LayoutState, OrientationHelper, ScrollbarHelper, RecyclerView, 
//            LinearSmoothScroller

public class StaggeredGridLayoutManager extends RecyclerView.LayoutManager
    implements RecyclerView.SmoothScroller.ScrollVectorProvider
{
    class AnchorInfo
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

        void saveSpanReferenceLines(Span aspan[])
        {
            int j = aspan.length;
            if (mSpanReferenceLines == null || mSpanReferenceLines.length < j)
            {
                mSpanReferenceLines = new int[mSpans.length];
            }
            for (int i = 0; i < j; i++)
            {
                mSpanReferenceLines[i] = aspan[i].getStartLine(0x80000000);
            }

        }

        AnchorInfo()
        {
            this$0 = StaggeredGridLayoutManager.this;
            super();
            reset();
        }
    }

    public static class LayoutParams extends RecyclerView.LayoutParams
    {

        public static final int INVALID_SPAN_ID = -1;
        boolean mFullSpan;
        Span mSpan;

        public final int getSpanIndex()
        {
            if (mSpan == null)
            {
                return -1;
            } else
            {
                return mSpan.mIndex;
            }
        }

        public boolean isFullSpan()
        {
            return mFullSpan;
        }

        public void setFullSpan(boolean flag)
        {
            mFullSpan = flag;
        }

        public LayoutParams(int i, int j)
        {
            super(i, j);
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }

        public LayoutParams(RecyclerView.LayoutParams layoutparams)
        {
            super(layoutparams);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
        }
    }

    static class LazySpanLookup
    {

        private static final int MIN_SIZE = 10;
        int mData[];
        List mFullSpanItems;

        private int invalidateFullSpansAfter(int i)
        {
            if (mFullSpanItems != null) goto _L2; else goto _L1
_L1:
            return -1;
_L2:
            int j;
            byte byte0;
            int l;
            FullSpanItem fullspanitem = getFullSpanItem(i);
            if (fullspanitem != null)
            {
                mFullSpanItems.remove(fullspanitem);
            }
            byte0 = -1;
            l = mFullSpanItems.size();
            j = 0;
_L4:
            int k;
            k = byte0;
            if (j >= l)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (((FullSpanItem)mFullSpanItems.get(j)).mPosition < i)
            {
                break MISSING_BLOCK_LABEL_117;
            }
            k = j;
            if (k == -1) goto _L1; else goto _L3
_L3:
            FullSpanItem fullspanitem1 = (FullSpanItem)mFullSpanItems.get(k);
            mFullSpanItems.remove(k);
            return fullspanitem1.mPosition;
            j++;
              goto _L4
        }

        private void offsetFullSpansForAddition(int i, int j)
        {
            if (mFullSpanItems != null)
            {
                int k = mFullSpanItems.size() - 1;
                while (k >= 0) 
                {
                    FullSpanItem fullspanitem = (FullSpanItem)mFullSpanItems.get(k);
                    if (fullspanitem.mPosition >= i)
                    {
                        fullspanitem.mPosition = fullspanitem.mPosition + j;
                    }
                    k--;
                }
            }
        }

        private void offsetFullSpansForRemoval(int i, int j)
        {
            if (mFullSpanItems != null)
            {
                int k = mFullSpanItems.size() - 1;
                while (k >= 0) 
                {
                    FullSpanItem fullspanitem = (FullSpanItem)mFullSpanItems.get(k);
                    if (fullspanitem.mPosition >= i)
                    {
                        if (fullspanitem.mPosition < i + j)
                        {
                            mFullSpanItems.remove(k);
                        } else
                        {
                            fullspanitem.mPosition = fullspanitem.mPosition - j;
                        }
                    }
                    k--;
                }
            }
        }

        public void addFullSpanItem(FullSpanItem fullspanitem)
        {
            if (mFullSpanItems == null)
            {
                mFullSpanItems = new ArrayList();
            }
            int j = mFullSpanItems.size();
            for (int i = 0; i < j; i++)
            {
                FullSpanItem fullspanitem1 = (FullSpanItem)mFullSpanItems.get(i);
                if (fullspanitem1.mPosition == fullspanitem.mPosition)
                {
                    mFullSpanItems.remove(i);
                }
                if (fullspanitem1.mPosition >= fullspanitem.mPosition)
                {
                    mFullSpanItems.add(i, fullspanitem);
                    return;
                }
            }

            mFullSpanItems.add(fullspanitem);
        }

        void clear()
        {
            if (mData != null)
            {
                Arrays.fill(mData, -1);
            }
            mFullSpanItems = null;
        }

        void ensureSize(int i)
        {
            if (mData == null)
            {
                mData = new int[Math.max(i, 10) + 1];
                Arrays.fill(mData, -1);
            } else
            if (i >= mData.length)
            {
                int ai[] = mData;
                mData = new int[sizeForPosition(i)];
                System.arraycopy(ai, 0, mData, 0, ai.length);
                Arrays.fill(mData, ai.length, mData.length, -1);
                return;
            }
        }

        int forceInvalidateAfter(int i)
        {
            if (mFullSpanItems != null)
            {
                for (int j = mFullSpanItems.size() - 1; j >= 0; j--)
                {
                    if (((FullSpanItem)mFullSpanItems.get(j)).mPosition >= i)
                    {
                        mFullSpanItems.remove(j);
                    }
                }

            }
            return invalidateAfter(i);
        }

        public FullSpanItem getFirstFullSpanItemInRange(int i, int j, int k, boolean flag)
        {
            if (mFullSpanItems != null) goto _L2; else goto _L1
_L1:
            FullSpanItem fullspanitem = null;
_L6:
            return fullspanitem;
_L2:
            int l;
            int i1;
            i1 = mFullSpanItems.size();
            l = 0;
_L9:
            if (l >= i1) goto _L4; else goto _L3
_L3:
            FullSpanItem fullspanitem1;
            fullspanitem1 = (FullSpanItem)mFullSpanItems.get(l);
            if (fullspanitem1.mPosition >= j)
            {
                return null;
            }
            if (fullspanitem1.mPosition < i)
            {
                continue; /* Loop/switch isn't completed */
            }
            fullspanitem = fullspanitem1;
            if (k == 0) goto _L6; else goto _L5
_L5:
            fullspanitem = fullspanitem1;
            if (fullspanitem1.mGapDir == k) goto _L6; else goto _L7
_L7:
            if (!flag)
            {
                continue; /* Loop/switch isn't completed */
            }
            fullspanitem = fullspanitem1;
            if (fullspanitem1.mHasUnwantedGapAfter) goto _L6; else goto _L8
_L8:
            l++;
              goto _L9
_L4:
            return null;
        }

        public FullSpanItem getFullSpanItem(int i)
        {
            if (mFullSpanItems != null) goto _L2; else goto _L1
_L1:
            FullSpanItem fullspanitem = null;
_L4:
            return fullspanitem;
_L2:
            int j = mFullSpanItems.size() - 1;
label0:
            do
            {
label1:
                {
                    if (j < 0)
                    {
                        break label1;
                    }
                    FullSpanItem fullspanitem1 = (FullSpanItem)mFullSpanItems.get(j);
                    fullspanitem = fullspanitem1;
                    if (fullspanitem1.mPosition == i)
                    {
                        break label0;
                    }
                    j--;
                }
            } while (true);
            if (true) goto _L4; else goto _L3
_L3:
            return null;
        }

        int getSpan(int i)
        {
            if (mData == null || i >= mData.length)
            {
                return -1;
            } else
            {
                return mData[i];
            }
        }

        int invalidateAfter(int i)
        {
            while (mData == null || i >= mData.length) 
            {
                return -1;
            }
            int j = invalidateFullSpansAfter(i);
            if (j == -1)
            {
                Arrays.fill(mData, i, mData.length, -1);
                return mData.length;
            } else
            {
                Arrays.fill(mData, i, j + 1, -1);
                return j + 1;
            }
        }

        void offsetForAddition(int i, int j)
        {
            if (mData == null || i >= mData.length)
            {
                return;
            } else
            {
                ensureSize(i + j);
                System.arraycopy(mData, i, mData, i + j, mData.length - i - j);
                Arrays.fill(mData, i, i + j, -1);
                offsetFullSpansForAddition(i, j);
                return;
            }
        }

        void offsetForRemoval(int i, int j)
        {
            if (mData == null || i >= mData.length)
            {
                return;
            } else
            {
                ensureSize(i + j);
                System.arraycopy(mData, i + j, mData, i, mData.length - i - j);
                Arrays.fill(mData, mData.length - j, mData.length, -1);
                offsetFullSpansForRemoval(i, j);
                return;
            }
        }

        void setSpan(int i, Span span)
        {
            ensureSize(i);
            mData[i] = span.mIndex;
        }

        int sizeForPosition(int i)
        {
            int j;
            for (j = mData.length; j <= i; j *= 2) { }
            return j;
        }

        LazySpanLookup()
        {
        }
    }

    static class LazySpanLookup.FullSpanItem
        implements Parcelable
    {

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public LazySpanLookup.FullSpanItem createFromParcel(Parcel parcel)
            {
                return new LazySpanLookup.FullSpanItem(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public LazySpanLookup.FullSpanItem[] newArray(int i)
            {
                return new LazySpanLookup.FullSpanItem[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        };
        int mGapDir;
        int mGapPerSpan[];
        boolean mHasUnwantedGapAfter;
        int mPosition;

        public int describeContents()
        {
            return 0;
        }

        int getGapForSpan(int i)
        {
            if (mGapPerSpan == null)
            {
                return 0;
            } else
            {
                return mGapPerSpan[i];
            }
        }

        public String toString()
        {
            return (new StringBuilder()).append("FullSpanItem{mPosition=").append(mPosition).append(", mGapDir=").append(mGapDir).append(", mHasUnwantedGapAfter=").append(mHasUnwantedGapAfter).append(", mGapPerSpan=").append(Arrays.toString(mGapPerSpan)).append('}').toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mPosition);
            parcel.writeInt(mGapDir);
            if (mHasUnwantedGapAfter)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            parcel.writeInt(i);
            if (mGapPerSpan != null && mGapPerSpan.length > 0)
            {
                parcel.writeInt(mGapPerSpan.length);
                parcel.writeIntArray(mGapPerSpan);
                return;
            } else
            {
                parcel.writeInt(0);
                return;
            }
        }


        LazySpanLookup.FullSpanItem()
        {
        }

        LazySpanLookup.FullSpanItem(Parcel parcel)
        {
            boolean flag = true;
            super();
            mPosition = parcel.readInt();
            mGapDir = parcel.readInt();
            int i;
            if (parcel.readInt() != 1)
            {
                flag = false;
            }
            mHasUnwantedGapAfter = flag;
            i = parcel.readInt();
            if (i > 0)
            {
                mGapPerSpan = new int[i];
                parcel.readIntArray(mGapPerSpan);
            }
        }
    }

    public static class SavedState
        implements Parcelable
    {

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        };
        boolean mAnchorLayoutFromEnd;
        int mAnchorPosition;
        List mFullSpanItems;
        boolean mLastLayoutRTL;
        boolean mReverseLayout;
        int mSpanLookup[];
        int mSpanLookupSize;
        int mSpanOffsets[];
        int mSpanOffsetsSize;
        int mVisibleAnchorPosition;

        public int describeContents()
        {
            return 0;
        }

        void invalidateAnchorPositionInfo()
        {
            mSpanOffsets = null;
            mSpanOffsetsSize = 0;
            mAnchorPosition = -1;
            mVisibleAnchorPosition = -1;
        }

        void invalidateSpanInfo()
        {
            mSpanOffsets = null;
            mSpanOffsetsSize = 0;
            mSpanLookupSize = 0;
            mSpanLookup = null;
            mFullSpanItems = null;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            boolean flag = true;
            parcel.writeInt(mAnchorPosition);
            parcel.writeInt(mVisibleAnchorPosition);
            parcel.writeInt(mSpanOffsetsSize);
            if (mSpanOffsetsSize > 0)
            {
                parcel.writeIntArray(mSpanOffsets);
            }
            parcel.writeInt(mSpanLookupSize);
            if (mSpanLookupSize > 0)
            {
                parcel.writeIntArray(mSpanLookup);
            }
            if (mReverseLayout)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            parcel.writeInt(i);
            if (mAnchorLayoutFromEnd)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            parcel.writeInt(i);
            if (mLastLayoutRTL)
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 0;
            }
            parcel.writeInt(i);
            parcel.writeList(mFullSpanItems);
        }


        public SavedState()
        {
        }

        SavedState(Parcel parcel)
        {
            boolean flag1 = true;
            super();
            mAnchorPosition = parcel.readInt();
            mVisibleAnchorPosition = parcel.readInt();
            mSpanOffsetsSize = parcel.readInt();
            if (mSpanOffsetsSize > 0)
            {
                mSpanOffsets = new int[mSpanOffsetsSize];
                parcel.readIntArray(mSpanOffsets);
            }
            mSpanLookupSize = parcel.readInt();
            if (mSpanLookupSize > 0)
            {
                mSpanLookup = new int[mSpanLookupSize];
                parcel.readIntArray(mSpanLookup);
            }
            boolean flag;
            if (parcel.readInt() == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            mReverseLayout = flag;
            if (parcel.readInt() == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            mAnchorLayoutFromEnd = flag;
            if (parcel.readInt() == 1)
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
            mLastLayoutRTL = flag;
            mFullSpanItems = parcel.readArrayList(android/support/v7/widget/StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem.getClassLoader());
        }

        public SavedState(SavedState savedstate)
        {
            mSpanOffsetsSize = savedstate.mSpanOffsetsSize;
            mAnchorPosition = savedstate.mAnchorPosition;
            mVisibleAnchorPosition = savedstate.mVisibleAnchorPosition;
            mSpanOffsets = savedstate.mSpanOffsets;
            mSpanLookupSize = savedstate.mSpanLookupSize;
            mSpanLookup = savedstate.mSpanLookup;
            mReverseLayout = savedstate.mReverseLayout;
            mAnchorLayoutFromEnd = savedstate.mAnchorLayoutFromEnd;
            mLastLayoutRTL = savedstate.mLastLayoutRTL;
            mFullSpanItems = savedstate.mFullSpanItems;
        }
    }

    class Span
    {

        static final int INVALID_LINE = 0x80000000;
        int mCachedEnd;
        int mCachedStart;
        int mDeletedSize;
        final int mIndex;
        ArrayList mViews;
        final StaggeredGridLayoutManager this$0;

        void appendToSpan(View view)
        {
            LayoutParams layoutparams = getLayoutParams(view);
            layoutparams.mSpan = this;
            mViews.add(view);
            mCachedEnd = 0x80000000;
            if (mViews.size() == 1)
            {
                mCachedStart = 0x80000000;
            }
            if (layoutparams.isItemRemoved() || layoutparams.isItemChanged())
            {
                mDeletedSize = mDeletedSize + mPrimaryOrientation.getDecoratedMeasurement(view);
            }
        }

        void cacheReferenceLineAndClear(boolean flag, int i)
        {
            int j;
            if (flag)
            {
                j = getEndLine(0x80000000);
            } else
            {
                j = getStartLine(0x80000000);
            }
            clear();
            while (j == 0x80000000 || flag && j < mPrimaryOrientation.getEndAfterPadding() || !flag && j > mPrimaryOrientation.getStartAfterPadding()) 
            {
                return;
            }
            int k = j;
            if (i != 0x80000000)
            {
                k = j + i;
            }
            mCachedEnd = k;
            mCachedStart = k;
        }

        void calculateCachedEnd()
        {
            View view = (View)mViews.get(mViews.size() - 1);
            LayoutParams layoutparams = getLayoutParams(view);
            mCachedEnd = mPrimaryOrientation.getDecoratedEnd(view);
            if (layoutparams.mFullSpan)
            {
                LazySpanLookup.FullSpanItem fullspanitem = mLazySpanLookup.getFullSpanItem(layoutparams.getViewLayoutPosition());
                if (fullspanitem != null && fullspanitem.mGapDir == 1)
                {
                    mCachedEnd = mCachedEnd + fullspanitem.getGapForSpan(mIndex);
                }
            }
        }

        void calculateCachedStart()
        {
            View view = (View)mViews.get(0);
            LayoutParams layoutparams = getLayoutParams(view);
            mCachedStart = mPrimaryOrientation.getDecoratedStart(view);
            if (layoutparams.mFullSpan)
            {
                LazySpanLookup.FullSpanItem fullspanitem = mLazySpanLookup.getFullSpanItem(layoutparams.getViewLayoutPosition());
                if (fullspanitem != null && fullspanitem.mGapDir == -1)
                {
                    mCachedStart = mCachedStart - fullspanitem.getGapForSpan(mIndex);
                }
            }
        }

        void clear()
        {
            mViews.clear();
            invalidateCache();
            mDeletedSize = 0;
        }

        public int findFirstCompletelyVisibleItemPosition()
        {
            if (mReverseLayout)
            {
                return findOneVisibleChild(mViews.size() - 1, -1, true);
            } else
            {
                return findOneVisibleChild(0, mViews.size(), true);
            }
        }

        public int findFirstPartiallyVisibleItemPosition()
        {
            if (mReverseLayout)
            {
                return findOnePartiallyVisibleChild(mViews.size() - 1, -1, true);
            } else
            {
                return findOnePartiallyVisibleChild(0, mViews.size(), true);
            }
        }

        public int findFirstVisibleItemPosition()
        {
            if (mReverseLayout)
            {
                return findOneVisibleChild(mViews.size() - 1, -1, false);
            } else
            {
                return findOneVisibleChild(0, mViews.size(), false);
            }
        }

        public int findLastCompletelyVisibleItemPosition()
        {
            if (mReverseLayout)
            {
                return findOneVisibleChild(0, mViews.size(), true);
            } else
            {
                return findOneVisibleChild(mViews.size() - 1, -1, true);
            }
        }

        public int findLastPartiallyVisibleItemPosition()
        {
            if (mReverseLayout)
            {
                return findOnePartiallyVisibleChild(0, mViews.size(), true);
            } else
            {
                return findOnePartiallyVisibleChild(mViews.size() - 1, -1, true);
            }
        }

        public int findLastVisibleItemPosition()
        {
            if (mReverseLayout)
            {
                return findOneVisibleChild(0, mViews.size(), false);
            } else
            {
                return findOneVisibleChild(mViews.size() - 1, -1, false);
            }
        }

        int findOnePartiallyOrCompletelyVisibleChild(int i, int j, boolean flag, boolean flag1, boolean flag2)
        {
            int l = mPrimaryOrientation.getStartAfterPadding();
            int i1 = mPrimaryOrientation.getEndAfterPadding();
            byte byte0;
            int k;
            if (j > i)
            {
                byte0 = 1;
            } else
            {
                byte0 = -1;
            }
            for (k = i; k != j; k += byte0)
            {
                View view = (View)mViews.get(k);
                int j1 = mPrimaryOrientation.getDecoratedStart(view);
                int k1 = mPrimaryOrientation.getDecoratedEnd(view);
                boolean flag3;
                if (flag2)
                {
                    if (j1 <= i1)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                } else
                if (j1 < i1)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (flag2)
                {
                    if (k1 >= l)
                    {
                        flag3 = true;
                    } else
                    {
                        flag3 = false;
                    }
                } else
                if (k1 > l)
                {
                    flag3 = true;
                } else
                {
                    flag3 = false;
                }
                if (i == 0 || !flag3)
                {
                    continue;
                }
                if (flag && flag1)
                {
                    if (j1 >= l && k1 <= i1)
                    {
                        return getPosition(view);
                    }
                    continue;
                }
                if (flag1)
                {
                    return getPosition(view);
                }
                if (j1 < l || k1 > i1)
                {
                    return getPosition(view);
                }
            }

            return -1;
        }

        int findOnePartiallyVisibleChild(int i, int j, boolean flag)
        {
            return findOnePartiallyOrCompletelyVisibleChild(i, j, false, false, flag);
        }

        int findOneVisibleChild(int i, int j, boolean flag)
        {
            return findOnePartiallyOrCompletelyVisibleChild(i, j, flag, true, false);
        }

        public int getDeletedSize()
        {
            return mDeletedSize;
        }

        int getEndLine()
        {
            if (mCachedEnd != 0x80000000)
            {
                return mCachedEnd;
            } else
            {
                calculateCachedEnd();
                return mCachedEnd;
            }
        }

        int getEndLine(int i)
        {
            if (mCachedEnd != 0x80000000)
            {
                i = mCachedEnd;
            } else
            if (mViews.size() != 0)
            {
                calculateCachedEnd();
                return mCachedEnd;
            }
            return i;
        }

        public View getFocusableViewAfter(int i, int j)
        {
            View view;
            View view1;
            view1 = null;
            view = null;
            if (j != -1) goto _L2; else goto _L1
_L1:
            int k;
            k = mViews.size();
            j = 0;
_L10:
            view1 = view;
            if (j >= k) goto _L4; else goto _L3
_L3:
            View view2 = (View)mViews.get(j);
            if (!mReverseLayout) goto _L6; else goto _L5
_L5:
            view1 = view;
            if (getPosition(view2) <= i) goto _L4; else goto _L6
_L6:
            if (mReverseLayout || getPosition(view2) < i) goto _L8; else goto _L7
_L7:
            view1 = view;
_L4:
            return view1;
_L8:
            view1 = view;
            if (!view2.hasFocusable()) goto _L4; else goto _L9
_L9:
            view = view2;
            j++;
              goto _L10
_L2:
            j = mViews.size() - 1;
            view = view1;
_L15:
            view1 = view;
            if (j < 0) goto _L4; else goto _L11
_L11:
            view2 = (View)mViews.get(j);
            if (!mReverseLayout)
            {
                break; /* Loop/switch isn't completed */
            }
            view1 = view;
            if (getPosition(view2) >= i) goto _L4; else goto _L12
_L12:
            if (mReverseLayout)
            {
                break; /* Loop/switch isn't completed */
            }
            view1 = view;
            if (getPosition(view2) <= i) goto _L4; else goto _L13
_L13:
            view1 = view;
            if (!view2.hasFocusable()) goto _L4; else goto _L14
_L14:
            view = view2;
            j--;
              goto _L15
        }

        LayoutParams getLayoutParams(View view)
        {
            return (LayoutParams)view.getLayoutParams();
        }

        int getStartLine()
        {
            if (mCachedStart != 0x80000000)
            {
                return mCachedStart;
            } else
            {
                calculateCachedStart();
                return mCachedStart;
            }
        }

        int getStartLine(int i)
        {
            if (mCachedStart != 0x80000000)
            {
                i = mCachedStart;
            } else
            if (mViews.size() != 0)
            {
                calculateCachedStart();
                return mCachedStart;
            }
            return i;
        }

        void invalidateCache()
        {
            mCachedStart = 0x80000000;
            mCachedEnd = 0x80000000;
        }

        void onOffset(int i)
        {
            if (mCachedStart != 0x80000000)
            {
                mCachedStart = mCachedStart + i;
            }
            if (mCachedEnd != 0x80000000)
            {
                mCachedEnd = mCachedEnd + i;
            }
        }

        void popEnd()
        {
            int i = mViews.size();
            View view = (View)mViews.remove(i - 1);
            LayoutParams layoutparams = getLayoutParams(view);
            layoutparams.mSpan = null;
            if (layoutparams.isItemRemoved() || layoutparams.isItemChanged())
            {
                mDeletedSize = mDeletedSize - mPrimaryOrientation.getDecoratedMeasurement(view);
            }
            if (i == 1)
            {
                mCachedStart = 0x80000000;
            }
            mCachedEnd = 0x80000000;
        }

        void popStart()
        {
            View view = (View)mViews.remove(0);
            LayoutParams layoutparams = getLayoutParams(view);
            layoutparams.mSpan = null;
            if (mViews.size() == 0)
            {
                mCachedEnd = 0x80000000;
            }
            if (layoutparams.isItemRemoved() || layoutparams.isItemChanged())
            {
                mDeletedSize = mDeletedSize - mPrimaryOrientation.getDecoratedMeasurement(view);
            }
            mCachedStart = 0x80000000;
        }

        void prependToSpan(View view)
        {
            LayoutParams layoutparams = getLayoutParams(view);
            layoutparams.mSpan = this;
            mViews.add(0, view);
            mCachedStart = 0x80000000;
            if (mViews.size() == 1)
            {
                mCachedEnd = 0x80000000;
            }
            if (layoutparams.isItemRemoved() || layoutparams.isItemChanged())
            {
                mDeletedSize = mDeletedSize + mPrimaryOrientation.getDecoratedMeasurement(view);
            }
        }

        void setLine(int i)
        {
            mCachedStart = i;
            mCachedEnd = i;
        }

        Span(int i)
        {
            this$0 = StaggeredGridLayoutManager.this;
            super();
            mViews = new ArrayList();
            mCachedStart = 0x80000000;
            mCachedEnd = 0x80000000;
            mDeletedSize = 0;
            mIndex = i;
        }
    }


    static final boolean DEBUG = false;
    public static final int GAP_HANDLING_LAZY = 1;
    public static final int GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS = 2;
    public static final int GAP_HANDLING_NONE = 0;
    public static final int HORIZONTAL = 0;
    static final int INVALID_OFFSET = 0x80000000;
    private static final float MAX_SCROLL_FACTOR = 0.3333333F;
    private static final String TAG = "StaggeredGridLayoutManager";
    public static final int VERTICAL = 1;
    private final AnchorInfo mAnchorInfo;
    private final Runnable mCheckForGapsRunnable;
    private int mFullSizeSpec;
    private int mGapStrategy;
    private boolean mLaidOutInvalidFullSpan;
    private boolean mLastLayoutFromEnd;
    private boolean mLastLayoutRTL;
    private final LayoutState mLayoutState;
    LazySpanLookup mLazySpanLookup;
    private int mOrientation;
    private SavedState mPendingSavedState;
    int mPendingScrollPosition;
    int mPendingScrollPositionOffset;
    private int mPrefetchDistances[];
    OrientationHelper mPrimaryOrientation;
    private BitSet mRemainingSpans;
    boolean mReverseLayout;
    OrientationHelper mSecondaryOrientation;
    boolean mShouldReverseLayout;
    private int mSizePerSpan;
    private boolean mSmoothScrollbarEnabled;
    private int mSpanCount;
    Span mSpans[];
    private final Rect mTmpRect;

    public StaggeredGridLayoutManager(int i, int j)
    {
        boolean flag = true;
        super();
        mSpanCount = -1;
        mReverseLayout = false;
        mShouldReverseLayout = false;
        mPendingScrollPosition = -1;
        mPendingScrollPositionOffset = 0x80000000;
        mLazySpanLookup = new LazySpanLookup();
        mGapStrategy = 2;
        mTmpRect = new Rect();
        mAnchorInfo = new AnchorInfo();
        mLaidOutInvalidFullSpan = false;
        mSmoothScrollbarEnabled = true;
        mCheckForGapsRunnable = new Runnable() {

            final StaggeredGridLayoutManager this$0;

            public void run()
            {
                checkForGaps();
            }

            
            {
                this$0 = StaggeredGridLayoutManager.this;
                super();
            }
        };
        mOrientation = j;
        setSpanCount(i);
        if (mGapStrategy == 0)
        {
            flag = false;
        }
        setAutoMeasureEnabled(flag);
        mLayoutState = new LayoutState();
        createOrientationHelpers();
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeset, int i, int j)
    {
        boolean flag = true;
        super();
        mSpanCount = -1;
        mReverseLayout = false;
        mShouldReverseLayout = false;
        mPendingScrollPosition = -1;
        mPendingScrollPositionOffset = 0x80000000;
        mLazySpanLookup = new LazySpanLookup();
        mGapStrategy = 2;
        mTmpRect = new Rect();
        mAnchorInfo = new AnchorInfo();
        mLaidOutInvalidFullSpan = false;
        mSmoothScrollbarEnabled = true;
        mCheckForGapsRunnable = new _cls1();
        context = getProperties(context, attributeset, i, j);
        setOrientation(((RecyclerView.LayoutManager.Properties) (context)).orientation);
        setSpanCount(((RecyclerView.LayoutManager.Properties) (context)).spanCount);
        setReverseLayout(((RecyclerView.LayoutManager.Properties) (context)).reverseLayout);
        if (mGapStrategy == 0)
        {
            flag = false;
        }
        setAutoMeasureEnabled(flag);
        mLayoutState = new LayoutState();
        createOrientationHelpers();
    }

    private void appendViewToAllSpans(View view)
    {
        for (int i = mSpanCount - 1; i >= 0; i--)
        {
            mSpans[i].appendToSpan(view);
        }

    }

    private void applyPendingSavedState(AnchorInfo anchorinfo)
    {
        if (mPendingSavedState.mSpanOffsetsSize > 0)
        {
            if (mPendingSavedState.mSpanOffsetsSize == mSpanCount)
            {
                int j = 0;
                while (j < mSpanCount) 
                {
                    mSpans[j].clear();
                    int k = mPendingSavedState.mSpanOffsets[j];
                    int i = k;
                    if (k != 0x80000000)
                    {
                        if (mPendingSavedState.mAnchorLayoutFromEnd)
                        {
                            i = k + mPrimaryOrientation.getEndAfterPadding();
                        } else
                        {
                            i = k + mPrimaryOrientation.getStartAfterPadding();
                        }
                    }
                    mSpans[j].setLine(i);
                    j++;
                }
            } else
            {
                mPendingSavedState.invalidateSpanInfo();
                mPendingSavedState.mAnchorPosition = mPendingSavedState.mVisibleAnchorPosition;
            }
        }
        mLastLayoutRTL = mPendingSavedState.mLastLayoutRTL;
        setReverseLayout(mPendingSavedState.mReverseLayout);
        resolveShouldLayoutReverse();
        if (mPendingSavedState.mAnchorPosition != -1)
        {
            mPendingScrollPosition = mPendingSavedState.mAnchorPosition;
            anchorinfo.mLayoutFromEnd = mPendingSavedState.mAnchorLayoutFromEnd;
        } else
        {
            anchorinfo.mLayoutFromEnd = mShouldReverseLayout;
        }
        if (mPendingSavedState.mSpanLookupSize > 1)
        {
            mLazySpanLookup.mData = mPendingSavedState.mSpanLookup;
            mLazySpanLookup.mFullSpanItems = mPendingSavedState.mFullSpanItems;
        }
    }

    private void attachViewToSpans(View view, LayoutParams layoutparams, LayoutState layoutstate)
    {
        if (layoutstate.mLayoutDirection == 1)
        {
            if (layoutparams.mFullSpan)
            {
                appendViewToAllSpans(view);
                return;
            } else
            {
                layoutparams.mSpan.appendToSpan(view);
                return;
            }
        }
        if (layoutparams.mFullSpan)
        {
            prependViewToAllSpans(view);
            return;
        } else
        {
            layoutparams.mSpan.prependToSpan(view);
            return;
        }
    }

    private int calculateScrollDirectionForPosition(int i)
    {
        byte byte0 = -1;
        if (getChildCount() == 0)
        {
            return !mShouldReverseLayout ? -1 : 1;
        }
        boolean flag;
        if (i < getFirstChildPosition())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag != mShouldReverseLayout)
        {
            i = byte0;
        } else
        {
            i = 1;
        }
        return i;
    }

    private boolean checkSpanForGap(Span span)
    {
        if (!mShouldReverseLayout) goto _L2; else goto _L1
_L1:
        if (span.getEndLine() >= mPrimaryOrientation.getEndAfterPadding())
        {
            break MISSING_BLOCK_LABEL_91;
        }
        if (span.getLayoutParams((View)span.mViews.get(span.mViews.size() - 1)).mFullSpan) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if (span.getStartLine() <= mPrimaryOrientation.getStartAfterPadding())
        {
            break MISSING_BLOCK_LABEL_91;
        }
        if (!span.getLayoutParams((View)span.mViews.get(0)).mFullSpan) goto _L3; else goto _L5
_L5:
        return false;
        return false;
    }

    private int computeScrollExtent(RecyclerView.State state)
    {
        boolean flag1 = true;
        if (getChildCount() == 0)
        {
            return 0;
        }
        OrientationHelper orientationhelper = mPrimaryOrientation;
        View view;
        boolean flag;
        if (!mSmoothScrollbarEnabled)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        view = findFirstVisibleItemClosestToStart(flag);
        if (!mSmoothScrollbarEnabled)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        return ScrollbarHelper.computeScrollExtent(state, orientationhelper, view, findFirstVisibleItemClosestToEnd(flag), this, mSmoothScrollbarEnabled);
    }

    private int computeScrollOffset(RecyclerView.State state)
    {
        boolean flag1 = true;
        if (getChildCount() == 0)
        {
            return 0;
        }
        OrientationHelper orientationhelper = mPrimaryOrientation;
        View view;
        boolean flag;
        if (!mSmoothScrollbarEnabled)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        view = findFirstVisibleItemClosestToStart(flag);
        if (!mSmoothScrollbarEnabled)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        return ScrollbarHelper.computeScrollOffset(state, orientationhelper, view, findFirstVisibleItemClosestToEnd(flag), this, mSmoothScrollbarEnabled, mShouldReverseLayout);
    }

    private int computeScrollRange(RecyclerView.State state)
    {
        boolean flag1 = true;
        if (getChildCount() == 0)
        {
            return 0;
        }
        OrientationHelper orientationhelper = mPrimaryOrientation;
        View view;
        boolean flag;
        if (!mSmoothScrollbarEnabled)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        view = findFirstVisibleItemClosestToStart(flag);
        if (!mSmoothScrollbarEnabled)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        return ScrollbarHelper.computeScrollRange(state, orientationhelper, view, findFirstVisibleItemClosestToEnd(flag), this, mSmoothScrollbarEnabled);
    }

    private int convertFocusDirectionToLayoutDirection(int i)
    {
        byte byte0;
        int j;
        boolean flag;
        byte0 = -1;
        j = 0x80000000;
        flag = true;
        i;
        JVM INSTR lookupswitch 6: default 68
    //                   1: 73
    //                   2: 94
    //                   17: 142
    //                   33: 115
    //                   66: 154
    //                   130: 128;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        i = 0x80000000;
_L9:
        return i;
_L2:
        i = byte0;
        if (mOrientation == 1) goto _L9; else goto _L8
_L8:
        i = byte0;
        if (!isLayoutRTL()) goto _L9; else goto _L10
_L10:
        return 1;
_L3:
        if (mOrientation == 1)
        {
            return 1;
        }
        i = byte0;
        if (isLayoutRTL()) goto _L9; else goto _L11
_L11:
        return 1;
_L5:
        i = byte0;
        if (mOrientation == 1) goto _L9; else goto _L12
_L12:
        return 0x80000000;
_L7:
        i = j;
        if (mOrientation == 1)
        {
            i = 1;
        }
        return i;
_L4:
        i = byte0;
        if (mOrientation == 0) goto _L9; else goto _L13
_L13:
        return 0x80000000;
_L6:
        if (mOrientation == 0)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0x80000000;
        }
        return i;
    }

    private LazySpanLookup.FullSpanItem createFullSpanItemFromEnd(int i)
    {
        LazySpanLookup.FullSpanItem fullspanitem = new LazySpanLookup.FullSpanItem();
        fullspanitem.mGapPerSpan = new int[mSpanCount];
        for (int j = 0; j < mSpanCount; j++)
        {
            fullspanitem.mGapPerSpan[j] = i - mSpans[j].getEndLine(i);
        }

        return fullspanitem;
    }

    private LazySpanLookup.FullSpanItem createFullSpanItemFromStart(int i)
    {
        LazySpanLookup.FullSpanItem fullspanitem = new LazySpanLookup.FullSpanItem();
        fullspanitem.mGapPerSpan = new int[mSpanCount];
        for (int j = 0; j < mSpanCount; j++)
        {
            fullspanitem.mGapPerSpan[j] = mSpans[j].getStartLine(i) - i;
        }

        return fullspanitem;
    }

    private void createOrientationHelpers()
    {
        mPrimaryOrientation = OrientationHelper.createOrientationHelper(this, mOrientation);
        mSecondaryOrientation = OrientationHelper.createOrientationHelper(this, 1 - mOrientation);
    }

    private int fill(RecyclerView.Recycler recycler, LayoutState layoutstate, RecyclerView.State state)
    {
        mRemainingSpans.set(0, mSpanCount, true);
        int i;
        int j;
        int k;
        if (mLayoutState.mInfinite)
        {
            if (layoutstate.mLayoutDirection == 1)
            {
                i = 0x7fffffff;
            } else
            {
                i = 0x80000000;
            }
        } else
        if (layoutstate.mLayoutDirection == 1)
        {
            i = layoutstate.mEndLine + layoutstate.mAvailable;
        } else
        {
            i = layoutstate.mStartLine - layoutstate.mAvailable;
        }
        updateAllRemainingSpans(layoutstate.mLayoutDirection, i);
        if (mShouldReverseLayout)
        {
            k = mPrimaryOrientation.getEndAfterPadding();
        } else
        {
            k = mPrimaryOrientation.getStartAfterPadding();
        }
        j = 0;
        do
        {
            if (layoutstate.hasMore(state) && (mLayoutState.mInfinite || !mRemainingSpans.isEmpty()))
            {
                View view = layoutstate.next(recycler);
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                int i2 = layoutparams.getViewLayoutPosition();
                j = mLazySpanLookup.getSpan(i2);
                Span span;
                int l;
                int i1;
                int j1;
                if (j == -1)
                {
                    j1 = 1;
                } else
                {
                    j1 = 0;
                }
                if (j1 != 0)
                {
                    LazySpanLookup.FullSpanItem fullspanitem;
                    int k1;
                    if (layoutparams.mFullSpan)
                    {
                        span = mSpans[0];
                    } else
                    {
                        span = getNextSpan(layoutstate);
                    }
                    mLazySpanLookup.setSpan(i2, span);
                } else
                {
                    span = mSpans[j];
                }
                layoutparams.mSpan = span;
                if (layoutstate.mLayoutDirection == 1)
                {
                    addView(view);
                } else
                {
                    addView(view, 0);
                }
                measureChildWithDecorationsAndMargin(view, layoutparams, false);
                if (layoutstate.mLayoutDirection == 1)
                {
                    if (layoutparams.mFullSpan)
                    {
                        j = getMaxEnd(k);
                    } else
                    {
                        j = span.getEndLine(k);
                    }
                    k1 = j + mPrimaryOrientation.getDecoratedMeasurement(view);
                    l = j;
                    i1 = k1;
                    if (j1 != 0)
                    {
                        l = j;
                        i1 = k1;
                        if (layoutparams.mFullSpan)
                        {
                            fullspanitem = createFullSpanItemFromEnd(j);
                            fullspanitem.mGapDir = -1;
                            fullspanitem.mPosition = i2;
                            mLazySpanLookup.addFullSpanItem(fullspanitem);
                            i1 = k1;
                            l = j;
                        }
                    }
                } else
                {
                    int l1;
                    if (layoutparams.mFullSpan)
                    {
                        j = getMinStart(k);
                    } else
                    {
                        j = span.getStartLine(k);
                    }
                    l1 = j - mPrimaryOrientation.getDecoratedMeasurement(view);
                    l = l1;
                    i1 = j;
                    if (j1 != 0)
                    {
                        l = l1;
                        i1 = j;
                        if (layoutparams.mFullSpan)
                        {
                            LazySpanLookup.FullSpanItem fullspanitem1 = createFullSpanItemFromStart(j);
                            fullspanitem1.mGapDir = 1;
                            fullspanitem1.mPosition = i2;
                            mLazySpanLookup.addFullSpanItem(fullspanitem1);
                            l = l1;
                            i1 = j;
                        }
                    }
                }
                if (layoutparams.mFullSpan && layoutstate.mItemDirection == -1)
                {
                    if (j1 != 0)
                    {
                        mLaidOutInvalidFullSpan = true;
                    } else
                    {
                        if (layoutstate.mLayoutDirection == 1)
                        {
                            if (!areAllEndsEqual())
                            {
                                j = 1;
                            } else
                            {
                                j = 0;
                            }
                        } else
                        if (!areAllStartsEqual())
                        {
                            j = 1;
                        } else
                        {
                            j = 0;
                        }
                        if (j != 0)
                        {
                            LazySpanLookup.FullSpanItem fullspanitem2 = mLazySpanLookup.getFullSpanItem(i2);
                            if (fullspanitem2 != null)
                            {
                                fullspanitem2.mHasUnwantedGapAfter = true;
                            }
                            mLaidOutInvalidFullSpan = true;
                        }
                    }
                }
                attachViewToSpans(view, layoutparams, layoutstate);
                if (isLayoutRTL() && mOrientation == 1)
                {
                    if (layoutparams.mFullSpan)
                    {
                        j = mSecondaryOrientation.getEndAfterPadding();
                    } else
                    {
                        j = mSecondaryOrientation.getEndAfterPadding() - (mSpanCount - 1 - span.mIndex) * mSizePerSpan;
                    }
                    k1 = j - mSecondaryOrientation.getDecoratedMeasurement(view);
                    j1 = j;
                    j = k1;
                } else
                {
                    if (layoutparams.mFullSpan)
                    {
                        j = mSecondaryOrientation.getStartAfterPadding();
                    } else
                    {
                        j = span.mIndex * mSizePerSpan + mSecondaryOrientation.getStartAfterPadding();
                    }
                    j1 = j + mSecondaryOrientation.getDecoratedMeasurement(view);
                }
                if (mOrientation == 1)
                {
                    layoutDecoratedWithMargins(view, j, l, j1, i1);
                } else
                {
                    layoutDecoratedWithMargins(view, l, j, i1, j1);
                }
                if (layoutparams.mFullSpan)
                {
                    updateAllRemainingSpans(mLayoutState.mLayoutDirection, i);
                } else
                {
                    updateRemainingSpans(span, mLayoutState.mLayoutDirection, i);
                }
                recycle(recycler, mLayoutState);
                if (mLayoutState.mStopInFocusable && view.hasFocusable())
                {
                    if (layoutparams.mFullSpan)
                    {
                        mRemainingSpans.clear();
                    } else
                    {
                        mRemainingSpans.set(span.mIndex, false);
                    }
                }
                j = 1;
                continue;
            }
            if (j == 0)
            {
                recycle(recycler, mLayoutState);
            }
            if (mLayoutState.mLayoutDirection == -1)
            {
                i = getMinStart(mPrimaryOrientation.getStartAfterPadding());
                i = mPrimaryOrientation.getStartAfterPadding() - i;
            } else
            {
                i = getMaxEnd(mPrimaryOrientation.getEndAfterPadding()) - mPrimaryOrientation.getEndAfterPadding();
            }
            if (i > 0)
            {
                return Math.min(layoutstate.mAvailable, i);
            }
            return 0;
        } while (true);
    }

    private int findFirstReferenceChildPosition(int i)
    {
        int k = getChildCount();
        for (int j = 0; j < k; j++)
        {
            int l = getPosition(getChildAt(j));
            if (l >= 0 && l < i)
            {
                return l;
            }
        }

        return 0;
    }

    private int findLastReferenceChildPosition(int i)
    {
        for (int j = getChildCount() - 1; j >= 0; j--)
        {
            int k = getPosition(getChildAt(j));
            if (k >= 0 && k < i)
            {
                return k;
            }
        }

        return 0;
    }

    private void fixEndGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean flag)
    {
        int i = getMaxEnd(0x80000000);
        if (i != 0x80000000)
        {
            if ((i = mPrimaryOrientation.getEndAfterPadding() - i) > 0)
            {
                i -= -scrollBy(-i, recycler, state);
                if (flag && i > 0)
                {
                    mPrimaryOrientation.offsetChildren(i);
                    return;
                }
            }
        }
    }

    private void fixStartGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean flag)
    {
        int i = getMinStart(0x7fffffff);
        if (i != 0x7fffffff)
        {
            if ((i -= mPrimaryOrientation.getStartAfterPadding()) > 0)
            {
                i -= scrollBy(i, recycler, state);
                if (flag && i > 0)
                {
                    mPrimaryOrientation.offsetChildren(-i);
                    return;
                }
            }
        }
    }

    private int getMaxEnd(int i)
    {
        int k = mSpans[0].getEndLine(i);
        for (int j = 1; j < mSpanCount;)
        {
            int i1 = mSpans[j].getEndLine(i);
            int l = k;
            if (i1 > k)
            {
                l = i1;
            }
            j++;
            k = l;
        }

        return k;
    }

    private int getMaxStart(int i)
    {
        int k = mSpans[0].getStartLine(i);
        for (int j = 1; j < mSpanCount;)
        {
            int i1 = mSpans[j].getStartLine(i);
            int l = k;
            if (i1 > k)
            {
                l = i1;
            }
            j++;
            k = l;
        }

        return k;
    }

    private int getMinEnd(int i)
    {
        int k = mSpans[0].getEndLine(i);
        for (int j = 1; j < mSpanCount;)
        {
            int i1 = mSpans[j].getEndLine(i);
            int l = k;
            if (i1 < k)
            {
                l = i1;
            }
            j++;
            k = l;
        }

        return k;
    }

    private int getMinStart(int i)
    {
        int k = mSpans[0].getStartLine(i);
        for (int j = 1; j < mSpanCount;)
        {
            int i1 = mSpans[j].getStartLine(i);
            int l = k;
            if (i1 < k)
            {
                l = i1;
            }
            j++;
            k = l;
        }

        return k;
    }

    private Span getNextSpan(LayoutState layoutstate)
    {
        Object obj;
        int i;
        byte byte0;
        int j;
        if (preferLastSpan(layoutstate.mLayoutDirection))
        {
            i = mSpanCount - 1;
            j = -1;
            byte0 = -1;
        } else
        {
            i = 0;
            j = mSpanCount;
            byte0 = 1;
        }
        if (layoutstate.mLayoutDirection == 1)
        {
            layoutstate = null;
            int k = 0x7fffffff;
            int i2 = mPrimaryOrientation.getStartAfterPadding();
            do
            {
                obj = layoutstate;
                if (i == j)
                {
                    break;
                }
                obj = mSpans[i];
                int k1 = ((Span) (obj)).getEndLine(i2);
                int i1 = k;
                if (k1 < k)
                {
                    layoutstate = ((LayoutState) (obj));
                    i1 = k1;
                }
                i += byte0;
                k = i1;
            } while (true);
        } else
        {
            layoutstate = null;
            int l = 0x80000000;
            int j2 = mPrimaryOrientation.getEndAfterPadding();
            while (i != j) 
            {
                obj = mSpans[i];
                int l1 = ((Span) (obj)).getStartLine(j2);
                int j1 = l;
                if (l1 > l)
                {
                    layoutstate = ((LayoutState) (obj));
                    j1 = l1;
                }
                i += byte0;
                l = j1;
            }
            obj = layoutstate;
        }
        return ((Span) (obj));
    }

    private void handleUpdate(int i, int j, int k)
    {
        int l;
        int i1;
        int j1;
        if (mShouldReverseLayout)
        {
            j1 = getLastChildPosition();
        } else
        {
            j1 = getFirstChildPosition();
        }
        if (k == 8)
        {
            if (i < j)
            {
                i1 = j + 1;
                l = i;
            } else
            {
                i1 = i + 1;
                l = j;
            }
        } else
        {
            l = i;
            i1 = i + j;
        }
        mLazySpanLookup.invalidateAfter(l);
        k;
        JVM INSTR lookupswitch 3: default 76
    //                   1: 115
    //                   2: 127
    //                   8: 139;
           goto _L1 _L2 _L3 _L4
_L1:
        if (i1 > j1) goto _L6; else goto _L5
_L5:
        return;
_L2:
        mLazySpanLookup.offsetForAddition(i, j);
        continue; /* Loop/switch isn't completed */
_L3:
        mLazySpanLookup.offsetForRemoval(i, j);
        continue; /* Loop/switch isn't completed */
_L4:
        mLazySpanLookup.offsetForRemoval(i, 1);
        mLazySpanLookup.offsetForAddition(j, 1);
        continue; /* Loop/switch isn't completed */
_L6:
        if (mShouldReverseLayout)
        {
            i = getFirstChildPosition();
        } else
        {
            i = getLastChildPosition();
        }
        if (l > i) goto _L5; else goto _L7
_L7:
        requestLayout();
        return;
        if (true) goto _L1; else goto _L8
_L8:
    }

    private void measureChildWithDecorationsAndMargin(View view, int i, int j, boolean flag)
    {
        calculateItemDecorationsForChild(view, mTmpRect);
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        i = updateSpecWithExtra(i, layoutparams.leftMargin + mTmpRect.left, layoutparams.rightMargin + mTmpRect.right);
        j = updateSpecWithExtra(j, layoutparams.topMargin + mTmpRect.top, layoutparams.bottomMargin + mTmpRect.bottom);
        if (flag)
        {
            flag = shouldReMeasureChild(view, i, j, layoutparams);
        } else
        {
            flag = shouldMeasureChild(view, i, j, layoutparams);
        }
        if (flag)
        {
            view.measure(i, j);
        }
    }

    private void measureChildWithDecorationsAndMargin(View view, LayoutParams layoutparams, boolean flag)
    {
        if (layoutparams.mFullSpan)
        {
            if (mOrientation == 1)
            {
                measureChildWithDecorationsAndMargin(view, mFullSizeSpec, getChildMeasureSpec(getHeight(), getHeightMode(), 0, layoutparams.height, true), flag);
                return;
            } else
            {
                measureChildWithDecorationsAndMargin(view, getChildMeasureSpec(getWidth(), getWidthMode(), 0, layoutparams.width, true), mFullSizeSpec, flag);
                return;
            }
        }
        if (mOrientation == 1)
        {
            measureChildWithDecorationsAndMargin(view, getChildMeasureSpec(mSizePerSpan, getWidthMode(), 0, layoutparams.width, false), getChildMeasureSpec(getHeight(), getHeightMode(), 0, layoutparams.height, true), flag);
            return;
        } else
        {
            measureChildWithDecorationsAndMargin(view, getChildMeasureSpec(getWidth(), getWidthMode(), 0, layoutparams.width, true), getChildMeasureSpec(mSizePerSpan, getHeightMode(), 0, layoutparams.height, false), flag);
            return;
        }
    }

    private void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state, boolean flag)
    {
        AnchorInfo anchorinfo;
        boolean flag2;
        flag2 = true;
        anchorinfo = mAnchorInfo;
        if (mPendingSavedState == null && mPendingScrollPosition == -1 || state.getItemCount() != 0) goto _L2; else goto _L1
_L1:
        removeAndRecycleAllViews(recycler);
        anchorinfo.reset();
_L6:
        return;
_L2:
        int i;
        if (!anchorinfo.mValid || mPendingScrollPosition != -1 || mPendingSavedState != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            anchorinfo.reset();
            if (mPendingSavedState != null)
            {
                applyPendingSavedState(anchorinfo);
            } else
            {
                resolveShouldLayoutReverse();
                anchorinfo.mLayoutFromEnd = mShouldReverseLayout;
            }
            updateAnchorInfoForLayout(state, anchorinfo);
            anchorinfo.mValid = true;
        }
        if (mPendingSavedState == null && mPendingScrollPosition == -1 && (anchorinfo.mLayoutFromEnd != mLastLayoutFromEnd || isLayoutRTL() != mLastLayoutRTL))
        {
            mLazySpanLookup.clear();
            anchorinfo.mInvalidateOffsets = true;
        }
        boolean flag1;
        boolean flag3;
        if (getChildCount() > 0 && (mPendingSavedState == null || mPendingSavedState.mSpanOffsetsSize < 1))
        {
            if (anchorinfo.mInvalidateOffsets)
            {
                for (i = 0; i < mSpanCount; i++)
                {
                    mSpans[i].clear();
                    if (anchorinfo.mOffset != 0x80000000)
                    {
                        mSpans[i].setLine(anchorinfo.mOffset);
                    }
                }

            } else
            if (i != 0 || mAnchorInfo.mSpanReferenceLines == null)
            {
                for (i = 0; i < mSpanCount; i++)
                {
                    mSpans[i].cacheReferenceLineAndClear(mShouldReverseLayout, anchorinfo.mOffset);
                }

                mAnchorInfo.saveSpanReferenceLines(mSpans);
            } else
            {
                i = 0;
                while (i < mSpanCount) 
                {
                    Span span = mSpans[i];
                    span.clear();
                    span.setLine(mAnchorInfo.mSpanReferenceLines[i]);
                    i++;
                }
            }
        }
        detachAndScrapAttachedViews(recycler);
        mLayoutState.mRecycle = false;
        mLaidOutInvalidFullSpan = false;
        updateMeasureSpecs(mSecondaryOrientation.getTotalSpace());
        updateLayoutState(anchorinfo.mPosition, state);
        if (anchorinfo.mLayoutFromEnd)
        {
            setLayoutStateDirection(-1);
            fill(recycler, mLayoutState, state);
            setLayoutStateDirection(1);
            mLayoutState.mCurrentPosition = anchorinfo.mPosition + mLayoutState.mItemDirection;
            fill(recycler, mLayoutState, state);
        } else
        {
            setLayoutStateDirection(1);
            fill(recycler, mLayoutState, state);
            setLayoutStateDirection(-1);
            mLayoutState.mCurrentPosition = anchorinfo.mPosition + mLayoutState.mItemDirection;
            fill(recycler, mLayoutState, state);
        }
        repositionToWrapContentIfNecessary();
        if (getChildCount() > 0)
        {
            if (mShouldReverseLayout)
            {
                fixEndGap(recycler, state, true);
                fixStartGap(recycler, state, false);
            } else
            {
                fixStartGap(recycler, state, true);
                fixEndGap(recycler, state, false);
            }
        }
        flag3 = false;
        flag1 = flag3;
        if (!flag) goto _L4; else goto _L3
_L3:
        flag1 = flag3;
        if (state.isPreLayout()) goto _L4; else goto _L5
_L5:
        if (mGapStrategy == 0 || getChildCount() <= 0)
        {
            break MISSING_BLOCK_LABEL_726;
        }
        i = ((flag2) ? 1 : 0);
        if (!mLaidOutInvalidFullSpan)
        {
            if (hasGapsToFix() == null)
            {
                break MISSING_BLOCK_LABEL_726;
            }
            i = ((flag2) ? 1 : 0);
        }
_L7:
        flag1 = flag3;
        if (i != 0)
        {
            removeCallbacks(mCheckForGapsRunnable);
            flag1 = flag3;
            if (checkForGaps())
            {
                flag1 = true;
            }
        }
_L4:
        if (state.isPreLayout())
        {
            mAnchorInfo.reset();
        }
        mLastLayoutFromEnd = anchorinfo.mLayoutFromEnd;
        mLastLayoutRTL = isLayoutRTL();
        if (flag1)
        {
            mAnchorInfo.reset();
            onLayoutChildren(recycler, state, false);
            return;
        }
          goto _L6
        i = 0;
          goto _L7
    }

    private boolean preferLastSpan(int i)
    {
        if (mOrientation != 0) goto _L2; else goto _L1
_L1:
        boolean flag;
        if (i == -1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag == mShouldReverseLayout) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        boolean flag1;
        if (i == -1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 == mShouldReverseLayout)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 != isLayoutRTL())
        {
            return false;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    private void prependViewToAllSpans(View view)
    {
        for (int i = mSpanCount - 1; i >= 0; i--)
        {
            mSpans[i].prependToSpan(view);
        }

    }

    private void recycle(RecyclerView.Recycler recycler, LayoutState layoutstate)
    {
        if (!layoutstate.mRecycle || layoutstate.mInfinite)
        {
            return;
        }
        if (layoutstate.mAvailable == 0)
        {
            if (layoutstate.mLayoutDirection == -1)
            {
                recycleFromEnd(recycler, layoutstate.mEndLine);
                return;
            } else
            {
                recycleFromStart(recycler, layoutstate.mStartLine);
                return;
            }
        }
        if (layoutstate.mLayoutDirection == -1)
        {
            int i = layoutstate.mStartLine - getMaxStart(layoutstate.mStartLine);
            if (i < 0)
            {
                i = layoutstate.mEndLine;
            } else
            {
                i = layoutstate.mEndLine - Math.min(i, layoutstate.mAvailable);
            }
            recycleFromEnd(recycler, i);
            return;
        }
        int j = getMinEnd(layoutstate.mEndLine) - layoutstate.mEndLine;
        if (j < 0)
        {
            j = layoutstate.mStartLine;
        } else
        {
            j = layoutstate.mStartLine + Math.min(j, layoutstate.mAvailable);
        }
        recycleFromStart(recycler, j);
    }

    private void recycleFromEnd(RecyclerView.Recycler recycler, int i)
    {
        int j = getChildCount() - 1;
_L12:
        if (j < 0) goto _L2; else goto _L1
_L1:
        View view = getChildAt(j);
        if (mPrimaryOrientation.getDecoratedStart(view) < i || mPrimaryOrientation.getTransformedStartWithDecoration(view) < i) goto _L2; else goto _L3
_L3:
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if (!layoutparams.mFullSpan) goto _L5; else goto _L4
_L4:
        int k = 0;
_L9:
        if (k >= mSpanCount) goto _L7; else goto _L6
_L6:
        if (mSpans[k].mViews.size() != 1) goto _L8; else goto _L2
_L2:
        return;
_L8:
        k++;
          goto _L9
_L7:
        for (int l = 0; l < mSpanCount; l++)
        {
            mSpans[l].popEnd();
        }

        break; /* Loop/switch isn't completed */
_L5:
        if (layoutparams.mSpan.mViews.size() == 1)
        {
            continue; /* Loop/switch isn't completed */
        }
        layoutparams.mSpan.popEnd();
        break; /* Loop/switch isn't completed */
        if (true) goto _L2; else goto _L10
_L10:
        removeAndRecycleView(view, recycler);
        j--;
        if (true) goto _L12; else goto _L11
_L11:
    }

    private void recycleFromStart(RecyclerView.Recycler recycler, int i)
    {
_L12:
        if (getChildCount() <= 0) goto _L2; else goto _L1
_L1:
        View view = getChildAt(0);
        if (mPrimaryOrientation.getDecoratedEnd(view) > i || mPrimaryOrientation.getTransformedEndWithDecoration(view) > i) goto _L2; else goto _L3
_L3:
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if (!layoutparams.mFullSpan) goto _L5; else goto _L4
_L4:
        int j = 0;
_L9:
        if (j >= mSpanCount) goto _L7; else goto _L6
_L6:
        if (mSpans[j].mViews.size() != 1) goto _L8; else goto _L2
_L2:
        return;
_L8:
        j++;
          goto _L9
_L7:
        for (int k = 0; k < mSpanCount; k++)
        {
            mSpans[k].popStart();
        }

        break; /* Loop/switch isn't completed */
_L5:
        if (layoutparams.mSpan.mViews.size() == 1)
        {
            continue; /* Loop/switch isn't completed */
        }
        layoutparams.mSpan.popStart();
        break; /* Loop/switch isn't completed */
        if (true) goto _L2; else goto _L10
_L10:
        removeAndRecycleView(view, recycler);
        if (true) goto _L12; else goto _L11
_L11:
    }

    private void repositionToWrapContentIfNecessary()
    {
        if (mSecondaryOrientation.getMode() != 0x40000000) goto _L2; else goto _L1
_L1:
        return;
_L2:
        float f = 0.0F;
        int i1 = getChildCount();
        int i = 0;
        while (i < i1) 
        {
            View view = getChildAt(i);
            float f2 = mSecondaryOrientation.getDecoratedMeasurement(view);
            if (f2 >= f)
            {
                float f1 = f2;
                if (((LayoutParams)view.getLayoutParams()).isFullSpan())
                {
                    f1 = (1.0F * f2) / (float)mSpanCount;
                }
                f = Math.max(f, f1);
            }
            i++;
        }
        int j1 = mSizePerSpan;
        int k = Math.round((float)mSpanCount * f);
        i = k;
        if (mSecondaryOrientation.getMode() == 0x80000000)
        {
            i = Math.min(k, mSecondaryOrientation.getTotalSpace());
        }
        updateMeasureSpecs(i);
        if (mSizePerSpan != j1)
        {
            int j = 0;
            while (j < i1) 
            {
                View view1 = getChildAt(j);
                LayoutParams layoutparams = (LayoutParams)view1.getLayoutParams();
                if (!layoutparams.mFullSpan)
                {
                    if (isLayoutRTL() && mOrientation == 1)
                    {
                        view1.offsetLeftAndRight(-(mSpanCount - 1 - layoutparams.mSpan.mIndex) * mSizePerSpan - -(mSpanCount - 1 - layoutparams.mSpan.mIndex) * j1);
                    } else
                    {
                        int l = layoutparams.mSpan.mIndex * mSizePerSpan;
                        int k1 = layoutparams.mSpan.mIndex * j1;
                        if (mOrientation == 1)
                        {
                            view1.offsetLeftAndRight(l - k1);
                        } else
                        {
                            view1.offsetTopAndBottom(l - k1);
                        }
                    }
                }
                j++;
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    private void resolveShouldLayoutReverse()
    {
        boolean flag = true;
        if (mOrientation == 1 || !isLayoutRTL())
        {
            mShouldReverseLayout = mReverseLayout;
            return;
        }
        if (mReverseLayout)
        {
            flag = false;
        }
        mShouldReverseLayout = flag;
    }

    private void setLayoutStateDirection(int i)
    {
        boolean flag = true;
        mLayoutState.mLayoutDirection = i;
        LayoutState layoutstate = mLayoutState;
        boolean flag2 = mShouldReverseLayout;
        boolean flag1;
        if (i == -1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag2 == flag1)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = -1;
        }
        layoutstate.mItemDirection = i;
    }

    private void updateAllRemainingSpans(int i, int j)
    {
        int k = 0;
        while (k < mSpanCount) 
        {
            if (!mSpans[k].mViews.isEmpty())
            {
                updateRemainingSpans(mSpans[k], i, j);
            }
            k++;
        }
    }

    private boolean updateAnchorFromChildren(RecyclerView.State state, AnchorInfo anchorinfo)
    {
        int i;
        if (mLastLayoutFromEnd)
        {
            i = findLastReferenceChildPosition(state.getItemCount());
        } else
        {
            i = findFirstReferenceChildPosition(state.getItemCount());
        }
        anchorinfo.mPosition = i;
        anchorinfo.mOffset = 0x80000000;
        return true;
    }

    private void updateLayoutState(int i, RecyclerView.State state)
    {
        boolean flag3 = true;
        mLayoutState.mAvailable = 0;
        mLayoutState.mCurrentPosition = i;
        boolean flag = false;
        boolean flag1 = false;
        int j = ((flag1) ? 1 : 0);
        int k = ((flag) ? 1 : 0);
        boolean flag2;
        if (isSmoothScrolling())
        {
            int l = state.getTargetScrollPosition();
            j = ((flag1) ? 1 : 0);
            k = ((flag) ? 1 : 0);
            if (l != -1)
            {
                boolean flag4 = mShouldReverseLayout;
                if (l < i)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                if (flag4 == flag2)
                {
                    j = mPrimaryOrientation.getTotalSpace();
                    k = ((flag) ? 1 : 0);
                } else
                {
                    k = mPrimaryOrientation.getTotalSpace();
                    j = ((flag1) ? 1 : 0);
                }
            }
        }
        if (getClipToPadding())
        {
            mLayoutState.mStartLine = mPrimaryOrientation.getStartAfterPadding() - k;
            mLayoutState.mEndLine = mPrimaryOrientation.getEndAfterPadding() + j;
        } else
        {
            mLayoutState.mEndLine = mPrimaryOrientation.getEnd() + j;
            mLayoutState.mStartLine = -k;
        }
        mLayoutState.mStopInFocusable = false;
        mLayoutState.mRecycle = true;
        state = mLayoutState;
        if (mPrimaryOrientation.getMode() == 0 && mPrimaryOrientation.getEnd() == 0)
        {
            flag2 = flag3;
        } else
        {
            flag2 = false;
        }
        state.mInfinite = flag2;
    }

    private void updateRemainingSpans(Span span, int i, int j)
    {
        int k = span.getDeletedSize();
        if (i == -1)
        {
            if (span.getStartLine() + k <= j)
            {
                mRemainingSpans.set(span.mIndex, false);
            }
        } else
        if (span.getEndLine() - k >= j)
        {
            mRemainingSpans.set(span.mIndex, false);
            return;
        }
    }

    private int updateSpecWithExtra(int i, int j, int k)
    {
        int l;
        if (j != 0 || k != 0)
        {
            if ((l = android.view.View.MeasureSpec.getMode(i)) == 0x80000000 || l == 0x40000000)
            {
                return android.view.View.MeasureSpec.makeMeasureSpec(Math.max(0, android.view.View.MeasureSpec.getSize(i) - j - k), l);
            }
        }
        return i;
    }

    boolean areAllEndsEqual()
    {
        int j = mSpans[0].getEndLine(0x80000000);
        for (int i = 1; i < mSpanCount; i++)
        {
            if (mSpans[i].getEndLine(0x80000000) != j)
            {
                return false;
            }
        }

        return true;
    }

    boolean areAllStartsEqual()
    {
        int j = mSpans[0].getStartLine(0x80000000);
        for (int i = 1; i < mSpanCount; i++)
        {
            if (mSpans[i].getStartLine(0x80000000) != j)
            {
                return false;
            }
        }

        return true;
    }

    public void assertNotInLayoutOrScroll(String s)
    {
        if (mPendingSavedState == null)
        {
            super.assertNotInLayoutOrScroll(s);
        }
    }

    public boolean canScrollHorizontally()
    {
        return mOrientation == 0;
    }

    public boolean canScrollVertically()
    {
        return mOrientation == 1;
    }

    boolean checkForGaps()
    {
        if (getChildCount() == 0 || mGapStrategy == 0 || !isAttachedToWindow())
        {
            return false;
        }
        int i;
        int j;
        if (mShouldReverseLayout)
        {
            j = getLastChildPosition();
            i = getFirstChildPosition();
        } else
        {
            j = getFirstChildPosition();
            i = getLastChildPosition();
        }
        if (j == 0 && hasGapsToFix() != null)
        {
            mLazySpanLookup.clear();
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        }
        if (!mLaidOutInvalidFullSpan)
        {
            return false;
        }
        LazySpanLookup.FullSpanItem fullspanitem;
        byte byte0;
        if (mShouldReverseLayout)
        {
            byte0 = -1;
        } else
        {
            byte0 = 1;
        }
        fullspanitem = mLazySpanLookup.getFirstFullSpanItemInRange(j, i + 1, byte0, true);
        if (fullspanitem == null)
        {
            mLaidOutInvalidFullSpan = false;
            mLazySpanLookup.forceInvalidateAfter(i + 1);
            return false;
        }
        LazySpanLookup.FullSpanItem fullspanitem1 = mLazySpanLookup.getFirstFullSpanItemInRange(j, fullspanitem.mPosition, byte0 * -1, true);
        if (fullspanitem1 == null)
        {
            mLazySpanLookup.forceInvalidateAfter(fullspanitem.mPosition);
        } else
        {
            mLazySpanLookup.forceInvalidateAfter(fullspanitem1.mPosition + 1);
        }
        requestSimpleAnimationsInNextLayout();
        requestLayout();
        return true;
    }

    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    public void collectAdjacentPrefetchPositions(int i, int j, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutprefetchregistry)
    {
        if (mOrientation != 0)
        {
            i = j;
        }
        if (getChildCount() != 0 && i != 0)
        {
            prepareLayoutStateForDelta(i, state);
            if (mPrefetchDistances == null || mPrefetchDistances.length < mSpanCount)
            {
                mPrefetchDistances = new int[mSpanCount];
            }
            i = 0;
            j = 0;
            while (j < mSpanCount) 
            {
                int k;
                int l;
                if (mLayoutState.mItemDirection == -1)
                {
                    l = mLayoutState.mStartLine - mSpans[j].getStartLine(mLayoutState.mStartLine);
                } else
                {
                    l = mSpans[j].getEndLine(mLayoutState.mEndLine) - mLayoutState.mEndLine;
                }
                k = i;
                if (l >= 0)
                {
                    mPrefetchDistances[i] = l;
                    k = i + 1;
                }
                j++;
                i = k;
            }
            Arrays.sort(mPrefetchDistances, 0, i);
            j = 0;
            while (j < i && mLayoutState.hasMore(state)) 
            {
                layoutprefetchregistry.addPosition(mLayoutState.mCurrentPosition, mPrefetchDistances[j]);
                LayoutState layoutstate = mLayoutState;
                layoutstate.mCurrentPosition = layoutstate.mCurrentPosition + mLayoutState.mItemDirection;
                j++;
            }
        }
    }

    public int computeHorizontalScrollExtent(RecyclerView.State state)
    {
        return computeScrollExtent(state);
    }

    public int computeHorizontalScrollOffset(RecyclerView.State state)
    {
        return computeScrollOffset(state);
    }

    public int computeHorizontalScrollRange(RecyclerView.State state)
    {
        return computeScrollRange(state);
    }

    public PointF computeScrollVectorForPosition(int i)
    {
        i = calculateScrollDirectionForPosition(i);
        PointF pointf = new PointF();
        if (i == 0)
        {
            return null;
        }
        if (mOrientation == 0)
        {
            pointf.x = i;
            pointf.y = 0.0F;
            return pointf;
        } else
        {
            pointf.x = 0.0F;
            pointf.y = i;
            return pointf;
        }
    }

    public int computeVerticalScrollExtent(RecyclerView.State state)
    {
        return computeScrollExtent(state);
    }

    public int computeVerticalScrollOffset(RecyclerView.State state)
    {
        return computeScrollOffset(state);
    }

    public int computeVerticalScrollRange(RecyclerView.State state)
    {
        return computeScrollRange(state);
    }

    public int[] findFirstCompletelyVisibleItemPositions(int ai[])
    {
        int ai1[];
        int i;
        if (ai == null)
        {
            ai1 = new int[mSpanCount];
        } else
        {
            ai1 = ai;
            if (ai.length < mSpanCount)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Provided int[]'s size must be more than or equal to span count. Expected:").append(mSpanCount).append(", array size:").append(ai.length).toString());
            }
        }
        for (i = 0; i < mSpanCount; i++)
        {
            ai1[i] = mSpans[i].findFirstCompletelyVisibleItemPosition();
        }

        return ai1;
    }

    View findFirstVisibleItemClosestToEnd(boolean flag)
    {
        int j = mPrimaryOrientation.getStartAfterPadding();
        int k = mPrimaryOrientation.getEndAfterPadding();
        View view = null;
        int i = getChildCount() - 1;
        while (i >= 0) 
        {
            View view2 = getChildAt(i);
            int l = mPrimaryOrientation.getDecoratedStart(view2);
            int i1 = mPrimaryOrientation.getDecoratedEnd(view2);
            View view1 = view;
            if (i1 > j)
            {
                if (l >= k)
                {
                    view1 = view;
                } else
                {
                    if (i1 <= k || !flag)
                    {
                        return view2;
                    }
                    view1 = view;
                    if (view == null)
                    {
                        view1 = view2;
                    }
                }
            }
            i--;
            view = view1;
        }
        return view;
    }

    View findFirstVisibleItemClosestToStart(boolean flag)
    {
        int j = mPrimaryOrientation.getStartAfterPadding();
        int k = mPrimaryOrientation.getEndAfterPadding();
        int l = getChildCount();
        View view = null;
        int i = 0;
        while (i < l) 
        {
            View view2 = getChildAt(i);
            int i1 = mPrimaryOrientation.getDecoratedStart(view2);
            View view1 = view;
            if (mPrimaryOrientation.getDecoratedEnd(view2) > j)
            {
                if (i1 >= k)
                {
                    view1 = view;
                } else
                {
                    if (i1 >= j || !flag)
                    {
                        return view2;
                    }
                    view1 = view;
                    if (view == null)
                    {
                        view1 = view2;
                    }
                }
            }
            i++;
            view = view1;
        }
        return view;
    }

    int findFirstVisibleItemPositionInt()
    {
        View view;
        if (mShouldReverseLayout)
        {
            view = findFirstVisibleItemClosestToEnd(true);
        } else
        {
            view = findFirstVisibleItemClosestToStart(true);
        }
        if (view == null)
        {
            return -1;
        } else
        {
            return getPosition(view);
        }
    }

    public int[] findFirstVisibleItemPositions(int ai[])
    {
        int ai1[];
        int i;
        if (ai == null)
        {
            ai1 = new int[mSpanCount];
        } else
        {
            ai1 = ai;
            if (ai.length < mSpanCount)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Provided int[]'s size must be more than or equal to span count. Expected:").append(mSpanCount).append(", array size:").append(ai.length).toString());
            }
        }
        for (i = 0; i < mSpanCount; i++)
        {
            ai1[i] = mSpans[i].findFirstVisibleItemPosition();
        }

        return ai1;
    }

    public int[] findLastCompletelyVisibleItemPositions(int ai[])
    {
        int ai1[];
        int i;
        if (ai == null)
        {
            ai1 = new int[mSpanCount];
        } else
        {
            ai1 = ai;
            if (ai.length < mSpanCount)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Provided int[]'s size must be more than or equal to span count. Expected:").append(mSpanCount).append(", array size:").append(ai.length).toString());
            }
        }
        for (i = 0; i < mSpanCount; i++)
        {
            ai1[i] = mSpans[i].findLastCompletelyVisibleItemPosition();
        }

        return ai1;
    }

    public int[] findLastVisibleItemPositions(int ai[])
    {
        int ai1[];
        int i;
        if (ai == null)
        {
            ai1 = new int[mSpanCount];
        } else
        {
            ai1 = ai;
            if (ai.length < mSpanCount)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Provided int[]'s size must be more than or equal to span count. Expected:").append(mSpanCount).append(", array size:").append(ai.length).toString());
            }
        }
        for (i = 0; i < mSpanCount; i++)
        {
            ai1[i] = mSpans[i].findLastVisibleItemPosition();
        }

        return ai1;
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams()
    {
        if (mOrientation == 0)
        {
            return new LayoutParams(-2, -1);
        } else
        {
            return new LayoutParams(-1, -2);
        }
    }

    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeset)
    {
        return new LayoutParams(context, attributeset);
    }

    public RecyclerView.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
        {
            return new LayoutParams((android.view.ViewGroup.MarginLayoutParams)layoutparams);
        } else
        {
            return new LayoutParams(layoutparams);
        }
    }

    public int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        if (mOrientation == 1)
        {
            return mSpanCount;
        } else
        {
            return super.getColumnCountForAccessibility(recycler, state);
        }
    }

    int getFirstChildPosition()
    {
        if (getChildCount() == 0)
        {
            return 0;
        } else
        {
            return getPosition(getChildAt(0));
        }
    }

    public int getGapStrategy()
    {
        return mGapStrategy;
    }

    int getLastChildPosition()
    {
        int i = getChildCount();
        if (i == 0)
        {
            return 0;
        } else
        {
            return getPosition(getChildAt(i - 1));
        }
    }

    public int getOrientation()
    {
        return mOrientation;
    }

    public boolean getReverseLayout()
    {
        return mReverseLayout;
    }

    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        if (mOrientation == 0)
        {
            return mSpanCount;
        } else
        {
            return super.getRowCountForAccessibility(recycler, state);
        }
    }

    public int getSpanCount()
    {
        return mSpanCount;
    }

    View hasGapsToFix()
    {
        BitSet bitset;
        View view;
        LayoutParams layoutparams;
        byte byte0;
        int j;
        byte byte1;
        int k;
        int i = getChildCount() - 1;
        bitset = new BitSet(mSpanCount);
        bitset.set(0, mSpanCount, true);
        if (mOrientation == 1 && isLayoutRTL())
        {
            byte0 = 1;
        } else
        {
            byte0 = -1;
        }
        if (mShouldReverseLayout)
        {
            j = 0 - 1;
        } else
        {
            byte1 = 0;
            j = i + 1;
            i = byte1;
        }
        if (i < j)
        {
            byte1 = 1;
        } else
        {
            byte1 = -1;
        }
        k = i;
_L13:
        if (k == j)
        {
            break MISSING_BLOCK_LABEL_355;
        }
        view = getChildAt(k);
        layoutparams = (LayoutParams)view.getLayoutParams();
        if (!bitset.get(layoutparams.mSpan.mIndex)) goto _L2; else goto _L1
_L1:
        if (!checkSpanForGap(layoutparams.mSpan)) goto _L4; else goto _L3
_L3:
        return view;
_L4:
        bitset.clear(layoutparams.mSpan.mIndex);
          goto _L2
_L6:
        k += byte1;
        continue; /* Loop/switch isn't completed */
_L2:
        if (layoutparams.mFullSpan || k + byte1 == j) goto _L6; else goto _L5
_L5:
        Object obj;
        boolean flag;
        obj = getChildAt(k + byte1);
        flag = false;
        if (!mShouldReverseLayout) goto _L8; else goto _L7
_L7:
        int l;
        int j1;
        l = mPrimaryOrientation.getDecoratedEnd(view);
        j1 = mPrimaryOrientation.getDecoratedEnd(((View) (obj)));
        if (l < j1) goto _L3; else goto _L9
_L9:
        if (l == j1)
        {
            flag = true;
        }
_L11:
        if (flag)
        {
            obj = (LayoutParams)((View) (obj)).getLayoutParams();
            int i1;
            int k1;
            if (layoutparams.mSpan.mIndex - ((LayoutParams) (obj)).mSpan.mIndex < 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (byte0 < 0)
            {
                i1 = 1;
            } else
            {
                i1 = 0;
            }
            if (flag != i1)
            {
                return view;
            }
        }
          goto _L6
_L8:
        i1 = mPrimaryOrientation.getDecoratedStart(view);
        k1 = mPrimaryOrientation.getDecoratedStart(((View) (obj)));
        if (i1 > k1) goto _L3; else goto _L10
_L10:
        if (i1 == k1)
        {
            flag = true;
        }
          goto _L11
        return null;
        if (true) goto _L13; else goto _L12
_L12:
    }

    public void invalidateSpanAssignments()
    {
        mLazySpanLookup.clear();
        requestLayout();
    }

    boolean isLayoutRTL()
    {
        return getLayoutDirection() == 1;
    }

    public void offsetChildrenHorizontal(int i)
    {
        super.offsetChildrenHorizontal(i);
        for (int j = 0; j < mSpanCount; j++)
        {
            mSpans[j].onOffset(i);
        }

    }

    public void offsetChildrenVertical(int i)
    {
        super.offsetChildrenVertical(i);
        for (int j = 0; j < mSpanCount; j++)
        {
            mSpans[j].onOffset(i);
        }

    }

    public void onDetachedFromWindow(RecyclerView recyclerview, RecyclerView.Recycler recycler)
    {
        removeCallbacks(mCheckForGapsRunnable);
        for (int i = 0; i < mSpanCount; i++)
        {
            mSpans[i].clear();
        }

        recyclerview.requestLayout();
    }

    public View onFocusSearchFailed(View view, int i, RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        if (getChildCount() != 0) goto _L2; else goto _L1
_L1:
        view = null;
_L4:
        return view;
_L2:
        View view1;
        Span span;
        int i1;
        boolean flag;
        view1 = findContainingItemView(view);
        if (view1 == null)
        {
            return null;
        }
        resolveShouldLayoutReverse();
        i1 = convertFocusDirectionToLayoutDirection(i);
        if (i1 == 0x80000000)
        {
            return null;
        }
        view = (LayoutParams)view1.getLayoutParams();
        flag = ((LayoutParams) (view)).mFullSpan;
        span = ((LayoutParams) (view)).mSpan;
        int j;
        if (i1 == 1)
        {
            i = getLastChildPosition();
        } else
        {
            i = getFirstChildPosition();
        }
        updateLayoutState(i, state);
        setLayoutStateDirection(i1);
        mLayoutState.mCurrentPosition = mLayoutState.mItemDirection + i;
        mLayoutState.mAvailable = (int)(0.3333333F * (float)mPrimaryOrientation.getTotalSpace());
        mLayoutState.mStopInFocusable = true;
        mLayoutState.mRecycle = false;
        fill(recycler, mLayoutState, state);
        mLastLayoutFromEnd = mShouldReverseLayout;
        if (flag)
        {
            break; /* Loop/switch isn't completed */
        }
        recycler = span.getFocusableViewAfter(i, i1);
        if (recycler == null)
        {
            break; /* Loop/switch isn't completed */
        }
        view = recycler;
        if (recycler != view1) goto _L4; else goto _L3
_L3:
        if (!preferLastSpan(i1))
        {
            break MISSING_BLOCK_LABEL_252;
        }
        j = mSpanCount - 1;
_L6:
        if (j < 0)
        {
            break MISSING_BLOCK_LABEL_299;
        }
        recycler = mSpans[j].getFocusableViewAfter(i, i1);
        if (recycler == null)
        {
            break; /* Loop/switch isn't completed */
        }
        view = recycler;
        if (recycler != view1) goto _L4; else goto _L5
_L5:
        j--;
          goto _L6
        int k = 0;
_L10:
        if (k >= mSpanCount) goto _L8; else goto _L7
_L7:
        recycler = mSpans[k].getFocusableViewAfter(i, i1);
        if (recycler == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        view = recycler;
        if (recycler != view1) goto _L4; else goto _L9
_L9:
        k++;
          goto _L10
_L8:
        if (!mReverseLayout)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i1 == -1)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (i == k)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (!flag)
        {
            if (i != 0)
            {
                k = span.findFirstPartiallyVisibleItemPosition();
            } else
            {
                k = span.findLastPartiallyVisibleItemPosition();
            }
            view = findViewByPosition(k);
            if (view != null && view != view1)
            {
                return view;
            }
        }
        if (!preferLastSpan(i1))
        {
            break MISSING_BLOCK_LABEL_477;
        }
        k = mSpanCount - 1;
_L12:
        if (k < 0)
        {
            break MISSING_BLOCK_LABEL_548;
        }
        if (k != span.mIndex)
        {
            break; /* Loop/switch isn't completed */
        }
_L14:
        k--;
        if (true) goto _L12; else goto _L11
_L11:
        int j1;
        if (i != 0)
        {
            j1 = mSpans[k].findFirstPartiallyVisibleItemPosition();
        } else
        {
            j1 = mSpans[k].findLastPartiallyVisibleItemPosition();
        }
        view = findViewByPosition(j1);
        if (view == null || view == view1) goto _L14; else goto _L13
_L13:
        return view;
        int k1;
        for (int l = 0; l < mSpanCount; l++)
        {
            if (i != 0)
            {
                k1 = mSpans[l].findFirstPartiallyVisibleItemPosition();
            } else
            {
                k1 = mSpans[l].findLastPartiallyVisibleItemPosition();
            }
            view = findViewByPosition(k1);
            if (view != null && view != view1)
            {
                return view;
            }
        }

        return null;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        View view;
        View view1;
label0:
        {
            super.onInitializeAccessibilityEvent(accessibilityevent);
            if (getChildCount() > 0)
            {
                accessibilityevent = AccessibilityEventCompat.asRecord(accessibilityevent);
                view = findFirstVisibleItemClosestToStart(false);
                view1 = findFirstVisibleItemClosestToEnd(false);
                if (view != null && view1 != null)
                {
                    break label0;
                }
            }
            return;
        }
        int i = getPosition(view);
        int j = getPosition(view1);
        if (i < j)
        {
            accessibilityevent.setFromIndex(i);
            accessibilityevent.setToIndex(j);
            return;
        } else
        {
            accessibilityevent.setFromIndex(j);
            accessibilityevent.setToIndex(i);
            return;
        }
    }

    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        recycler = view.getLayoutParams();
        if (!(recycler instanceof LayoutParams))
        {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilitynodeinfocompat);
            return;
        }
        recycler = (LayoutParams)recycler;
        if (mOrientation == 0)
        {
            int k = recycler.getSpanIndex();
            int i;
            if (((LayoutParams) (recycler)).mFullSpan)
            {
                i = mSpanCount;
            } else
            {
                i = 1;
            }
            accessibilitynodeinfocompat.setCollectionItemInfo(android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(k, i, -1, -1, ((LayoutParams) (recycler)).mFullSpan, false));
            return;
        }
        int l = recycler.getSpanIndex();
        int j;
        if (((LayoutParams) (recycler)).mFullSpan)
        {
            j = mSpanCount;
        } else
        {
            j = 1;
        }
        accessibilitynodeinfocompat.setCollectionItemInfo(android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(-1, -1, l, j, ((LayoutParams) (recycler)).mFullSpan, false));
    }

    public void onItemsAdded(RecyclerView recyclerview, int i, int j)
    {
        handleUpdate(i, j, 1);
    }

    public void onItemsChanged(RecyclerView recyclerview)
    {
        mLazySpanLookup.clear();
        requestLayout();
    }

    public void onItemsMoved(RecyclerView recyclerview, int i, int j, int k)
    {
        handleUpdate(i, j, 8);
    }

    public void onItemsRemoved(RecyclerView recyclerview, int i, int j)
    {
        handleUpdate(i, j, 2);
    }

    public void onItemsUpdated(RecyclerView recyclerview, int i, int j, Object obj)
    {
        handleUpdate(i, j, 4);
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        onLayoutChildren(recycler, state, true);
    }

    public void onLayoutCompleted(RecyclerView.State state)
    {
        super.onLayoutCompleted(state);
        mPendingScrollPosition = -1;
        mPendingScrollPositionOffset = 0x80000000;
        mPendingSavedState = null;
        mAnchorInfo.reset();
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        if (parcelable instanceof SavedState)
        {
            mPendingSavedState = (SavedState)parcelable;
            requestLayout();
        }
    }

    public Parcelable onSaveInstanceState()
    {
        if (mPendingSavedState == null) goto _L2; else goto _L1
_L1:
        SavedState savedstate = new SavedState(mPendingSavedState);
_L4:
        return savedstate;
_L2:
        SavedState savedstate1;
label0:
        {
            savedstate1 = new SavedState();
            savedstate1.mReverseLayout = mReverseLayout;
            savedstate1.mAnchorLayoutFromEnd = mLastLayoutFromEnd;
            savedstate1.mLastLayoutRTL = mLastLayoutRTL;
            int i;
            int j;
            if (mLazySpanLookup != null && mLazySpanLookup.mData != null)
            {
                savedstate1.mSpanLookup = mLazySpanLookup.mData;
                savedstate1.mSpanLookupSize = savedstate1.mSpanLookup.length;
                savedstate1.mFullSpanItems = mLazySpanLookup.mFullSpanItems;
            } else
            {
                savedstate1.mSpanLookupSize = 0;
            }
            if (getChildCount() <= 0)
            {
                break label0;
            }
            if (mLastLayoutFromEnd)
            {
                i = getLastChildPosition();
            } else
            {
                i = getFirstChildPosition();
            }
            savedstate1.mAnchorPosition = i;
            savedstate1.mVisibleAnchorPosition = findFirstVisibleItemPositionInt();
            savedstate1.mSpanOffsetsSize = mSpanCount;
            savedstate1.mSpanOffsets = new int[mSpanCount];
            j = 0;
            do
            {
                savedstate = savedstate1;
                if (j >= mSpanCount)
                {
                    break;
                }
                if (mLastLayoutFromEnd)
                {
                    int k = mSpans[j].getEndLine(0x80000000);
                    i = k;
                    if (k != 0x80000000)
                    {
                        i = k - mPrimaryOrientation.getEndAfterPadding();
                    }
                } else
                {
                    int l = mSpans[j].getStartLine(0x80000000);
                    i = l;
                    if (l != 0x80000000)
                    {
                        i = l - mPrimaryOrientation.getStartAfterPadding();
                    }
                }
                savedstate1.mSpanOffsets[j] = i;
                j++;
            } while (true);
        }
        if (true) goto _L4; else goto _L3
_L3:
        savedstate1.mAnchorPosition = -1;
        savedstate1.mVisibleAnchorPosition = -1;
        savedstate1.mSpanOffsetsSize = 0;
        return savedstate1;
    }

    public void onScrollStateChanged(int i)
    {
        if (i == 0)
        {
            checkForGaps();
        }
    }

    void prepareLayoutStateForDelta(int i, RecyclerView.State state)
    {
        int j;
        int k;
        if (i > 0)
        {
            j = 1;
            k = getLastChildPosition();
        } else
        {
            j = -1;
            k = getFirstChildPosition();
        }
        mLayoutState.mRecycle = true;
        updateLayoutState(k, state);
        setLayoutStateDirection(j);
        mLayoutState.mCurrentPosition = mLayoutState.mItemDirection + k;
        mLayoutState.mAvailable = Math.abs(i);
    }

    int scrollBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        if (getChildCount() == 0 || i == 0)
        {
            return 0;
        }
        prepareLayoutStateForDelta(i, state);
        int j = fill(recycler, mLayoutState, state);
        if (mLayoutState.mAvailable >= j)
        {
            if (i < 0)
            {
                i = -j;
            } else
            {
                i = j;
            }
        }
        mPrimaryOrientation.offsetChildren(-i);
        mLastLayoutFromEnd = mShouldReverseLayout;
        mLayoutState.mAvailable = 0;
        recycle(recycler, mLayoutState);
        return i;
    }

    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        return scrollBy(i, recycler, state);
    }

    public void scrollToPosition(int i)
    {
        if (mPendingSavedState != null && mPendingSavedState.mAnchorPosition != i)
        {
            mPendingSavedState.invalidateAnchorPositionInfo();
        }
        mPendingScrollPosition = i;
        mPendingScrollPositionOffset = 0x80000000;
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i, int j)
    {
        if (mPendingSavedState != null)
        {
            mPendingSavedState.invalidateAnchorPositionInfo();
        }
        mPendingScrollPosition = i;
        mPendingScrollPositionOffset = j;
        requestLayout();
    }

    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        return scrollBy(i, recycler, state);
    }

    public void setGapStrategy(int i)
    {
        assertNotInLayoutOrScroll(null);
        if (i == mGapStrategy)
        {
            return;
        }
        if (i != 0 && i != 2)
        {
            throw new IllegalArgumentException("invalid gap strategy. Must be GAP_HANDLING_NONE or GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS");
        }
        mGapStrategy = i;
        boolean flag;
        if (mGapStrategy != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setAutoMeasureEnabled(flag);
        requestLayout();
    }

    public void setMeasuredDimension(Rect rect, int i, int j)
    {
        int i1 = getPaddingLeft() + getPaddingRight();
        int j1 = getPaddingTop() + getPaddingBottom();
        if (mOrientation == 1)
        {
            int k = chooseSize(j, rect.height() + j1, getMinimumHeight());
            j = chooseSize(i, mSizePerSpan * mSpanCount + i1, getMinimumWidth());
            i = k;
        } else
        {
            int l = chooseSize(i, rect.width() + i1, getMinimumWidth());
            i = chooseSize(j, mSizePerSpan * mSpanCount + j1, getMinimumHeight());
            j = l;
        }
        setMeasuredDimension(j, i);
    }

    public void setOrientation(int i)
    {
        if (i != 0 && i != 1)
        {
            throw new IllegalArgumentException("invalid orientation.");
        }
        assertNotInLayoutOrScroll(null);
        if (i == mOrientation)
        {
            return;
        } else
        {
            mOrientation = i;
            OrientationHelper orientationhelper = mPrimaryOrientation;
            mPrimaryOrientation = mSecondaryOrientation;
            mSecondaryOrientation = orientationhelper;
            requestLayout();
            return;
        }
    }

    public void setReverseLayout(boolean flag)
    {
        assertNotInLayoutOrScroll(null);
        if (mPendingSavedState != null && mPendingSavedState.mReverseLayout != flag)
        {
            mPendingSavedState.mReverseLayout = flag;
        }
        mReverseLayout = flag;
        requestLayout();
    }

    public void setSpanCount(int i)
    {
        assertNotInLayoutOrScroll(null);
        if (i != mSpanCount)
        {
            invalidateSpanAssignments();
            mSpanCount = i;
            mRemainingSpans = new BitSet(mSpanCount);
            mSpans = new Span[mSpanCount];
            for (i = 0; i < mSpanCount; i++)
            {
                mSpans[i] = new Span(i);
            }

            requestLayout();
        }
    }

    public void smoothScrollToPosition(RecyclerView recyclerview, RecyclerView.State state, int i)
    {
        recyclerview = new LinearSmoothScroller(recyclerview.getContext());
        recyclerview.setTargetPosition(i);
        startSmoothScroll(recyclerview);
    }

    public boolean supportsPredictiveItemAnimations()
    {
        return mPendingSavedState == null;
    }

    boolean updateAnchorFromPendingData(RecyclerView.State state, AnchorInfo anchorinfo)
    {
        boolean flag = false;
        if (state.isPreLayout() || mPendingScrollPosition == -1)
        {
            return false;
        }
        if (mPendingScrollPosition < 0 || mPendingScrollPosition >= state.getItemCount())
        {
            mPendingScrollPosition = -1;
            mPendingScrollPositionOffset = 0x80000000;
            return false;
        }
        if (mPendingSavedState == null || mPendingSavedState.mAnchorPosition == -1 || mPendingSavedState.mSpanOffsetsSize < 1)
        {
            state = findViewByPosition(mPendingScrollPosition);
            if (state != null)
            {
                int i;
                if (mShouldReverseLayout)
                {
                    i = getLastChildPosition();
                } else
                {
                    i = getFirstChildPosition();
                }
                anchorinfo.mPosition = i;
                if (mPendingScrollPositionOffset != 0x80000000)
                {
                    if (anchorinfo.mLayoutFromEnd)
                    {
                        anchorinfo.mOffset = mPrimaryOrientation.getEndAfterPadding() - mPendingScrollPositionOffset - mPrimaryOrientation.getDecoratedEnd(state);
                        return true;
                    } else
                    {
                        anchorinfo.mOffset = (mPrimaryOrientation.getStartAfterPadding() + mPendingScrollPositionOffset) - mPrimaryOrientation.getDecoratedStart(state);
                        return true;
                    }
                }
                if (mPrimaryOrientation.getDecoratedMeasurement(state) > mPrimaryOrientation.getTotalSpace())
                {
                    if (anchorinfo.mLayoutFromEnd)
                    {
                        i = mPrimaryOrientation.getEndAfterPadding();
                    } else
                    {
                        i = mPrimaryOrientation.getStartAfterPadding();
                    }
                    anchorinfo.mOffset = i;
                    return true;
                }
                i = mPrimaryOrientation.getDecoratedStart(state) - mPrimaryOrientation.getStartAfterPadding();
                if (i < 0)
                {
                    anchorinfo.mOffset = -i;
                    return true;
                }
                i = mPrimaryOrientation.getEndAfterPadding() - mPrimaryOrientation.getDecoratedEnd(state);
                if (i < 0)
                {
                    anchorinfo.mOffset = i;
                    return true;
                } else
                {
                    anchorinfo.mOffset = 0x80000000;
                    return true;
                }
            }
            anchorinfo.mPosition = mPendingScrollPosition;
            if (mPendingScrollPositionOffset == 0x80000000)
            {
                if (calculateScrollDirectionForPosition(anchorinfo.mPosition) == 1)
                {
                    flag = true;
                }
                anchorinfo.mLayoutFromEnd = flag;
                anchorinfo.assignCoordinateFromPadding();
            } else
            {
                anchorinfo.assignCoordinateFromPadding(mPendingScrollPositionOffset);
            }
            anchorinfo.mInvalidateOffsets = true;
            return true;
        } else
        {
            anchorinfo.mOffset = 0x80000000;
            anchorinfo.mPosition = mPendingScrollPosition;
            return true;
        }
    }

    void updateAnchorInfoForLayout(RecyclerView.State state, AnchorInfo anchorinfo)
    {
        while (updateAnchorFromPendingData(state, anchorinfo) || updateAnchorFromChildren(state, anchorinfo)) 
        {
            return;
        }
        anchorinfo.assignCoordinateFromPadding();
        anchorinfo.mPosition = 0;
    }

    void updateMeasureSpecs(int i)
    {
        mSizePerSpan = i / mSpanCount;
        mFullSizeSpec = android.view.View.MeasureSpec.makeMeasureSpec(i, mSecondaryOrientation.getMode());
    }
}
