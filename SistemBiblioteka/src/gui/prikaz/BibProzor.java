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
import osobe.Administrator;
import osobe.Bibliotekar;
import osobe.Zaposleni;
import biblioteka.Biblioteka;
import gui.forme.BibForma;

public class BibProzor extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable bibTabela;
	
	private Biblioteka biblioteka;
	private Administrator LogovaniAdmin = null;
	private Bibliotekar LogovaniBibliotekar = null;
	
	public BibProzor(Biblioteka biblioteka, Zaposleni zaposleni) {
		this.biblioteka = biblioteka;
		if(zaposleni instanceof Administrator){
			this.LogovaniAdmin = (Administrator)zaposleni;
		}
		else{
			this.LogovaniBibliotekar = (Bibliotekar)zaposleni;
		}
		setTitle("Bibliotekari");
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
		if(LogovaniBibliotekar != null){
			mainToolbar.setVisible(false);
		}
		
		String[] zaglavlja = new String[] {"ID", "Ime", "Prezime", "JMBG", "Pol", "Adresa", "Plata", "Korisnicko ime", "Lozinka"};
		Object[][] sadrzaj = new Object[biblioteka.getBibliotekari().size()][zaglavlja.length];
		
		for(int i = 0; i < biblioteka.getBibliotekari().size(); i++) {
			Bibliotekar bib = biblioteka.getBibliotekari().get(i);
			sadrzaj[i][0] = bib.getId();
			sadrzaj[i][1] = bib.getIme();
			sadrzaj[i][2] = bib.getPrezime();
			sadrzaj[i][3] = bib.getJmbg();
			sadrzaj[i][4] = bib.getPol();
			sadrzaj[i][5] = bib.getAdresa();
			sadrzaj[i][6] = bib.getPlata();
			sadrzaj[i][7] = bib.getKorisnickoIme();
			sadrzaj[i][8] = bib.getLozinka();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		bibTabela = new JTable(tableModel);
		
		bibTabela.setRowSelectionAllowed(true);
		bibTabela.setColumnSelectionAllowed(false);
		bibTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bibTabela.setDefaultEditor(Object.class, null);
		bibTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(bibTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = bibTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnickoIme = tableModel.getValueAt(red, 7).toString();
                    Bibliotekar bibliotekar = null;
                    for(Bibliotekar bib : biblioteka.getBibliotekari()){
                        if(bib.getKorisnickoIme().equals(korisnickoIme)){
                            bibliotekar = bib;
                        }
                    }
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete bibliotekara?", 
							korisnickoIme + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						biblioteka.ukloniBibliotekara(bibliotekar);
						tableModel.removeRow(red);
						biblioteka.snimiZaposlene(BibliotekaMain.fZaposleni);
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BibForma bf = new BibForma(biblioteka, null);
				bf.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = bibTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnickoIme = tableModel.getValueAt(red, 7).toString();
					Bibliotekar bibliotekar = null;
                    for(Bibliotekar bib : biblioteka.getBibliotekari()){
                        if(bib.getKorisnickoIme().equals(korisnickoIme)){
                            bibliotekar = bib;
                        }
                    }
					if(bibliotekar == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja bibliotekara sa tim korisnickim imenom", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						BibForma bf = new BibForma(biblioteka, bibliotekar);
						bf.setVisible(true);
					}
				}
			}
		});
	}
}
