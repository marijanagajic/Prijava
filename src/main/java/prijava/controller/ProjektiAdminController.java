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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import prijava.beans.Clanprojekat;
import prijava.beans.Dokument;
import prijava.beans.Projekat;

/**
 *
 * @author SVETLANA.LOKAL
 */
@ManagedBean(name = "projektiAdminController")
@RequestScoped
public class ProjektiAdminController implements Serializable{
    
    static List<Projekat> projektiTima;
    
    //projekat
    private String nazivProjekta;
    private String tehnologije;
    private String opis;
    List<Clanprojekat> clanoviUcesniciNaProjektu;
    List<Dokument> dokumentaProjekta;
    static List<String> ponudjeneTehnologije;
    
    private StreamedContent fileDownload;

    public StreamedContent getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(StreamedContent fileDownload) {
        this.fileDownload = fileDownload;
    }

    public List<Clanprojekat> getClanoviUcesniciNaProjektu() {
        return clanoviUcesniciNaProjektu;
    }

    public void setClanoviUcesniciNaProjektu(List<Clanprojekat> clanoviUcesniciNaProjektu) {
        this.clanoviUcesniciNaProjektu = clanoviUcesniciNaProjektu;
    }

    public List<Dokument> getDokumentaProjekta() {
        return dokumentaProjekta;
    }

    public void setDokumentaProjekta(List<Dokument> dokumentaProjekta) {
        this.dokumentaProjekta = dokumentaProjekta;
    }

    public String getNazivProjekta() {
        return nazivProjekta;
    }

    public void setNazivProjekta(String nazivProjekta) {
        this.nazivProjekta = nazivProjekta;
    }

    public String getTehnologije() {
        return tehnologije;
    }

    public void setTehnologije(String tehnologije) {
        this.tehnologije = tehnologije;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    
    public List<Projekat> getProjektiTima() {
        return projektiTima;
    }

    public void setProjektiTima(List<Projekat> projektiTima) {
        this.projektiTima = projektiTima;
    }

    public static List<String> getPonudjeneTehnologije() {
        return ponudjeneTehnologije;
    }
    
    
    public void podacioProjektuAdmin(Projekat p){
        nazivProjekta = p.getNaziv();
        tehnologije = p.getTehnologije();
        opis = p.getOpis();
       // vratiSveClanoveTima();
        vratiSveClanoveUcesnikeNaProjektu(p);
        vratiSveDokumenteProjekta(p);
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        try {
            ec.redirect("ProjekatAdmin.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ProjekatController.class.getName()).log(Level.SEVERE, null, ex);
        }

//return "ProjekatAdmin.xhtml";
    }
    
//    public void projektiTima(){
//        vratiSveProjekteTima();
//        
//        FacesContext fc = FacesContext.getCurrentInstance();
//        ExternalContext ec = fc.getExternalContext();
//            try {
//                ec.redirect("ProjektiAdmin.xhtml");
//            } catch (IOException ex) {
//                Logger.getLogger(ProjekatController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//       // return "ProjektiAdmin.xhtml";
//    }

    
    
    public static void vratiSveProjekteTima() {
        FacesContext context = FacesContext.getCurrentInstance();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query q = session.createQuery("from Projekat where idprijava=:idprijava");
           q.setParameter("idprijava", AdminController.prijavaTim.getIdprijava());
            projektiTima = q.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        //tabelaTehnologije();
    }

    private static void tabelaTehnologije(){
        ponudjeneTehnologije = new ArrayList<>();
        ponudjeneTehnologije = new ArrayList<>();
        ponudjeneTehnologije.add("Веб дизајн");
        ponudjeneTehnologije.add("Мобилне технологије");
        ponudjeneTehnologije.add("Управљање сервисима");
        ponudjeneTehnologije.add("Друго");
        
    }
    
    private void vratiSveClanoveUcesnikeNaProjektu(Projekat p) {
        FacesContext context = FacesContext.getCurrentInstance();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query q = session.createQuery("from Clanprojekat cp join fetch cp.projekat p join fetch cp.clan c where p.idprojekat=:idprojekat ");
           q.setParameter("idprojekat", p.getIdprojekat());
            clanoviUcesniciNaProjektu = q.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private void vratiSveDokumenteProjekta(Projekat p) {
        FacesContext context = FacesContext.getCurrentInstance();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query q = session.createQuery("from Dokument where idprojekat=:idprojekat ");
           q.setParameter("idprojekat", p.getIdprojekat());
            dokumentaProjekta = q.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void fileDownload(Dokument dok) {
        
        try{
        byte[] d = dok.getDokument();
        InputStream stream = new ByteArrayInputStream(d);
        
        fileDownload = new DefaultStreamedContent(stream, "image", dok.getNaziv()+"."+dok.getEkstenzija());
        FacesMessage message = new FacesMessage("Uspesno", "procitan  fajla iz baze");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }catch(Exception e){
            FacesMessage message = new FacesMessage("Greska", "greska sa iscitavanjem fajla iz baze");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
