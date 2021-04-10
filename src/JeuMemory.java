/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author Mamoudou Diallo
 */
public class JeuMemory extends javax.swing.JFrame {

    //Attributs
    private LesPersonnages persos ;
    private LesJoueurs joueurs ;
    private Jeu monJeu; // pour la gestion d’un tour de jeu
    private int l1, c1, l2, c2; // pour les cartes sélectionnées à chaque tour de jeu
    private int indc; //indice du joueur
    private int bton1, bton2;
    
    //Constructeur
    public JeuMemory() {
        initComponents();
        this.persos = new LesPersonnages(4);
        this.joueurs = new LesJoueurs();
        this.l1= -1;
        this.l2= -1;
        this.c1= -1;
        this.c2= -1; 
        Recommencer.setEnabled(false);
    }
    
    private void boutonActionPerformed(ActionEvent evt)
    {
        JButton jbt= (JButton)evt.getSource();
        String no=jbt.getName();
        int n=Integer.parseInt(no);
        int l, c;
        int co=this.monJeu.getMonP().getNbcol();
        l= n/co;
        c = n%co;
        int d= this.monJeu.getMonP().getCase(l, c);
        Image img=this.persos.getPerso(d).getPhoto().getScaledInstance(jbt.getWidth(), jbt.getHeight(), Image.SCALE_DEFAULT);
        jbt.setIcon(new ImageIcon(img));
        if (l1 == -1 && c1 == -1)
        {
            l1 =l;
            c1 = c;
            bton1= Integer.parseInt(no);
        }
        else 

            {
                l2 = l;
                c2 = c;
                bton2= Integer.parseInt(no);
                if(bton1!=bton2)
                startTimer();
            }
        
    }
    public void startTimer(){
        // définit un timer qui lance la vérification des deux personnages au bout d'une demi-seconde
        Timer t = new Timer(500, new ActionListener(){
        public void actionPerformed(ActionEvent ae){
        verifPersos();
        }
        });
        t.setRepeats(false);
        t.start();
    }
    
    //Methode pour bloquer les cartes
    public void bloqueCartes()
    {
        //Boucle permettant de parcourir tous les boutons et les rendres invalides
        for (int i=0; i<this.monJeu.getMonP().getNbc()*2; i++)
        {
            JButton s= (JButton)Panneau.getComponent(i);
            s.setEnabled(false);
        }
    }
    
    //Methode pour effacer les boutons
    public void EffaceBoutons()
    {
        JButton x= (JButton)Panneau.getComponent(this.bton1);
            x.setIcon(null);
        JButton y= (JButton)Panneau.getComponent(this.bton2);
            y.setIcon(null);            
    }
    //Methode pour rendre les cartes semblable invisible
    public void AnnuleBoutons()
    {
        JButton h= (JButton)Panneau.getComponent(this.bton1);
        h.setEnabled(false);
        JButton m= (JButton)Panneau.getComponent(this.bton2);
        m.setEnabled(false);
        
    }
   
    public void verifPersos()
    {
        
        if(this.monJeu.getMonP().getCase(l1, c1) == this.monJeu.getMonP().getCase(l2, c2))
        {
            String fam= this.persos.getPerso(this.monJeu.getMonP().getCase(l1, c1)).getFamille();
            int bonus= this.monJeu.traiteTour(this.joueurs.getJoueur(indc), this.monJeu.getMonP().getCase(l1, c1));
            if(bonus>=0)
            {
                Edition.setText("Vous avez gagné tous les personnages de la famille: " + fam);
                if (bonus==0)
                {
                    this.monJeu.getMonP().termineJeu();
                    Edition.setText("Le joueur gagnant est : " + this.joueurs.getJoueur(indc));
                    bloqueCartes();
                }
                if( bonus==1)
                {
                    TransfertDlg diag= new TransfertDlg(this, true, this.joueurs, indc);
                    diag.setVisible(true);
                    diag.setSize(1000,400);
                    Edition.setText(""+diag.getTc().getDeroulement());
                }
                if (bonus==2)
                {
                    BatailleDlg diag= new BatailleDlg(this, true, this.joueurs, this.indc);
                    diag.setVisible(true);
                    diag.setSize(600,600);
                    Edition.setText(""+diag.getBataille().getDeroulement());
                }
                bonus= -1;
                indc= this.monJeu.getIndSuivant(indc);
                //ChangeJoueur(); //Methode pour faire appel au joueur suivant
            }
            this.monJeu.getMonP().invalide(l1, c1, l2, c2);
            if (this.monJeu.getMonP().jeuVide()== true)
                
            {
                bloqueCartes();
                Edition.setText(this.joueurs.getGagnants().toString() +"Vous avez gagné, bravo !!!"); 
                jLabel1.setText("");
                jLabel2.setText("Fin du Jeu");
                jLabel3.setText("Félicitations !!!");
                jLabel3.setForeground(Color.red);
                jLabel2.setFont(new Font("Vivaldi", 3, 36));
            }
            else
            {
                indc= this.monJeu.getIndSuivant(indc);
                Edition.append("\n C'est au tour de: " +this.joueurs.getJoueur(indc).getPseudo()+ "" + this.joueurs.getJoueur(indc).toString());
                jLabel1.setText("Le nombre de personnage trouvé est: " +this.joueurs.getJoueur(indc).getPaquet().getTaille());
                jLabel2.setText("Le nombre de personnage restant: " + this.monJeu.getMonP().getNbp());
                jLabel3.setText("C'est à " +this.joueurs.getJoueur(indc).getPseudo()+ " de jouer !");
            }
            
            AnnuleBoutons(); //appel de la méthode si les cartes sont semblables
            
        }
       else
        {
            EffaceBoutons();
            //ChangeJoueur();
            indc= this.monJeu.getIndSuivant(indc);
            Edition.append("\n C'est au tour de: " +this.joueurs.getJoueur(indc).getPseudo()+"" + this.joueurs.getJoueur(indc).toString());
            jLabel1.setText("Le nombre de personnage trouvé est: " +this.joueurs.getJoueur(indc).getPaquet().getTaille());
            jLabel2.setText("Le nombre de personnage restant: " + this.monJeu.getMonP().getNbp());
            jLabel3.setText("C'est à " +this.joueurs.getJoueur(indc).getPseudo()+ " de jouer !");
        }
        this.l1= -1;
        this.l2= -1;
        this.c1= -1;
        this.c2= -1; 
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panneau = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Edition = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        Demarrer = new javax.swing.JButton();
        Recommencer = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Options = new javax.swing.JMenuItem();
        AjoutJoueur = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Joueurs = new javax.swing.JMenuItem();
        Cartes = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Panneau.setLayout(new java.awt.GridLayout(4, 5));
        getContentPane().add(Panneau, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.GridLayout(3, 1));

        jLabel1.setText("Nb de personnages trouvés");
        jPanel4.add(jLabel1);

        jLabel2.setText("Nb de personnages restants");
        jPanel4.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPanel4.add(jLabel3);

        jPanel2.add(jPanel4, java.awt.BorderLayout.NORTH);

        Edition.setEditable(false);
        Edition.setColumns(20);
        Edition.setRows(5);
        jScrollPane1.setViewportView(Edition);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new java.awt.GridLayout(1, 2));

        Demarrer.setText("Demarrer");
        Demarrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DemarrerActionPerformed(evt);
            }
        });
        jPanel5.add(Demarrer);

        Recommencer.setText("Recommencer");
        Recommencer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecommencerActionPerformed(evt);
            }
        });
        jPanel5.add(Recommencer);

        jPanel2.add(jPanel5, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.WEST);

        jMenu1.setText("Parametre");

        Options.setText("Options");
        Options.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OptionsActionPerformed(evt);
            }
        });
        jMenu1.add(Options);

        AjoutJoueur.setText("Ajout Joueur");
        AjoutJoueur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjoutJoueurActionPerformed(evt);
            }
        });
        jMenu1.add(AjoutJoueur);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Visualiser");

        Joueurs.setText("Joueurs");
        Joueurs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JoueursActionPerformed(evt);
            }
        });
        jMenu2.add(Joueurs);

        Cartes.setText("Cartes");
        Cartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CartesActionPerformed(evt);
            }
        });
        jMenu2.add(Cartes);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RecommencerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecommencerActionPerformed
        // TODO add your handling code here:
        Panneau.removeAll();
        Panneau.repaint();
        Recommencer.setEnabled(false);
        Demarrer.setEnabled(true);
        Options.setEnabled(true);
        AjoutJoueur.setEnabled(true);
        Joueurs.setEnabled(true);
        Cartes.setEnabled(true);
        this.persos = new LesPersonnages();
        this.joueurs = new LesJoueurs();
        jLabel2.setFont(new Font("Tahoma", 0, 13));
        jLabel1.setText("Le nombre de personnage trouvé est: ");
        jLabel2.setText("Le nombre de personnage restant: ");
        jLabel3.setText("");
        Edition.setText("");
    }//GEN-LAST:event_RecommencerActionPerformed

    private void OptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OptionsActionPerformed
        // ouverture de la boite de dialogue InitDlg
        
        InitDlg choix = new InitDlg(this,true);
        choix.setSize(900,600);
        choix.setVisible(true);
        if (choix.getOk()) {
              // ajoute les joueurs sélectionnés aux joueurs existants
              LesJoueurs mesJ=choix.getJoueurs();
              for(int i=0; i<mesJ.getNbJoueurs(); i++)
              { this.joueurs.ajouteJoueur(mesJ.getJoueur(i));
              }
              // initialise les personnages et le niveau de jeu
              this.persos = new LesPersonnages(choix.getNiveau());
              // pour vérification
              Edition.setText(this.joueurs.toString());
              monJeu = new Jeu( this.persos, this.joueurs, choix.getNiveau());
        }
        
    }//GEN-LAST:event_OptionsActionPerformed

    private void JoueursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JoueursActionPerformed
        // ouverture de la boite de dialogue VisuJoueursDlg
        VisuJoueursDlg choix = new VisuJoueursDlg(this,true,this.joueurs);
        choix.setSize(1000,400);
        choix.setVisible(true);
    }//GEN-LAST:event_JoueursActionPerformed

    private void AjoutJoueurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjoutJoueurActionPerformed
        // ouverture de la boite de dialogue SaisieJoueurDlg
        SaisieJoueurDlg diag= new SaisieJoueurDlg (this, true, this.persos);
        diag.setVisible(true);
        if (diag.getFermeture()== true)
        {
            Joueur d= diag.getNouveau_joueur();
            this.joueurs.ajouteJoueur(d);
            Edition.append(d.toString());
        }
    }//GEN-LAST:event_AjoutJoueurActionPerformed

    private void CartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CartesActionPerformed
        // ouverture de la boite de dialogue VisuPersonnagesDlg
        VisuPersonnagesDlg diag = new VisuPersonnagesDlg(this, true,this.joueurs.getJoueur(indc));
        diag.setSize(600,600);
        diag.setVisible(true);
                
    }//GEN-LAST:event_CartesActionPerformed

    private void DemarrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DemarrerActionPerformed
        // TODO add your handling code here:
            if (this.joueurs.getNbJoueurs()<2)
            {
                Edition.setText("Il faut un minimum de 2 joueurs");
            }
            else
            {
                Demarrer.setEnabled(false);
                Recommencer.setEnabled(true);
                Options.setEnabled(false);
                AjoutJoueur.setEnabled(false);
                Joueurs.setEnabled(true);
                Cartes.setEnabled(true);
                jLabel1.setText("Le nombre de personnage trouvé est: " +this.joueurs.getJoueur(indc).getPaquet().getTaille());
                jLabel2.setText("Le nombre de personnage restant: " + this.monJeu.getMonP().getNbp());
                jLabel3.setForeground(Color.black);
                jLabel3.setText("C'est à " +this.joueurs.getJoueur(indc).getPseudo()+ " de jouer !");
                Edition.setText("\n C'est au tour de : " +this.joueurs.getJoueur(indc).getPseudo()+ "" + this.joueurs.getJoueur(indc).toString());
                Panneau.setLayout(new GridLayout(this.monJeu.getMonP().getNblig(), this.monJeu.getMonP().getNbcol()));
                for(int i=0;i<this.persos.getTaille()*2;i++)
                {
                    JButton bt=new JButton();
                    Panneau.add(bt);
                    bt.setName(""+i);
                    bt.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                    boutonActionPerformed(evt);
                    }
                    });
                }
            }
    }//GEN-LAST:event_DemarrerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JeuMemory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JeuMemory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JeuMemory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JeuMemory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JeuMemory game = new JeuMemory();
                game.setSize(1000,800);
                game.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AjoutJoueur;
    private javax.swing.JMenuItem Cartes;
    private javax.swing.JButton Demarrer;
    private javax.swing.JTextArea Edition;
    private javax.swing.JMenuItem Joueurs;
    private javax.swing.JMenuItem Options;
    private javax.swing.JPanel Panneau;
    private javax.swing.JButton Recommencer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
