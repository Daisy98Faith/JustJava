// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v7.view.menu.MenuBuilder;
import android.view.MenuItem;

public interface MenuItemHoverListener
{

    public abstract void onItemHoverEnter(MenuBuilder menubuilder, MenuItem menuitem);

    public abstract void onItemHoverExit(MenuBuilder menubuilder, MenuItem menuitem);
}
