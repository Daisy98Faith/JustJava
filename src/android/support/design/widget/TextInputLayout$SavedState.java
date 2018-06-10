// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;
import android.text.TextUtils;

// Referenced classes of package android.support.design.widget:
//            TextInputLayout

static class error extends AbsSavedState
{

    public static final android.os.CreatorCallbacks CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks() {

        public TextInputLayout.SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new TextInputLayout.SavedState(parcel, classloader);
        }

        public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return createFromParcel(parcel, classloader);
        }

        public TextInputLayout.SavedState[] newArray(int i)
        {
            return new TextInputLayout.SavedState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    });
    CharSequence error;

    public String toString()
    {
        return (new StringBuilder()).append("TextInputLayout.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" error=").append(error).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        TextUtils.writeToParcel(error, parcel, i);
    }


    _cls1(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
        error = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.omParcel(parcel);
    }

    error(Parcelable parcelable)
    {
        super(parcelable);
    }
}
