// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.PackageManager;

// Referenced classes of package android.support.v4.accessibilityservice:
//            AccessibilityServiceInfoCompat, AccessibilityServiceInfoCompatJellyBean

static class  extends 
{

    public String loadDescription(AccessibilityServiceInfo accessibilityserviceinfo, PackageManager packagemanager)
    {
        return AccessibilityServiceInfoCompatJellyBean.loadDescription(accessibilityserviceinfo, packagemanager);
    }

    ()
    {
    }
}
