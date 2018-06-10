// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

// Referenced classes of package android.support.v7.widget:
//            ScrollingTabContainerView, LinearLayoutCompat

private class this._cls0 extends BaseAdapter
{

    final ScrollingTabContainerView this$0;

    public int getCount()
    {
        return mTabLayout.getChildCount();
    }

    public Object getItem(int i)
    {
        return ((this._cls0)mTabLayout.getChildAt(i)).Tab();
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if (view == null)
        {
            return createTabView((android.support.v7.app.bView)getItem(i), true);
        } else
        {
            ((getItem)view).dTab((android.support.v7.app.bindTab)getItem(i));
            return view;
        }
    }

    ()
    {
        this$0 = ScrollingTabContainerView.this;
        super();
    }
}
