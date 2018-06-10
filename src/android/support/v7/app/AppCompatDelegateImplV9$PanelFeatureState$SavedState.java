// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImplV9

private static class _cls1
    implements Parcelable
{

    public static final android.os..SavedState.menuState CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks() {

        public AppCompatDelegateImplV9.PanelFeatureState.SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return AppCompatDelegateImplV9.PanelFeatureState.SavedState.readFromParcel(parcel, classloader);
        }

        public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return createFromParcel(parcel, classloader);
        }

        public AppCompatDelegateImplV9.PanelFeatureState.SavedState[] newArray(int i)
        {
            return new AppCompatDelegateImplV9.PanelFeatureState.SavedState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    });
    int featureId;
    boolean isOpen;
    Bundle menuState;

    static _cls1 readFromParcel(Parcel parcel, ClassLoader classloader)
    {
        boolean flag = true;
        _cls1 _lcls1 = new <init>();
        _lcls1.featureId = parcel.readInt();
        if (parcel.readInt() != 1)
        {
            flag = false;
        }
        _lcls1.isOpen = flag;
        if (_lcls1.isOpen)
        {
            _lcls1.menuState = parcel.readBundle(classloader);
        }
        return _lcls1;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(featureId);
        if (isOpen)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (isOpen)
        {
            parcel.writeBundle(menuState);
        }
    }


    _cls1()
    {
    }
}
