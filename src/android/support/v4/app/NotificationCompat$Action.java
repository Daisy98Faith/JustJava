// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v4.app:
//            NotificationCompat, RemoteInput

public static class mAllowGeneratedReplies extends ion
{
    public static final class Builder
    {

        private boolean mAllowGeneratedReplies;
        private final Bundle mExtras;
        private final int mIcon;
        private final PendingIntent mIntent;
        private ArrayList mRemoteInputs;
        private final CharSequence mTitle;

        public Builder addExtras(Bundle bundle)
        {
            if (bundle != null)
            {
                mExtras.putAll(bundle);
            }
            return this;
        }

        public Builder addRemoteInput(RemoteInput remoteinput)
        {
            if (mRemoteInputs == null)
            {
                mRemoteInputs = new ArrayList();
            }
            mRemoteInputs.add(remoteinput);
            return this;
        }

        public NotificationCompat.Action build()
        {
            RemoteInput aremoteinput[] = new ArrayList();
            RemoteInput aremoteinput1[] = new ArrayList();
            if (mRemoteInputs != null)
            {
                for (Iterator iterator = mRemoteInputs.iterator(); iterator.hasNext();)
                {
                    RemoteInput remoteinput = (RemoteInput)iterator.next();
                    if (remoteinput.isDataOnly())
                    {
                        aremoteinput.add(remoteinput);
                    } else
                    {
                        aremoteinput1.add(remoteinput);
                    }
                }

            }
            if (aremoteinput.isEmpty())
            {
                aremoteinput = null;
            } else
            {
                aremoteinput = (RemoteInput[])aremoteinput.toArray(new RemoteInput[aremoteinput.size()]);
            }
            if (aremoteinput1.isEmpty())
            {
                aremoteinput1 = null;
            } else
            {
                aremoteinput1 = (RemoteInput[])aremoteinput1.toArray(new RemoteInput[aremoteinput1.size()]);
            }
            return new NotificationCompat.Action(mIcon, mTitle, mIntent, mExtras, aremoteinput1, aremoteinput, mAllowGeneratedReplies);
        }

        public Builder extend(Extender extender)
        {
            extender.extend(this);
            return this;
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        public Builder setAllowGeneratedReplies(boolean flag)
        {
            mAllowGeneratedReplies = flag;
            return this;
        }

        public Builder(int i, CharSequence charsequence, PendingIntent pendingintent)
        {
            this(i, charsequence, pendingintent, new Bundle(), null, true);
        }

        private Builder(int i, CharSequence charsequence, PendingIntent pendingintent, Bundle bundle, RemoteInput aremoteinput[], boolean flag)
        {
            mAllowGeneratedReplies = true;
            mIcon = i;
            mTitle = NotificationCompat.Builder.limitCharSequenceLength(charsequence);
            mIntent = pendingintent;
            mExtras = bundle;
            if (aremoteinput == null)
            {
                charsequence = null;
            } else
            {
                charsequence = new ArrayList(Arrays.asList(aremoteinput));
            }
            mRemoteInputs = charsequence;
            mAllowGeneratedReplies = flag;
        }

        public Builder(NotificationCompat.Action action)
        {
            this(action.icon, action.title, action.actionIntent, new Bundle(action.mExtras), action.getRemoteInputs(), action.getAllowGeneratedReplies());
        }
    }

    public static interface Extender
    {

        public abstract Builder extend(Builder builder);
    }

    public static final class WearableExtender
        implements Extender
    {

        private static final int DEFAULT_FLAGS = 1;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_AVAILABLE_OFFLINE = 1;
        private static final int FLAG_HINT_DISPLAY_INLINE = 4;
        private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
        private static final String KEY_CANCEL_LABEL = "cancelLabel";
        private static final String KEY_CONFIRM_LABEL = "confirmLabel";
        private static final String KEY_FLAGS = "flags";
        private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
        private CharSequence mCancelLabel;
        private CharSequence mConfirmLabel;
        private int mFlags;
        private CharSequence mInProgressLabel;

        private void setFlag(int i, boolean flag)
        {
            if (flag)
            {
                mFlags = mFlags | i;
                return;
            } else
            {
                mFlags = mFlags & ~i;
                return;
            }
        }

        public WearableExtender clone()
        {
            WearableExtender wearableextender = new WearableExtender();
            wearableextender.mFlags = mFlags;
            wearableextender.mInProgressLabel = mInProgressLabel;
            wearableextender.mConfirmLabel = mConfirmLabel;
            wearableextender.mCancelLabel = mCancelLabel;
            return wearableextender;
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public Builder extend(Builder builder)
        {
            Bundle bundle = new Bundle();
            if (mFlags != 1)
            {
                bundle.putInt("flags", mFlags);
            }
            if (mInProgressLabel != null)
            {
                bundle.putCharSequence("inProgressLabel", mInProgressLabel);
            }
            if (mConfirmLabel != null)
            {
                bundle.putCharSequence("confirmLabel", mConfirmLabel);
            }
            if (mCancelLabel != null)
            {
                bundle.putCharSequence("cancelLabel", mCancelLabel);
            }
            builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
            return builder;
        }

        public CharSequence getCancelLabel()
        {
            return mCancelLabel;
        }

        public CharSequence getConfirmLabel()
        {
            return mConfirmLabel;
        }

        public boolean getHintDisplayActionInline()
        {
            return (mFlags & 4) != 0;
        }

        public boolean getHintLaunchesActivity()
        {
            return (mFlags & 2) != 0;
        }

        public CharSequence getInProgressLabel()
        {
            return mInProgressLabel;
        }

        public boolean isAvailableOffline()
        {
            return (mFlags & 1) != 0;
        }

        public WearableExtender setAvailableOffline(boolean flag)
        {
            setFlag(1, flag);
            return this;
        }

        public WearableExtender setCancelLabel(CharSequence charsequence)
        {
            mCancelLabel = charsequence;
            return this;
        }

        public WearableExtender setConfirmLabel(CharSequence charsequence)
        {
            mConfirmLabel = charsequence;
            return this;
        }

        public WearableExtender setHintDisplayActionInline(boolean flag)
        {
            setFlag(4, flag);
            return this;
        }

        public WearableExtender setHintLaunchesActivity(boolean flag)
        {
            setFlag(2, flag);
            return this;
        }

        public WearableExtender setInProgressLabel(CharSequence charsequence)
        {
            mInProgressLabel = charsequence;
            return this;
        }

        public