/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prijava.controller;

import hibernate.HibernateUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import prijava.beans.Clan;
import prijava.beans.Clanprojekat;
import prijava.beans.Grad;
import prijava.beans.Prijava;

/**
 *
 * @author SVETLANA.LOKAL
 */
@ManagedBean(name = "adminController")
@SessionScoped
public class AdminController implements Serializable {

    List<Prijava> listaPrijava;
    private String nazivTima;
    private String napomena;
    private String grad;
    List<Clan> clanoviTima;

    public static Prijava prijavaTim;

    public static Clan clanTima;
    private String ime;
    private String prezime;
    private String email;
    private int godIskustva;

    List<Clanprojekat> projektiClana;

    //fileDownload
    private StreamedContent fileDownload;
    private String messageDownlaodCV;

    private Grad gradCombo;
    List<Grad> listaGradova;

    public String getMessageDownlaodCV() {
        return messageDownlaodCV;
    }

    public void setMessageDownlaodCV(String messageDownlaodCV) {
        this.messageDownlaodCV = messageDownlaodCV;
    }

    public Grad getGradCombo() {
        return gradCombo;
    }

    public void setGradCombo(Grad gradCombo) {
        this.gradCombo = gradCombo;
    }

    public List<Grad> getListaGradova() {
        return listaGradova;
    }

    public void setListaGradova(List<Grad> listaGradova) {
        this.listaGradova = listaGradova;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public StreamedContent getFileDownload() {
        return fileDownload;
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

    public int getGodIskustva() {
        return godIskustva;
    }

    public void setGodIskustva(int godIskustva) {
        this.godIskustva = godIskustva;
    }

    public List<Clanprojekat> getProjektiClana() {
        return projektiClana;
    }

    public void setProjektiClana(List<Clanprojekat> projektiClana) {
        this.projektiClana = projektiClana;
    }

    public static Clan getClanTima() {
        return clanTima;
    }

    public static void setClanTima(Clan clanTima) {
        AdminController.clanTima = clanTima;
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

    public List<Clan> getClanoviTima() {
        return clanoviTima;
    }

    public void setClanoviTima(List<Clan> clanoviTima) {
        this.clanoviTima = clanoviTima;
    }

    public List<Prijava> getListaPrijava() {
        return listaPrijava;
    }

    public void setListaPrijava(List<Prijava> listaPrijava) {
        this.listaPrijava = listaPrijava;
    }

    @PostConstruct
    private void inicijalizuj() {
        vratiSveGradove();
        vratiSvePrijave();

    }

    public void pretraziTabeluPoGradu() {
        vratiSvePrijave();
        List<Prijava> lista = new LinkedList<>();
        for (Prijava prijava : listaPrijava) {
            if (prijava.getGrad().getNaziv().equals(gradCombo.getNaziv())) {
                lista.add(prijava);
            }
        }
        listaPrijava = lista;
    }

    private void vratiSveGradove() {
        FacesContext context = FacesContext.getCurrentInstance();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query q = session.createQuery("from Grad");
            listaGradova = q.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private void vratiSvePrijave() {
        FacesContext context = FacesContext.getCurrentInstance();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query q = session.createQuery("from Prijava p join fetch p.grad g where p.uloga='user'");
            listaPrijava = q.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void pregledTimaAdmin(Prijava prijava) {
        prijavaTim = prijava;
        nazivTima = prijava.getNazivtima();
        grad = prijava.getGrad().getNaziv();
        napomena = prijava.getNapomena();
        vratiClanoveIzabranogTima();
        ProjektiAdminController.vratiSveProjekteTima();

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        try {
            ec.redirect("TimAdmin.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ProjekatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void vratiClanoveIzabranogTima() {
        FacesContext context = FacesContext.getCurrentInstance();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query q = session.createQuery("from Clan where idprijava=:idprijava");
            q.setParameter("idprijava", prijavaTim.getIdprijava());
            clanoviTima = q.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void pregledClanaAdmin(Clan clan) {

        clanTima = clan;
        ime = clanTima.getIme();
        prezime = clanTima.getPrezime();
        email = clanTima.getEmail();
        godIskustva = clanTima.getGodiskustva();
        vratiProjekteClana();

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        try {
            ec.redirect("ClanAdmin.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ProjekatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void vratiProjekteClana() {
        FacesContext context = FacesContext.getCurrentInstance();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query q = session.createQuery("from Clanprojekat cp join fetch cp.projekat p join fetch cp.clan c where c.idclan=:idclan");
            q.setParameter("idclan", clanTima.getIdclan());
            projektiClana = q.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void fileDownload(Clan clan) {
        if (clan.getDokumentcv() != null) {
            try {
                messageDownlaodCV = null;
                byte[] d = clan.getDokumentcv();
                InputStream stream = new ByteArrayInputStream(d);

                fileDownload = new DefaultStreamedContent(stream, "image", clan.getIme() + clan.getPrezime() + "_cv." + clan.getEkstenzija());
                FacesMessage message = new FacesMessage("Uspesno", "procitan  fajl iz baze");
                FacesContext.getCurrentInstance().addMessage(null, message);
                
            } catch (Exception e) {
                FacesMessage message = new FacesMessage("Greska", "Грешка - не постоји cv");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            //null da se ne bi skidalo prethodno ucitano
            fileDownload = null;
            FacesMessage message = new FacesMessage("Uspe", "procitan fajl iz baze1");
            FacesContext.getCurrentInstance().addMessage(null, message);
            messageDownlaodCV = "Не постоји CV";
        }
    }
}
