package org.mql.java.ui;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.mql.java.models.VoitureModel;
import org.mql.java.xml.Parsser;

public class AddCarView extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private Form form;
	private Parsser parser ;
	private DefaultTableModel model;

	public AddCarView(Parsser parser , DefaultTableModel model) {
		this.parser=parser;
		this.model=model;
		form=new Form("Add car");
		
		setSize(800, 600);
		setLayout(new FlowLayout());
		add(form);
		
		JTextField matriculeField = new JTextField(20);
		form.add("Matricule",30,matriculeField);
		
		JTextField marqueField=new JTextField(20);
		form.add("Marque",30,marqueField);
	
		JTextField modelField = new JTextField(20);
		form.add("Model",30,modelField);
        
        JTextField couleurField = new JTextField(20);
        form.add("Couleur",30,couleurField);
		
        JTextField anneeField = new JTextField(20);
        form.add("Année",30,anneeField);
        
        JTextField automatiqueField = new JTextField(20);
        form.add("Automatique:",30,automatiqueField);
        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e ->{
        	String matricule = matriculeField.getText(); 
        	String marque = marqueField.getText();
        	String modele = modelField.getText();
        	String couleur = couleurField.getText();
        	String annee = anneeField.getText();
        	String automatique = automatiqueField.getText();
        	
        	//creation d nouvelle VoitureModel
        	VoitureModel car = new VoitureModel(modele, marque, matricule, couleur, annee, automatique);
        	model.addRow(new Object[] {  matricule, marque, modele, couleur, annee, automatique});
        	parser.addCar(car);
        	//fermer le dialogue
        	setVisible(false);
        });
        add(saveButton);
        setVisible(true);
		
	
		
	}
}
