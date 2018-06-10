// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;

// Referenced classes of package android.support.v4.widget:
//            SlidingPaneLayout

static class isOpen extends AbsSavedState
{

    public static final android.os.eatorCallbacks CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks() {

        public SlidingPaneLayout.SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new SlidingPaneLayout.SavedState(parcel, classloader);
        }

        public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return createFromParcel(parcel, classloader);
        }

        public SlidingPaneLayout.SavedState[] newArray(int i)
        {
            return new SlidingPaneLayout.SavedState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    });
    boolean isOpen;

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        if (isOpen)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
    }


    _cls1(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
        boolean flag;
        if (parcel.readInt() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        isOpen = flag;
    }

    isOpen(Parcelable parcelable)
    {
        super(parcelable);
    }
}
