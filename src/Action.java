/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import javax.swing.*;
/**
 *
 *  * @author DIALLO Mamoudou 
 */
public abstract class Action {
    private Joueur j; // joueur à l'origine de l'action
    private String descriptif; //nature de l'action
    private String deroulement; // description de ce qui s’est passé durant l’action

    public abstract int execute();
    
    public Action(Joueur sc, String s) 
    {
        this.j=sc;
        this.descriptif=s;
        this.deroulement="";
    }
    
    public void setJ( Joueur joueur)
    {
        this.j = joueur;
    }
    public Joueur getJ()
    {
        return this.j;
    }
    
    public void setDescriptif( String descr )
    {
        this.descriptif = descr;
    }
    public String getDescriptif()
    {
        return this.descriptif;
    }
    public void setDeroulement( String deroul)
    {
        this.deroulement = deroul ;
    }
    public String getDeroulement()
    {
        return this.deroulement ;
    }
    
    
    
    public String toString()
    { 
        return "Action effectuée par "+j.getPseudo()+ " : " +descriptif+"\n"+this.deroulement+"\n";
    }
}
