// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView

static final class ks
    implements ParcelableCompatCreatorCallbacks
{

    public ks createFromParcel(Parcel parcel, ClassLoader classloader)
    {
        return new nit>(parcel, classloader);
    }

    public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
    {
        return createFromParcel(parcel, classloader);
    }

    public createFromParcel[] newArray(int i)
    {
        return new createFromParcel[i];
    }

    public volatile Object[] newArray(int i)
    {
        return newArray(i);
    }

    ks()
    {
    }
}
