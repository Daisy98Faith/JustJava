// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.os.Bundle;

// Referenced classes of package android.support.v4.app:
//            FragmentTabHost, Fragment

static final class args
{

    final Bundle args;
    final Class clss;
    Fragment fragment;
    final String tag;

    (String s, Class class1, Bundle bundle)
    {
        tag = s;
        clss = class1;
        args = bundle;
    }
}
