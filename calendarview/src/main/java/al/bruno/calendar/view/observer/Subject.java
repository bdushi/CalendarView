package al.bruno.calendar.view.observer;

/**
 * Created by 1sd on 3/15/18.
 */

public interface Subject<L, T> {
    void registerObserver(Observer<L, T> o);
    void removeObserver(Observer<L, T> o);
    void notifyObserver(T t);
}
