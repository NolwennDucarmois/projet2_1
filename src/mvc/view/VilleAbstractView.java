package mvc.view;

import automobile.metier.Ville;
import mvc.controller.VilleController;
import mvc.observer.Observer;

import java.util.List;

public abstract class VilleAbstractView implements Observer {
    protected VilleController villeController;
    protected List<Ville> lv;

    public void setController(VilleController villeController) {
        this.villeController = villeController;
    }

    public abstract void affMsg(String msg);

    public abstract Ville selectionner();

    public abstract void menu();

    public abstract void affList(List l);

    @Override
    public void update(List lv) {
        this.lv = lv;
        affList(lv);
    }
}
