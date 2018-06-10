// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.view.MenuItem;

// Referenced classes of package android.support.v4.view:
//            MenuItemCompat

static interface 
{

    public abstract CharSequence getContentDescription(MenuItem menuitem);

    public abstract CharSequence getTooltipText(MenuItem menuitem);

    public abstract void setContentDescription(MenuItem menuitem, CharSequence charsequence);

    public abstract void setTooltipText(MenuItem menuitem, CharSequence charsequence);
}
