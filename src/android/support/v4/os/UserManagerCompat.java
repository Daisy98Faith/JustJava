// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.os;

import android.content.Context;

// Referenced classes of package android.support.v4.os:
//            BuildCompat, UserManagerCompatApi24

public class UserManagerCompat
{

    private UserManagerCompat()
    {
    }

    public static boolean isUserUnlocked(Context context)
    {
        if (BuildCompat.isAtLeastN())
        {
            return UserManagerCompatApi24.isUserUnlocked(context);
        } else
        {
            return true;
        }
    }
}
