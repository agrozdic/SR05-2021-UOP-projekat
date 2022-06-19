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
import osobe.Bibliotekar;
import osobe.Clan;
import biblioteka.Biblioteka;
import gui.forme.BibForma;
import gui.forme.ClanForma;

public class ClanProzor extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable clanTabela;
	
	private Biblioteka biblioteka;
	
	public ClanProzor(Biblioteka biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Clanovi");
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
		
		String[] zaglavlja = new String[] {"ID", "Ime", "Prezime", "JMBG", "Pol", "Adresa", "Broj clanske karte", "Tip Clanarine", "Datum poslednje uplate", "Broj uplacenih meseci", "Aktivnost"};
		Object[][] sadrzaj = new Object[biblioteka.getClanovi().size()][zaglavlja.length];
		
		for(int i = 0; i < biblioteka.getClanovi().size(); i++) {
			Clan clan = biblioteka.getClanovi().get(i);
			sadrzaj[i][0] = clan.getId();
			sadrzaj[i][1] = clan.getIme();
			sadrzaj[i][2] = clan.getPrezime();
			sadrzaj[i][3] = clan.getJmbg();
			sadrzaj[i][4] = clan.getPol();
			sadrzaj[i][5] = clan.getAdresa();
			sadrzaj[i][6] = clan.getBrojClanskeKarte();
			sadrzaj[i][7] = clan.getTipClanarine();
			sadrzaj[i][8] = clan.getDatumPoslednjeUplateClanarine();
			sadrzaj[i][9] = clan.getBrojUplacenihMeseci();
			sadrzaj[i][10] = clan.isAktivnost();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		clanTabela = new JTable(tableModel);
		
		clanTabela.setRowSelectionAllowed(true);
		clanTabela.setColumnSelectionAllowed(false);
		clanTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		clanTabela.setDefaultEditor(Object.class, null);
		clanTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(clanTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = clanTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = tableModel.getValueAt(red, 0).toString();
                    Clan clan = null;
                    for(Clan cl : biblioteka.getClanovi()){
                        if(cl.getId().equals(id)){
                            clan = cl;
                        }
                    }
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete clana?", 
							clan.getIme() + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						biblioteka.ukloniClana(clan);
						tableModel.removeRow(red);
						biblioteka.snimiClanove(BibliotekaMain.fClanovi);
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClanForma cf = new ClanForma(biblioteka, null);
				cf.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = clanTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = tableModel.getValueAt(red, 0).toString();
                    Clan clan = null;
                    for(Clan cl : biblioteka.getClanovi()){
                        if(cl.getId().equals(id)){
                            clan = cl;
                        }
                    }
					if(clan == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja clana sa tim ID-jem", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						ClanForma cf = new ClanForma(biblioteka, clan);
						cf.setVisible(true);
					}
				}
			}
		});
	}
}
