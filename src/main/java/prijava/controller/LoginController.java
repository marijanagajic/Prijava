/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prijava.controller;

import hibernate.HibernateUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import prijava.beans.Prijava;

/**
 *
 * @author Administrator
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String username;
    private String password;
    private String uloga;

    private int alertType;
    private static String message;

    public static Prijava prijava;

    public boolean prikaziUser() {
        return uloga.equals("user");
    }
    
    public boolean prikaziAdmin() {
        if (uloga.equals("admin")) {
            return true;
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public int getAlertType() {
        return alertType;
    }

    public void setAlertType(int alertType) {
        this.alertType = alertType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Prijava getPrijava() {
        return prijava;
    }

    public static void setPrijava(Prijava prijava) {
        LoginController.prijava = prijava;
    }

    public void onLoadIndex(){
        message = null;
    }
    
    public void onLoad() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        if (prijava == null) {
            try {
                ec.redirect("index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String userLogin() {
        String adr = "";
        FacesContext context1 = FacesContext.getCurrentInstance();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query q = session.createQuery("from Prijava where username=:username")
                    .setMaxResults(1);
            q.setParameter("username", username);
            prijava = (Prijava) q.list().get(0);
            session.getTransaction().commit();
            if (prijava != null) {
                //message = "Све је у реду!";
                uloga = prijava.getUloga();
                if (uloga.equals("user")) {
                    adr = "Tim.xhtml";
                    message = null;
                    context1.getExternalContext().getSession(true);
                } else if (uloga.equals("admin")) {
                    adr = "PrijaveAdmin.xhtml";
                    message = null;
                }
                if (!password.equals(prijava.getPassword())) {
                    message = "Унели сте погрешну лозинку!";
                    adr = "index.xhtml";
                }
            }
//            else {
//                message = "Унели сте погрешно корисничко име!";
//                adr = "index.xhtml";
//            }
        } catch (Exception e) {
            message = "Погрешно корисничко име или лозинка!";
            adr = "index.xhtml";
        } finally {
            session.close();
        }

        return adr;
//        FacesContext fc = FacesContext.getCurrentInstance();
//        ExternalContext ec = fc.getExternalContext();
//        try {
//            ec.redirect(adr);
//        } catch (IOException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public String prijaviSe() {
        return "index";
    }

    public void logout() {
        prijava = null;
//        uloga = null;
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
//          return "index";
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        try {
            ec.redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void postaviPoruku() {
        message = "Успешно сте се регистровали, пријавите се!";
    }
    
    public static void postaviPorukuNull(){
        message=null;
    }
}
