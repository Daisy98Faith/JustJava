// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.ShowableListMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            ForwardingListener

public class PopupMenu
{
    public static interface OnDismissListener
    {

        public abstract void onDismiss(PopupMenu popupmenu);
    }

    public static interface OnMenuItemClickListener
    {

        public abstract boolean onMenuItemClick(MenuItem menuitem);
    }


    private final View mAnchor;
    private final Context mContext;
    private android.view.View.OnTouchListener mDragListener;
    private final MenuBuilder mMenu;
    OnMenuItemClickListener mMenuItemClickListener;
    OnDismissListener mOnDismissListener;
    final MenuPopupHelper mPopup;

    public PopupMenu(Context context, View view)
    {
        this(context, view, 0);
    }

    public PopupMenu(Context context, View view, int i)
    {
        this(context, view, i, android.support.v7.appcompat.R.attr.popupMenuStyle, 0);
    }

    public PopupMenu(Context context, View view, int i, int j, int k)
    {
        mContext = context;
        mAnchor = view;
        mMenu = new MenuBuilder(context);
        mMenu.setCallback(new android.support.v7.view.menu.MenuBuilder.Callback() {

            final PopupMenu this$0;

            public boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
            {
                if (mMenuItemClickListener != null)
                {
                    return mMenuItemClickListener.onMenuItemClick(menuitem);
                } else
                {
                    return false;
                }
            }

            public void onMenuModeChange(MenuBuilder menubuilder)
            {
            }

            
            {
                this$0 = PopupMenu.this;
                super();
            }
        });
        mPopup = new MenuPopupHelper(context, mMenu, view, false, j, k);
        mPopup.setGravity(i);
        mPopup.setOnDismissListener(new android.widget.PopupWindow.OnDismissListener() {

            final PopupMenu this$0;

            public void onDismiss()
            {
                if (mOnDismissListener != null)
                {
                    mOnDismissListener.onDismiss(PopupMenu.this);
                }
            }

            
            {
                this$0 = PopupMenu.this;
                super();
            }
        });
    }

    public void dismiss()
    {
        mPopup.dismiss();
    }

    public android.view.View.OnTouchListener getDragToOpenListener()
    {
        if (mDragListener == null)
        {
            mDragListener = new ForwardingListener(mAnchor) {

                final PopupMenu this$0;

                public ShowableListMenu getPopup()
                {
                    return mPopup.getPopup();
                }

                protected boolean onForwardingStarted()
                {
                    show();
                    return true;
                }

                protected boolean onForwardingStopped()
                {
                    dismiss();
                    return true;
                }

            
            {
                this$0 = PopupMenu.this;
                super(view);
            }
            };
        }
        return mDragListener;
    }

    public int getGravity()
    {
        return mPopup.getGravity();
    }

    public Menu getMenu()
    {
        return mMenu;
    }

    public MenuInflater getMenuInflater()
    {
        return new SupportMenuInflater(mContext);
    }

    public void inflate(int i)
    {
        getMenuInflater().inflate(i, mMenu);
    }

    public void setGravity(int i)
    {
        mPopup.setGravity(i);
    }

    public void setOnDismissListener(OnDismissListener ondismisslistener)
    {
        mOnDismissListener = ondismisslistener;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onmenuitemclicklistener)
    {
        mMenuItemClickListener = onmenuitemclicklistener;
    }

    public void show()
    {
        mPopup.show();
    }
}
