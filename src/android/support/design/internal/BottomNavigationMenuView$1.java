// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.support.v7.view.menu.MenuBuilder;
import android.view.MenuItem;
import android.view.View;

// Referenced classes of package android.support.design.internal:
//            BottomNavigationMenuView, BottomNavigationItemView

class this._cls0
    implements android.view.vigationMenuView._cls1
{

    final BottomNavigationMenuView this$0;

    public void onClick(View view)
    {
        view = ((BottomNavigationItemView)view).getItemData();
        if (!BottomNavigationMenuView.access$100(BottomNavigationMenuView.this).performItemAction(view, BottomNavigationMenuView.access$000(BottomNavigationMenuView.this), 0))
        {
            view.setChecked(true);
        }
    }

    ()
    {
        this$0 = BottomNavigationMenuView.this;
        super();
    }
}
