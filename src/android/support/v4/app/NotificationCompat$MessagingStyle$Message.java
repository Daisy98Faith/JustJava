// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v4.app:
//            NotificationCompat

public static final class mSender
{

    static final String KEY_DATA_MIME_TYPE = "type";
    static final String KEY_DATA_URI = "uri";
    static final String KEY_SENDER = "sender";
    static final String KEY_TEXT = "text";
    static final String KEY_TIMESTAMP = "time";
    private String mDataMimeType;
    private Uri mDataUri;
    private final CharSequence mSender;
    private final CharSequence mText;
    private final long mTimestamp;

    static Bundle[] getBundleArrayForMessages(List list)
    {
        Bundle abundle[] = new Bundle[list.size()];
        int j = list.size();
        for (int i = 0; i < j; i++)
        {
            abundle[i] = ((mSender)list.get(i)).toBundle();
        }

        return abundle;
    }

    static toBundle getMessageFromBundle(Bundle bundle)
    {
        toBundle tobundle;
        if (!bundle.containsKey("text") || !bundle.containsKey("time"))
        {
            break MISSING_BLOCK_LABEL_94;
        }
        toBundle tobundle1;
        try
        {
            tobundle1 = new <init>(bundle.getCharSequence("text"), bundle.getLong("time"), bundle.getCharSequence("sender"));
        }
        // Misplaced declaration of an exception variable
        catch (Bundle bundle)
        {
            return null;
        }
        tobundle = tobundle1;
        if (!bundle.containsKey("type"))
        {
            break MISSING_BLOCK_LABEL_96;
        }
        tobundle = tobundle1;
        if (!bundle.containsKey("uri"))
        {
            break MISSING_BLOCK_LABEL_96;
        }
        tobundle1.setData(bundle.getString("type"), (Uri)bundle.getParcelable("uri"));
        return tobundle1;
        tobundle = null;
        return tobundle;
    }

    static List getMessagesFromBundleArray(Parcelable aparcelable[])
    {
        ArrayList arraylist = new ArrayList(aparcelable.length);
        for (int i = 0; i < aparcelable.length; i++)
        {
            if (!(aparcelable[i] instanceof Bundle))
            {
                continue;
            }
            setData setdata = getMessageFromBundle((Bundle)aparcelable[i]);
            if (setdata != null)
            {
                arraylist.add(setdata);
            }
        }

        return arraylist;
    }

    private Bundle toBundle()
    {
        Bundle bundle = new Bundle();
        if (mText != null)
        {
            bundle.putCharSequence("text", mText);
        }
        bundle.putLong("time", mTimestamp);
        if (mSender != null)
        {
            bundle.putCharSequence("sender", mSender);
        }
        if (mDataMimeType != null)
        {
            bundle.putString("type", mDataMimeType);
        }
        if (mDataUri != null)
        {
            bundle.putParcelable("uri", mDataUri);
        }
        return bundle;
    }

    public String getDataMimeType()
    {
        return mDataMimeType;
    }

    public Uri getDataUri()
    {
        return mDataUri;
    }

    public CharSequence getSender()
    {
        return mSender;
    }

    public CharSequence getText()
    {
        return mText;
    }

    public long getTimestamp()
    {
        return mTimestamp;
    }

    public mTimestamp setData(String s, Uri uri)
    {
        mDataMimeType = s;
        mDataUri = uri;
        return this;
    }

    public (CharSequence charsequence, long l, CharSequence charsequence1)
    {
        mText = charsequence;
        mTimestamp = l;
        mSender = charsequence1;
    }
}
