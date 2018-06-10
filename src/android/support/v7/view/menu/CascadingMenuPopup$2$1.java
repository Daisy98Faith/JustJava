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

class val.menu
    implements Runnable
{

    final val.item this$1;
    final MenuItem val$item;
    final MenuBuilder val$menu;
    final adingMenuInfo val$nextInfo;

    public void run()
    {
        if (val$nextInfo != null)
        {
            mShouldCloseImmediately = true;
            val$nextInfo.menu.close(false);
            mShouldCloseImmediately = false;
        }
        if (val$item.isEnabled() && val$item.hasSubMenu())
        {
            val$menu.performItemAction(val$item, 4);
        }
    }

    adingMenuInfo()
    {
        this$1 = final_adingmenuinfo;
        val$nextInfo = adingmenuinfo1;
        val$item = menuitem;
        val$menu = MenuBuilder.this;
        super();
    }

    // Unreferenced inner class android/support/v7/view/menu/CascadingMenuPopup$2

/* anonymous class */
    class CascadingMenuPopup._cls2
        implements MenuItemHoverListener
    {

        final CascadingMenuPopup this$0;

        public void onItemHoverEnter(MenuBuilder menubuilder, final MenuItem item)
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
                        if (menubuilder != ((CascadingMenuPopup.CascadingMenuInfo)mShowingMenus.get(i)).menu)
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
            final CascadingMenuPopup.CascadingMenuInfo nextInfo;
            long l;
            if (i < mShowingMenus.size())
            {
                nextInfo = (CascadingMenuPopup.CascadingMenuInfo)mShowingMenus.get(i);
            } else
            {
                nextInfo = null;
            }
            item = menubuilder. new CascadingMenuPopup._cls2._cls1();
            l = SystemClock.uptimeMillis();
            mSubMenuHoverHandler.postAtTime(item, menubuilder, l + 200L);
        }

        public void onItemHoverExit(MenuBuilder menubuilder, MenuItem menuitem)
        {
            mSubMenuHoverHandler.removeCallbacksAndMessages(menubuilder);
        }

            
            {
                this$0 = CascadingMenuPopup.this;
                super();
            }
    }

}
