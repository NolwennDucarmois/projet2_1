package mvc.controller;

import automobile.metier.Pilote;
import mvc.model.DAOPilote;
import mvc.view.PiloteAbstractView;

import java.util.List;

public class PiloteController {
    private DAOPilote model;
    private PiloteAbstractView view;

    public PiloteController(DAOPilote model, PiloteAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Pilote> getAll() {
        return model.getPilote();
    }

    public Pilote addPilote(Pilote pilote) {
        return model.addPilote(pilote);
    }

    public boolean removePilote(Pilote pilote) {
        return model.removePilote(pilote);
    }

    public Pilote update(Pilote pilote) {
        return model.updatePilote(pilote);
    }

    public Pilote search(int idPilote) {
        return model.readPilote(idPilote);
    }
}
