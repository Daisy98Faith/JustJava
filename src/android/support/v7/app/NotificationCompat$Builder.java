// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.Context;

// Referenced classes of package android.support.v7.app:
//            NotificationCompat

public static class <init> extends android.support.v4.app.<init>
{

    protected android.support.v4.app.xtender getExtender()
    {
        if (android.os.pat._fld1 >= 24)
        {
            return new ender(null);
        }
        if (android.os.pat._fld1 >= 21)
        {
            return new Extender();
        }
        if (android.os.pat.LollipopExtender >= 16)
        {
            return new nExtender();
        }
        if (android.os.pat.JellybeanExtender >= 14)
        {
            return new SandwichExtender();
        } else
        {
            return super.getExtender();
        }
    }

    protected CharSequence resolveText()
    {
        if (mStyle instanceof android.support.v4.app.gStyle)
        {
            android.support.v4.app.gStyle gstyle = (android.support.v4.app.gStyle)mStyle;
            android.support.v4.app.gStyle.Message message = NotificationCompat.access$000(gstyle);
            CharSequence charsequence = gstyle.getConversationTitle();
            if (message != null)
            {
                if (charsequence != null)
                {
                    return NotificationCompat.access$100(this, gstyle, message);
                } else
                {
                    return message.getText();
                }
            }
        }
        return super.resolveText();
    }

    protected CharSequence resolveTitle()
    {
        if (mStyle instanceof android.support.v4.app.gStyle)
        {
            Object obj = (android.support.v4.app.gStyle)mStyle;
            android.support.v4.app.gStyle.Message message = NotificationCompat.access$000(((android.support.v4.app.gStyle) (obj)));
            obj = ((android.support.v4.app.gStyle) (obj)).getConversationTitle();
            if (obj != null || message != null)
            {
                if (obj != null)
                {
                    return ((CharSequence) (obj));
                } else
                {
                    return message.getSender();
                }
            }
        }
        return super.resolveTitle();
    }

    public xtender(Context context)
    {
        super(context);
    }
}
