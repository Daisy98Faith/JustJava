// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;

// Referenced classes of package android.support.design.widget:
//            BottomNavigationView

static class readFromParcel extends AbsSavedState
{

    public static final android.os.orCallbacks CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks() {

        public BottomNavigationView.SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new BottomNavigationView.SavedState(parcel, classloader);
        }

        public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return createFromParcel(parcel, classloader);
        }

        public BottomNavigationView.SavedState[] newArray(int i)
        {
            return new BottomNavigationView.SavedState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    });
    Bundle menuPresenterState;

    private void readFromParcel(Parcel parcel, ClassLoader classloader)
    {
        menuPresenterState = parcel.readBundle(classloader);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeBundle(menuPresenterState);
    }


    public _cls1(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
        readFromParcel(parcel, classloader);
    }

    public readFromParcel(Parcelable parcelable)
    {
        super(parcelable);
    }
}
