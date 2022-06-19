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
import knjige.Iznajmljivanje;
import gui.forme.IznajmForma;

public class IznajmProzor extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable iznajmTabela;
	
	private Biblioteka biblioteka;
	
	public IznajmProzor(Biblioteka biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Iznajmljivanja");
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
		
		String[] zaglavlja = new String[] {"ID", "Zaposleni", "Clan", "Datum", "Primerak"};
		Object[][] sadrzaj = new Object[biblioteka.getIznajmljivanja().size()][zaglavlja.length];
		
		for(int i = 0; i < biblioteka.getIznajmljivanja().size(); i++) {
			Iznajmljivanje izn = biblioteka.getIznajmljivanja().get(i);
			sadrzaj[i][0] = izn.getId();
			sadrzaj[i][1] = izn.getZaposleni().getId();
			sadrzaj[i][2] = izn.getClan().getId();
			sadrzaj[i][3] = izn.getDatum().toString();
			sadrzaj[i][4] = izn.getPrimerak().getId();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		iznajmTabela = new JTable(tableModel);
		
		iznajmTabela.setRowSelectionAllowed(true);
		iznajmTabela.setColumnSelectionAllowed(false);
		iznajmTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		iznajmTabela.setDefaultEditor(Object.class, null);
		iznajmTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(iznajmTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = iznajmTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = tableModel.getValueAt(red, 0).toString();
                    Iznajmljivanje iznajm = null;
                    for(Iznajmljivanje izn : biblioteka.getIznajmljivanja()){
                        if(izn.getId().equals(id)){
                            iznajm = izn;
                        }
                    }
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete iznajmljivanje?", 
							iznajm.getId() + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						biblioteka.ukloniIznajmljivanje(iznajm);;
						tableModel.removeRow(red);
						biblioteka.snimiIznajmljivanja(BibliotekaMain.fIznajmljivanja);
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IznajmForma izf = new IznajmForma(biblioteka, null);
				izf.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = iznajmTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = tableModel.getValueAt(red, 0).toString();
                    Iznajmljivanje iznajm = null;
                    for(Iznajmljivanje izn : biblioteka.getIznajmljivanja()){
                        if(izn.getId().equals(id)){
                            iznajm = izn;
                        }
                    }
					if(iznajm == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja iznajmljivanja sa tim ID-jem", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						IznajmForma izf = new IznajmForma(biblioteka, iznajm);
				        izf.setVisible(true);
					}
				}
			}
		});
	}
}
