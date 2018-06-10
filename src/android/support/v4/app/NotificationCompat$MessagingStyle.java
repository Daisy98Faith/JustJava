// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Notification;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v4.app:
//            NotificationCompat

public static class mUserDisplayName extends mUserDisplayName
{
    public static final class Message
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
                abundle[i] = ((Message)list.get(i)).toBundle();
            }

            return abundle;
        }

        static Message getMessageFromBundle(Bundle bundle)
        {
            Message message;
            if (!bundle.containsKey("text") || !bundle.containsKey("time"))
            {
                break MISSING_BLOCK_LABEL_94;
            }
            Message message1;
            try
            {
                message1 = new Message(bundle.getCharSequence("text"), bundle.getLong("time"), bundle.getCharSequence("sender"));
            }
            // Misplaced declaration of an exception variable
            catch (Bundle bundle)
            {
                return null;
            }
            message = message1;
            if (!bundle.containsKey("type"))
            {
                break MISSING_BLOCK_LABEL_96;
            }
            message = message1;
            if (!bundle.containsKey("uri"))
            {
                break MISSING_BLOCK_LABEL_96;
            }
            message1.setData(bundle.getString("type"), (Uri)bundle.getParcelable("uri"));
            return message1;
            message = null;
            return message;
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
                Message message = getMessageFromBundle((Bundle)aparcelable[i]);
                if (message != null)
                {
                    arraylist.add(message);
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

        public Message setData(String s, Uri uri)
        {
            mDataMimeType = s;
            mDataUri = uri;
            return this;
        }

        public Message(CharSequence charsequence, long l, CharSequence charsequence1)
        {
            mText = charsequence;
            mTimestamp = l;
            mSender = charsequence1;
        }
    }


    public static final int MAXIMUM_RETAINED_MESSAGES = 25;
    CharSequence mConversationTitle;
    List mMessages;
    CharSequence mUserDisplayName;

    public static Message.mDataUri extractMessagingStyleFromNotification(Notification notification)
    {
        notification = NotificationCompat.IMPL.getExtras(notification);
        if (notification != null && !notification.containsKey("android.selfDisplayName"))
        {
            return null;
        }
        Message.mDataUri mdatauri;
        try
        {
            mdatauri = new <init>();
            mdatauri.restoreFromCompatExtras(notification);
        }
        // Misplaced declaration of an exception variable
        catch (Notification notification)
        {
            return null;
        }
        return mdatauri;
    }

    public void addCompatExtras(Bundle bundle)
    {
        super.Extras(bundle);
        if (mUserDisplayName != null)
        {
            bundle.putCharSequence("android.selfDisplayName", mUserDisplayName);
        }
        if (mConversationTitle != null)
        {
            bundle.putCharSequence("android.conversationTitle", mConversationTitle);
        }
        if (!mMessages.isEmpty())
        {
            bundle.putParcelableArray("android.messages", Message.getBundleArrayForMessages(mMessages));
        }
    }

    public Message addMessage(Message message)
    {
        mMessages.add(message);
        if (mMessages.size() > 25)
        {
            mMessages.remove(0);
        }
        return this;
    }

    public mMessages addMessage(CharSequence charsequence, long l, CharSequence charsequence1)
    {
        mMessages.add(new Message(charsequence, l, charsequence1));
        if (mMessages.size() > 25)
        {
            mMessages.remove(0);
        }
        return this;
    }

    public CharSequence getConversationTitle()
    {
        return mConversationTitle;
    }

    public List getMessages()
    {
        return mMessages;
    }

    public CharSequence getUserDisplayName()
    {
        return mUserDisplayName;
    }

    protected void restoreFromCompatExtras(Bundle bundle)
    {
        mMessages.clear();
        mUserDisplayName = bundle.getString("android.selfDisplayName");
        mConversationTitle = bundle.getString("android.conversationTitle");
        bundle = bundle.getParcelableArray("android.messages");
        if (bundle != null)
        {
            mMessages = Message.getMessagesFromBundleArray(bundle);
        }
    }

    public Message.getMessagesFromBundleArray setConversationTitle(CharSequence charsequence)
    {
        mConversationTitle = charsequence;
        return this;
    }

    Message.mSender()
    {
        mMessages = new ArrayList();
    }

    public mMessages(CharSequence charsequence)
    {
        mMessages = new ArrayList();
        mUserDisplayName = charsequence;
    }
}
