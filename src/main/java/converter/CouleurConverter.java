package converter;

import bean.CouleurBean;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import model.Couleur;
import model.Marque;

@FacesConverter(value = "couleurConverter", managed = true)
public class CouleurConverter implements Converter<Couleur> {
    @Inject
    private CouleurBean couleurBean;
    @Override
    public Couleur getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && value.trim().length() > 0) {
            for (Couleur couleur : couleurBean.getAllCouleurs()) {
                if (couleur.getId() == Integer.parseInt(value)){
                    return couleur;}
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Couleur couleur) {
        return String.valueOf(couleur.getId());
    }
}
