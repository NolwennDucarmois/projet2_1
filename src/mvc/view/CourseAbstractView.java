package mvc.view;

import automobile.metier.Course;
import mvc.controller.CourseController;
import mvc.controller.PiloteController;
import mvc.observer.Observer;

import java.util.List;

public abstract class CourseAbstractView implements Observer {
    protected CourseController courseController;
    protected List<Course> lc;
    protected PiloteAbstractView pv;

    public void setController(CourseController courseController) {
        this.courseController = courseController;
    }

    public abstract void affMsg(String msg);

    public abstract Course selectionner();

    public abstract void menu();

    public abstract void affList(List l);

    public void setPiloteView(PiloteAbstractView pv) {
        this.pv = pv;
    }

    @Override
    public void update(List lc) {
        this.lc = lc;
        affList(lc);
    }
}
