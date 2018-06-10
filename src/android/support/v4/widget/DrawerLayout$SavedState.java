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
//            DrawerLayout

protected static class openDrawerGravity extends AbsSavedState
{

    public static final android.os.patCreatorCallbacks CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks() {

        public DrawerLayout.SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new DrawerLayout.SavedState(parcel, classloader);
        }

        public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return createFromParcel(parcel, classloader);
        }

        public DrawerLayout.SavedState[] newArray(int i)
        {
            return new DrawerLayout.SavedState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    });
    int lockModeEnd;
    int lockModeLeft;
    int lockModeRight;
    int lockModeStart;
    int openDrawerGravity;

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeInt(openDrawerGravity);
        parcel.writeInt(lockModeLeft);
        parcel.writeInt(lockModeRight);
        parcel.writeInt(lockModeStart);
        parcel.writeInt(lockModeEnd);
    }


    public _cls1(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
        openDrawerGravity = 0;
        openDrawerGravity = parcel.readInt();
        lockModeLeft = parcel.readInt();
        lockModeRight = parcel.readInt();
        lockModeStart = parcel.readInt();
        lockModeEnd = parcel.readInt();
    }

    public lockModeEnd(Parcelable parcelable)
    {
        super(parcelable);
        openDrawerGravity = 0;
    }
}
