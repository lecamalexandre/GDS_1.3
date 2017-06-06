/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import IHM.CIHMGestionEntreprises;
import bdd.CTableEntreprises;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import objets.CEntreprise;

/**
 *
 * @author Le Cam
 */
public class ControleurIHMEntreprise {
    
    CIHMGestionEntreprises ihm;
    
    //accès à la table entreprise de la base de donnée
    private final CTableEntreprises tableBDD = new CTableEntreprises();

    //Collection des entreprises
    private final ArrayList<CEntreprise> collectionEntreprises = new CTableEntreprises().selectEntreprise();
    
        //tableau
    private DefaultTableModel model;
    private JTable tableauEntreprise;
    private final JPanel panneauTableauEntreprise = new JPanel();
    
    

    public ControleurIHMEntreprise(CIHMGestionEntreprises ihm) {
        this.ihm=ihm;   
    }
    
    
     /**************************************************
           *     Action après validation ajout entreprise    *
          **************************************************/

           public class ValiderAjoutEntreprise implements ActionListener{
               public void actionPerformed(ActionEvent arg0) {

                   //Enregistrement des données récupérées dans un tableau 
                      ArrayList<String> donneeNouvelleEntreprise = ihm.getDonneeNouvelleEntreprise();
                

                  //Envoie du tableau pour enregistrement dans la BDD  
                     tableBDD.ajoutEntreprise(donneeNouvelleEntreprise);     
                      
                  //Envoie du tableau pour ajouter à la collection 
                      int lastId = collectionEntreprises.get(collectionEntreprises.size()-1).getId();
                      collectionEntreprises.add(new CEntreprise(lastId+1,donneeNouvelleEntreprise));

                  //mise a jour du tableau  
                      model.addRow(collectionEntreprises.get(collectionEntreprises.size()-1).getDonneesTab()); 
                        
                  //affiche message pour confirmer l'ajout de l'entreprise   
                     ihm.getMessage().setText("L'entreperise "+ ihm.getNomR().getText() + " à été ajouté");

                  //on referme la fenêtre ajout entreprise   
                     ihm.getIhmAjoutEntreprise().dispose();
               }
            }

    public CIHMGestionEntreprises getIhm() {
        return ihm;
    }

    public CTableEntreprises getTableBDD() {
        return tableBDD;
    }

    public ArrayList<CEntreprise> getCollectionEntreprises() {
        return collectionEntreprises;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JTable getTableauEntreprise() {
        return tableauEntreprise;
    }

    public JPanel getPanneauTableauEntreprise() {
        return panneauTableauEntreprise;
    }
    
    
    
    
    
}
