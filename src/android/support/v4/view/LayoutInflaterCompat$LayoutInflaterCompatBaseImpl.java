// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.view.LayoutInflater;

// Referenced classes of package android.support.v4.view:
//            LayoutInflaterCompat, LayoutInflaterFactory

static class 
{

    public LayoutInflaterFactory getFactory(LayoutInflater layoutinflater)
    {
        layoutinflater = layoutinflater.getFactory();
        if (layoutinflater instanceof )
        {
            return (()layoutinflater).ory;
        } else
        {
            return null;
        }
    }

    public void setFactory(LayoutInflater layoutinflater, LayoutInflaterFactory layoutinflaterfactory)
    {
        if (layoutinflaterfactory != null)
        {
            layoutinflaterfactory = new ory(layoutinflaterfactory);
        } else
        {
            layoutinflaterfactory = null;
        }
        setFactory2(layoutinflater, layoutinflaterfactory);
    }

    public void setFactory2(LayoutInflater layoutinflater, android.view.atBaseImpl atbaseimpl)
    {
        layoutinflater.setFactory2(atbaseimpl);
        android.view.atBaseImpl atbaseimpl1 = layoutinflater.getFactory();
        if (atbaseimpl1 instanceof android.view.atBaseImpl)
        {
            LayoutInflaterCompat.forceSetFactory2(layoutinflater, (android.view.atBaseImpl)atbaseimpl1);
            return;
        } else
        {
            LayoutInflaterCompat.forceSetFactory2(layoutinflater, atbaseimpl);
            return;
        }
    }

    ()
    {
    }
}
