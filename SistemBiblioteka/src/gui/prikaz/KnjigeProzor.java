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
import gui.forme.KnjigaForma;
import knjige.Knjiga;

public class KnjigeProzor extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable knjigeTabela;
	
	private Biblioteka biblioteka;
	
	public KnjigeProzor(Biblioteka biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Knjige");
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
		
		String[] zaglavlja = new String[] {"ID", "Naslov", "Originalni naslov", "Autor", "Godina", "Jezik", "Opis", "Zanr"};
		Object[][] sadrzaj = new Object[biblioteka.getKnjige().size()][zaglavlja.length];
		
		for(int i = 0; i < biblioteka.getKnjige().size(); i++) {
			Knjiga knjiga = biblioteka.getKnjige().get(i);
			sadrzaj[i][0] = knjiga.getId();
			sadrzaj[i][1] = knjiga.getNaslov();
			sadrzaj[i][2] = knjiga.getOriginalniNaslov();
			sadrzaj[i][3] = knjiga.getPisac();
			sadrzaj[i][4] = knjiga.getGodinaObjave();
			sadrzaj[i][5] = knjiga.getJezikOriginala();
			sadrzaj[i][6] = knjiga.getOpis();
			sadrzaj[i][7] = knjiga.getZanr().getNaziv();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		knjigeTabela = new JTable(tableModel);
		
		knjigeTabela.setRowSelectionAllowed(true);
		knjigeTabela.setColumnSelectionAllowed(false);
		knjigeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		knjigeTabela.setDefaultEditor(Object.class, null);
		knjigeTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(knjigeTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = knjigeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = tableModel.getValueAt(red, 0).toString();
                    Knjiga knjiga = null;
                    for(Knjiga knj : biblioteka.getKnjige()){
                        if(knj.getId().equals(id)){
                            knjiga = knj;
                        }
                    }
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete knjigu?", 
							knjiga.getId() + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						biblioteka.ukloniKnjigu(knjiga);
						tableModel.removeRow(red);
						biblioteka.snimiKnjige(BibliotekaMain.fKnjige);
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KnjigaForma kf = new KnjigaForma(biblioteka, null);
				kf.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = knjigeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = tableModel.getValueAt(red, 0).toString();
                    Knjiga knjiga = null;
                    for(Knjiga knj : biblioteka.getKnjige()){
                        if(knj.getId().equals(id)){
                            knjiga = knj;
                        }
                    }
					if(knjiga == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja knjige sa tim ID-jem", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						KnjigaForma kf = new KnjigaForma(biblioteka, knjiga);
				        kf.setVisible(true);
					}
				}
			}
		});
	}
}
