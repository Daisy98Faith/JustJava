// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget.helper;

import android.support.v7.widget.RecyclerView;

// Referenced classes of package android.support.v7.widget.helper:
//            ItemTouchHelper

class this._cls0
    implements android.support.v7.widget.DrawingOrderCallback
{

    final ItemTouchHelper this$0;

    public int onGetChildDrawingOrder(int i, int j)
    {
        if (mOverdrawChild != null)
        {
            int l = mOverdrawChildPosition;
            int k = l;
            if (l == -1)
            {
                k = mRecyclerView.indexOfChild(mOverdrawChild);
                mOverdrawChildPosition = k;
            }
            if (j == i - 1)
            {
                return k;
            }
            if (j >= k)
            {
                return j + 1;
            }
        }
        return j;
    }

    OrderCallback()
    {
        this$0 = ItemTouchHelper.this;
        super();
    }
}
