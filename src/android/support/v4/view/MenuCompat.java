// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.view.MenuItem;

public final class MenuCompat
{

    private MenuCompat()
    {
    }

    public static void setShowAsAction(MenuItem menuitem, int i)
    {
        menuitem.setShowAsAction(i);
    }
}
