// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;

// Referenced classes of package android.support.v7.app:
//            AlertController

class sCheckedColumn extends CursorAdapter
{

    private final int mIsCheckedIndex;
    private final int mLabelIndex;
    final emLayout this$0;
    final AlertController val$dialog;
    final w val$listView;

    public void bindView(View view, Context context, Cursor cursor)
    {
        boolean flag = true;
        ((CheckedTextView)view.findViewById(0x1020014)).setText(cursor.getString(mLabelIndex));
        view = val$listView;
        int i = cursor.getPosition();
        if (cursor.getInt(mIsCheckedIndex) != 1)
        {
            flag = false;
        }
        view.setItemChecked(i, flag);
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewgroup)
    {
        return nflater.inflate(val$dialog.mMultiChoiceItemLayout, viewgroup, false);
    }

    w(boolean flag, w w1, AlertController alertcontroller)
    {
        this$0 = final_w;
        val$listView = w1;
        val$dialog = alertcontroller;
        super(final_context, Cursor.this, flag);
        final_w = getCursor();
        mLabelIndex = final_w.getColumnIndexOrThrow(abelColumn);
        mIsCheckedIndex = final_w.getColumnIndexOrThrow(sCheckedColumn);
    }
}
