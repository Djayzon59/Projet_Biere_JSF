package bean;

import dao.DAOFactory;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import model.Article;
import model.Couleur;
import model.Marque;
import model.TypeBiere;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

@Named(value = "articleBean")
@ViewScoped
public class ArticleBean implements Serializable {
    @Inject
    private MarqueBean marqueBean;
    @Inject
    private CouleurBean couleurBean;
    @Inject
    private TypeBean typeBean;

    private ArrayList<Article> allArticle;
    private ArrayList<Marque> allMarque;
    private ArrayList<Couleur> allCouleur;
    private ArrayList<TypeBiere> allType;
    private Article articleSelected;

    @PostConstruct
    private void init(){
        allArticle = DAOFactory.getArticleDAO().getAll();
        allMarque = new ArrayList<>(marqueBean.getAllMarque());
        allCouleur = new ArrayList<>(couleurBean.getAllCouleurs());
        allType = new ArrayList<>(typeBean.getAllTypes());
    }

    public void saveArticle() throws SQLException {
        if (this.articleSelected.getId() == 0) {
            DAOFactory.getArticleDAO().insert(articleSelected);
            allArticle.add(articleSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("L'article %s a été ajoutée !", articleSelected.getLibelle())));
        } else {
            DAOFactory.getArticleDAO().update(articleSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("L'article %s a été mise à jour  !", articleSelected.getLibelle())));
        }
        PrimeFaces.current().executeScript("PF('widgetGererArticle').hide()");
        PrimeFaces.current().ajax().update("form:id-message", "form:datatable-articles", "form:id_bouton_delete", "dialogs:confirmer-supprimer-article");
    }

    public void deleteArticle() {
        if (DAOFactory.getArticleDAO().delete(articleSelected)) {
            allArticle.remove(articleSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("L'article %s a été supprimée !", articleSelected.getLibelle())));
            articleSelected = null;
        } else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("L'article %s ne peut pas être supprimée !", articleSelected.getLibelle())));

        PrimeFaces.current().ajax().update("form:id-message", "form:datatable-articles", "form:id_bouton_delete");
    }

    public void onRowSelect(SelectEvent<Article> event) {
        FacesMessage message = new FacesMessage("Article sélectionné", String.format("%(d - %s", articleSelected.getId(), articleSelected.getLibelle()));
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void newArticle() {
        this.articleSelected = new Article();
    }


    public ArrayList<Article> getAllArticle() {
        return allArticle;
    }

    public void setAllArticle(ArrayList<Article> allArticle) {
        this.allArticle = allArticle;
    }

    public ArrayList<Marque> getAllMarque() {
        return allMarque;
    }

    public void setAllMarque(ArrayList<Marque> allMarque) {
        this.allMarque = allMarque;
    }

    public ArrayList<Couleur> getAllCouleur() {
        return allCouleur;
    }

    public void setAllCouleur(ArrayList<Couleur> allCouleur) {
        this.allCouleur = allCouleur;
    }

    public ArrayList<TypeBiere> getAllType() {
        return allType;
    }

    public void setAllType(ArrayList<TypeBiere> allType) {
        this.allType = allType;
    }

    public Article getArticleSelected() {
        return articleSelected;
    }

    public void setArticleSelected(Article articleSelected) {
        this.articleSelected = articleSelected;
    }
}
