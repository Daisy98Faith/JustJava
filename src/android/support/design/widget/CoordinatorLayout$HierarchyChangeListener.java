// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.view.View;

// Referenced classes of package android.support.design.widget:
//            CoordinatorLayout

private class this._cls0
    implements android.view.hyChangeListener
{

    final CoordinatorLayout this$0;

    public void onChildViewAdded(View view, View view1)
    {
        if (mOnHierarchyChangeListener != null)
        {
            mOnHierarchyChangeListener.dViewAdded(view, view1);
        }
    }

    public void onChildViewRemoved(View view, View view1)
    {
        onChildViewsChanged(2);
        if (mOnHierarchyChangeListener != null)
        {
            mOnHierarchyChangeListener.dViewRemoved(view, view1);
        }
    }

    ()
    {
        this$0 = CoordinatorLayout.this;
        super();
    }
}
