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
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.model.UploadedFile;
import prijava.beans.Clan;
import prijava.beans.Clanprojekat;
import prijava.beans.Projekat;

/**
 *
 * @author Administrator
 */
@ManagedBean(name = "clanController")
@RequestScoped
public class ClanController implements Serializable {

    private int idclan;
    private String ime;
    private String prezime;
    List<Clan> clanovi;
    private int godIskustva;
    private String email;
    public static Clan clan;
    private byte vodjaGrupe;
    private String labelVodja;
    private String labelCV;
    List<Clanprojekat> projektiClana;

    private String message;
    private String messageCV = "";
    public static Clanprojekat cp;
    private UploadedFile uploadedFile;

    public String getLabelCV() {
        return labelCV;
    }

    public void setLabelCV(String labelCV) {
        this.labelCV = labelCV;
    }

    public String getMessageCV() {
        return messageCV;
    }

    public void setMessageCV(String messageCV) {
        this.messageCV = messageCV;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Clanprojekat getCp() {
        return cp;
    }

    public static void setCp(Clanprojekat cp) {
        ClanController.cp = cp;
    }

    public List<Clanprojekat> getProjektiClana() {
        return projektiClana;
    }

    public void setProjektiClana(List<Clanprojekat> projektiClana) {
        this.projektiClana = projektiClana;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public byte getVodjaGrupe() {
        return vodjaGrupe;
    }

    public void setVodjaGrupe(byte vodjaGrupe) {
        this.vodjaGrupe = vodjaGrupe;
    }

    public int getIdclan() {
        return idclan;
    }

    public void setIdclan(int idclan) {
        this.idclan = idclan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public static Clan getClan() {
        return clan;
    }

    public static void setClan(Clan clan) {
        ClanController.clan = clan;
    }

    public List<Clan> getClanovi() {
        return clanovi;
    }

    public void setClanovi(List<Clan> clanovi) {
        this.clanovi = clanovi;
    }

    public int getGodIskustva() {
        return godIskustva;
    }

    public void setGodIskustva(int godIskustva) {
        this.godIskustva = godIskustva;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getLabelVodja() {
        return labelVodja;
    }

    public void setLabelVodja(String labelVodja) {
        this.labelVodja = labelVodja;
    }

    @PostConstruct
    public void inicijalizuj() {

    }

    private void vratiSveProjekteClana() {
        List<Clanprojekat> projekti = new ArrayList<Clanprojekat>();
        FacesContext context = FacesContext.getCurrentInstance();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query q = session.createQuery("from Clanprojekat cp join fetch cp.projekat p join fetch cp.clan c where c.idclan=:idclan");
            q.setParameter("idclan", clan.getIdclan());
            projekti = q.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        projektiClana = projekti;

    }

    public void podaciOClanu(Clan izabraniClan) {
        clan = izabraniClan;
        ime = clan.getIme();
        prezime = clan.getPrezime();
        godIskustva = clan.getGodiskustva();
        email = clan.getEmail();
        vratiSveProjekteClana();
        vodjaGrupe = clan.getVodjagrupe();
        if (vodjaGrupe == 1) {
            labelVodja = "Ви сте лидер тима!";
        } else {
            labelVodja = null;
        }
        if (clan.getDokumentcv() != null) {
            labelCV = "*Већ сте унели CV";
        } else {
            labelCV = null;
        }

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        try {
            ec.redirect("PodaciClanTima.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PocetnaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // return "PodaciClanTima.xhtml";
    }

    public void sacuvaj() {
        FacesContext context = FacesContext.getCurrentInstance();

        clan.setIme(ime);
        clan.setPrezime(prezime);
        clan.setEmail(email);
        clan.setGodiskustva(godIskustva);

//        if (uploadedFile != null) {
//            String fileName = uploadedFile.getFileName();
//            
//            String[] niz = fileName.split(Pattern.quote("."));
//            String ekstenzija = niz[niz.length-1];
//            byte[] contents = uploadedFile.getContents();
//            clan.setDokumentcv(contents);
//            clan.setEkstenzija(ekstenzija);
//            validacijaClanCV(fileName);
//        }
        //  if (messageCV.isEmpty()) {
        Session session1 = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session1.beginTransaction();
            session1.update(clan);

            message = "Успешно сачувано!";
            tx.commit();
        } catch (Exception e) {
            message = "Неуспешно сачувано. Покушајте поново!";
            if (tx != null) {
                tx.commit();
            }
        } finally {
            session1.close();
        }
        // }

    }

    public void sacuvajCV() {
        if (uploadedFile != null) {
            String fileName = uploadedFile.getFileName();

            String[] niz = fileName.split(Pattern.quote("."));
            String ekstenzija = niz[niz.length - 1];
            byte[] contents = uploadedFile.getContents();
            clan.setDokumentcv(contents);
            clan.setEkstenzija(ekstenzija);
            validacijaClanCV(fileName);

            Session session1 = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;
            try {
                tx = session1.beginTransaction();
                session1.update(clan);

                messageCV = "Успешно сачуван CV!";
                tx.commit();
            } catch (Exception e) {
                messageCV = "Неуспешно сачуван CV. Покушајте поново!";
                if (tx != null) {
                    tx.commit();
                }
            } finally {
                session1.close();
            }
        } else {
            messageCV = "Морате прво додати CV!";
        }

        // if (messageCV.isEmpty()) {
    }

    private void validacijaClanCV(String fileName) {
        messageCV = "";
//        if (fileName.endsWith(".doc") || fileName.endsWith(".docx") || fileName.endsWith(".pdf")) {
//
//        } else {
//            messageCV += "Формат документа није дозвољен. Можете додати документ типа .pdf, .doc, .docx ! ";
//        }

        //2MB
//        if (uploadedFile.getSize() > 2097152) {
//            messageCV += "Документ не може бити већи од 2MB! ";
//        }
    }
}
