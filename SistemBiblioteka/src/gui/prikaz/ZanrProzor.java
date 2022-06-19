package gui.prikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


import main.BibliotekaMain;
import biblioteka.Biblioteka;
import gui.forme.ZanrForma;
import knjige.Zanr;

public class ZanrProzor extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable zanrTabela;
	
	private Biblioteka biblioteka;
	
	public ZanrProzor(Biblioteka biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Zanrovi");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}
	
	private void initGUI() {
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
		btnAdd.setIcon(addIcon);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
		btnEdit.setIcon(editIcon);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
		btnDelete.setIcon(deleteIcon);
		
		mainToolbar.add(btnAdd);
		mainToolbar.add(btnEdit);
		mainToolbar.add(btnDelete);
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] zaglavlja = new String[] {"Naziv", "Oznaka", "Opis"};
		Object[][] sadrzaj = new Object[biblioteka.getZanrovi().size()][zaglavlja.length];
		
		for(int i = 0; i < biblioteka.getZanrovi().size(); i++) {
			Zanr zanr = biblioteka.getZanrovi().get(i);
			sadrzaj[i][0] = zanr.getNaziv();
			sadrzaj[i][1] = zanr.getOznaka();
			sadrzaj[i][2] = zanr.getOpis();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		zanrTabela = new JTable(tableModel);
		
		zanrTabela.setRowSelectionAllowed(true);
		zanrTabela.setColumnSelectionAllowed(false);
		zanrTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		zanrTabela.setDefaultEditor(Object.class, null);
		zanrTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(zanrTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = zanrTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = tableModel.getValueAt(red, 1).toString();
                    Zanr zanr = null;
                    for(Zanr znr : biblioteka.getZanrovi()){
                        if(znr.getOznaka().equals(id)){
                            zanr = znr;
                        }
                    }
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete zanr?", 
							zanr.getNaziv() + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						biblioteka.ukloniZanr(zanr);
						tableModel.removeRow(red);
						biblioteka.snimiZanrove(BibliotekaMain.fZanrovi);
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ZanrForma zf = new ZanrForma(biblioteka, null);
				zf.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = zanrTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = tableModel.getValueAt(red, 1).toString();
                    Zanr zanr = null;
                    for(Zanr znr : biblioteka.getZanrovi()){
                        if(znr.getOznaka().equals(id)){
                            zanr = znr;
                        }
                    }
					if(zanr == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja zanra sa tim ID-jem", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						ZanrForma zf = new ZanrForma(biblioteka, zanr);
				        zf.setVisible(true);
					}
				}
			}
		});
	}
}
