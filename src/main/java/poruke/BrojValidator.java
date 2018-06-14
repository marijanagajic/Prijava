/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poruke;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author SVETLANA.LOKAL
 */
@FacesValidator("brojValidator")
public class BrojValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        String broj = value.toString();
        try {
            int a = Integer.parseInt(broj);
        } catch (NumberFormatException e) {
            FacesMessage msg
                = new FacesMessage("brojjjjj");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(msg);
        }

        

    }
}
