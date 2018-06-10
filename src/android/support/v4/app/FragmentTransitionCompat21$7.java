// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.view.View;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package android.support.v4.app:
//            FragmentTransitionCompat21

static final class val.nameOverrides
    implements Runnable
{

    final Map val$nameOverrides;
    final ArrayList val$sharedElementsIn;

    public void run()
    {
        int j = val$sharedElementsIn.size();
        for (int i = 0; i < j; i++)
        {
            View view = (View)val$sharedElementsIn.get(i);
            String s = view.getTransitionName();
            view.setTransitionName((String)val$nameOverrides.get(s));
        }

    }

    _cls9(ArrayList arraylist, Map map)
    {
        val$sharedElementsIn = arraylist;
        val$nameOverrides = map;
        super();
    }
}
