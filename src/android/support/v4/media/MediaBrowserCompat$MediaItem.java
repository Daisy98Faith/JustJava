// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v4.media:
//            MediaBrowserCompat, MediaDescriptionCompat

public static class mDescription
    implements Parcelable
{
    public static interface Flags
        extends Annotation
    {
    }


    public static final android.os.nCompat.writeToParcel CREATOR = new android.os.Parcelable.Creator() {

        public MediaBrowserCompat.MediaItem createFromParcel(Parcel parcel)
        {
            return new MediaBrowserCompat.MediaItem(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public MediaBrowserCompat.MediaItem[] newArray(int i)
        {
            return new MediaBrowserCompat.MediaItem[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    };
    public static final int FLAG_BROWSABLE = 1;
    public static final int FLAG_PLAYABLE = 2;
    private final MediaDescriptionCompat mDescription;
    private final int mFlags;

    public static Flags fromMediaItem(Object obj)
    {
        if (obj == null || android.os.nCompat < 21)
        {
            return null;
        } else
        {
            int i = Item.getFlags(obj);
            return new <init>(MediaDescriptionCompat.fromMediaDescription(Item.getDescription(obj)), i);
        }
    }

    public static List fromMediaItemList(List list)
    {
        if (list != null && android.os.nCompat >= 21) goto _L2; else goto _L1
_L1:
        list = null;
_L4:
        return list;
_L2:
        ArrayList arraylist = new ArrayList(list.size());
        Iterator iterator = list.iterator();
        do
        {
            list = arraylist;
            if (!iterator.hasNext())
            {
                continue;
            }
            arraylist.add(fromMediaItem(iterator.next()));
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    public int describeContents()
    {
        return 0;
    }

    public MediaDescriptionCompat getDescription()
    {
        return mDescription;
    }

    public int getFlags()
    {
        return mFlags;
    }

    public String getMediaId()
    {
        return mDescription.getMediaId();
    }

    public boolean isBrowsable()
    {
        return (mFlags & 1) != 0;
    }

    public boolean isPlayable()
    {
        return (mFlags & 2) != 0;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("MediaItem{");
        stringbuilder.append("mFlags=").append(mFlags);
        stringbuilder.append(", mDescription=").append(mDescription);
        stringbuilder.append('}');
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mFlags);
        mDescription.writeToParcel(parcel, i);
    }


    _cls1(Parcel parcel)
    {
        mFlags = parcel.readInt();
        mDescription = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.Parcel(parcel);
    }

    public R(MediaDescriptionCompat mediadescriptioncompat, int i)
    {
        if (mediadescriptioncompat == null)
        {
            throw new IllegalArgumentException("description cannot be null");
        }
        if (TextUtils.isEmpty(mediadescriptioncompat.getMediaId()))
        {
            throw new IllegalArgumentException("description must have a non-empty media id");
        } else
        {
            mFlags = i;
            mDescription = mediadescriptioncompat;
            return;
        }
    }
}
