package bean;

import dao.DAOFactory;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import model.Continent;

import java.io.Serializable;
import java.util.ArrayList;

@Named
@ViewScoped
public class ContinentBean implements Serializable {

    private ArrayList<Continent> allContinents;


    @PostConstruct
    private void init(){
        allContinents = DAOFactory.getContinentDAO().getAll();
    }


    public ArrayList<Continent> getAllContinents() {
        return allContinents;
    }

    public void setAllContinents(ArrayList<Continent> allContinents) {
        this.allContinents = allContinents;
    }
}
