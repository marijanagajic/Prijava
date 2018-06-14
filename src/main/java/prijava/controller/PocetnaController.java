/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prijava.controller;

import hibernate.HibernateUtil;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import prijava.beans.Clan;
import prijava.beans.Grad;
import prijava.beans.Prijava;

/**
 *
 * @author Administrator
 */
@ManagedBean(name = "pocetnaController")
@SessionScoped

public class PocetnaController implements Serializable {

    private String nazivTima;
    private String napomena;
    private Grad mesto;
    List<Grad> gradovi;
    List<Clan> clanoviTima = new LinkedList<>();
    public static Clan clan;

    private String message;

    public List<Grad> getGradovi() {
        return gradovi;
    }

    public void setGradovi(List<Grad> gradovi) {
        this.gradovi = gradovi;
    }

    public Grad getMesto() {
        return mesto;
    }

    public void setMesto(Grad mesto) {
        this.mesto = mesto;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Clan getClan() {
        return clan;
    }

    public static void setClan(Clan clan) {
        PocetnaController.clan = clan;
    }

    public List<Clan> getClanoviTima() {
        return clanoviTima;
    }

    public void setClanoviTima(List<Clan> clanoviTima) {
        this.clanoviTima = clanoviTima;
    }

    public String getNazivTima() {
        return nazivTima;
    }

    public void setNazivTima(String nazivTima) {
        this.nazivTima = nazivTima;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    @PostConstruct
    public void inicijalizuj() {

            nazivTima = LoginController.prijava.getNazivtima();
            napomena = LoginController.prijava.getNapomena();
            vratiSveClanove();
            vratiSveGradove();
            mesto = gradovi.get(LoginController.prijava.getGrad().getIdgrad()-1);
    }

    public void izmeni() {

        FacesContext context = FacesContext.getCurrentInstance();

        Prijava prijava1 = LoginController.prijava;

        prijava1.setNazivtima(nazivTima);
        prijava1.setNapomena(napomena);
        prijava1.setGrad(mesto);
        Session session1 = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session1.beginTransaction();
            session1.update(prijava1);
            LoginController.setPrijava(prijava1);
            message = "Успешно сачувано!";
            tx.commit();
        } catch (Exception e) {
            message = "Неуспешно чување. Покушајте поново!";
            if (tx != null) {
                tx.commit();
            }
        } finally {
            session1.close();
        }
    }

    private void vratiSveClanove() {
        List<Clan> clanovi = new LinkedList<Clan>();
        FacesContext context = FacesContext.getCurrentInstance();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query q = session.createQuery("from Clan where idprijava=:idprijava ");
            q.setParameter("idprijava", LoginController.prijava.getIdprijava());
            clanovi = q.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        clanoviTima = clanovi;

    }
    
    private void vratiSveGradove(){
        FacesContext context = FacesContext.getCurrentInstance();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query q = session.createQuery("from Grad");
            gradovi = q.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

//    public String postaviClana(Clan clanTima){
//     clan = clanTima;
//     return "PodaciClanTima.xhtml";
//    }
}
