// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import java.util.List;

public class FragmentManagerNonConfig
{

    private final List mChildNonConfigs;
    private final List mFragments;

    FragmentManagerNonConfig(List list, List list1)
    {
        mFragments = list;
        mChildNonConfigs = list1;
    }

    List getChildNonConfigs()
    {
        return mChildNonConfigs;
    }

    List getFragments()
    {
        return mFragments;
    }
}
