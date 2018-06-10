// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.view.View;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentTransitionCompat21

static final class val.outNames
    implements Runnable
{

    final ArrayList val$inNames;
    final int val$numSharedElements;
    final ArrayList val$outNames;
    final ArrayList val$sharedElementsIn;
    final ArrayList val$sharedElementsOut;

    public void run()
    {
        for (int i = 0; i < val$numSharedElements; i++)
        {
            ((View)val$sharedElementsIn.get(i)).setTransitionName((String)val$inNames.get(i));
            ((View)val$sharedElementsOut.get(i)).setTransitionName((String)val$outNames.get(i));
        }

    }

    _cls9(int i, ArrayList arraylist, ArrayList arraylist1, ArrayList arraylist2, ArrayList arraylist3)
    {
        val$numSharedElements = i;
        val$sharedElementsIn = arraylist;
        val$inNames = arraylist1;
        val$sharedElementsOut = arraylist2;
        val$outNames = arraylist3;
        super();
    }
}
