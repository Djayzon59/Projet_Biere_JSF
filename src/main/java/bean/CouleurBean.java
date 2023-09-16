package bean;

import dao.DAOFactory;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import model.Couleur;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import java.io.Serializable;
import java.util.ArrayList;

@Named
@ViewScoped
public class CouleurBean implements Serializable {

    private ArrayList<Couleur> allCouleurs;
    private Couleur couleurSelected;

    @PostConstruct
    private void init() {
        allCouleurs = DAOFactory.getCouleurDAO().getAll();
    }

    public void saveColor() {
        if (this.couleurSelected.getId() == 0) {
            DAOFactory.getCouleurDAO().insert(couleurSelected);
            allCouleurs.add(couleurSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("La couleur %s a été ajoutée !", couleurSelected.getLibelle())));
        } else {
            DAOFactory.getCouleurDAO().update(couleurSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("La couleur %s a été mise à jour  !", couleurSelected.getLibelle())));
        }
        PrimeFaces.current().executeScript("PF('widgetGererCouleur').hide()");
        PrimeFaces.current().ajax().update("form:id-message", "form:datatable-couleurs", "form:id_bouton_delete", "dialogs:confirmer-supprimer-couleur");
    }

    public void deleteColor() {
        if (DAOFactory.getCouleurDAO().delete(couleurSelected)) {
            allCouleurs.remove(couleurSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("La couleur %s a été supprimée !", couleurSelected.getLibelle())));
            couleurSelected = null;
        } else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("La couleur %s ne peut pas être supprimée !", couleurSelected.getLibelle())));

        PrimeFaces.current().ajax().update("form:id-message", "form:datatable-couleurs", "form:id_bouton_delete");
    }

    public void onRowSelect(SelectEvent<Couleur> event) {
        System.out.println();
        FacesMessage message = new FacesMessage("Couleur sélectionnée", String.format("%(d - %s", couleurSelected.getId(), couleurSelected.getLibelle()));
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void newColor() {
        this.couleurSelected = new Couleur();
    }


    public ArrayList<Couleur> getAllCouleurs() {
        return allCouleurs;
    }

    public void setAllCouleurs(ArrayList<Couleur> allCouleurs) {
        this.allCouleurs = allCouleurs;
    }

    public Couleur getCouleurSelected() {
        return couleurSelected;
    }

    public void setCouleurSelected(Couleur couleurSelected) {
        this.couleurSelected = couleurSelected;
    }

}
