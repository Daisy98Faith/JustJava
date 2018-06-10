// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.os;

import android.content.Context;
import android.os.UserManager;

public class UserManagerCompatApi24
{

    public UserManagerCompatApi24()
    {
    }

    public static boolean isUserUnlocked(Context context)
    {
        return ((UserManager)context.getSystemService(android/os/UserManager)).isUserUnlocked();
    }
}
