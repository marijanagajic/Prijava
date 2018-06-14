package prijava.beans;
// Generated Feb 13, 2018 3:26:51 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Projekat generated by hbm2java
 */
public class Projekat  implements java.io.Serializable {


     private Integer idprojekat;
     private Prijava prijava;
     private String naziv;
     private String tehnologije;
     private String opis;
     private Set dokuments = new HashSet(0);
     private Set clanprojekats = new HashSet(0);

    public Projekat() {
    }

	
    public Projekat(Prijava prijava, String naziv, String tehnologije, String opis) {
        this.prijava = prijava;
        this.naziv = naziv;
        this.tehnologije = tehnologije;
        this.opis = opis;
    }
    public Projekat(Prijava prijava, String naziv, String tehnologije, String opis, Set dokuments, Set clanprojekats) {
       this.prijava = prijava;
       this.naziv = naziv;
       this.tehnologije = tehnologije;
       this.opis = opis;
       this.dokuments = dokuments;
       this.clanprojekats = clanprojekats;
    }
   
    public Integer getIdprojekat() {
        return this.idprojekat;
    }
    
    public void setIdprojekat(Integer idprojekat) {
        this.idprojekat = idprojekat;
    }
    public Prijava getPrijava() {
        return this.prijava;
    }
    
    public void setPrijava(Prijava prijava) {
        this.prijava = prijava;
    }
    public String getNaziv() {
        return this.naziv;
    }
    
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    public String getTehnologije() {
        return this.tehnologije;
    }
    
    public void setTehnologije(String tehnologije) {
        this.tehnologije = tehnologije;
    }
    public String getOpis() {
        return this.opis;
    }
    
    public void setOpis(String opis) {
        this.opis = opis;
    }
    public Set getDokuments() {
        return this.dokuments;
    }
    
    public void setDokuments(Set dokuments) {
        this.dokuments = dokuments;
    }
    public Set getClanprojekats() {
        return this.clanprojekats;
    }
    
    public void setClanprojekats(Set clanprojekats) {
        this.clanprojekats = clanprojekats;
    }




}


