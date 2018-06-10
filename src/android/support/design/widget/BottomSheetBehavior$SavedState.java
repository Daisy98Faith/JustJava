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
//            BottomSheetBehavior

protected static class state extends AbsSavedState
{

    public static final android.os.torCallbacks CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks() {

        public BottomSheetBehavior.SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new BottomSheetBehavior.SavedState(parcel, classloader);
        }

        public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return createFromParcel(parcel, classloader);
        }

        public BottomSheetBehavior.SavedState[] newArray(int i)
        {
            return new BottomSheetBehavior.SavedState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    });
    final int state;

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeInt(state);
    }


    public _cls1(Parcel parcel)
    {
        this(parcel, ((ClassLoader) (null)));
    }

    public <init>(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
        state = parcel.readInt();
    }

    public state(Parcelable parcelable, int i)
    {
        super(parcelable);
        state = i;
    }
}
