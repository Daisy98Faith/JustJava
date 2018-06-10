// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.support.v7.widget.MenuPopupWindow;
import android.view.View;

// Referenced classes of package android.support.v7.view.menu:
//            StandardMenuPopup

class this._cls0
    implements android.view.lobalLayoutListener
{

    final StandardMenuPopup this$0;

    public void onGlobalLayout()
    {
label0:
        {
            if (isShowing() && !mPopup.isModal())
            {
                View view = mShownAnchorView;
                if (view != null && view.isShown())
                {
                    break label0;
                }
                dismiss();
            }
            return;
        }
        mPopup.show();
    }

    ner()
    {
        this$0 = StandardMenuPopup.this;
        super();
    }
}
