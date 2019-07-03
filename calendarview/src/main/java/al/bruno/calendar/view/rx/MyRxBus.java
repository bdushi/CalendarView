package al.bruno.calendar.view.rx;

import org.joda.time.LocalDate;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class MyRxBus {
    private Subject<LocalDate[]> subject = PublishSubject.create();

    public void send(LocalDate[] localDate) {
        subject.onNext(localDate);
    }

    public Observable<LocalDate[]> toObserverable()  {
        return subject;
    }
}
