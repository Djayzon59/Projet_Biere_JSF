package bean;

import dao.DAOFactory;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import model.Couleur;
import model.TypeBiere;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

@Named
@ViewScoped
public class TypeBean implements Serializable {
    private static ArrayList<TypeBiere> allTypes;
    private TypeBiere typeBiereSelected;

    @PostConstruct
    private void init() {
        allTypes = DAOFactory.getTypeDAO().getAll();
    }

    public void saveType() throws SQLException {
        if (this.typeBiereSelected.getId() == 0) {
            DAOFactory.getTypeDAO().insert(typeBiereSelected);
            allTypes.add(typeBiereSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("Le type %s a été ajouté !", typeBiereSelected.getLibelle())));
        } else {
            DAOFactory.getTypeDAO().update(typeBiereSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("Le type %s a été mise à jour  !", typeBiereSelected.getLibelle())));
        }
        PrimeFaces.current().executeScript("PF('widgetGererType').hide()");
        PrimeFaces.current().ajax().update("form:id-message", "form:datatable-types", "form:id_bouton_delete", "dialogs:confirmer-supprimer-type");
    }

    public void deleteType() {
        if (DAOFactory.getTypeDAO().delete(typeBiereSelected)) {
            allTypes.remove(typeBiereSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("Le type %s a été supprimée !", typeBiereSelected.getLibelle())));
            typeBiereSelected = null;
        } else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("Le type %s ne peut pas être supprimée !", typeBiereSelected.getLibelle())));

        PrimeFaces.current().ajax().update("form:id-message", "form:datatable-types", "form:id_bouton_delete");
    }

    public void onRowSelect(SelectEvent<TypeBiere> event) {
        System.out.println();
        FacesMessage message = new FacesMessage("Type sélectionné", String.format("%(d - %s", typeBiereSelected.getId(), typeBiereSelected.getLibelle()));
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void newType() {
        this.typeBiereSelected = new TypeBiere();
    }

    public ArrayList<TypeBiere> getAllTypes() {
        return allTypes;
    }

    public static void setAllTypes(ArrayList<TypeBiere> allTypes) {
        TypeBean.allTypes = allTypes;
    }

    public TypeBiere getTypeBiereSelected() {
        return typeBiereSelected;
    }

    public void setTypeBiereSelected(TypeBiere typeBiereSelected) {
        this.typeBiereSelected = typeBiereSelected;
    }
}

