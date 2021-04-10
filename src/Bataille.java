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
public class Bataille extends Action {
    //Attributs
    private Joueur adversaire;
    
    //Accesseurs en lecture
    public Joueur getAdversaire()
    {
        return this.adversaire;
    }
    public Bataille(Joueur sc, Joueur a) 
    {
        super(sc, "Bataille");
        this.adversaire = a;
    }


    @Override
    public int execute() {
        int res = -1;
        if (this.getJ().getPaquet().getTaille() >0 && this.adversaire.getPaquet().getTaille() > 0)
        {
            Personnage c1 = new Personnage();
            c1 = this.getJ().getPaquet().getPerso(0);
            Personnage c2 = new Personnage();
            c2 = this.adversaire.getPaquet().getPerso(0);
            this.getJ().getPaquet().retirePerso(c1);
            this.adversaire.getPaquet().retirePerso(c2);
            if ( c1.getValeur()==c2.getValeur())
            {
                res = 0;
                this.getJ().getPaquet().ajoutePerso(c1);
                this.adversaire.getPaquet().ajoutePerso(c2);
            }
            else
                if (c1.getValeur()> c2.getValeur() )
                {
                    res = 1;
                    this.getJ().getPaquet().ajoutePerso(c1);
                    this.getJ().getPaquet().ajoutePerso(c2);
                }
                else
                {
                    res =2;
                    this.adversaire.getPaquet().ajoutePerso(c2);
                    this.adversaire.getPaquet().ajoutePerso(c1);
                }
            if(this.getJ().getPaquet().getTaille() ==0  || this.adversaire.getPaquet().getTaille()==0)
            {
                this.setDeroulement(this.getJ().getPseudo()+ " a joué contre " + this.adversaire.getPseudo());
                
                //Condition pour te determiner le gagnant en fonction du resultat
                if ( res== 1)
                {
                    this.setDeroulement(this.getJ().getPseudo()+ " a gagné une bataille contre " + this.adversaire.getPseudo());
                }
                else
                    if (res==2)
                    {
                       this.setDeroulement(this.getJ().getPseudo()+ " a perdu une bataille contre " + this.adversaire.getPseudo()); 
                    }
            }  System.out.println(this.getDeroulement());
        } 
        return res; 
    }
}
