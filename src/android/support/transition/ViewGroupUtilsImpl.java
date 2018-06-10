// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            ViewGroupOverlayImpl

interface ViewGroupUtilsImpl
{

    public abstract ViewGroupOverlayImpl getOverlay(ViewGroup viewgroup);

    public abstract void suppressLayout(ViewGroup viewgroup, boolean flag);
}
