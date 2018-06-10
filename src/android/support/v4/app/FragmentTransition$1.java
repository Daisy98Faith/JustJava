// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentTransition

static final class val.exitingViews
    implements Runnable
{

    final ArrayList val$exitingViews;

    public void run()
    {
        FragmentTransition.access$000(val$exitingViews, 4);
    }

    (ArrayList arraylist)
    {
        val$exitingViews = arraylist;
        super();
    }
}
