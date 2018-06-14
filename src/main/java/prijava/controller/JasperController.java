/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prijava.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Marijana
 */
@ManagedBean(name = "jasperController")
@SessionScoped
public class JasperController implements Serializable{
    
    private String targetPrikaz = "";
    private String file = "";
    private String izabranNacinPrikazivanja = "";
    private boolean visibleNacinPrikaza = false;
    //private DokumentResult dokumentResult;
    
    public void prikaziIzvestaj(){
        targetPrikaz = "";
        file = "";
        izabranNacinPrikazivanja = "";
       // dokumentResult.getOdabran().setFormatResult(new FormatDokumentaResult());
       // dokumentResult.getOdabran().setTipResult(new TipDokumentaResult());
        visibleNacinPrikaza = false;
    }
    
}
