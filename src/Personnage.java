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

public class Personnage {
    //Attributs
    private String famille;
    private String nom;
    private int valeur;
    private Image photo;
    
    
    //Accesseurs
    public void setFamille(String f){
        this.famille = f;  
    }
    
    public String getFamille(){
        return this.famille;   
    }
    
    public void setNom(String n){
        this.nom = n;  
    }
    
    public String getNom(){
        return this.nom;   
    }
    
    public void setValeur(int v){
        this.valeur = v;  
    }
    
    public int getValeur(){
        return this.valeur;   
    }
    
    public void setPhoto(Image p){
        this.photo = p;  
    }
    
    public Image getPhoto(){
        return this.photo;   
    }
    
    //Constructeur par défaut
    public Personnage(){
        this.famille = "anonyme";
        this.nom = "anonyme";
        this.valeur = 0;
        this.photo = new ImageIcon(getClass().getResource("/img/anonyme.png")).getImage();
        
    }
    
    //Constructeur avec paramètre
    public Personnage(String f, String name, int v){
        
        this.famille = f;
        this.nom = name;
        this.valeur = v;
        this.photo = new ImageIcon(getClass().getResource("/img/" + name + ".jpg")).getImage();
    
    }
    
    @Override
    public String toString()
    {
        String s="";
        s += "\n Famille :" +this.famille;
        s += "\n Nom :" +this.nom;
        s += "\n Valeur :" +this.valeur;
        
        return s;
    }
    

    
    
    
}
