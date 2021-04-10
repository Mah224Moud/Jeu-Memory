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
public class Transfert extends Action{

    //Attributs
    private Joueur cible;
    private String fp; //famille de la carte sélectionnée
    private LesPersonnages cartesTransferees; // cartes transférées

    
    //Accesseurs
    public Joueur getJoueurCible()
    {
        return this.cible;
    }
    public LesPersonnages getCartesTransferees() 
    {
        return cartesTransferees;
    } 
    
    public Transfert(Joueur sc, Joueur c, String f) 
    {
        super(sc, "Transfert de cartes");
        this.cible = c;
        this.fp=f;
        this.cartesTransferees= new LesPersonnages();
    }

    @Override
    public int execute() {
        int res =0;
        if ( this.cible.getPaquet().getPersosFamille(fp) != null)
        {
            cartesTransferees = this.cible.getPaquet().retirePeros(fp);
            this.getJ().getPaquet().ajoutePersos(cartesTransferees);
            this.setDeroulement(this.getJ().getPseudo()+ " a pris la famille de carte "+ this.fp+ " de " +this.cible.getPseudo());
            
            res = this.getCartesTransferees().getTaille();
        }
        return res;
    }
    
}
