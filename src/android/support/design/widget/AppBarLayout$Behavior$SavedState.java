// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;

// Referenced classes of package android.support.design.widget:
//            AppBarLayout

protected static class firstVisibleChildAtMinimumHeight extends AbsSavedState
{

    public static final android.os.rCallbacks CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks() {

        public AppBarLayout.Behavior.SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new AppBarLayout.Behavior.SavedState(parcel, classloader);
        }

        public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return createFromParcel(parcel, classloader);
        }

        public AppBarLayout.Behavior.SavedState[] newArray(int i)
        {
            return new AppBarLayout.Behavior.SavedState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    });
    boolean firstVisibleChildAtMinimumHeight;
    int firstVisibleChildIndex;
    float firstVisibleChildPercentageShown;

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeInt(firstVisibleChildIndex);
        parcel.writeFloat(firstVisibleChildPercentageShown);
        if (firstVisibleChildAtMinimumHeight)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeByte((byte)i);
    }


    public _cls1(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
        firstVisibleChildIndex = parcel.readInt();
        firstVisibleChildPercentageShown = parcel.readFloat();
        boolean flag;
        if (parcel.readByte() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        firstVisibleChildAtMinimumHeight = flag;
    }

    public firstVisibleChildAtMinimumHeight(Parcelable parcelable)
    {
        super(parcelable);
    }
}
