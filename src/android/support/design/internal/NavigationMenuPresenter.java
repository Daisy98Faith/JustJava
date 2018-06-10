// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

// Referenced classes of package android.support.design.internal:
//            NavigationMenuView, ParcelableSparseArray, NavigationMenuItemView

public class NavigationMenuPresenter
    implements MenuPresenter
{
    private static class HeaderViewHolder extends ViewHolder
    {

        public HeaderViewHolder(View view)
        {
            super(view);
        }
    }

    private class NavigationMenuAdapter extends android.support.v7.widget.RecyclerView.Adapter
    {

        private static final String STATE_ACTION_VIEWS = "android:menu:action_views";
        private static final String STATE_CHECKED_ITEM = "android:menu:checked";
        private static final int VIEW_TYPE_HEADER = 3;
        private static final int VIEW_TYPE_NORMAL = 0;
        private static final int VIEW_TYPE_SEPARATOR = 2;
        private static final int VIEW_TYPE_SUBHEADER = 1;
        private MenuItemImpl mCheckedItem;
        private final ArrayList mItems = new ArrayList();
        private boolean mUpdateSuspended;
        final NavigationMenuPresenter this$0;

        private void appendTransparentIconIfMissing(int i, int j)
        {
            for (; i < j; i++)
            {
                ((NavigationMenuTextItem)mItems.get(i)).needsEmptyIcon = true;
            }

        }

        private void prepareMenuItems()
        {
            if (mUpdateSuspended)
            {
                return;
            }
            mUpdateSuspended = true;
            mItems.clear();
            mItems.add(new NavigationMenuHeaderItem());
            byte byte1 = -1;
            int j = 0;
            boolean flag1 = false;
            int l = 0;
            int j1 = mMenu.getVisibleItems().size();
            while (l < j1) 
            {
                Object obj = (MenuItemImpl)mMenu.getVisibleItems().get(l);
                if (((MenuItemImpl) (obj)).isChecked())
                {
                    setCheckedItem(((MenuItemImpl) (obj)));
                }
                if (((MenuItemImpl) (obj)).isCheckable())
                {
                    ((MenuItemImpl) (obj)).setExclusiveCheckable(false);
                }
                int k;
                int i1;
                boolean flag;
                if (((MenuItemImpl) (obj)).hasSubMenu())
                {
                    SubMenu submenu = ((MenuItemImpl) (obj)).getSubMenu();
                    flag = flag1;
                    k = byte1;
                    i1 = j;
                    if (submenu.hasVisibleItems())
                    {
                        if (l != 0)
                        {
                            mItems.add(new NavigationMenuSeparatorItem(mPaddingSeparator, 0));
                        }
                        mItems.add(new NavigationMenuTextItem(((MenuItemImpl) (obj))));
                        byte byte0 = 0;
                        int k1 = mItems.size();
                        i1 = 0;
                        for (int l1 = submenu.size(); i1 < l1;)
                        {
                            MenuItemImpl menuitemimpl = (MenuItemImpl)submenu.getItem(i1);
                            k = byte0;
                            if (menuitemimpl.isVisible())
                            {
                                k = byte0;
                                if (byte0 == 0)
                                {
                                    k = byte0;
                                    if (menuitemimpl.getIcon() != null)
                                    {
                                        k = 1;
                                    }
                                }
                                if (menuitemimpl.isCheckable())
                                {
                                    menuitemimpl.setExclusiveCheckable(false);
                                }
                                if (((MenuItemImpl) (obj)).isChecked())
                                {
                                    setCheckedItem(((MenuItemImpl) (obj)));
                                }
                                mItems.add(new NavigationMenuTextItem(menuitemimpl));
                            }
                            i1++;
                            byte0 = k;
                        }

                        flag = flag1;
                        k = byte1;
                        i1 = j;
                        if (byte0 != 0)
                        {
                            appendTransparentIconIfMissing(k1, mItems.size());
                            i1 = j;
                            k = byte1;
                            flag = flag1;
                        }
                    }
                } else
                {
                    k = ((MenuItemImpl) (obj)).getGroupId();
                    int i;
                    if (k != byte1)
                    {
                        j = mItems.size();
                        if (((MenuItemImpl) (obj)).getIcon() != null)
                        {
                            flag1 = true;
                        } else
                        {
                            flag1 = false;
                        }
                        flag = flag1;
                        i = j;
                        if (l != 0)
                        {
                            i = j + 1;
                            mItems.add(new NavigationMenuSeparatorItem(mPaddingSeparator, mPaddingSeparator));
                            flag = flag1;
                        }
                    } else
                    {
                        flag = flag1;
                        i = j;
                        if (!flag1)
                        {
                            flag = flag1;
                            i = j;
                            if (((MenuItemImpl) (obj)).getIcon() != null)
                            {
                                flag = true;
                                appendTransparentIconIfMissing(j, mItems.size());
                                i = j;
                            }
                        }
                    }
                    obj = new NavigationMenuTextItem(((MenuItemImpl) (obj)));
                    obj.needsEmptyIcon = flag;
                    mItems.add(obj);
                    i1 = i;
                }
                l++;
                flag1 = flag;
                byte1 = k;
                j = i1;
            }
            mUpdateSuspended = false;
        }

        public Bundle createInstanceState()
        {
            Bundle bundle = new Bundle();
            if (mCheckedItem != null)
            {
                bundle.putInt("android:menu:checked", mCheckedItem.getItemId());
            }
            SparseArray sparsearray = new SparseArray();
            int i = 0;
            int j = mItems.size();
            while (i < j) 
            {
                Object obj = (NavigationMenuItem)mItems.get(i);
                if (!(obj instanceof NavigationMenuTextItem))
                {
                    continue;
                }
                MenuItemImpl menuitemimpl = ((NavigationMenuTextItem)obj).getMenuItem();
                if (menuitemimpl != null)
                {
                    obj = menuitemimpl.getActionView();
                } else
                {
                    obj = null;
                }
                if (obj != null)
                {
                    ParcelableSparseArray parcelablesparsearray = new ParcelableSparseArray();
                    ((View) (obj)).saveHierarchyState(parcelablesparsearray);
                    sparsearray.put(menuitemimpl.getItemId(), parcelablesparsearray);
                }
                i++;
            }
            bundle.putSparseParcelableArray("android:menu:action_views", sparsearray);
            return bundle;
        }

        public int getItemCount()
        {
            return mItems.size();
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public int getItemViewType(int i)
        {
            NavigationMenuItem navigationmenuitem = (NavigationMenuItem)mItems.get(i);
            if (navigationmenuitem instanceof NavigationMenuSeparatorItem)
            {
                return 2;
            }
            if (navigationmenuitem instanceof NavigationMenuHeaderItem)
            {
                return 3;
            }
            if (navigationmenuitem instanceof NavigationMenuTextItem)
            {
                return !((NavigationMenuTextItem)navigationmenuitem).getMenuItem().hasSubMenu() ? 0 : 1;
            } else
            {
                throw new RuntimeException("Unknown item type.");
            }
        }

        public void onBindViewHolder(ViewHolder viewholder, int i)
        {
            switch (getItemViewType(i))
            {
            default:
                return;

            case 0: // '\0'
                NavigationMenuItemView navigationmenuitemview = (NavigationMenuItemView)viewholder.itemView;
                navigationmenuitemview.setIconTintList(mIconTintList);
                if (mTextAppearanceSet)
                {
                    navigationmenuitemview.setTextAppearance(mTextAppearance);
                }
                if (mTextColor != null)
                {
                    navigationmenuitemview.setTextColor(mTextColor);
                }
                if (mItemBackground != null)
                {
                    viewholder = mItemBackground.getConstantState().newDrawable();
                } else
                {
                    viewholder = null;
                }
                ViewCompat.setBackground(navigationmenuitemview, viewholder);
                viewholder = (NavigationMenuTextItem)mItems.get(i);
                navigationmenuitemview.setNeedsEmptyIcon(((NavigationMenuTextItem) (viewholder)).needsEmptyIcon);
                navigationmenuitemview.initialize(viewholder.getMenuItem(), 0);
                return;

            case 1: // '\001'
                ((TextView)viewholder.itemView).setText(((NavigationMenuTextItem)mItems.get(i)).getMenuItem().getTitle());
                return;

            case 2: // '\002'
                NavigationMenuSeparatorItem navigationmenuseparatoritem = (NavigationMenuSeparatorItem)mItems.get(i);
                viewholder.itemView.setPadding(0, navigationmenuseparatoritem.getPaddingTop(), 0, navigationmenuseparatoritem.getPaddingBottom());
                return;
            }
        }

        public volatile void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewholder, int i)
        {
            onBindViewHolder((ViewHolder)viewholder, i);
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewgroup, int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return new NormalViewHolder(mLayoutInflater, viewgroup, mOnClickListener);

            case 1: // '\001'
                return new SubheaderViewHolder(mLayoutInflater, viewgroup);

            case 2: // '\002'
                return new SeparatorViewHolder(mLayoutInflater, viewgroup);

            case 3: // '\003'
                return new HeaderViewHolder(mHeaderLayout);
            }
        }

        public volatile android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewgroup, int i)
        {
            return onCreateViewHolder(viewgroup, i);
        }

        public void onViewRecycled(ViewHolder viewholder)
        {
            if (viewholder instanceof NormalViewHolder)
            {
                ((NavigationMenuItemView)viewholder.itemView).recycle();
            }
        }

        public volatile void onViewRecycled(android.support.v7.widget.RecyclerView.ViewHolder viewholder)
        {
            onViewRecycled((ViewHolder)viewholder);
        }

        public void restoreInstanceState(Bundle bundle)
        {
            int j = bundle.getInt("android:menu:checked", 0);
            if (j == 0) goto _L2; else goto _L1
_L1:
            int i;
            int l;
            mUpdateSuspended = true;
            i = 0;
            l = mItems.size();
_L9:
            if (i >= l) goto _L4; else goto _L3
_L3:
            Object obj = (NavigationMenuItem)mItems.get(i);
            if (!(obj instanceof NavigationMenuTextItem)) goto _L6; else goto _L5
_L5:
            obj = ((NavigationMenuTextItem)obj).getMenuItem();
            if (obj == null || ((MenuItemImpl) (obj)).getItemId() != j) goto _L6; else goto _L7
_L7:
            setCheckedItem(((MenuItemImpl) (obj)));
_L4:
            mUpdateSuspended = false;
            prepareMenuItems();
              goto _L2
_L6:
            i++;
            continue; /* Loop/switch isn't completed */
_L2:
            bundle = bundle.getSparseParcelableArray("android:menu:action_views");
            if (bundle != null)
            {
                i = 0;
                int k = mItems.size();
                while (i < k) 
                {
                    NavigationMenuItem navigationmenuitem = (NavigationMenuItem)mItems.get(i);
                    if (navigationmenuitem instanceof NavigationMenuTextItem)
                    {
                        Object obj1 = ((NavigationMenuTextItem)navigationmenuitem).getMenuItem();
                        if (obj1 != null)
                        {
                            View view = ((MenuItemImpl) (obj1)).getActionView();
                            if (view != null)
                            {
                                obj1 = (ParcelableSparseArray)bundle.get(((MenuItemImpl) (obj1)).getItemId());
                                if (obj1 != null)
                                {
                                    view.restoreHierarchyState(((SparseArray) (obj1)));
                                }
                            }
                        }
                    }
                    i++;
                }
            }
            return;
            if (true) goto _L9; else goto _L8
_L8:
        }

        public void setCheckedItem(MenuItemImpl menuitemimpl)
        {
            if (mCheckedItem == menuitemimpl || !menuitemimpl.isCheckable())
            {
                return;
            }
            if (mCheckedItem != null)
            {
                mCheckedItem.setChecked(false);
            }
            mCheckedItem = menuitemimpl;
            menuitemimpl.setChecked(true);
        }

        public void setUpdateSuspended(boolean flag)
        {
            mUpdateSuspended = flag;
        }

        public void update()
        {
            prepareMenuItems();
            notifyDataSetChanged();
        }

        NavigationMenuAdapter()
        {
            this$0 = NavigationMenuPresenter.this;
            super();
            prepareMenuItems();
        }
    }

    private static class NavigationMenuHeaderItem
        implements NavigationMenuItem
    {

        NavigationMenuHeaderItem()
        {
        }
    }

    private static interface NavigationMenuItem
    {
    }

    private static class NavigationMenuSeparatorItem
        implements NavigationMenuItem
    {

        private final int mPaddingBottom;
        private final int mPaddingTop;

        public int getPaddingBottom()
        {
            return mPaddingBottom;
        }

        public int getPaddingTop()
        {
            return mPaddingTop;
        }

        public NavigationMenuSeparatorItem(int i, int j)
        {
            mPaddingTop = i;
            mPaddingBottom = j;
        }
    }

    private static class NavigationMenuTextItem
        implements NavigationMenuItem
    {

        private final MenuItemImpl mMenuItem;
        boolean needsEmptyIcon;

        public MenuItemImpl getMenuItem()
        {
            return mMenuItem;
        }

        NavigationMenuTextItem(MenuItemImpl menuitemimpl)
        {
            mMenuItem = menuitemimpl;
        }
    }

    private static class NormalViewHolder extends ViewHolder
    {

        public NormalViewHolder(LayoutInflater layoutinflater, ViewGroup viewgroup, android.view.View.OnClickListener onclicklistener)
        {
            super(layoutinflater.inflate(android.support.design.R.layout.design_navigation_item, viewgroup, false));
            itemView.setOnClickListener(onclicklistener);
        }
    }

    private static class SeparatorViewHolder extends ViewHolder
    {

        public SeparatorViewHolder(LayoutInflater layoutinflater, ViewGroup viewgroup)
        {
            super(layoutinflater.inflate(android.support.design.R.layout.design_navigation_item_separator, viewgroup, false));
        }
    }

    private static class SubheaderViewHolder extends ViewHolder
    {

        public SubheaderViewHolder(LayoutInflater layoutinflater, ViewGroup viewgroup)
        {
            super(layoutinflater.inflate(android.support.design.R.layout.design_navigation_item_subheader, viewgroup, false));
        }
    }

    private static abstract class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder
    {

        public ViewHolder(View view)
        {
            super(view);
        }
    }


    private static final String STATE_ADAPTER = "android:menu:adapter";
    private static final String STATE_HEADER = "android:menu:header";
    private static final String STATE_HIERARCHY = "android:menu:list";
    NavigationMenuAdapter mAdapter;
    private android.support.v7.view.menu.MenuPresenter.Callback mCallback;
    LinearLayout mHeaderLayout;
    ColorStateList mIconTintList;
    private int mId;
    Drawable mItemBackground;
    LayoutInflater mLayoutInflater;
    MenuBuilder mMenu;
    private NavigationMenuView mMenuView;
    final android.view.View.OnClickListener mOnClickListener = new android.view.View.OnClickListener() {

        final NavigationMenuPresenter this$0;

        public void onClick(View view)
        {
            view = (NavigationMenuItemView)view;
            setUpdateSuspended(true);
            view = view.getItemData();
            boolean flag = mMenu.performItemAction(view, NavigationMenuPresenter.this, 0);
            if (view != null && view.isCheckable() && flag)
            {
                mAdapter.setCheckedItem(view);
            }
            setUpdateSuspended(false);
            updateMenuView(false);
        }

            
            {
                this$0 = NavigationMenuPresenter.this;
                super();
            }
    };
    int mPaddingSeparator;
    private int mPaddingTopDefault;
    int mTextAppearance;
    boolean mTextAppearanceSet;
    ColorStateList mTextColor;

    public NavigationMenuPresenter()
    {
    }

    public void addHeaderView(View view)
    {
        mHeaderLayout.addView(view);
        mMenuView.setPadding(0, 0, 0, mMenuView.getPaddingBottom());
    }

    public boolean collapseItemActionView(MenuBuilder menubuilder, MenuItemImpl menuitemimpl)
    {
        return false;
    }

    public void dispatchApplyWindowInsets(WindowInsetsCompat windowinsetscompat)
    {
        int i = windowinsetscompat.getSystemWindowInsetTop();
        if (mPaddingTopDefault != i)
        {
            mPaddingTopDefault = i;
            if (mHeaderLayout.getChildCount() == 0)
            {
                mMenuView.setPadding(0, mPaddingTopDefault, 0, mMenuView.getPaddingBottom());
            }
        }
        ViewCompat.dispatchApplyWindowInsets(mHeaderLayout, windowinsetscompat);
    }

    public boolean expandItemActionView(MenuBuilder menubuilder, MenuItemImpl menuitemimpl)
    {
        return false;
    }

    public boolean flagActionItems()
    {
        return false;
    }

    public int getHeaderCount()
    {
        return mHeaderLayout.getChildCount();
    }

    public View getHeaderView(int i)
    {
        return mHeaderLayout.getChildAt(i);
    }

    public int getId()
    {
        return mId;
    }

    public Drawable getItemBackground()
    {
        return mItemBackground;
    }

    public ColorStateList getItemTextColor()
    {
        return mTextColor;
    }

    public ColorStateList getItemTintList()
    {
        return mIconTintList;
    }

    public MenuView getMenuView(ViewGroup viewgroup)
    {
        if (mMenuView == null)
        {
            mMenuView = (NavigationMenuView)mLayoutInflater.inflate(android.support.design.R.layout.design_navigation_menu, viewgroup, false);
            if (mAdapter == null)
            {
                mAdapter = new NavigationMenuAdapter();
            }
            mHeaderLayout = (LinearLayout)mLayoutInflater.inflate(android.support.design.R.layout.design_navigation_item_header, mMenuView, false);
            mMenuView.setAdapter(mAdapter);
        }
        return mMenuView;
    }

    public View inflateHeaderView(int i)
    {
        View view = mLayoutInflater.inflate(i, mHeaderLayout, false);
        addHeaderView(view);
        return view;
    }

    public void initForMenu(Context context, MenuBuilder menubuilder)
    {
        mLayoutInflater = LayoutInflater.from(context);
        mMenu = menubuilder;
        mPaddingSeparator = context.getResources().getDimensionPixelOffset(android.support.design.R.dimen.design_navigation_separator_vertical_padding);
    }

    public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        if (mCallback != null)
        {
            mCallback.onCloseMenu(menubuilder, flag);
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        if (parcelable instanceof Bundle)
        {
            parcelable = (Bundle)parcelable;
            Object obj = parcelable.getSparseParcelableArray("android:menu:list");
            if (obj != null)
            {
                mMenuView.restoreHierarchyState(((SparseArray) (obj)));
            }
            obj = parcelable.getBundle("android:menu:adapter");
            if (obj != null)
            {
                mAdapter.restoreInstanceState(((Bundle) (obj)));
            }
            parcelable = parcelable.getSparseParcelableArray("android:menu:header");
            if (parcelable != null)
            {
                mHeaderLayout.restoreHierarchyState(parcelable);
            }
        }
    }

    public Parcelable onSaveInstanceState()
    {
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            Bundle bundle = new Bundle();
            if (mMenuView != null)
            {
                SparseArray sparsearray = new SparseArray();
                mMenuView.saveHierarchyState(sparsearray);
                bundle.putSparseParcelableArray("android:menu:list", sparsearray);
            }
            if (mAdapter != null)
            {
                bundle.putBundle("android:menu:adapter", mAdapter.createInstanceState());
            }
            if (mHeaderLayout != null)
            {
                SparseArray sparsearray1 = new SparseArray();
                mHeaderLayout.saveHierarchyState(sparsearray1);
                bundle.putSparseParcelableArray("android:menu:header", sparsearray1);
            }
            return bundle;
        } else
        {
            return null;
        }
    }

    public boolean onSubMenuSelected(SubMenuBuilder submenubuilder)
    {
        return false;
    }

    public void removeHeaderView(View view)
    {
        mHeaderLayout.removeView(view);
        if (mHeaderLayout.getChildCount() == 0)
        {
            mMenuView.setPadding(0, mPaddingTopDefault, 0, mMenuView.getPaddingBottom());
        }
    }

    public void setCallback(android.support.v7.view.menu.MenuPresenter.Callback callback)
    {
        mCallback = callback;
    }

    public void setCheckedItem(MenuItemImpl menuitemimpl)
    {
        mAdapter.setCheckedItem(menuitemimpl);
    }

    public void setId(int i)
    {
        mId = i;
    }

    public void setItemBackground(Drawable drawable)
    {
        mItemBackground = drawable;
        updateMenuView(false);
    }

    public void setItemIconTintList(ColorStateList colorstatelist)
    {
        mIconTintList = colorstatelist;
        updateMenuView(false);
    }

    public void setItemTextAppearance(int i)
    {
        mTextAppearance = i;
        mTextAppearanceSet = true;
        updateMenuView(false);
    }

    public void setItemTextColor(ColorStateList colorstatelist)
    {
        mTextColor = colorstatelist;
        updateMenuView(false);
    }

    public void setUpdateSuspended(boolean flag)
    {
        if (mAdapter != null)
        {
            mAdapter.setUpdateSuspended(flag);
        }
    }

    public void updateMenuView(boolean flag)
    {
        if (mAdapter != null)
        {
            mAdapter.update();
        }
    }
}
