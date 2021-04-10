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

import java.util.*;

public class LesPersonnages {
    //Attributs
    
    private ArrayList<Personnage> persos;
    
    public LesPersonnages(){
        persos = new ArrayList<Personnage>();
    }
    
    //Accesseurs
    public int getTaille(){
        return persos.size();
    }
    
    public Personnage getPerso(int i){
        if(i>=0 && i<persos.size())
            return persos.get(i);
        else
            return null;
    }
    
    //Mehtode pour reccupére le score
    public int getScore(){
        int s = 0;
        for(int i = 0; i < this.getTaille(); i++){
            s += getPerso(i).getValeur();
        }
        return s;
    }
    
    //Methode pour remplir la Jlist sans repetition
    public ArrayList<String> getFamilles()
    {
        ArrayList<String> lst = new ArrayList<String>();
      for (int j=0; j<this.persos.size(); j++)
      { Personnage p = this.persos.get(j);
      boolean trouve = false;
      for (int i=0; i< lst.size(); i++)
          if (lst.get(i).equals(p.getFamille()))trouve = true;
      if (!trouve) lst.add(p.getFamille()); }
      return lst;
    }
    
    //Methode ajoutePerso
    public void ajoutePerso ( Personnage p)
    {
        this.persos.add(p);
    }
    
    //Methode ajout d'un personnage passé en paramètre
    public void ajoutePersos( LesPersonnages lp)
    {
        for (int i=0; i<lp.getTaille(); i++)
        {
            //recuperation du personnage d'indice i
            Personnage p = lp.getPerso(i);
            this.ajoutePerso(p);
        }
    }
    
    //Methode retirePerso
    public void retirePerso ( Personnage p)
    {
        boolean trouve = false;
        int i= 0;
        while(! trouve && i< this.getTaille())
        if (this.getPerso(i).getNom().equals(p.getNom()))
        {
            this.persos.remove(i);
            trouve = true;
        }
        else
            i++;
    }
    
    //Methode retirePersosFamille
    public LesPersonnages retirePeros( String f)
    {
        LesPersonnages lpr = new LesPersonnages();
        int i = 0;
        while (i < this.getTaille())
        {
            if ( this.getPerso(i).getFamille().equals(f))
            {
                lpr.ajoutePerso(this.getPerso(i));
                this.persos.remove(i);  
            }
            else
                i++;
        }
        return lpr;
    }
    
    
    public void retireCartes()
    { 
           persos.clear();
    }
 
    
    public LesPersonnages retirePersos(int n)
    { LesPersonnages lcr = new LesPersonnages();
            for (int i=0; i<=n; i++)
            {
                lcr.ajoutePerso(getPerso(0));
                this.persos.remove(0);  
            }
            return lcr;
    }
    
    
    //Methode getPersosFamilles
    public LesPersonnages getPersosFamille( String f)
    {
        LesPersonnages lpf = new LesPersonnages();
        for (int i= 0; i< this.getTaille(); i++)
        {
            if (this.getPerso(i).getFamille().equals(f))
                lpf.ajoutePerso(this.getPerso(i));
        }
        return lpf;
    }
            
    
    @Override
        public String toString() {
        String s = "";
        for(int i=0; i<getTaille(); i++)
            s+=i+"- "+getPerso(i)+"\n";
       return s;
    }
    
    public LesPersonnages(int nc) {
        this.persos = new ArrayList<Personnage>();
        if (nc >= 4){ // 2 familles
            ajoutePerso(new Personnage("communs", "assault-trooper", 10));
            ajoutePerso(new Personnage("communs", "commando", 20));
            ajoutePerso(new Personnage("rares", "absolute-zero", 10));
            ajoutePerso(new Personnage("rares", "arctice-assassin", 20));
        }
        if (nc >= 10){ // 4 familles
            ajoutePerso(new Personnage("communs", "devestrator", 30));
            ajoutePerso(new Personnage("rares", "brawler", 30));
            ajoutePerso(new Personnage("alpins-femmes", "mogul-master", 10));
            ajoutePerso(new Personnage("alpins-femmes", "mogul-master-can", 20));
            ajoutePerso(new Personnage("as-des-pistes", "alpine-ace", 10));
            ajoutePerso(new Personnage("as-des-pistes", "alpine-ace-can", 20));
        }
        if (nc >= 18){ // 6 familles

            ajoutePerso(new Personnage("alpins-femmes", "mogul-master-chn", 30));
            ajoutePerso(new Personnage("as-des-pistes", "alpine-ace-chn", 30));
            ajoutePerso(new Personnage("legendaires", "power-chord", 10));
            ajoutePerso(new Personnage("legendaires", "raptor", 20));
            ajoutePerso(new Personnage("legendaires", "raven", 30));
            ajoutePerso(new Personnage("epiques", "burnout", 10));
            ajoutePerso(new Personnage("epiques", "funk-ops", 20));
            ajoutePerso(new Personnage("epiques", "rex", 30));
        }
        if (nc == 32){ // 6 familles
            ajoutePerso(new Personnage("communs", "dominator", 40));
            ajoutePerso(new Personnage("communs", "highrise-assault-trooper", 50));
            ajoutePerso(new Personnage("communs", "jungle-scout", 60));
            ajoutePerso(new Personnage("communs", "pathfinder", 70));
            ajoutePerso(new Personnage("rares", "brilliant-striker", 40));
            ajoutePerso(new Personnage("rares", "brite-bomber", 50));
            ajoutePerso(new Personnage("rares", "circuit-breaker", 60));
            ajoutePerso(new Personnage("rares", "dazzle", 70));
            ajoutePerso(new Personnage("alpins-femmes", "mogul-master-fra", 40));
            ajoutePerso(new Personnage("alpins-femmes", "mogul-master-gbr", 50));
            ajoutePerso(new Personnage("as-des-pistes", "alpine-ace-fra", 40));
            ajoutePerso(new Personnage("as-des-pistes", "alpine-ace-gbr", 50));
            ajoutePerso(new Personnage("legendaires", "red-knight", 40));
            ajoutePerso(new Personnage("epiques", "shadow-ops", 40));
        }
    }
    
}
