// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v7.view.menu.MenuBuilder;
import android.view.MenuItem;

// Referenced classes of package android.support.v7.widget:
//            ActionMenuView

private class this._cls0
    implements android.support.v7.view.menu.s._cls0
{

    final ActionMenuView this$0;

    public boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
    {
        return mOnMenuItemClickListener != null && mOnMenuItemClickListener.onMenuItemClick(menuitem);
    }

    public void onMenuModeChange(MenuBuilder menubuilder)
    {
        if (mMenuBuilderCallback != null)
        {
            mMenuBuilderCallback.ge(menubuilder);
        }
    }

    ner()
    {
        this$0 = ActionMenuView.this;
        super();
    }
}
