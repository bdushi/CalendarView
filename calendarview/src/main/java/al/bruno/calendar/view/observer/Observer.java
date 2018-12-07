package al.bruno.calendar.view.observer;

/**
 * Created by 1sd on 3/15/18.
 */

public interface Observer<L, T> {
    L update(T t);
}
