// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;


// Referenced classes of package android.support.v4.widget:
//            SearchViewCompat

static final class val.listener
    implements android.widget.istener
{

    final CloseListener val$listener;

    public boolean onClose()
    {
        return val$listener.onClose();
    }

    CloseListener(CloseListener closelistener)
    {
        val$listener = closelistener;
        super();
    }
}
