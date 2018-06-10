// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;

// Referenced classes of package android.support.v4.view:
//            ViewCompatICS, ViewCompat

private static class mAnchor
    implements android.view.tipHandler, android.view.tipHandler
{

    private final View mAnchor;
    private final Runnable mShowRunnable = new Runnable() {

        final ViewCompatICS.TooltipHandler this$0;

        public void run()
        {
            show(1);
        }

            
            {
                this$0 = ViewCompatICS.TooltipHandler.this;
                super();
            }
    };
    private Toast mTooltip;
    private final CharSequence mTooltipText;

    private void hide()
    {
        if (mTooltip != null)
        {
            mTooltip.cancel();
            mTooltip = null;
        }
        mAnchor.getHandler().removeCallbacks(mShowRunnable);
    }

    private void show(int i)
    {
        Context context = mAnchor.getContext();
        Resources resources = context.getResources();
        int l = resources.getDisplayMetrics().widthPixels;
        int k = resources.getDisplayMetrics().heightPixels;
        Rect rect = new Rect();
        mAnchor.getWindowVisibleDisplayFrame(rect);
        int j;
        if (rect.left < 0 && rect.top < 0)
        {
            j = resources.getIdentifier("status_bar_height", "dimen", "android");
            int ai[];
            if (j > 0)
            {
                j = resources.getDimensionPixelSize(j);
            } else
            {
                j = 0;
            }
            rect.set(0, j, l, k);
        }
        ai = new int[2];
        mAnchor.getLocationOnScreen(ai);
        k = ai[0] + mAnchor.getWidth() / 2;
        j = k;
        if (ViewCompat.getLayoutDirection(mAnchor) == 0)
        {
            j = l - k;
        }
        k = ai[1];
        hide();
        mTooltip = Toast.makeText(context, mTooltipText, i);
        if ((double)k < (double)rect.height() * 0.80000000000000004D)
        {
            mTooltip.setGravity(0x800035, j, (mAnchor.getHeight() + k) - rect.top);
        } else
        {
            mTooltip.setGravity(0x800055, j, rect.bottom - k);
        }
        mTooltip.show();
    }

    public boolean onHover(View view, MotionEvent motionevent)
    {
        view = (AccessibilityManager)mAnchor.getContext().getSystemService("accessibility");
        if (!view.isEnabled() || !AccessibilityManagerCompat.isTouchExplorationEnabled(view))
        {
            int i = motionevent.getAction();
            if (i == 7)
            {
                hide();
                mAnchor.getHandler().postDelayed(mShowRunnable, ViewConfiguration.getLongPressTimeout());
                return false;
            }
            if (i == 10)
            {
                hide();
                return false;
            }
        }
        return false;
    }

    public boolean onLongClick(View view)
    {
        show(0);
        return true;
    }


    _cls1.this._cls0(View view, CharSequence charsequence)
    {
        mAnchor = view;
        mTooltipText = charsequence;
        mAnchor.setOnLongClickListener(this);
        mAnchor.setOnHoverListener(this);
    }
}
