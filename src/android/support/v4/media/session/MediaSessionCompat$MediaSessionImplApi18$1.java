// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;


// Referenced classes of package android.support.v4.media.session:
//            MediaSessionCompat

class this._cls0
    implements android.media.eListener
{

    final stToHandler this$0;

    public void onPlaybackPositionUpdate(long l)
    {
        stToHandler(18, Long.valueOf(l));
    }

    Q()
    {
        this$0 = this._cls0.this;
        super();
    }
}
