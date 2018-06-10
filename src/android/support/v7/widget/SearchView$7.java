// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            SearchView

class this._cls0
    implements android.view.istener
{

    final SearchView this$0;

    public void onClick(View view)
    {
        if (view == mSearchButton)
        {
            onSearchClicked();
        } else
        {
            if (view == mCloseButton)
            {
                onCloseClicked();
                return;
            }
            if (view == mGoButton)
            {
                onSubmitQuery();
                return;
            }
            if (view == mVoiceButton)
            {
                onVoiceClicked();
                return;
            }
            if (view == mSearchSrcTextView)
            {
                forceSuggestionQuery();
                return;
            }
        }
    }

    ()
    {
        this$0 = SearchView.this;
        super();
    }
}
