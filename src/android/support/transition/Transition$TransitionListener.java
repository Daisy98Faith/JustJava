// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;


// Referenced classes of package android.support.transition:
//            Transition

public static interface 
{

    public abstract void onTransitionCancel(Transition transition);

    public abstract void onTransitionEnd(Transition transition);

    public abstract void onTransitionPause(Transition transition);

    public abstract void onTransitionResume(Transition transition);

    public abstract void onTransitionStart(Transition transition);
}
