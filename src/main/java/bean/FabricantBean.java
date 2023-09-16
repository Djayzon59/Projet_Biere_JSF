package bean;

import dao.DAOFactory;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import model.Fabricant;

import java.io.Serializable;
import java.util.ArrayList;

@Named
@ViewScoped
public class FabricantBean implements Serializable {

    private ArrayList<Fabricant> allFabricants;

    @PostConstruct
    private void init(){
        allFabricants = DAOFactory.getFabricantDAO().getAll();
    }

    public ArrayList<Fabricant> getAllFabricants() {
        return allFabricants;
    }

    public void setAllFabricants(ArrayList<Fabricant> allFabricants) {
        this.allFabricants = allFabricants;
    }
}
