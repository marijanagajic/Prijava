package prijava.beans;
// Generated Feb 13, 2018 3:26:51 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Clan generated by hbm2java
 */
public class Clan  implements java.io.Serializable {


     private Integer idclan;
     private Prijava prijava;
     private String ime;
     private String prezime;
     private byte vodjagrupe;
     private int godiskustva;
     private byte[] dokumentcv;
     private String email;
     private String ekstenzija;
     private Set clanprojekats = new HashSet(0);

    public Clan() {
    }

	
    public Clan(Prijava prijava, String ime, String prezime, byte vodjagrupe, int godiskustva, String email) {
        this.prijava = prijava;
        this.ime = ime;
        this.prezime = prezime;
        this.vodjagrupe = vodjagrupe;
        this.godiskustva = godiskustva;
        this.email = email;
    }
    public Clan(Prijava prijava, String ime, String prezime, byte vodjagrupe, int godiskustva, byte[] dokumentcv, String email, String ekstenzija, Set clanprojekats) {
       this.prijava = prijava;
       this.ime = ime;
       this.prezime = prezime;
       this.vodjagrupe = vodjagrupe;
       this.godiskustva = godiskustva;
       this.dokumentcv = dokumentcv;
       this.email = email;
       this.ekstenzija = ekstenzija;
       this.clanprojekats = clanprojekats;
    }
   
    public Integer getIdclan() {
        return this.idclan;
    }
    
    public void setIdclan(Integer idclan) {
        this.idclan = idclan;
    }
    public Prijava getPrijava() {
        return this.prijava;
    }
    
    public void setPrijava(Prijava prijava) {
        this.prijava = prijava;
    }
    public String getIme() {
        return this.ime;
    }
    
    public void setIme(String ime) {
        this.ime = ime;
    }
    public String getPrezime() {
        return this.prezime;
    }
    
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    public byte getVodjagrupe() {
        return this.vodjagrupe;
    }
    
    public void setVodjagrupe(byte vodjagrupe) {
        this.vodjagrupe = vodjagrupe;
    }
    public int getGodiskustva() {
        return this.godiskustva;
    }
    
    public void setGodiskustva(int godiskustva) {
        this.godiskustva = godiskustva;
    }
    public byte[] getDokumentcv() {
        return this.dokumentcv;
    }
    
    public void setDokumentcv(byte[] dokumentcv) {
        this.dokumentcv = dokumentcv;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEkstenzija() {
        return this.ekstenzija;
    }
    
    public void setEkstenzija(String ekstenzija) {
        this.ekstenzija = ekstenzija;
    }
    public Set getClanprojekats() {
        return this.clanprojekats;
    }
    
    public void setClanprojekats(Set clanprojekats) {
        this.clanprojekats = clanprojekats;
    }

    @Override
    public String toString() {
        return  ime + " " + prezime;
    }

    


}


