package converter;

import bean.TypeBean;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

import model.TypeBiere;

@FacesConverter(value="typeConverter", managed = true)
public class TypeConverter implements Converter<TypeBiere> {
    @Inject
    private TypeBean typeBean;
    @Override
    public TypeBiere getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && value.trim().length() > 0) {
            for (TypeBiere typeBiere : typeBean.getAllTypes()) {
                if (typeBiere.getId() == Integer.parseInt(value)){
                    return typeBiere;}
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, TypeBiere typeBiere) {
        return String.valueOf(typeBiere.getId());
    }
}
