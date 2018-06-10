// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.database.DataSetObserver;

// Referenced classes of package android.support.v4.widget:
//            CursorAdapter

private class this._cls0 extends DataSetObserver
{

    final CursorAdapter this$0;

    public void onChanged()
    {
        mDataValid = true;
        notifyDataSetChanged();
    }

    public void onInvalidated()
    {
        mDataValid = false;
        notifyDataSetInvalidated();
    }

    ()
    {
        this$0 = CursorAdapter.this;
        super();
    }
}
