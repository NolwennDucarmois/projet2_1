package mvc.controller;

import automobile.metier.Pays;
import automobile.metier.Pilote;
import mvc.model.DAOPays;
import mvc.view.PaysAbstractView;

import java.util.List;

public class PaysController {
    private DAOPays model;
    private PaysAbstractView view;

    public PaysController(DAOPays model, PaysAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Pays> getAll() {
        return model.getPays();
    }

    public Pays addPays(Pays pays) {
        return model.addPays(pays);
    }

    public boolean removePays(Pays pays) {
        return model.removePays(pays);
    }

    public Pays update(Pays pays) {
        return model.updatePays(pays);
    }

    public Pays search(int idPays) {
        return model.readPays(idPays);
    }
    public List<Pilote> listePilotes(Pays pays){
        return model.listePilotes(pays);
    }
}
