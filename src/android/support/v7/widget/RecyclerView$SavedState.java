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
//            RecyclerView

public static class mLayoutState extends AbsSavedState
{

    public static final android.os.patCreatorCallbacks CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks() {

        public RecyclerView.SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new RecyclerView.SavedState(parcel, classloader);
        }

        public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return createFromParcel(parcel, classloader);
        }

        public RecyclerView.SavedState[] newArray(int i)
        {
            return new RecyclerView.SavedState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    });
    Parcelable mLayoutState;

    void copyFrom(mLayoutState mlayoutstate)
    {
        mLayoutState = mlayoutstate.mLayoutState;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(mLayoutState, 0);
    }


    _cls1(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
        if (classloader == null)
        {
            classloader = android/support/v7/widget/RecyclerView$LayoutManager.getClassLoader();
        }
        mLayoutState = parcel.readParcelable(classloader);
    }

    mLayoutState(Parcelable parcelable)
    {
        super(parcelable);
    }
}
