/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poruke;

import hibernate.HibernateUtil;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author SVETLANA.LOKAL
 */
@FacesValidator("usernameValidator")
public class UsernameValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        String username = value.toString();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Prijava where username=:username");
        q.setParameter("username", username);

        if (q.list().size() == 1) {
            FacesMessage msg
                    = new FacesMessage("Корисничко име већ постоји! ");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        session.getTransaction().commit();

        session.close();
    }

}
