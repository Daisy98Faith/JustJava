// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.view.MenuItem;

// Referenced classes of package android.support.v4.view:
//            MenuItemCompat

static class  extends 
{

    public CharSequence getContentDescription(MenuItem menuitem)
    {
        return menuitem.getContentDescription();
    }

    public CharSequence getTooltipText(MenuItem menuitem)
    {
        return menuitem.getTooltipText();
    }

    public void setContentDescription(MenuItem menuitem, CharSequence charsequence)
    {
        menuitem.setContentDescription(charsequence);
    }

    public void setTooltipText(MenuItem menuitem, CharSequence charsequence)
    {
        menuitem.setTooltipText(charsequence);
    }

    ()
    {
    }
}
