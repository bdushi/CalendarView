package al.bruno.calendar.view.rx;

import org.joda.time.LocalDate;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class PushLocalDate {
    public PushLocalDate() {}
    private Subject<LocalDate[]> subject = PublishSubject.create();

    public Subject<LocalDate[]> subject() {
        return subject;
    }

    public void send(LocalDate[] localDate) {
        subject.onNext(localDate);
    }

    public Observable<LocalDate[]> toObservable()  {
        return subject;
    }
}
