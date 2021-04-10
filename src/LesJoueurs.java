
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DIALLO Mamoudou 
 */
public class LesJoueurs {
    
    //Attributs
    private ArrayList<Joueur> lstJ ;
    
    //Constructeur par defaut
    public LesJoueurs()
    {
        this.lstJ = new ArrayList<Joueur>();
    }
    
    
    //Methode getJoueur
    public Joueur getJoueur (int i)
    {
        if(i>=0 && i<lstJ.size())
            return lstJ.get(i);
        else
            return null;
    }
    
    //Methode getIndiceJoueur
    public int getIndiceJoueur (Joueur j)
    {
         int i = 0;
        int indice_trouve = -1;
        boolean trouve = false; // Flag pour arrêter la boucle
        while(i<this.getNbJoueurs() && !trouve)
        {
            if (getJoueur(i).getPseudo().equals(j.getPseudo())) // Si le joeur est trouvé
            {
                trouve = true;
                indice_trouve = this.lstJ.indexOf(j);
            }
            else
            {
                i++;
            }
        }
        if(trouve)
            return indice_trouve;
        else
            return -1;
    }

    
    //Methode getNbJoueurs
    public int getNbJoueurs()
    {
        return this.lstJ.size();
    }
    
    //Methode ajouteJoueur
    public void ajouteJoueur( Joueur j)
    {
        this.lstJ.add(j);
    }
    
    //Methode rechJoueur
    public Joueur rechJoueur (String p)
    {
        boolean trouve = false;
        Joueur joueur_trouve = new Joueur();
        for(int i=0; i<this.lstJ.size(); i++)
            if(getJoueur(i).getPseudo().equals(p))
            {
                trouve = true;
                joueur_trouve = this.lstJ.get(i);
            }
        if(!trouve)
        {
            return null;
        }
        else
        {
            return joueur_trouve;
        }
        
    }
    
    //Methode supprimeJoueur
    public void supprimeJoueur ( Joueur j)
    {
        boolean trouve = false;
        int i =0;
         while(!trouve && i<this.lstJ.size()) // On boucle sur la liste des joueurs
        {
            if(this.getJoueur(i).getPseudo().equals(j.getPseudo())) // Si le joueur est trouvé
            {
                this.lstJ.remove(i);
                trouve = true;
               
            }
             else
                {
                    i =+ 1;
                }
        }
    }
    public LesJoueurs getGagnants()
    {
        int max=getJoueur(0).getScore();
        for(int i=1; i<getNbJoueurs();i++)
            if (getJoueur(i).getScore()>max)
            max=this.getJoueur(i).getScore();
        
        LesJoueurs lst=new LesJoueurs();
        for(int i=0; i<getNbJoueurs();i++)
            if (getJoueur(i).getScore()==max)
                lst.ajouteJoueur(getJoueur(i));
        return lst;
    } 
    
    @Override
    public String toString()
    {
        String res="";
        for(int i=0; i<this.getNbJoueurs();i++)
            res+=" "+this.getJoueur(i)+"\n";
        return res;
    }
    
    
}
