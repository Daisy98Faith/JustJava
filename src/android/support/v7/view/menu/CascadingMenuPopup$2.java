// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.widget.MenuItemHoverListener;
import android.view.MenuItem;
import java.util.List;

// Referenced classes of package android.support.v7.view.menu:
//            CascadingMenuPopup, MenuBuilder

class this._cls0
    implements MenuItemHoverListener
{

    final CascadingMenuPopup this$0;

    public void onItemHoverEnter(final MenuBuilder menu, final MenuItem item)
    {
        mSubMenuHoverHandler.removeCallbacksAndMessages(null);
        byte byte0 = -1;
        int i = 0;
        int k = mShowingMenus.size();
        int j;
label0:
        do
        {
label1:
            {
                j = byte0;
                if (i < k)
                {
                    if (menu != ((scadingMenuInfo)mShowingMenus.get(i)).menu)
                    {
                        break label1;
                    }
                    j = i;
                }
                if (j == -1)
                {
                    return;
                }
                break label0;
            }
            i++;
        } while (true);
        i = j + 1;
        final scadingMenuInfo nextInfo;
        long l;
        if (i < mShowingMenus.size())
        {
            nextInfo = (scadingMenuInfo)mShowingMenus.get(i);
        } else
        {
            nextInfo = null;
        }
        item = new Runnable() {

            final CascadingMenuPopup._cls2 this$1;
            final MenuItem val$item;
            final MenuBuilder val$menu;
            final CascadingMenuPopup.CascadingMenuInfo val$nextInfo;

            public void run()
            {
                if (nextInfo != null)
                {
                    mShouldCloseImmediately = true;
                    nextInfo.menu.close(false);
                    mShouldCloseImmediately = false;
                }
                if (item.isEnabled() && item.hasSubMenu())
                {
                    menu.performItemAction(item, 4);
                }
            }

            
            {
                this$1 = CascadingMenuPopup._cls2.this;
                nextInfo = cascadingmenuinfo;
                item = menuitem;
                menu = menubuilder;
                super();
            }
        };
        l = SystemClock.uptimeMillis();
        mSubMenuHoverHandler.postAtTime(item, menu, l + 200L);
    }

    public void onItemHoverExit(MenuBuilder menubuilder, MenuItem menuitem)
    {
        mSubMenuHoverHandler.removeCallbacksAndMessages(menubuilder);
    }

    _cls1.val.menu()
    {
        this$0 = CascadingMenuPopup.this;
        super();
    }
}
