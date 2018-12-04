package al.bruno.calendar.view.calendar;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 18/03/2017.
 */

public class BusProvider extends Bus
{
    private static final Handler handler = new Handler(Looper.getMainLooper());

    private static BusProvider mInstance = null;
    private List objects = new ArrayList();

    private BusProvider()
    {
        super(ThreadEnforcer.ANY);
    }

    public static BusProvider getInstance()
    {
        if(mInstance == null)
            mInstance = new BusProvider();
        return mInstance;
    }

    static void disposeInstance()
    {
        mInstance = null;
    }

    @Override
    public void post(final Object event)
    {
        if(Looper.myLooper() == Looper.getMainLooper())
            super.post(event);
        else
            handler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    post(event);
                }
            });
    }

    @Override
    public void register(Object object) {
        if(objects.contains(object))
            return;
        objects.add(object);
        super.register(object);
    }

    @Override
    public void unregister(Object object)
    {
        if(!objects.contains(object))
            return;
        objects.remove(object);
        super.unregister(object);
    }
}
