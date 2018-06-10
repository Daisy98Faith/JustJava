// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

// Referenced classes of package android.support.v4.view:
//            AbsSavedState

static final class eatorCallbacks
    implements ParcelableCompatCreatorCallbacks
{

    public AbsSavedState createFromParcel(Parcel parcel, ClassLoader classloader)
    {
        if (parcel.readParcelable(classloader) != null)
        {
            throw new IllegalStateException("superState must be null");
        } else
        {
            return AbsSavedState.EMPTY_STATE;
        }
    }

    public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
    {
        return createFromParcel(parcel, classloader);
    }

    public AbsSavedState[] newArray(int i)
    {
        return new AbsSavedState[i];
    }

    public volatile Object[] newArray(int i)
    {
        return newArray(i);
    }

    eatorCallbacks()
    {
    }
}
