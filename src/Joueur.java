/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DIALLO Mamoudou 
 */

import java.awt.*; 
import javax.swing.*;

public class Joueur {
    //Attributs

    private String pseudo;
    private String famillePref;
    private LesPersonnages paquet;
    private ImageIcon photo;
    
    //Accesseurs
    
    public void setPseudo(String p){
        this.pseudo = p;
    }
    
    public String getPseudo(){
        return this.pseudo;
    }
    
    public void setFamille(String f){
        this.famillePref = f;
    }
    
    public String getFamille(String f){
        return this.famillePref;
    }
    public String getFamille(){
        return this.famillePref;
    }
    
    public void setPhoto(ImageIcon p){
        this.photo = p;
    }
    
    public ImageIcon getPhoto(){
        return this.photo;
    }
    
    public void setPaquet(LesPersonnages p){
        this.paquet = p;
    }
    
    public LesPersonnages getPaquet(){
        return this.paquet;
    }
    
    
    //Constructeurs
    public Joueur(String pseudo, String famillePref, LesPersonnages paquet, ImageIcon photo){
        setPseudo(pseudo);
        setFamille(famillePref);
        setPaquet(paquet);
        setPhoto(photo);
    }
    public Joueur()
    {
        this.pseudo= "anonyme";
        this.famillePref= "anonyme";
        this.paquet= new LesPersonnages();
        this.photo = new ImageIcon(getClass().getResource("/img/anonyme.png"));
    }
    public Joueur(String pseudo, String famillePref){
        setPseudo(pseudo);
        setFamille(famillePref);
        this.paquet= new LesPersonnages();
        this.photo = new ImageIcon(getClass().getResource("/img/anonyme.png"));
    }
    
    //Methode
    public int getScore()
    {
        int j;
        j= this.paquet.getScore();
        
        return j;
    }
    
    
    //Methodes
    public void ajoutePersoPaquet(Personnage p) 
    {
        this.paquet.ajoutePerso(p);
    }
    public void initPaquetTest()
    {
        ajoutePersoPaquet(new Personnage("communs", "assault-trooper", 10));
        ajoutePersoPaquet(new Personnage("communs", "commando", 20));
        ajoutePersoPaquet(new Personnage("rares", "absolute-zero", 10));
    }

    
    @Override
    public String toString(){
        String s = "";
        s += "\n Joueur : " + this.pseudo;
        s += "\n Famille préférée : " + this.famillePref;
        s += "\n Score:" + this.getScore();
        
        return s;
    }
    
    
    
    
}
