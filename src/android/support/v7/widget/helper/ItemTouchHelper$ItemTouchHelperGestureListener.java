// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget.helper;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

// Referenced classes of package android.support.v7.widget.helper:
//            ItemTouchHelper

private class this._cls0 extends android.view.lperGestureListener
{

    final ItemTouchHelper this$0;

    public boolean onDown(MotionEvent motionevent)
    {
        return true;
    }

    public void onLongPress(MotionEvent motionevent)
    {
        Object obj;
        obj = findChildView(motionevent);
        if (obj != null)
        {
            obj = mRecyclerView.getChildViewHolder(((android.view.View) (obj)));
            break MISSING_BLOCK_LABEL_29;
        }
_L1:
        return;
        if (obj != null && mCallback._mth0(mRecyclerView, ((android.support.v7.widget.stener) (obj))) && motionevent.getPointerId(0) == mActivePointerId)
        {
            int i = motionevent.findPointerIndex(mActivePointerId);
            float f = motionevent.getX(i);
            float f1 = motionevent.getY(i);
            mInitialTouchX = f;
            mInitialTouchY = f1;
            motionevent = ItemTouchHelper.this;
            mDy = 0.0F;
            motionevent.mDx = 0.0F;
            if (mCallback.())
            {
                select(((android.support.v7.widget.Enabled) (obj)), 2);
                return;
            }
        }
          goto _L1
    }

    ()
    {
        this$0 = ItemTouchHelper.this;
        super();
    }
}
