// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view.accessibility;


// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityManagerCompat

class er
    implements ListenerBridge
{

    final er.onTouchExplorationStateChanged this$0;
    final er val$listener;

    public void onTouchExplorationStateChanged(boolean flag)
    {
        val$listener.onTouchExplorationStateChanged(flag);
    }

    er()
    {
        this$0 = final_er;
        val$listener = er.this;
        super();
    }
}
