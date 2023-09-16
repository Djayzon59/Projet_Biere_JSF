package converter;


import bean.PaysBean;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;


import model.Pays;


@FacesConverter(value = "paysConverter", managed=true)
public class PaysConverter implements Converter<Pays> {

    @Inject
    private PaysBean paysBean;
    @Override
    public Pays getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && value.trim().length() > 0) {
            for (Pays pays :paysBean.getAllPays()) {
                if (pays.getId() == Integer.parseInt(value)){
                    return pays;
                }
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Pays pays) {
        return String.valueOf(pays.getId());
    }
}
