// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.design.internal.SnackbarContentLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

// Referenced classes of package android.support.design.widget:
//            BaseTransientBottomBar, CoordinatorLayout

public final class Snackbar extends BaseTransientBottomBar
{
    public static class Callback extends BaseTransientBottomBar.BaseCallback
    {

        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        public void onDismissed(Snackbar snackbar, int i)
        {
        }

        public volatile void onDismissed(Object obj, int i)
        {
            onDismissed((Snackbar)obj, i);
        }

        public void onShown(Snackbar snackbar)
        {
        }

        public volatile void onShown(Object obj)
        {
            onShown((Snackbar)obj);
        }

        public Callback()
        {
        }
    }

    public static final class SnackbarLayout extends BaseTransientBottomBar.SnackbarBaseLayout
    {

        protected void onMeasure(int i, int j)
        {
            super.onMeasure(i, j);
            j = getChildCount();
            int k = getMeasuredWidth();
            int l = getPaddingLeft();
            int i1 = getPaddingRight();
            for (i = 0; i < j; i++)
            {
                View view = getChildAt(i);
                if (view.getLayoutParams().width == -1)
                {
                    view.measure(android.view.View.MeasureSpec.makeMeasureSpec(k - l - i1, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 0x40000000));
                }
            }

        }

        public SnackbarLayout(Context context)
        {
            super(context);
        }

        public SnackbarLayout(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }
    }


    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;
    private BaseTransientBottomBar.BaseCallback mCallback;

    private Snackbar(ViewGroup viewgroup, View view, BaseTransientBottomBar.ContentViewCallback contentviewcallback)
    {
        super(viewgroup, view, contentviewcallback);
    }

    private static ViewGroup findSuitableParent(View view)
    {
        ViewGroup viewgroup1 = null;
        View view1 = view;
        do
        {
            if (view1 instanceof CoordinatorLayout)
            {
                return (ViewGroup)view1;
            }
            ViewGroup viewgroup = viewgroup1;
            if (view1 instanceof FrameLayout)
            {
                if (view1.getId() == 0x1020002)
                {
                    return (ViewGroup)view1;
                }
                viewgroup = (ViewGroup)view1;
            }
            view = view1;
            if (view1 != null)
            {
                view = view1.getParent();
                if (view instanceof View)
                {
                    view = (View)view;
                } else
                {
                    view = null;
                }
            }
            viewgroup1 = viewgroup;
            view1 = view;
        } while (view != null);
        return viewgroup;
    }

    public static Snackbar make(View view, int i, int j)
    {
        return make(view, view.getResources().getText(i), j);
    }

    public static Snackbar make(View view, CharSequence charsequence, int i)
    {
        view = findSuitableParent(view);
        if (view == null)
        {
            throw new IllegalArgumentException("No suitable parent found from the given view. Please provide a valid view.");
        } else
        {
            SnackbarContentLayout snackbarcontentlayout = (SnackbarContentLayout)LayoutInflater.from(view.getContext()).inflate(android.support.design.R.layout.design_layout_snackbar_include, view, false);
            view = new Snackbar(view, snackbarcontentlayout, snackbarcontentlayout);
            view.setText(charsequence);
            view.setDuration(i);
            return view;
        }
    }

    public Snackbar setAction(int i, android.view.View.OnClickListener onclicklistener)
    {
        return setAction(getContext().getText(i), onclicklistener);
    }

    public Snackbar setAction(CharSequence charsequence, final android.view.View.OnClickListener listener)
    {
        android.widget.Button button = ((SnackbarContentLayout)mView.getChildAt(0)).getActionView();
        if (TextUtils.isEmpty(charsequence) || listener == null)
        {
            button.setVisibility(8);
            button.setOnClickListener(null);
            return this;
        } else
        {
            button.setVisibility(0);
            button.setText(charsequence);
            button.setOnClickListener(new android.view.View.OnClickListener() {

                final Snackbar this$0;
                final android.view.View.OnClickListener val$listener;

                public void onClick(View view)
                {
                    listener.onClick(view);
                    dispatchDismiss(1);
                }

            
            {
                this$0 = Snackbar.this;
                listener = onclicklistener;
                super();
            }
            });
            return this;
        }
    }

    public Snackbar setActionTextColor(int i)
    {
        ((SnackbarContentLayout)mView.getChildAt(0)).getActionView().setTextColor(i);
        return this;
    }

    public Snackbar setActionTextColor(ColorStateList colorstatelist)
    {
        ((SnackbarContentLayout)mView.getChildAt(0)).getActionView().setTextColor(colorstatelist);
        return this;
    }

    public Snackbar setCallback(Callback callback)
    {
        if (mCallback != null)
        {
            removeCallback(mCallback);
        }
        if (callback != null)
        {
            addCallback(callback);
        }
        mCallback = callback;
        return this;
    }

    public Snackbar setText(int i)
    {
        return setText(getContext().getText(i));
    }

    public Snackbar setText(CharSequence charsequence)
    {
        ((SnackbarContentLayout)mView.getChildAt(0)).getMessageView().setText(charsequence);
        return this;
    }
}
