package bean;

import dao.DAOFactory;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.annotation.View;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import model.*;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Named
@ViewScoped
public class ArticleSearchBean implements Serializable {
    @Inject
    private MarqueBean marqueBean;
    @Inject
    private CouleurBean couleurBean;
    @Inject
    private TypeBean typeBean;
    @Inject
    private PaysBean paysBean;
    @Inject
    private ContinentBean continentBean;
    @Inject
    private FabricantBean fabricantBean;


    private ArticleSearch articleSearch;
    private Article articleSelected;
    private ArrayList<Article> allArticles;
    private ArrayList<Marque> allMarques;
    private ArrayList<Couleur> allCouleurs;
    private ArrayList<TypeBiere> allTypes;
    private ArrayList<Pays> allPays;
    private ArrayList<Continent> allContinents;
    private ArrayList<Fabricant> allFabricants;


    @PostConstruct
    private void init() {
        allMarques = marqueBean.getAllMarque();
        allContinents = new ArrayList<>(continentBean.getAllContinents());
        allTypes = new ArrayList<>(typeBean.getAllTypes());
        allCouleurs = new ArrayList<>(couleurBean.getAllCouleurs());
        allPays = new ArrayList<>(paysBean.getAllPays());
        allFabricants = fabricantBean.getAllFabricants();

        allMarques.add(0, new Marque(0, "choisir une marque"));
        allCouleurs.add(0, new Couleur(0, "choisir une couleur"));
        allTypes.add(0, new TypeBiere(0, "choisir un type"));
        allPays.add(0, new Pays(0, "choisir un pays"));
        allContinents.add(0, new Continent(0, "choisir un continent"));
        allFabricants.add(0, new Fabricant(0, "choisir un fabricant"));

        articleSearch = new ArticleSearch();
        allArticles = DAOFactory.getArticleDAO().getLike(articleSearch);
    }

    public void chercher() {
        System.out.println(articleSearch);
        allArticles = DAOFactory.getArticleDAO().getLike(articleSearch);
    }

    public void paysToChanged() {
        if (articleSearch.getContinent() == null) {
            allPays = new ArrayList<>(paysBean.getAllPays());
            allPays.add(0, new Pays(0, "Choisir un pays"));
        } else {
            allPays = new ArrayList<>();
            for (Pays pays : paysBean.getAllPays()) {
                if (pays.getContinent().getId() == articleSearch.getContinent().getId()) {
                    allPays.add(pays);
                }
            }
            allPays.add(0, new Pays(0, "Choisir un pays"));
        }
        articleSearch.setPays(allPays.get(0));
    }

    public void saveArticle() throws SQLException {
        if (this.articleSelected.getId() == 0) {
            DAOFactory.getArticleDAO().insert(articleSelected);
            allArticles.add(articleSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("L'article %s a été ajouté !", articleSelected.getLibelle())));
        } else {
            DAOFactory.getArticleDAO().update(articleSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("L'article %s a été mis à jour  !", articleSelected.getLibelle())));
        }
        PrimeFaces.current().executeScript("PF('widgetGererArticle').hide()");
        PrimeFaces.current().ajax().update("form:id-message", "form:datatable-article", "form:id_bouton_delete", "dialogs:confirmer-supprimer-article");
    }

    public void deleteArticle() {
        if (DAOFactory.getArticleDAO().delete(articleSelected)) {
            allArticles.remove(articleSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("L'article %s a été supprimé !", articleSelected.getLibelle())));
            articleSelected = null;
        } else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(String.format("L'article %s ne peut pas être supprimé !", articleSelected.getLibelle())));

        PrimeFaces.current().ajax().update("form:id-message", "form:datatable-article", "form:id_bouton_delete");
    }

    public void onRowSelect(SelectEvent<Article> event) {
        FacesMessage message = new FacesMessage("Article selectionné", String.format("%(d - %s", articleSelected.getId(), articleSelected.getLibelle()));
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void newArticle() {
        this.articleSelected = new Article();
    }


    public ArticleSearch getArticleSearch() {
        return articleSearch;
    }

    public void setArticleSearch(ArticleSearch articleSearch) {
        this.articleSearch = articleSearch;
    }

    public ArrayList<Article> getAllArticles() {
        return allArticles;
    }

    public void setAllArticles(ArrayList<Article> allArticles) {
        this.allArticles = allArticles;
    }

    public ArrayList<Marque> getAllMarques() {
        return allMarques;
    }

    public void setAllMarques(ArrayList<Marque> allMarques) {
        this.allMarques = allMarques;
    }

    public ArrayList<Couleur> getAllCouleurs() {
        return allCouleurs;
    }

    public void setAllCouleurs(ArrayList<Couleur> allCouleurs) {
        this.allCouleurs = allCouleurs;
    }

    public ArrayList<TypeBiere> getAllTypes() {
        return allTypes;
    }

    public void setAllTypes(ArrayList<TypeBiere> allTypes) {
        this.allTypes = allTypes;
    }

    public ArrayList<Pays> getAllPays() {
        return allPays;
    }

    public void setAllPays(ArrayList<Pays> allPays) {
        this.allPays = allPays;
    }

    public ArrayList<Continent> getAllContinents() {
        return allContinents;
    }

    public void setAllContinents(ArrayList<Continent> allContinents) {
        this.allContinents = allContinents;
    }

    public ArrayList<Fabricant> getAllFabricants() {
        return allFabricants;
    }

    public void setAllFabricants(ArrayList<Fabricant> allFabricants) {
        this.allFabricants = allFabricants;
    }

    public Article getArticleSelected() {
        return articleSelected;
    }

    public void setArticleSelected(Article articleSelected) {
        this.articleSelected = articleSelected;
    }
}
