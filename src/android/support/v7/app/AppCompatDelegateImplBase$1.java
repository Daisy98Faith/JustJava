// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;


// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImplBase

static final class val.defHandler
    implements ler
{

    final ler val$defHandler;

    private boolean shouldWrapException(Throwable throwable)
    {
        boolean flag;
label0:
        {
            boolean flag1 = false;
            flag = flag1;
            if (!(throwable instanceof android.content.res.._cls1))
            {
                break label0;
            }
            throwable = throwable.getMessage();
            flag = flag1;
            if (throwable == null)
            {
                break label0;
            }
            if (!throwable.contains("drawable"))
            {
                flag = flag1;
                if (!throwable.contains("Drawable"))
                {
                    break label0;
                }
            }
            flag = true;
        }
        return flag;
    }

    public void uncaughtException(Thread thread, Throwable throwable)
    {
        if (shouldWrapException(throwable))
        {
            android.content.res.._cls1 _lcls1 = new android.content.res.<init>((new StringBuilder()).append(throwable.getMessage()).append(". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.").toString());
            _lcls1.initCause(throwable.getCause());
            _lcls1.setStackTrace(throwable.getStackTrace());
            val$defHandler.uncaughtException(thread, _lcls1);
            return;
        } else
        {
            val$defHandler.uncaughtException(thread, throwable);
            return;
        }
    }

    (ler ler)
    {
        val$defHandler = ler;
        super();
    }
}
