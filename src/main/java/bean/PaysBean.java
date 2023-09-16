package bean;

import dao.DAOFactory;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import model.Continent;
import model.Couleur;
import model.Pays;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

@Named
@ViewScoped
public class PaysBean implements Serializable {

    @Inject
    private ContinentBean continentBean;
    private ArrayList<Pays> allPays;
    private Pays paysSelected;
    private ArrayList<Continent> allContinents;

    @PostConstruct
    private void init() {
        allPays = DAOFactory.getPaysDAO().getAll();
        allContinents = new ArrayList<>(continentBean.getAllContinents());
        allContinents.add(0, new Continent(0, "choisir un continent"));
    }

    public void savePays() throws SQLException {
        if (this.paysSelected.getId() == 0) {
            DAOFactory.getPaysDAO().insert(paysSelected);
            allPays.add(paysSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("Le pays %s a été ajouté !", paysSelected.getLibelle())));
        } else {
            DAOFactory.getPaysDAO().update(paysSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("Le pays %s a été mise à jour  !", paysSelected.getLibelle())));
        }
        PrimeFaces.current().executeScript("PF('widgetGererPays').hide()");
        PrimeFaces.current().ajax().update("form:id-message", "form:datatable-pays", "form:id_bouton_delete", "dialogs:confirmer-supprimer-pays");
    }

    public void deletePays() {
        if (DAOFactory.getPaysDAO().delete(paysSelected)) {
            allPays.remove(paysSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("Le pays %s a été supprimée !", paysSelected.getLibelle())));
            paysSelected = null;
        } else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("Le pays %s ne peut pas être supprimée !", paysSelected.getLibelle())));

        PrimeFaces.current().ajax().update("form:id-message", "form:datatable-pays", "form:id_bouton_delete");
    }

    public void onRowSelect(SelectEvent<Couleur> event) {
        FacesMessage message = new FacesMessage("Pays sélectionnée", String.format("%(d - %s", paysSelected.getId(), paysSelected.getLibelle()));
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void newPays() {
        this.paysSelected = new Pays();
    }

    public ArrayList<Pays> getAllPays() {
        return allPays;
    }

    public void setAllPays(ArrayList<Pays> allPays) {
        this.allPays = allPays;
    }

    public Pays getPaysSelected() {
        return paysSelected;
    }

    public void setPaysSelected(Pays paysSelected) {
        this.paysSelected = paysSelected;
    }

    public ArrayList<Continent> getAllContinents() {
        return allContinents;
    }

    public void setAllContinents(ArrayList<Continent> allContinents) {
        this.allContinents = allContinents;
    }
}
