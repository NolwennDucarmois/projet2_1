package mvc.view;

import automobile.metier.Pilote;
import mvc.controller.PiloteController;
import mvc.observer.Observer;

import java.util.List;

public abstract class PiloteAbstractView implements Observer {
    protected PiloteController piloteController;
    protected List<Pilote> lp;

    public void setController(PiloteController piloteController) {
        this.piloteController = piloteController;
    }

    public abstract void affMsg(String msg);

    public abstract Pilote selectionner();

    public abstract void menu();

    public abstract void affList(List l);

    @Override
    public void update(List lp) {
        this.lp = lp;
    }
}
