// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView

public class DividerItemDecoration extends RecyclerView.ItemDecoration
{

    private static final int ATTRS[] = {
        0x1010214
    };
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private final Rect mBounds = new Rect();
    private Drawable mDivider;
    private int mOrientation;

    public DividerItemDecoration(Context context, int i)
    {
        context = context.obtainStyledAttributes(ATTRS);
        mDivider = context.getDrawable(0);
        context.recycle();
        setOrientation(i);
    }

    private void drawHorizontal(Canvas canvas, RecyclerView recyclerview)
    {
        canvas.save();
        int i;
        int j;
        int l;
        if (recyclerview.getClipToPadding())
        {
            j = recyclerview.getPaddingTop();
            i = recyclerview.getHeight() - recyclerview.getPaddingBottom();
            canvas.clipRect(recyclerview.getPaddingLeft(), j, recyclerview.getWidth() - recyclerview.getPaddingRight(), i);
        } else
        {
            j = 0;
            i = recyclerview.getHeight();
        }
        l = recyclerview.getChildCount();
        for (int k = 0; k < l; k++)
        {
            View view = recyclerview.getChildAt(k);
            recyclerview.getLayoutManager().getDecoratedBoundsWithMargins(view, mBounds);
            int i1 = mBounds.right + Math.round(view.getTranslationX());
            int j1 = mDivider.getIntrinsicWidth();
            mDivider.setBounds(i1 - j1, j, i1, i);
            mDivider.draw(canvas);
        }

        canvas.restore();
    }

    private void drawVertical(Canvas canvas, RecyclerView recyclerview)
    {
        canvas.save();
        int i;
        int j;
        int l;
        if (recyclerview.getClipToPadding())
        {
            i = recyclerview.getPaddingLeft();
            j = recyclerview.getWidth() - recyclerview.getPaddingRight();
            canvas.clipRect(i, recyclerview.getPaddingTop(), j, recyclerview.getHeight() - recyclerview.getPaddingBottom());
        } else
        {
            i = 0;
            j = recyclerview.getWidth();
        }
        l = recyclerview.getChildCount();
        for (int k = 0; k < l; k++)
        {
            View view = recyclerview.getChildAt(k);
            recyclerview.getDecoratedBoundsWithMargins(view, mBounds);
            int i1 = mBounds.bottom + Math.round(view.getTranslationY());
            int j1 = mDivider.getIntrinsicHeight();
            mDivider.setBounds(i, i1 - j1, j, i1);
            mDivider.draw(canvas);
        }

        canvas.restore();
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerview, RecyclerView.State state)
    {
        if (mOrientation == 1)
        {
            rect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            return;
        } else
        {
            rect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            return;
        }
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerview, RecyclerView.State state)
    {
        if (recyclerview.getLayoutManager() == null)
        {
            return;
        }
        if (mOrientation == 1)
        {
            drawVertical(canvas, recyclerview);
            return;
        } else
        {
            drawHorizontal(canvas, recyclerview);
            return;
        }
    }

    public void setDrawable(Drawable drawable)
    {
        if (drawable == null)
        {
            throw new IllegalArgumentException("Drawable cannot be null.");
        } else
        {
            mDivider = drawable;
            return;
        }
    }

    public void setOrientation(int i)
    {
        if (i != 0 && i != 1)
        {
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        } else
        {
            mOrientation = i;
            return;
        }
    }

}
