// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;

// Referenced classes of package android.support.v7.widget:
//            Toolbar

public static class isOverflowOpen extends AbsSavedState
{

    public static final android.os.leCompatCreatorCallbacks CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks() {

        public Toolbar.SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new Toolbar.SavedState(parcel, classloader);
        }

        public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return createFromParcel(parcel, classloader);
        }

        public Toolbar.SavedState[] newArray(int i)
        {
            return new Toolbar.SavedState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    });
    int expandedMenuItemId;
    boolean isOverflowOpen;

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeInt(expandedMenuItemId);
        if (isOverflowOpen)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
    }


    public _cls1(Parcel parcel)
    {
        this(parcel, null);
    }

    public <init>(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
        expandedMenuItemId = parcel.readInt();
        boolean flag;
        if (parcel.readInt() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        isOverflowOpen = flag;
    }

    public isOverflowOpen(Parcelable parcelable)
    {
        super(parcelable);
    }
}
