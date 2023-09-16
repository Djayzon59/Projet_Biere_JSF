package converter;

import bean.ContinentBean;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import model.Continent;


@FacesConverter(value = "continentConverter", managed = true)
public class ContinentConverter implements Converter<Continent> {
    @Inject
    private ContinentBean continentBean;
    @Override
    public Continent getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && value.trim().length() > 0) {
            for (Continent continent : continentBean.getAllContinents()) {
                if (continent.getId() == Integer.parseInt(value)){
                    return continent;
                }
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Continent continent) {
        return String.valueOf(continent.getId());
    }
}
