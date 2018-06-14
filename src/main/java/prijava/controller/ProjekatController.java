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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import prijava.beans.ClanprojekatId;
import prijava.beans.Dokument;
import prijava.beans.Projekat;

/**
 *
 * @author SVETLANA.LOKAL
 */
@ManagedBean(name = "projekatController")
@RequestScoped
public class ProjekatController implements Serializable {

    private String nazivProjekta;
    private String tehnologije;
    private String opis;

    private String nazivProjektaUnos;
    private String opisUnos;

    private String ulogaUProjektu;
    public static Projekat projekat;
    List<Clanprojekat> clanoviUcesniciNaProjektu;
    List<Clanprojekat> clanoviUcesniciNaProjektuNovi = new ArrayList<>();
    List<Clan> clanoviTima;
    private Clan clanIme;
    private String messageSacuvaj;
    private String alertTypeSacuvaj;
    private String messageDodajProjekat;
    List<Projekat> projektiTima;
    List<String> ponudjeneTehnologije;
    private String[] izabraneTehnologije;

    //dokumenta projekta
    List<Dokument> dokumentaProjekta;
    List<Dokument> dokumentaProjektaNova = new ArrayList<>();
    private String labelDokFile = null;
    private String labelDokFile2 = null;
    private String nazivDokumenta;
    private String opisDokumenta;
    private byte dokument;
    private UploadedFile uploadedFileDok;

    public String getLabelDokFile2() {
        return labelDokFile2;
    }

    public void setLabelDokFile2(String labelDokFile2) {
        this.labelDokFile2 = labelDokFile2;
    }

    public String getLabelDokFile() {
        return labelDokFile;
    }

    public void setLabelDokFile(String labelDokFile) {
        this.labelDokFile = labelDokFile;
    }

    public String getAlertTypeSacuvaj() {
        return alertTypeSacuvaj;
    }

    public void setAlertTypeSacuvaj(String alertTypeSacuvaj) {
        this.alertTypeSacuvaj = alertTypeSacuvaj;
    }

    public String getNazivProjektaUnos() {
        return nazivProjektaUnos;
    }

    public void setNazivProjektaUnos(String nazivProjektaUnos) {
        this.nazivProjektaUnos = nazivProjektaUnos;
    }

    public String getOpisUnos() {
        return opisUnos;
    }

    public void setOpisUnos(String opisUnos) {
        this.opisUnos = opisUnos;
    }

    public String getMessageDodajProjekat() {
        return messageDodajProjekat;
    }

    public void setMessageDodajProjekat(String messageDodajProjekat) {
        this.messageDodajProjekat = messageDodajProjekat;
    }

    public String getMessageSacuvaj() {
        return messageSacuvaj;
    }

    public void setMessageSacuvaj(String messageSacuvaj) {
        this.messageSacuvaj = messageSacuvaj;
    }

    public List<Dokument> getDokumentaProjektaNova() {
        return dokumentaProjektaNova;
    }

    public void setDokumentaProjektaNova(List<Dokument> dokumentaProjektaNova) {
        this.dokumentaProjektaNova = dokumentaProjektaNova;
    }

    public UploadedFile getUploadedFileDok() {
        return uploadedFileDok;
    }

    public void setUploadedFileDok(UploadedFile uploadedFileDok) {
        this.uploadedFileDok = uploadedFileDok;
    }

    public String getNazivDokumenta() {
        return nazivDokumenta;
    }

    public void setNazivDokumenta(String nazivDokumenta) {
        this.nazivDokumenta = nazivDokumenta;
    }

    public String getOpisDokumenta() {
        return opisDokumenta;
    }

    public void setOpisDokumenta(String opisDokumenta) {
        this.opisDokumenta = opisDokumenta;
    }

    public byte getDokument() {
        return dokument;
    }

    public void setDokument(byte dokument) {
        this.dokument = dokument;
    }

    public List<Dokument> getDokumentaProjekta() {
        return dokumentaProjekta;
    }

    public void setDokumentaProjekta(List<Dokument> dokumentaProjekta) {
        this.dokumentaProjekta = dokumentaProjekta;
    }

    public String getTehnologije() {
        return tehnologije;
    }

    public void setTehnologije(String tehnologije) {
        this.tehnologije = tehnologije;
    }

    public String[] getIzabraneTehnologije() {
        return izabraneTehnologije;
    }

    public void setIzabraneTehnologije(String[] izabraneTehnologije) {
        this.izabraneTehnologije = izabraneTehnologije;
    }

    public List<String> getPonudjeneTehnologije() {
        return ponudjeneTehnologije;
    }

    public void setPonudjeneTehnologije(List<String> ponudjeneTehnologije) {
        this.ponudjeneTehnologije = ponudjeneTehnologije;
    }

    public List<Projekat> getProjektiTima() {
        return projektiTima;
    }

    public void setProjektiTima(List<Projekat> projektiTima) {
        this.projektiTima = projektiTima;
    }

    public List<Clanprojekat> getClanoviUcesniciNaProjektuNovi() {
        return clanoviUcesniciNaProjektuNovi;
    }

    public void setClanoviUcesniciNaProjektuNovi(List<Clanprojekat> clanoviUcesniciNaProjektuNovi) {
        this.clanoviUcesniciNaProjektuNovi = clanoviUcesniciNaProjektuNovi;
    }

    public Clan getClanIme() {
        return clanIme;
    }

    public void setClanIme(Clan clanIme) {
        this.clanIme = clanIme;
    }

    public List<Clan> getClanoviTima() {
        return clanoviTima;
    }

    public void setClanoviTima(List<Clan> clanoviTima) {
        this.clanoviTima = clanoviTima;
    }

    public List<Clanprojekat> getClanoviUcesniciNaProjektu() {
        return clanoviUcesniciNaProjektu;
    }

    public void setClanoviUcesniciNaProjektu(List<Clanprojekat> clanoviUcesniciNaProjektu) {
        this.clanoviUcesniciNaProjektu = clanoviUcesniciNaProjektu;
    }

    public static Projekat getProjekat() {
        return projekat;
    }

    public static void setProjekat(Projekat projekat) {
        ProjekatController.projekat = projekat;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getUlogaUProjektu() {
        return ulogaUProjektu;
    }

    public void setUlogaUProjektu(String ulogaUProjektu) {
        this.ulogaUProjektu = ulogaUProjektu;
    }

    public String getNazivProjekta() {
        return nazivProjekta;
    }

    public void setNazivProjekta(String nazivProjekta) {
        this.nazivProjekta = nazivProjekta;
    }

    @PostConstruct
    public void inicijalizuj() {

        // if (LoginController.prijava != null) {
        vratiSveProjekteTima();
        ponudjeneTehnologije = new ArrayList<>();
        ponudjeneTehnologije.add("Веб дизајн");
        ponudjeneTehnologije.add("Мобилне технологије");
        ponudjeneTehnologije.add("Управљање сервисима");
        ponudjeneTehnologije.add("Друго");
//        } else {
//            FacesContext fc = FacesContext.getCurrentInstance();
//            ExternalContext ec = fc.getExternalContext();
//            try {
//                ec.redirect("index.xhtml");
//            } catch (IOException ex) {
//                Logger.getLogger(ProjekatController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

    public void projekatDetalji(Clanprojekat cp) {
        projekat = cp.getProjekat();
        nazivProjekta = projekat.getNaziv();
        tehnologije = projekat.getTehnologije();
        opis = projekat.getOpis();
        vratiSveClanoveUcesnikeNaProjektu();
        vratiSveDokumenteProjekta();

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        try {
            ec.redirect("Projekat.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ProjekatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void vratiSveProjekteTima() {
        List<Projekat> projekti = new LinkedList<>();
        FacesContext context = FacesContext.getCurrentInstance();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query q = session.createQuery("from Projekat where idprijava=:idprijava ");
            q.setParameter("idprijava", LoginController.prijava.getIdprijava());
            projekti = q.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        projektiTima = projekti;
    }

    private void vratiSveClanoveTima() {
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

    public void podacioProjektu(Projekat p) {
        projekat = p;
        nazivProjekta = p.getNaziv();
        tehnologije = p.getTehnologije();
        opis = p.getOpis();
        vratiSveClanoveTima();
//        clanoviUcesniciNaProjektu = (List<Clanprojekat>) p.getClanprojekats();
//        dokumentaProjekta = (List<Dokument>) p.getDokuments();
        vratiSveClanoveUcesnikeNaProjektu();
        vratiSveDokumenteProjekta();
        //return "Projekat.xhtml";

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        try {
            ec.redirect("Projekat.xhtml");
        } catch (IOException ex) {
            System.out.println("Projecat redirect faillllll");
            Logger.getLogger(ProjekatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void vratiSveClanoveUcesnikeNaProjektu() {
        List<Clanprojekat> clanoviUloge = new LinkedList<Clanprojekat>();
        FacesContext context = FacesContext.getCurrentInstance();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query q = session.createQuery("from Clanprojekat cp join fetch cp.projekat p join fetch cp.clan c where p.idprojekat=:idprojekat ");
            q.setParameter("idprojekat", projekat.getIdprojekat());
            clanoviUloge = q.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        clanoviUcesniciNaProjektu = clanoviUloge;
    }

    private void vratiSveDokumenteProjekta() {
        FacesContext context = FacesContext.getCurrentInstance();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query q = session.createQuery("from Dokument where idprojekat=:idprojekat ");
            q.setParameter("idprojekat", projekat.getIdprojekat());
            dokumentaProjekta = q.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

//    public void dodajProjekat() {
//        Projekat noviP = new Projekat(LoginController.prijava, nazivProjekta, tehnologijа, opis);
//        projektiTima.add(noviP);
//        projektiTimaNovi.add(noviP);
//    }
    public void dodajClanaUcesnikaProjekta() {

        ClanprojekatId cpID = new ClanprojekatId(clanIme.getIdclan(), projekat.getIdprojekat());
        Clanprojekat clanP = new Clanprojekat(cpID, clanIme, projekat, ulogaUProjektu);

        clanoviUcesniciNaProjektu.add(clanP);
        //clanoviUcesniciNaProjektuNovi.add(clanP);

        FacesContext context = FacesContext.getCurrentInstance();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(clanP);

            messageSacuvaj = "Успешно сачувано!";
            alertTypeSacuvaj = "success";
            tx.commit();
        } catch (Exception e) {
            alertTypeSacuvaj = "danger";
            messageSacuvaj = "Чување није успело. Молимо покушајте поново.";
            if (tx != null) {
                tx.commit();
            }
        } finally {
            session.close();
        }
    }

    public void izbrisi(Clanprojekat clanP) {
//        if (clanoviUcesniciNaProjektuNovi.contains(clanP)) {
//            clanoviUcesniciNaProjektuNovi.remove(clanP);
//        } else {
        FacesContext context = FacesContext.getCurrentInstance();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(clanP);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.commit();
            }
        } finally {

            session.close();
        }
//        }

        clanoviUcesniciNaProjektu.remove(clanP);
    }

    public void sacuvajProjekat() {
        FacesContext context = FacesContext.getCurrentInstance();
        String tehnologijeProjekta = "";
        for (String it : izabraneTehnologije) {
            tehnologijeProjekta += it + ";";
        }
        Projekat noviP = new Projekat(LoginController.prijava, nazivProjektaUnos, tehnologijeProjekta, opisUnos);
        projektiTima.add(noviP);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(noviP);
            messageDodajProjekat = "Успешно додат пројекат!";
            tx.commit();
        } catch (Exception e) {
            messageDodajProjekat = "Неуспешно додавање пројекта! Молимо покушајте касније.";
            if (tx != null) {
                tx.commit();
            }
        } finally {
            session.close();
        }
        ocistiFormu();
    }

    private void ocistiFormu() {
        nazivProjektaUnos = "";
        opisUnos = "";
        izabraneTehnologije = new String[]{};
    }

    private byte[] contentFile;
    private String eks;

    public void dodajDokFile() {
        if (uploadedFileDok.getSize() <= 16777215) {

            byte[] contents = uploadedFileDok.getContents();
            String[] niz = uploadedFileDok.getFileName().split(Pattern.quote("."));
            String ekstenzija = niz[niz.length - 1];

            contentFile = contents;
            eks = ekstenzija;
            labelDokFile = "Успешно ✓ - " + uploadedFileDok.getFileName();
            labelDokFile2 = "Сада упишите назив и сачувајте документ";
        } else {
            labelDokFile = "Величина документа је преко 16МB";
        }

    }

    public void dodajDokument() {
        //uploadedFileDok.enctype="";
//        byte[] contents = uploadedFileDok.getContents();
//        String[] niz = uploadedFileDok.getFileName().split(Pattern.quote("."));
//        String ekstenzija = niz[niz.length - 1];

        if(!labelDokFile.equals("Величина документа је преко 16МB")){
        Dokument dok = new Dokument();
        dok.setProjekat(projekat);
        dok.setNaziv(nazivDokumenta);
        dok.setDokument(contentFile);
        dok.setEkstenzija(eks);
        if (opisDokumenta != null) {
            dok.setOpis(opisDokumenta);
        }
        dokumentaProjekta.add(dok);
        //dokumentaProjektaNova.add(dok);

        contentFile = null;
        eks = null;
        labelDokFile = null;
        labelDokFile2 = null;
        nazivDokumenta = "";
        opis = "";

        FacesContext context = FacesContext.getCurrentInstance();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(dok);
            messageSacuvaj = "Успешно сачувано!";
            alertTypeSacuvaj = "success";
            tx.commit();
        } catch (Exception e) {
            alertTypeSacuvaj = "danger";
            messageSacuvaj = "Чување није успело. Молимо покушајте поново.";
            if (tx != null) {
                tx.commit();
            }
        } finally {
            session.close();
        }
        }
    }

    public void izbrisiDok(Dokument dok) {
//        if (dokumentaProjektaNova.contains(dok)) {
//            dokumentaProjektaNova.remove(dok);
//        } else {
        FacesContext context = FacesContext.getCurrentInstance();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(dok);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.commit();
            }
        } finally {

            session.close();
        }
//        }

        dokumentaProjekta.remove(dok);
    }

//    public void sacuvajUlogeiDokumenta() {
//
//        FacesContext context = FacesContext.getCurrentInstance();
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            for (Clanprojekat clanPr : clanoviUcesniciNaProjektuNovi) {
//                session.save(clanPr);
//            }
//            for (Dokument dok : dokumentaProjektaNova) {
//                session.save(dok);
//            }
//            messageSacuvaj = "Успешно сачувано!";
//            alertTypeSacuvaj = "success";
//            tx.commit();
//        } catch (Exception e) {
//            alertTypeSacuvaj = "danger";
//            messageSacuvaj = "Чување није успело. Молимо покушајте поново.";
//            if (tx != null) {
//                tx.commit();
//            }
//        } finally {
//            session.close();
//        }
//
//    }
}
