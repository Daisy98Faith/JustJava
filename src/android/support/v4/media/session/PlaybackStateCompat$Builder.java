// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.os.Bundle;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v4.media.session:
//            PlaybackStateCompat

public static final class mExtras
{

    private long mActions;
    private long mActiveItemId;
    private long mBufferedPosition;
    private final List mCustomActions;
    private int mErrorCode;
    private CharSequence mErrorMessage;
    private Bundle mExtras;
    private long mPosition;
    private float mRate;
    private int mState;
    private long mUpdateTime;

    public tion addCustomAction(tion tion)
    {
        if (tion == null)
        {
            throw new IllegalArgumentException("You may not add a null CustomAction to PlaybackStateCompat.");
        } else
        {
            mCustomActions.add(tion);
            return this;
        }
    }

    public mCustomActions addCustomAction(String s, String s1, int i)
    {
        return addCustomAction(new tion(s, s1, i, null));
    }

    public PlaybackStateCompat build()
    {
        return new PlaybackStateCompat(mState, mPosition, mBufferedPosition, mRate, mActions, mErrorCode, mErrorMessage, mUpdateTime, mCustomActions, mActiveItemId, mExtras);
    }

    public mExtras setActions(long l)
    {
        mActions = l;
        return this;
    }

    public mActions setActiveQueueItemId(long l)
    {
        mActiveItemId = l;
        return this;
    }

    public mActiveItemId setBufferedPosition(long l)
    {
        mBufferedPosition = l;
        return this;
    }

    public mBufferedPosition setErrorMessage(int i, CharSequence charsequence)
    {
        mErrorCode = i;
        mErrorMessage = charsequence;
        return this;
    }

    public mErrorMessage setErrorMessage(CharSequence charsequence)
    {
        mErrorMessage = charsequence;
        return this;
    }

    public mErrorMessage setExtras(Bundle bundle)
    {
        mExtras = bundle;
        return this;
    }

    public mExtras setState(int i, long l, float f)
    {
        return setState(i, l, f, SystemClock.elapsedRealtime());
    }

    public setState setState(int i, long l, float f, long l1)
    {
        mState = i;
        mPosition = l;
        mUpdateTime = l1;
        mRate = f;
        return this;
    }

    public tion()
    {
        mCustomActions = new ArrayList();
        mActiveItemId = -1L;
    }

    public mActiveItemId(PlaybackStateCompat playbackstatecompat)
    {
        mCustomActions = new ArrayList();
        mActiveItemId = -1L;
        mState = playbackstatecompat.mState;
        mPosition = playbackstatecompat.mPosition;
        mRate = playbackstatecompat.mSpeed;
        mUpdateTime = playbackstatecompat.mUpdateTime;
        mBufferedPosition = playbackstatecompat.mBufferedPosition;
        mActions = playbackstatecompat.mActions;
        mErrorCode = playbackstatecompat.mErrorCode;
        mErrorMessage = playbackstatecompat.mErrorMessage;
        if (playbackstatecompat.mCustomActions != null)
        {
            mCustomActions.addAll(playbackstatecompat.mCustomActions);
        }
        mActiveItemId = playbackstatecompat.mActiveItemId;
        mExtras = playbackstatecompat.mExtras;
    }
}
