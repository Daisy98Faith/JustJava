// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.KeyEvent;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            SearchView

class this._cls0
    implements android.view.tener
{

    final SearchView this$0;

    public boolean onKey(View view, int i, KeyEvent keyevent)
    {
        if (mSearchable != null)
        {
            if (mSearchSrcTextView.isPopupShowing() && mSearchSrcTextView.getListSelection() != -1)
            {
                return onSuggestionsKey(view, i, keyevent);
            }
            if (!archAutoComplete.access._mth000(mSearchSrcTextView) && keyevent.hasNoModifiers() && keyevent.getAction() == 1 && i == 66)
            {
                view.cancelLongPress();
                launchQuerySearch(0, null, mSearchSrcTextView.getText().toString());
                return true;
            }
        }
        return false;
    }

    archAutoComplete()
    {
        this$0 = SearchView.this;
        super();
    }
}
