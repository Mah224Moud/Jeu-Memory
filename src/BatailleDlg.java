/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Mamoudou Diallo
 */
public class BatailleDlg extends javax.swing.JDialog {
    
    private LesJoueurs lj; //collection des joueurs (en entrée)
    private int indj; //indice du joueur courant (en entrée)
    private Joueur adversaire; //adversaire sélectionné (en sortie)
    private boolean ok; // action faite ou non (en sortie)
    private Bataille b; // action réalisée

    public boolean isOk() 
    {
        return ok; 
    }
    public Joueur getAdversaire() 
    { 
        return adversaire;
    }
    public Bataille getBataille()
    { 
        return b;
    }
    
    public BatailleDlg(java.awt.Frame parent, boolean modal, LesJoueurs lj, int jc)
    {
        super(parent, modal);
        initComponents();
        this.lj = lj;
        this.indj = jc;
        this.ok=false;
        this.b=null;
        initListeJ();
        Annuler.setText("Annuler");
        Annuler.setVisible(false); // aucune bataille existe
        MessageJ.setText(lj.getJoueur(indj).getPseudo()+" va effectuer une bataille contre : ");
    }
    
    public void initListeJ() 
    {
        DefaultListModel model= new DefaultListModel();
        ListeJ.setModel(model);
        for (int i=0; i<this.lj.getNbJoueurs(); i++)
        {
            model.addElement(this.lj.getJoueur(i).getPseudo());
        }
        
    }
    


    /**
     * Creates new form MysterDlg
     */
    public BatailleDlg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        MessageJ = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListeJ = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        Joueur = new javax.swing.JLabel();
        Adversaire = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        InfosCarte1 = new javax.swing.JTextArea();
        Carte1 = new javax.swing.JButton();
        Carte2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        InfosCarte2 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        Vainqueur = new javax.swing.JLabel();
        Annuler = new javax.swing.JButton();
        Demarrer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));
        jPanel1.add(MessageJ);

        ListeJ.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ListeJ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListeJMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ListeJ);

        jPanel1.add(jScrollPane1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.GridLayout(1, 2));
        jPanel4.add(Joueur);
        jPanel4.add(Adversaire);

        jPanel2.add(jPanel4, java.awt.BorderLayout.NORTH);

        jPanel5.setLayout(new java.awt.GridLayout(1, 4));

        InfosCarte1.setEditable(false);
        InfosCarte1.setColumns(20);
        InfosCarte1.setRows(5);
        jScrollPane2.setViewportView(InfosCarte1);

        jPanel5.add(jScrollPane2);

        Carte1.setText("jButton1");
        jPanel5.add(Carte1);

        Carte2.setText("jButton2");
        Carte2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Carte2ActionPerformed(evt);
            }
        });
        jPanel5.add(Carte2);

        InfosCarte2.setEditable(false);
        InfosCarte2.setColumns(20);
        InfosCarte2.setRows(5);
        jScrollPane3.setViewportView(InfosCarte2);

        jPanel5.add(jScrollPane3);

        jPanel2.add(jPanel5, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.GridLayout(1, 3));
        jPanel3.add(Vainqueur);

        Annuler.setText("Annuler/Fermer");
        Annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnulerActionPerformed(evt);
            }
        });
        jPanel3.add(Annuler);

        Demarrer.setText("Demarrer");
        Demarrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DemarrerActionPerformed(evt);
            }
        });
        jPanel3.add(Demarrer);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnulerActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.ok=false;
    }//GEN-LAST:event_AnnulerActionPerformed

    private void Carte2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Carte2ActionPerformed
        // TODO add your handling code here:
        this.ok=false;
        if (Annuler.getText().equals("Annuler"))
        this.b.setDeroulement("Bataille interrompue en cours de partie");
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_Carte2ActionPerformed

    private void ListeJMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListeJMouseClicked
        // TODO add your handling code here:
        int p=ListeJ.getSelectedIndex();
        this.adversaire= lj.getJoueur(p);
        if (p!=-1)
        {
            if(p==this.indj)
            {
                MessageJ.setText("Vous ne pouvez pas  sélectionner le courant, veuillez choisir un autre");
            }
            else
            {
                this.b= new Bataille(this.lj.getJoueur(indj), this.adversaire);
                MessageJ.setText(lj.getJoueur(indj).getPseudo()+" va effectuer une bataille contre : ");
                Annuler.setVisible(true);
                Joueur.setText(this.lj.getJoueur(indj).getPseudo());
                Adversaire.setText(""+this.adversaire.getPseudo());
                InfosCarte1.setText(this.lj.getJoueur(indj).getPaquet().toString());
                InfosCarte2.setText(this.adversaire.getPaquet().toString());
            }
        }
        
    }//GEN-LAST:event_ListeJMouseClicked

    private void DemarrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DemarrerActionPerformed
        // TODO add your handling code here:
        InfosCarte1.setText(this.lj.getJoueur(indj).getPaquet().getPerso(0).toString());
        InfosCarte2.setText(this.adversaire.getPaquet().getPerso(0).toString());
        int d = b.execute();
        //Conditions permettant reccuperer la valeur du joueur gagnant et d'afficher dans les zones d'éditions
        if (d== 1)
        {
            InfosCarte1.append(this.lj.getJoueur(indj).getPaquet().toString());
        }
        else
            if(d==2)
            {
                InfosCarte2.append(this.adversaire.getPaquet().toString());
            }
        this.ok= true;
        
        //Conditions permettant de verifier l'un des joueurs n'a plus plus personnages et d'afficher le vainqueur
        if (this.lj.getJoueur(indj).getPaquet().getTaille() == 0 || this.adversaire.getPaquet().getTaille()==0)
        {
            if (d==2)
            {
                Vainqueur.setText(this.adversaire.getPseudo());
            }
            else if ( d==1 )
            {
                Vainqueur.setText(this.lj.getJoueur(indj).getPseudo());
            }
         Demarrer.setEnabled(false);
         Annuler.setText("Fermer");
        }
        if (this.lj.getJoueur(indj).getPaquet().getTaille() > 0 )
        {
            Image j;
            j= this.lj.getJoueur(indj).getPaquet().getPerso(0).getPhoto();
            Carte1.setIcon(new ImageIcon(j));
        }
        if (this.adversaire.getPaquet().getTaille()>0)
        {
            Image f;
            f= this.adversaire.getPaquet().getPerso(0).getPhoto();
            Carte2.setIcon(new ImageIcon(f));
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
            java.util.logging.Logger.getLogger(BatailleDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BatailleDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BatailleDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BatailleDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BatailleDlg dialog = new BatailleDlg(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Adversaire;
    private javax.swing.JButton Annuler;
    private javax.swing.JButton Carte1;
    private javax.swing.JButton Carte2;
    private javax.swing.JButton Demarrer;
    private javax.swing.JTextArea InfosCarte1;
    private javax.swing.JTextArea InfosCarte2;
    private javax.swing.JLabel Joueur;
    private javax.swing.JList<String> ListeJ;
    private javax.swing.JLabel MessageJ;
    private javax.swing.JLabel Vainqueur;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
