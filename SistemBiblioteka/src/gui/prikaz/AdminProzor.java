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

//import gui.formeZaDodavanjeIIzmenu.ProdavciForma;
import main.BibliotekaMain;
import osobe.Administrator;
import biblioteka.Biblioteka;
import gui.forme.AdminForma;

public class AdminProzor extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable adminTabela;
	
	private Biblioteka biblioteka;
	
	public AdminProzor(Biblioteka biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Administratori");
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
		
		String[] zaglavlja = new String[] {"ID", "Ime", "Prezime", "JMBG", "Pol", "Adresa", "Plata", "Korisnicko ime", "Lozinka"};
		Object[][] sadrzaj = new Object[biblioteka.getAdministratori().size()][zaglavlja.length];
		
		for(int i = 0; i < biblioteka.getAdministratori().size(); i++) {
			Administrator adm = biblioteka.getAdministratori().get(i);
			sadrzaj[i][0] = adm.getId();
			sadrzaj[i][1] = adm.getIme();
			sadrzaj[i][2] = adm.getPrezime();
			sadrzaj[i][3] = adm.getJmbg();
			sadrzaj[i][4] = adm.getPol();
			sadrzaj[i][5] = adm.getAdresa();
			sadrzaj[i][6] = adm.getPlata();
			sadrzaj[i][7] = adm.getKorisnickoIme();
			sadrzaj[i][8] = adm.getLozinka();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		adminTabela = new JTable(tableModel);
		
		adminTabela.setRowSelectionAllowed(true);
		adminTabela.setColumnSelectionAllowed(false);
		adminTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		adminTabela.setDefaultEditor(Object.class, null);
		adminTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(adminTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = adminTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnickoIme = tableModel.getValueAt(red, 7).toString();
                    Administrator admin = null;
                    for(Administrator adm : biblioteka.getAdministratori()){
                        if(adm.getKorisnickoIme().equals(korisnickoIme)){
                            admin = adm;
                        }
                    }
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete administratora?", 
							korisnickoIme + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						biblioteka.ukloniAdministratora(admin);
						tableModel.removeRow(red);
						biblioteka.snimiZaposlene(BibliotekaMain.fZaposleni);
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminForma af = new AdminForma(biblioteka, null);
				af.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = adminTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnickoIme = tableModel.getValueAt(red, 7).toString();
					Administrator admin = null;
                    for(Administrator adm : biblioteka.getAdministratori()){
                        if(adm.getKorisnickoIme().equals(korisnickoIme)){
                            admin = adm;
                        }
                    }
					if(admin == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja prodavca sa tim korisnickim imenom", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						AdminForma af = new AdminForma(biblioteka, admin);
						af.setVisible(true);
					}
				}
			}
		});
	}
}
