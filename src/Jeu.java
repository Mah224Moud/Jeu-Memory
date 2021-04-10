/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
* @author Mamoudou Diallo
 */
public class Jeu {
    //Attributs
    private LesPersonnages lesPers;
    private PlateauJeu monP;
    private LesJoueurs lesJ;
    private int indC;
    private Action act;
    
    public Jeu(LesPersonnages lp, LesJoueurs lj, int nbc)
    { 
        this.lesPers=lp;
        this.monP=new PlateauJeu(nbc);
        this.lesJ=lj;
        this.act=null;
        this.indC=0;
    }
    
    public LesPersonnages getLesPers()
    {
        return this.lesPers;
    }
    public PlateauJeu getMonP()
    {
        return this.monP;
    }
    public LesJoueurs getLesJ()
    {
        return this.lesJ;
    }
    public Action getAct()
    {
        return this.act;
    }
    public void setIndC( int indc)
    {
        this.indC = indc;
    }
     
    public int getIndSuivant(int j) //Retourne l'indice du joueur courant
    { 
        return (j+1)%lesJ.getNbJoueurs(); 
    }
    public Joueur getJoueurCourant() //retourne le joueur courant
    {
        return lesJ.getJoueur(indC);
    }
    public Joueur getJoueurSuivant(int j) //retourne le joueur suivant
    { 
        return lesJ.getJoueur(getIndSuivant(j)); 
    }
    
    public boolean finJeu() 
    {  
        return monP.jeuVide();
    }
    public int nbPers(){
    int n = 0;
    for(int i = 0; i < lesJ.getNbJoueurs(); i++)
        if (i !=this.indC) n+=lesJ.getJoueur(i).getPaquet().getTaille();
        return n;
   }
    
    public int traiteTour(Joueur jo, int s)
    {
        int bonus = -1;
        Personnage pers = this.lesPers.getPerso(s);
        jo.ajoutePersoPaquet(pers);
        String f= pers.getFamille();
        int npf = this.lesPers.getPersosFamille(f).getTaille();
        int nbj = jo.getPaquet().getPersosFamille(f).getTaille();
        if (npf == nbj)
        {
            if (jo.getFamille().equals(f))
            {
                bonus = 0;
                this.monP.termineJeu();
                
            }
            else
                if(this.nbPers() >0)
                {
                    if (f.equals("rares") || f == "communs")
                    {
                        bonus = 1;
                    }
                    else 
                        if (f == "legendaires" || f == "epiques")
                        {
                            bonus = 2;
                        }
                        else
                            bonus = 3;
                }
        }
        
        
        return bonus;
    }
}
