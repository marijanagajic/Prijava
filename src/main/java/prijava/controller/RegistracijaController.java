/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prijava.controller;

import hibernate.HibernateUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
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
@ManagedBean(name = "registracijaController")
@SessionScoped
public class RegistracijaController implements Serializable {

    private String nazivtima;
    private String napomena;
    private String username;
    private String password;

    private String ime;
    private String prezime;
    private String email;
    private boolean vodja;
    private boolean uslovi;
    private boolean postojiVodja;

    private String message = "";
    private String messageClan = "";
    public static Prijava prijava;
    List<Clan> clanovi = new ArrayList<>();

    private Grad mesto;
    List<Grad> gradovi;

    public Grad getMesto() {
        return mesto;
    }

    public void setMesto(Grad mesto) {
        this.mesto = mesto;
    }

    public List<Grad> getGradovi() {
        return gradovi;
    }

    public void setGradovi(List<Grad> gradovi) {
        this.gradovi = gradovi;
    }

    public String getMessageClan() {
        return messageClan;
    }

    public void setMessageClan(String messageClan) {
        this.messageClan = messageClan;
    }

    public boolean isVodja() {
        return vodja;
    }

    public void setVodja(boolean vodja) {
        this.vodja = vodja;
    }

    public boolean isPostojiVodja() {
        return postojiVodja;
    }

    public void setPostojiVodja(boolean postojiVodja) {
        this.postojiVodja = postojiVodja;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        RegistracijaController.prijava = prijava;
    }

    public List<Clan> getClanovi() {
        return clanovi;
    }

    public void setClanovi(List<Clan> clanovi) {
        this.clanovi = clanovi;
    }

    public String getNazivtima() {
        return nazivtima;
    }

    public void setNazivtima(String nazivtima) {
        this.nazivtima = nazivtima;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
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

    public boolean isUslovi() {
        return uslovi;
    }

    public void setUslovi(boolean uslovi) {
        this.uslovi = uslovi;
    }

    public String registruj() {
        return "Registracija.xhtml";
    }

    @PostConstruct
    public void inicijalizuj() {
        vratiSveGradove();
    }

    private void vratiSveGradove() {
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

    public void dodajClana() {

        validacija();
        if (messageClan.isEmpty()) {
            Clan clan = new Clan();
            clan.setIme(ime);
            clan.setPrezime(prezime);
            clan.setEmail(email);
            byte vodjaGrupe = 0;

            if (vodja) {
                vodjaGrupe = 1;
            }

            clan.setVodjagrupe(vodjaGrupe);
            clanovi.add(clan);

            if (vodjaGrupe == 1) {
                postojiVodja = true;
            }
        }

    }

    private void validacija() {
        messageClan = "";
        if (clanovi.size() == 4) {
            messageClan += "Максимални број чланова је 4! ";
        }
        if (postojiVodja && vodja) {
            messageClan += "Тим може имати само једног лидера групе! ";
        }

    }

    public void izbrisi(Clan clan) {
        clanovi.remove(clan);
        if (clan.getVodjagrupe() == 1) {
            postojiVodja = false;
        }
    }

    public void sacuvajPrijavu() {
        validacijaRegistracija();
        if (!message.isEmpty()) {

            //return;
        } else {
            prijava = new Prijava(nazivtima, username, password, "user");
            byte us = 1;
            prijava.setUslovi(us);
            if (mesto instanceof Grad) {
                prijava.setGrad(mesto);
            }
            prijava.setNapomena(napomena);

            for (Clan clan1 : clanovi) {
                clan1.setPrijava(prijava);
            }

            Set<Clan> clanoviSet = new HashSet<>(clanovi);
            prijava.setClans(clanoviSet);

            FacesContext context = FacesContext.getCurrentInstance();

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(prijava);
                for (Clan clanPrijave : clanovi) {
                    session.save(clanPrijave);
                }
                
                LoginController.postaviPoruku();
                FacesContext fc = FacesContext.getCurrentInstance();
                ExternalContext ec = fc.getExternalContext();
                try {
                    ec.redirect("index.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                //message = "Успешно сте се регистровали!";
                tx.commit();
            } catch (Exception e) {
                message = "Регистрација није успела! Молимо покушајте поново.";
                if (tx != null) {
                    tx.commit();
                }
            } finally {

                session.close();
            }
        }
    }

    private void validacijaRegistracija() {
        message = "";
        messageClan = "";
        if (clanovi.size() < 3) {
            message += "Тим мора имати најмање 3 члана! ";
        }

        if (!postojiVodja) {
            message += "Тим мора имати једног лидера! ";
        }

        if (!uslovi) {
            message += "Да бисте се регистровали, морате прихватити правила учешћа на Хакатону.";
        }

//        FacesContext context = FacesContext.getCurrentInstance();
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        try {
//            Query q = session.createQuery("from Prijava where username=:username");
//            q.setParameter("username", username);
//            
//            if (q.list().size()==1) {
//                message+="Корисничко име већ постоји! ";
//            }
//            session.getTransaction().commit();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
    }
}
