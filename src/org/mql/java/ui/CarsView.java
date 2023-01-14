package org.mql.java.ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.mql.java.models.VoitureModel;
import org.mql.java.xml.Parsser;

public class CarsView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Parsser parser;
	private JTable table;
	private DefaultTableModel model;
	

	public CarsView() {
		parser = new Parsser("resources/voitures.xml");
		setTitle("List des voitures");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		String[] columnNames = {"Matricule" ,"Marque" ,"Model" ,"Couleur" ,"Année" ,"Automatique", "Delete"};
		model = new DefaultTableModel(columnNames, 0);
		table = new JTable(model);
		
		Font headerFont = new Font("Arial", Font.BOLD, 14);
		table.getTableHeader().setFont(headerFont);
		
		//////////////////
		table.getColumn("Delete").setCellRenderer(new ButtonRenderer());
		table.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(),table,parser));
		
		
		/////////////////////
		
		
		add(new JScrollPane(table), BorderLayout.CENTER);
		for (VoitureModel car : parser.voitureParse()) {
			model.addRow(new Object[] { car.getMatricule() ,car.getMarque() ,car.getModel() ,car.getColeur() ,car.getAnnée(), car.getAutomatique()});
		}
		 JButton addButton = new JButton("Add Car");
		 addButton.addActionListener(e -> {
	            AddCarView form = new AddCarView(parser, model);
	            form.setModal(true);
	            form.setVisible(true);
	        });
		 add(addButton, BorderLayout.SOUTH); 
		 
		setVisible(true);
	}

	public static void main(String[] args) {
		new CarsView();
	}

}