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
//            SearchView

static class isIconified extends AbsSavedState
{

    public static final android.os.ompatCreatorCallbacks CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks() {

        public SearchView.SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new SearchView.SavedState(parcel, classloader);
        }

        public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return createFromParcel(parcel, classloader);
        }

        public SearchView.SavedState[] newArray(int i)
        {
            return new SearchView.SavedState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    });
    boolean isIconified;

    public String toString()
    {
        return (new StringBuilder()).append("SearchView.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" isIconified=").append(isIconified).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeValue(Boolean.valueOf(isIconified));
    }


    public _cls1(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
        isIconified = ((Boolean)parcel.readValue(null)).booleanValue();
    }

    isIconified(Parcelable parcelable)
    {
        super(parcelable);
    }
}
