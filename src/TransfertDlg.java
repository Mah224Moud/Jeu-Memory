/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
/**
 *
 *  * @author DIALLO Mamoudou 
 */
public class TransfertDlg extends javax.swing.JDialog {
    
    private LesJoueurs lj; //collection des joueurs, pour initialiser la liste déroulante avec les pseudos des joueurs
    private int indj; //indice joueur courant
    private Transfert tc; // cette classe sera étudiée ultérieurement – laisser l’attribut en commentaire
    private boolean ok; // indicateur pour savoir le transfert a bien été effectué.
    private int indjs; //indice du joueur sélectionné dans la liste déroulante
    private String fs; //famille du personnage sélectionné en cliquant sur un des personnages du joueur sélectionné
    
    public boolean getOk(){
        return this.ok;
    }
    public Transfert getTc(){ return this.tc;}

    /**
     * Creates new form TransfertDlg
     */
    public TransfertDlg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public TransfertDlg(java.awt.Frame parent, boolean modal, LesJoueurs lj, int indj) {
        super(parent, modal);
        initComponents();
        this.lj = lj;
        this.indj = indj ;
        this.tc=null;
        this.ok=false;
        this.fs=null;
        initCombo(); // méthode pour remplir la liste déroulante
        indjs = 0;
        jLabel1.setText("Le joueur "+lj.getJoueur(indj).getPseudo()+" a obtenu une famille complète");
        Infos.setText("Personnages de "+lj.getJoueur(indj).getPseudo()+" : \n"+lj.getJoueur(indj).getPaquet());
    }

    public void initCombo()
    {
        for(int i=0;i<this.lj.getNbJoueurs();i++)
        {
            ComboJoueurs.addItem(this.lj.getJoueur(i).getPseudo());
        }
    }
            
    public void initPanneau()
    {
        PanneauG.removeAll();//supression de tous les composants du panneau 
        this.repaint();//Ré-affichage de la boite de dialogue 
        LesPersonnages lcs=this.lj.getJoueur(this.indjs).getPaquet();
        int t=lcs.getTaille();
        int n=1+(t-1)/4;
        PanneauG.setLayout(new GridLayout(4,n));
        for(int i=0;i<t;i++){
            JButton jb=new JButton();
            jb.setName(lcs.getPerso(i).getFamille());
             jb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonActionPerformed(evt);
            }
        });
             PanneauG.add(jb);
        }this.pack();
    }
    public void creePanneau(JPanel jp, LesPersonnages lc)
    {
        jp.removeAll();
        this.repaint();
        int e= lc.getTaille();
        int f= 1+(e-1)/4;
        jp.setLayout(new GridLayout(4,f));
        for(int i=0;i<e;i++)
        {
            JButton bt=new JButton();
            jp.add(bt);
            jp.setName(lc.getPerso(i).getFamille());
        }
        this.pack();
    }
    
    public void affichePanneau()
    {
        LesPersonnages p=this.lj.getJoueur(this.indjs).getPaquet();
        for(int i=0;i<p.getTaille();i++)
        {
            JButton jb=(JButton)PanneauG.getComponent(i);
            Image img=p.getPerso(i).getPhoto();
            jb.setIcon(new ImageIcon(img));
        }
    }
    public void dessinePanneau(JPanel jp, LesPersonnages lc)
    {
        for(int i=0;i<lc.getTaille();i++)
        {
            JButton jb=(JButton)jp.getComponent(i);
            Image img=lc.getPerso(i).getPhoto();
            jb.setIcon(new ImageIcon(img));
        } 
    }
    
    private void boutonActionPerformed(ActionEvent evt)
    {
        LesPersonnages lp = lj.getJoueur(indjs).getPaquet();
        int t = lp.getTaille();
        JButton bt=(JButton) evt.getSource();
        fs = bt.getName(); // la proprité Name, contient ici le nom du personnage affiché sur le bouton
        for(int i = 0; i < t; i++) {
        JButton b = (JButton)(PanneauG.getComponent(i));
        if (b.getName().equals(fs))
        b.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(255, 0, 0)));
        else
        b.setBorder(null);
        }
        LesPersonnages lps = lp.getPersosFamille(fs);
        Infos.setText("Vous pouvez récupérer "+lps.getTaille()+" personnages : \n"+lps);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanneauG = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        ComboJoueurs = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        Infos = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        Transfert = new javax.swing.JButton();
        Fermer = new javax.swing.JButton();
        PanneauD = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 3));

        PanneauG.setLayout(new java.awt.GridLayout(1, 1));
        getContentPane().add(PanneauG);

        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        jPanel2.setLayout(new java.awt.GridLayout(3, 1));

        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1);
        jPanel2.add(jLabel2);
        jPanel2.add(jLabel3);

        jPanel1.add(jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        ComboJoueurs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboJoueursActionPerformed(evt);
            }
        });
        jPanel3.add(ComboJoueurs, java.awt.BorderLayout.NORTH);

        Infos.setEditable(false);
        Infos.setColumns(20);
        Infos.setRows(5);
        jScrollPane1.setViewportView(Infos);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        Transfert.setText("Transfert");
        Transfert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransfertActionPerformed(evt);
            }
        });
        jPanel4.add(Transfert);

        Fermer.setText("Fermer");
        Fermer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FermerActionPerformed(evt);
            }
        });
        jPanel4.add(Fermer);

        jPanel3.add(jPanel4, java.awt.BorderLayout.SOUTH);

        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1);

        javax.swing.GroupLayout PanneauDLayout = new javax.swing.GroupLayout(PanneauD);
        PanneauD.setLayout(PanneauDLayout);
        PanneauDLayout.setHorizontalGroup(
            PanneauDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
        );
        PanneauDLayout.setVerticalGroup(
            PanneauDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 463, Short.MAX_VALUE)
        );

        getContentPane().add(PanneauD);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FermerActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.ok=false;
    }//GEN-LAST:event_FermerActionPerformed

    private void TransfertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransfertActionPerformed
        // TODO add your handling code here:
        if (this.indjs !=-1 && this.fs != null)
        {
            tc = new Transfert(this.lj.getJoueur(indj), this.lj.getJoueur(indjs), this.fs);
            int d = tc.execute();
            if ( d >0)
            {
                
                LesPersonnages lp = this.tc.getCartesTransferees();
                creePanneau(this.PanneauD, lp);
                dessinePanneau(this.PanneauD, lp);
                LesPersonnages s = this.lj.getJoueur(indjs).getPaquet();
                creePanneau(this.PanneauG, s);
                dessinePanneau(this.PanneauG, s);
                this.ok = true;
            }
        }
        else
        {
            Infos.setText("Veillez choisir un joueur qui a au moins une carte");
        }
    }//GEN-LAST:event_TransfertActionPerformed

    private void ComboJoueursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboJoueursActionPerformed
        // TODO add your handling code here:
        this.indjs = ComboJoueurs.getSelectedIndex();
        if (indjs != -1){
        if (this.indjs == this.indj) {
        Infos.setText("Sélectionnez un joueur différent du joueur courant !");
        PanneauG.removeAll();
        PanneauG.repaint();
        }
        else {
        Infos.setText("\nJoueur sélectionné: "+lj.getJoueur(indjs).toString());
        initPanneau();
        affichePanneau();
        }
        }
    }//GEN-LAST:event_ComboJoueursActionPerformed

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
            java.util.logging.Logger.getLogger(TransfertDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransfertDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransfertDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransfertDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TransfertDlg dialog = new TransfertDlg(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> ComboJoueurs;
    private javax.swing.JButton Fermer;
    private javax.swing.JTextArea Infos;
    private javax.swing.JPanel PanneauD;
    private javax.swing.JPanel PanneauG;
    private javax.swing.JButton Transfert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}