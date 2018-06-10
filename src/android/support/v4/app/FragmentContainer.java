// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

// Referenced classes of package android.support.v4.app:
//            Fragment

public abstract class FragmentContainer
{

    public FragmentContainer()
    {
    }

    public Fragment instantiate(Context context, String s, Bundle bundle)
    {
        return Fragment.instantiate(context, s, bundle);
    }

    public abstract View onFindViewById(int i);

    public abstract boolean onHasView();
}
