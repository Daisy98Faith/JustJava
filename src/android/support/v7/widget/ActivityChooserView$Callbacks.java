// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ActionProvider;
import android.view.View;
import android.widget.AdapterView;

// Referenced classes of package android.support.v7.widget:
//            ActivityChooserView, ActivityChooserModel

private class this._cls0
    implements android.widget.r, android.view.ner, android.view.ner, android.widget.r
{

    final ActivityChooserView this$0;

    private void notifyOnDismissListener()
    {
        if (mOnDismissListener != null)
        {
            mOnDismissListener.onDismiss();
        }
    }

    public void onClick(View view)
    {
        if (view == mDefaultActivityButton)
        {
            dismissPopup();
            view = mAdapter.getDefaultActivity();
            int i = mAdapter.getDataModel().getActivityIndex(view);
            view = mAdapter.getDataModel().chooseActivity(i);
            if (view != null)
            {
                view.addFlags(0x80000);
                getContext().startActivity(view);
            }
            return;
        }
        if (view == mExpandActivityOverflowButton)
        {
            mIsSelectingDefaultActivity = false;
            showPopupUnchecked(mInitialActivityCount);
            return;
        } else
        {
            throw new IllegalArgumentException();
        }
    }

    public void onDismiss()
    {
        notifyOnDismissListener();
        if (mProvider != null)
        {
            mProvider.subUiVisibilityChanged(false);
        }
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        ((ooserViewAdapter)adapterview.getAdapter()).getItemViewType(i);
        JVM INSTR tableswitch 0 1: default 32
    //                   0 50
    //                   1 40;
           goto _L1 _L2 _L3
_L1:
        throw new IllegalArgumentException();
_L3:
        showPopupUnchecked(0x7fffffff);
_L5:
        return;
_L2:
        dismissPopup();
        if (!mIsSelectingDefaultActivity)
        {
            break; /* Loop/switch isn't completed */
        }
        if (i > 0)
        {
            mAdapter.getDataModel().setDefaultActivity(i);
            return;
        }
        if (true) goto _L5; else goto _L4
_L4:
        if (!mAdapter.getShowDefaultActivity())
        {
            i++;
        }
        adapterview = mAdapter.getDataModel().chooseActivity(i);
        if (adapterview != null)
        {
            adapterview.addFlags(0x80000);
            getContext().startActivity(adapterview);
            return;
        }
        if (true) goto _L5; else goto _L6
_L6:
    }

    public boolean onLongClick(View view)
    {
        if (view == mDefaultActivityButton)
        {
            if (mAdapter.getCount() > 0)
            {
                mIsSelectingDefaultActivity = true;
                showPopupUnchecked(mInitialActivityCount);
            }
            return true;
        } else
        {
            throw new IllegalArgumentException();
        }
    }

    ooserViewAdapter()
    {
        this$0 = ActivityChooserView.this;
        super();
    }
}
