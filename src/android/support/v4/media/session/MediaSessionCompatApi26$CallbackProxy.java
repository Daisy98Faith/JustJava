// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;


// Referenced classes of package android.support.v4.media.session:
//            MediaSessionCompatApi26

static class <init> extends <init>
{

    public void onSetRepeatMode(int i)
    {
        ((<init>)mCallback).RepeatMode(i);
    }

    public void onSetShuffleModeEnabled(boolean flag)
    {
        ((RepeatMode)mCallback).ShuffleModeEnabled(flag);
    }

    ( )
    {
        super();
    }
}
