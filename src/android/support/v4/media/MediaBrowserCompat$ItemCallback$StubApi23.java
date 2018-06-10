// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Parcel;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat

private class this._cls0
    implements this._cls0
{

    final ed this$0;

    public void onError(String s)
    {
        _mth0(s);
    }

    public void onItemLoaded(Parcel parcel)
    {
        if (parcel == null)
        {
            ed(null);
            return;
        } else
        {
            parcel.setDataPosition(0);
            this._cls0 _lcls0 = (ed)ed.CREATOR(parcel);
            parcel.recycle();
            ed(_lcls0);
            return;
        }
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
