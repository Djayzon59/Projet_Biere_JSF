package converter;

import bean.FabricantBean;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import model.Fabricant;



@FacesConverter(value = "fabricantConverter", managed=true)
public class FabricantConverter implements Converter<Fabricant> {

    @Inject
    private FabricantBean fabricantBean;
    @Override
    public Fabricant getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && value.trim().length() > 0) {
            for (Fabricant fabricant : fabricantBean.getAllFabricants()) {
                if (fabricant.getId() == Integer.parseInt(value)){
                    return fabricant;}
            }
        }
        return null;
    }
    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Fabricant fabricant) {
        return String.valueOf(fabricant.getId());
    }
}
