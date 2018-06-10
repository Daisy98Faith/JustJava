// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media.session;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.RatingCompat;
import android.view.KeyEvent;

// Referenced classes of package android.support.v4.media.session:
//            MediaSessionCompat, PlaybackStateCompat

class this._cls0 extends Handler
{

    private static final int KEYCODE_MEDIA_PAUSE = 127;
    private static final int KEYCODE_MEDIA_PLAY = 126;
    private static final int MSG_ADD_QUEUE_ITEM = 25;
    private static final int MSG_ADD_QUEUE_ITEM_AT = 26;
    private static final int MSG_ADJUST_VOLUME = 2;
    private static final int MSG_COMMAND = 1;
    private static final int MSG_CUSTOM_ACTION = 20;
    private static final int MSG_FAST_FORWARD = 16;
    private static final int MSG_MEDIA_BUTTON = 21;
    private static final int MSG_NEXT = 14;
    private static final int MSG_PAUSE = 12;
    private static final int MSG_PLAY = 7;
    private static final int MSG_PLAY_MEDIA_ID = 8;
    private static final int MSG_PLAY_SEARCH = 9;
    private static final int MSG_PLAY_URI = 10;
    private static final int MSG_PREPARE = 3;
    private static final int MSG_PREPARE_MEDIA_ID = 4;
    private static final int MSG_PREPARE_SEARCH = 5;
    private static final int MSG_PREPARE_URI = 6;
    private static final int MSG_PREVIOUS = 15;
    private static final int MSG_RATE = 19;
    private static final int MSG_REMOVE_QUEUE_ITEM = 27;
    private static final int MSG_REMOVE_QUEUE_ITEM_AT = 28;
    private static final int MSG_REWIND = 17;
    private static final int MSG_SEEK_TO = 18;
    private static final int MSG_SET_REPEAT_MODE = 23;
    private static final int MSG_SET_SHUFFLE_MODE_ENABLED = 24;
    private static final int MSG_SET_VOLUME = 22;
    private static final int MSG_SKIP_TO_ITEM = 11;
    private static final int MSG_STOP = 13;
    final obtainMessage this$0;

    private void onMediaButtonEvent(KeyEvent keyevent, this._cls0 _pcls0)
    {
        boolean flag2 = true;
        if (keyevent != null && keyevent.getAction() == 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        boolean flag;
        boolean flag1;
        long l;
        if (_fld0 == null)
        {
            l = 0L;
        } else
        {
            l = _fld0.getActions();
        }
        keyevent.getKeyCode();
        JVM INSTR lookupswitch 9: default 116
    //                   79: 117
    //                   85: 117
    //                   86: 261
    //                   87: 229
    //                   88: 245
    //                   89: 291
    //                   90: 275
    //                   126: 197
    //                   127: 213;
           goto _L3 _L4 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L8:
        continue; /* Loop/switch isn't completed */
_L3:
        return;
_L4:
        if (_fld0 != null && _fld0.getState() == 3)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if ((516L & l) != 0L)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if ((514L & l) == 0L)
        {
            flag2 = false;
        }
        if (flag && flag2)
        {
            _pcls0._mth0();
            return;
        }
        continue; /* Loop/switch isn't completed */
_L10:
        if ((4L & l) == 0L) goto _L1; else goto _L12
_L12:
        _pcls0._mth0();
        return;
_L11:
        if ((2L & l) == 0L) goto _L1; else goto _L13
_L13:
        _pcls0._mth0();
        return;
_L6:
        if ((32L & l) == 0L) goto _L1; else goto _L14
_L14:
        _pcls0._mth0();
        return;
_L7:
        if ((16L & l) == 0L) goto _L1; else goto _L15
_L15:
        _pcls0._mth0();
        return;
_L5:
        if ((1L & l) == 0L) goto _L1; else goto _L16
_L16:
        _pcls0._mth0();
        return;
_L9:
        if ((64L & l) == 0L) goto _L1; else goto _L17
_L17:
        _pcls0._mth0();
        return;
        if ((8L & l) == 0L) goto _L1; else goto _L18
_L18:
        _pcls0._mth0();
        return;
        if (flag || !flag1) goto _L1; else goto _L19
_L19:
        _pcls0._mth0();
        return;
    }

    public void handleMessage(Message message)
    {
        this._cls0 _lcls0 = _fld0;
        if (_lcls0 != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        switch (message.what)
        {
        default:
            return;

        case 1: // '\001'
            message = (this._cls0)message.obj;
            _lcls0._mth0(((this._cls0) (message))., (() (message))., (() (message)).);
            return;

        case 21: // '\025'
            message = (KeyEvent)message.obj;
            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
            intent.putExtra("android.intent.extra.KEY_EVENT", message);
            if (!_lcls0.(intent))
            {
                onMediaButtonEvent(message, _lcls0);
                return;
            }
            break;

        case 3: // '\003'
            _lcls0.onMediaButtonEvent();
            return;

        case 4: // '\004'
            _lcls0.onMediaButtonEvent((String)message.obj, message.getData());
            return;

        case 5: // '\005'
            _lcls0.onMediaButtonEvent((String)message.obj, message.getData());
            return;

        case 6: // '\006'
            _lcls0.onMediaButtonEvent((Uri)message.obj, message.getData());
            return;

        case 7: // '\007'
            _lcls0.onMediaButtonEvent();
            return;

        case 8: // '\b'
            _lcls0.onMediaButtonEvent((String)message.obj, message.getData());
            return;

        case 9: // '\t'
            _lcls0.onMediaButtonEvent((String)message.obj, message.getData());
            return;

        case 10: // '\n'
            _lcls0.onMediaButtonEvent((Uri)message.obj, message.getData());
            return;

        case 11: // '\013'
            _lcls0.onMediaButtonEvent(((Long)message.obj).longValue());
            return;

        case 12: // '\f'
            _lcls0.onMediaButtonEvent();
            return;

        case 13: // '\r'
            _lcls0.onMediaButtonEvent();
            return;

        case 14: // '\016'
            _lcls0.onMediaButtonEvent();
            return;

        case 15: // '\017'
            _lcls0.onMediaButtonEvent();
            return;

        case 16: // '\020'
            _lcls0.onMediaButtonEvent();
            return;

        case 17: // '\021'
            _lcls0.onMediaButtonEvent();
            return;

        case 18: // '\022'
            _lcls0.onMediaButtonEvent(((Long)message.obj).longValue());
            return;

        case 19: // '\023'
            _lcls0.onMediaButtonEvent((RatingCompat)message.obj);
            return;

        case 20: // '\024'
            _lcls0.onMediaButtonEvent((String)message.obj, message.getData());
            return;

        case 25: // '\031'
            _lcls0.onMediaButtonEvent((MediaDescriptionCompat)message.obj);
            return;

        case 26: // '\032'
            _lcls0.onMediaButtonEvent((MediaDescriptionCompat)message.obj, message.arg1);
            return;

        case 27: // '\033'
            _lcls0.onMediaButtonEvent((MediaDescriptionCompat)message.obj);
            return;

        case 28: // '\034'
            _lcls0.onMediaButtonEvent(message.arg1);
            return;

        case 2: // '\002'
            onMediaButtonEvent.this.onMediaButtonEvent(message.arg1, 0);
            return;

        case 22: // '\026'
            onMediaButtonEvent.this.onMediaButtonEvent(message.arg1, 0);
            return;

        case 23: // '\027'
            _lcls0.onMediaButtonEvent(message.arg1);
            return;

        case 24: // '\030'
            _lcls0.onMediaButtonEvent(((Boolean)message.obj).booleanValue());
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public void post(int i)
    {
        post(i, null);
    }

    public void post(int i, Object obj)
    {
        obtainMessage(i, obj).sendToTarget();
    }

    public void post(int i, Object obj, int j)
    {
        obtainMessage(i, j, 0, obj).sendToTarget();
    }

    public void post(int i, Object obj, Bundle bundle)
    {
        obj = obtainMessage(i, obj);
        ((Message) (obj)).setData(bundle);
        ((Message) (obj)).sendToTarget();
    }

    public (Looper looper)
    {
        this$0 = this._cls0.this;
        super(looper);
    }
}
