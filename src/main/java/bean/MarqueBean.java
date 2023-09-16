package bean;

import dao.DAOFactory;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.faces.event.ValueChangeListener;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


import model.Fabricant;
import model.Marque;
import model.Pays;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import java.io.Serializable;

import java.util.ArrayList;

@Named(value = "marqueBean")
@ViewScoped
public class MarqueBean implements Serializable {
    @Inject
    private PaysBean paysBean;
    @Inject
    private FabricantBean fabricantBean;

    private ArrayList<Marque> allMarque;
    private Marque marqueSelected;
    private ArrayList<Pays> listePays;
    private ArrayList<Fabricant> listeFabricants;


    @PostConstruct
    private void init() {
        allMarque = DAOFactory.getMarqueDAO().getAll();
        listePays = new ArrayList<>(paysBean.getAllPays());
        listePays.add(0,new Pays(0,"Choisir un pays"));
        listeFabricants = new ArrayList<>(fabricantBean.getAllFabricants());
        listeFabricants.add(0,new Fabricant(0,"Choisir un fabricant"));
    }

    public void saveMarque() {
        if (this.marqueSelected.getId() == 0) {
            DAOFactory.getMarqueDAO().insert(marqueSelected);
            allMarque.add(marqueSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("La marque %s a été ajoutée !", marqueSelected.getLibelle())));
        } else {
            DAOFactory.getMarqueDAO().update(marqueSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("La marque %s a été mise à jour  !", marqueSelected.getLibelle())));
        }
        PrimeFaces.current().executeScript("PF('widgetGererMarque').hide()");
        PrimeFaces.current().ajax().update("form:id-message", "form:datatable-marques", "form:id_bouton_delete", "dialogs:confirmer-supprimer-marque");
    }

    public void deleteMarque() {
        if (DAOFactory.getMarqueDAO().delete(marqueSelected)) {
            allMarque.remove(marqueSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("La marque %s a été supprimée !", marqueSelected.getLibelle())));
            marqueSelected = null;
        } else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("La marque %s ne peut pas être supprimée !", marqueSelected.getLibelle())));

        PrimeFaces.current().ajax().update("form:id-message", "form:datatable-marques", "form:id_bouton_delete");
    }

    public void onRowSelect(SelectEvent<Marque> event) {
        FacesMessage message = new FacesMessage("Marque sélectionnée", String.format("%(d - %s", marqueSelected.getId(), marqueSelected.getLibelle()));
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void newMarque() {
        this.marqueSelected = new Marque();
    }


    public ArrayList<Marque> getAllMarque() {
        return allMarque;
    }

    public void setAllMarque(ArrayList<Marque> allMarque) {
        this.allMarque = allMarque;
    }

    public Marque getMarqueSelected() {
        return marqueSelected;
    }

    public void setMarqueSelected(Marque marqueSelected) {
        this.marqueSelected = marqueSelected;
    }

    public ArrayList<Pays> getListePays() {
        return listePays;
    }

    public void setListePays(ArrayList<Pays> listePays) {
        this.listePays = listePays;
    }

    public ArrayList<Fabricant> getListeFabricants() {
        return listeFabricants;
    }

    public void setListeFabricants(ArrayList<Fabricant> listeFabricants) {
        this.listeFabricants = listeFabricants;
    }

}
