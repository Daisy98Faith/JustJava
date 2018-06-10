// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.os.Message;
import android.view.LayoutInflater;

// Referenced classes of package android.support.v4.view:
//            AsyncLayoutInflater

class this._cls0
    implements android.os.utInflater._cls1
{

    final AsyncLayoutInflater this$0;

    public boolean handleMessage(Message message)
    {
        message = (flateRequest)message.obj;
        if (((flateRequest) (message)).view == null)
        {
            message.view = mInflater.inflate(((flateRequest) (message)).resid, ((flateRequest) (message)).parent, false);
        }
        ((flateRequest) (message)).callback.onInflateFinished(((flateRequest) (message)).view, ((flateRequest) (message)).resid, ((flateRequest) (message)).parent);
        mInflateThread.releaseRequest(message);
        return true;
    }

    flateThread()
    {
        this$0 = AsyncLayoutInflater.this;
        super();
    }
}
