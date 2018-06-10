// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.util.ArrayMap;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package android.support.v4.media:
//            MediaMetadataCompat, RatingCompat

public static final class putBitmap
{

    private final Bundle mBundle;

    private Bitmap scaleBitmap(Bitmap bitmap, int i)
    {
        float f = i;
        f = Math.min(f / (float)bitmap.getWidth(), f / (float)bitmap.getHeight());
        i = (int)((float)bitmap.getHeight() * f);
        return Bitmap.createScaledBitmap(bitmap, (int)((float)bitmap.getWidth() * f), i, true);
    }

    public MediaMetadataCompat build()
    {
        return new MediaMetadataCompat(mBundle);
    }

    public mBundle putBitmap(String s, Bitmap bitmap)
    {
        if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(s) && ((Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(s)).intValue() != 2)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("The ").append(s).append(" key cannot be used to put a Bitmap").toString());
        } else
        {
            mBundle.putParcelable(s, bitmap);
            return this;
        }
    }

    public mBundle putLong(String s, long l)
    {
        if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(s) && ((Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(s)).intValue() != 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("The ").append(s).append(" key cannot be used to put a long").toString());
        } else
        {
            mBundle.putLong(s, l);
            return this;
        }
    }

    public mBundle putRating(String s, RatingCompat ratingcompat)
    {
        if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(s) && ((Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(s)).intValue() != 3)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("The ").append(s).append(" key cannot be used to put a Rating").toString());
        }
        if (android.os.t> >= 19)
        {
            mBundle.putParcelable(s, (Parcelable)ratingcompat.getRating());
            return this;
        } else
        {
            mBundle.putParcelable(s, ratingcompat);
            return this;
        }
    }

    public mBundle putString(String s, String s1)
    {
        if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(s) && ((Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(s)).intValue() != 1)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("The ").append(s).append(" key cannot be used to put a String").toString());
        } else
        {
            mBundle.putCharSequence(s, s1);
            return this;
        }
    }

    public mBundle putText(String s, CharSequence charsequence)
    {
        if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(s) && ((Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(s)).intValue() != 1)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("The ").append(s).append(" key cannot be used to put a CharSequence").toString());
        } else
        {
            mBundle.putCharSequence(s, charsequence);
            return this;
        }
    }

    public ()
    {
        mBundle = new Bundle();
    }

    public mBundle(MediaMetadataCompat mediametadatacompat)
    {
        mBundle = new Bundle(mediametadatacompat.mBundle);
    }

    public mBundle(MediaMetadataCompat mediametadatacompat, int i)
    {
        this(mediametadatacompat);
        mediametadatacompat = mBundle.keySet().iterator();
        do
        {
            if (!mediametadatacompat.hasNext())
            {
                break;
            }
            String s = (String)mediametadatacompat.next();
            Object obj = mBundle.get(s);
            if (obj != null && (obj instanceof Bitmap))
            {
                obj = (Bitmap)obj;
                if (((Bitmap) (obj)).getHeight() > i || ((Bitmap) (obj)).getWidth() > i)
                {
                    putBitmap(s, scaleBitmap(((Bitmap) (obj)), i));
                } else
                if (android.os.ompat.Builder.scaleBitmap >= 14 && (s.equals("android.media.metadata.ART") || s.equals("android.media.metadata.ALBUM_ART")))
                {
                    putBitmap(s, ((Bitmap) (obj)).copy(((Bitmap) (obj)).getConfig(), false));
                }
            }
        } while (true);
    }
}
