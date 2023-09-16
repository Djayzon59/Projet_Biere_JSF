package converter;

import bean.MarqueBean;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import model.Fabricant;
import model.Marque;

@FacesConverter(value = "marqueConverter", managed = true)
public class MarqueConverter implements Converter<Marque> {
    @Inject
    private MarqueBean marqueBean;
    @Override
    public Marque getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && value.trim().length() > 0) {
            for (Marque marque : marqueBean.getAllMarque()) {
                if (marque.getId() == Integer.parseInt(value)){
                    return marque;}
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Marque marque) {
        return String.valueOf(marque.getId());
    }
}
