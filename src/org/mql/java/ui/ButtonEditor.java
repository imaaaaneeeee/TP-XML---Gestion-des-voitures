package org.mql.java.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.mql.java.xml.Parsser;

class ButtonEditor extends DefaultCellEditor {
	protected JButton button;
	private String label;
	private boolean isPushed;
	private JTable table;
	private Parsser parser;

	public ButtonEditor(JCheckBox checkBox, JTable table, Parsser parser) {
		super(checkBox);
		this.table = table;
		this.parser = parser;
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());

		}
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		isPushed = true;
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed) {

			int selectedRow = table.getSelectedRow();
			parser.deleteCar((String) table.getValueAt(selectedRow, 0));
			((DefaultTableModel) table.getModel()).removeRow(selectedRow);
			
		}
		isPushed = false;
		return new String(label);
	}

	public boolean stopCellEditing() {

		isPushed = false;
		return super.stopCellEditing();
	}
}
