/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prijava.controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import prijava.beans.Dokument;

/**
 *
 * @author SVETLANA.LOKAL
 */
@ManagedBean(name = "dokumentController")
@SessionScoped
public class DokumentController implements Serializable{
    private String nazivDokumenta;
    private String opis;
    private byte dokument;
    List<Dokument> listaDokumenata;

    public List<Dokument> getListaDokumenata() {
        return listaDokumenata;
    }

    public void setListaDokumenata(List<Dokument> listaDokumenata) {
        this.listaDokumenata = listaDokumenata;
    }

    public String getNazivDokumenta() {
        return nazivDokumenta;
    }

    public void setNazivDokumenta(String nazivDokumenta) {
        this.nazivDokumenta = nazivDokumenta;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public byte getDokument() {
        return dokument;
    }

    public void setDokument(byte dokument) {
        this.dokument = dokument;
    }

    public void dodajDokument(){
        
    }
    
    public void skiniDokument(){
        
    }

}
