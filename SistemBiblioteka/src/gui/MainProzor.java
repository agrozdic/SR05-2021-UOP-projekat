package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import biblioteka.Biblioteka;
import gui.prikaz.*;
import main.BibliotekaMain;
import net.miginfocom.swing.MigLayout;
import osobe.Administrator;
import osobe.Bibliotekar;
import osobe.Zaposleni;

public class MainProzor extends JFrame {

	private JMenuBar mainMenu = new JMenuBar();
	private JMenu zaposleniMenu = new JMenu("Zaposleni");
	private JMenuItem adminItem = new JMenuItem("Administatori");
	private JMenuItem bibItem = new JMenuItem("Bibiliotekari");
	private JMenu BibliotekaMenu = new JMenu("Biblioteka");
	private JMenuItem knjigeItem = new JMenuItem("Knjige");
	private JMenuItem primerciItem = new JMenuItem("Primerci");
	private JMenuItem zanroviItem = new JMenuItem("Zanrovi");
	private JMenuItem iznajmItem = new JMenuItem("Iznajmljivanje");
	private JMenu clanoviMenu = new JMenu("Clanovi");
	private JMenuItem clanoviItem = new JMenuItem("Clanovi");
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnEdit = new JButton();

	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(10);
	private JLabel lblNaziv = new JLabel("Naziv");
	private JTextField txtNaziv = new JTextField(20);
	private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
	private JLabel lblTelefon = new JLabel("Telefon");
	private JTextField txtTelefon = new JTextField(10);
	private JLabel lblRadnoVreme = new JLabel("Radno vreme");
	private JTextField txtRadnoVreme = new JTextField(10);

	private JButton btnPotvrdi = new JButton("Potvrdi");
	
	private Biblioteka biblioteka;
	private Administrator LogovaniAdmin = null;
	private Bibliotekar LogovaniBibliotekar = null;
	
	public MainProzor(Biblioteka biblioteka, Zaposleni zaposleni) {
		this.biblioteka = biblioteka;
		this.biblioteka = biblioteka;
		if(zaposleni instanceof Administrator){
			this.LogovaniAdmin = (Administrator)zaposleni;
		}
		else{
			this.LogovaniBibliotekar = (Bibliotekar)zaposleni;
		}
		setTitle("Zaposleni: " + zaposleni.getKorisnickoIme());
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
		initGUI();
		initActions();
	}
	
	private void initMenu() {
		setJMenuBar(mainMenu);
		mainMenu.add(zaposleniMenu);
		zaposleniMenu.add(adminItem);
		zaposleniMenu.add(bibItem);
		mainMenu.add(clanoviMenu);
		clanoviMenu.add(clanoviItem);
		mainMenu.add(BibliotekaMenu);
		BibliotekaMenu.add(knjigeItem);
		BibliotekaMenu.add(primerciItem);
		BibliotekaMenu.add(zanroviItem);
		BibliotekaMenu.add(iznajmItem);
	}

	private void initGUI() {
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
		btnEdit.setIcon(editIcon);
		

		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][]20[]");
		setLayout(layout);
		mainToolbar.add(btnEdit);
		add(mainToolbar, BorderLayout.NORTH);
		add(lblID);
		add(txtID);
		add(lblNaziv);
		add(txtNaziv);
		add(lblAdresa);
		add(txtAdresa);
		add(lblTelefon);
		add(txtTelefon);
		add(lblRadnoVreme);
		add(txtRadnoVreme);
		add(new JLabel());
		add(btnPotvrdi);
		btnPotvrdi.setEnabled(false);
		btnPotvrdi.setVisible(false);
		txtID.setEditable(false);
		txtNaziv.setEditable(false);
		txtAdresa.setEditable(false);
		txtTelefon.setEditable(false);
		txtRadnoVreme.setEditable(false);
		
		if(LogovaniBibliotekar != null){
			mainToolbar.setVisible(false);
		}

		txtID.setText(biblioteka.getId());
		txtNaziv.setText(biblioteka.getNaziv());
		txtAdresa.setText(biblioteka.getAdresa());
		txtTelefon.setText(biblioteka.getTelefon());
		txtRadnoVreme.setText(biblioteka.getRadnoVreme());
	}
	
	private void initActions() {
		adminItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(LogovaniAdmin != null){
					AdminProzor ap = new AdminProzor(biblioteka, LogovaniAdmin);
					ap.setVisible(true);
				}
				else{
					AdminProzor ap = new AdminProzor(biblioteka, LogovaniBibliotekar);
					ap.setVisible(true);
				}
			}
		});

		bibItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(LogovaniAdmin != null){
					BibProzor bp = new BibProzor(biblioteka, LogovaniAdmin);
					bp.setVisible(true);
				}
				else{
					BibProzor bp = new BibProzor(biblioteka, LogovaniBibliotekar);
					bp.setVisible(true);
				}
			}
		});

		clanoviItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClanProzor cp = new ClanProzor(biblioteka);
				cp.setVisible(true);
			}
		});

		knjigeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KnjigeProzor kp = new KnjigeProzor(biblioteka);
				kp.setVisible(true);
			}
		});

		primerciItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrimProzor pp = new PrimProzor(biblioteka);
				pp.setVisible(true);
			}
		});

		zanroviItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ZanrProzor zp = new ZanrProzor(biblioteka);
				zp.setVisible(true);
			}
		});

		iznajmItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IznajmProzor ip = new IznajmProzor(biblioteka);
				ip.setVisible(true);
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtNaziv.setEditable(true);
				txtAdresa.setEditable(true);
				txtTelefon.setEditable(true);
				txtRadnoVreme.setEditable(true);
				btnPotvrdi.setVisible(true);
				btnPotvrdi.setEnabled(true);
			}
		});

		btnPotvrdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					btnEdit.setEnabled(false);
					String naziv = txtNaziv.getText();
					String adresa = txtAdresa.getText();
					String telefon = txtTelefon.getText().trim();
                    String radnoVreme = txtRadnoVreme.getText();

					biblioteka.setNaziv(naziv);
					biblioteka.setAdresa(adresa);
					biblioteka.setTelefon(telefon);
					biblioteka.setRadnoVreme(radnoVreme);
				}
				BibliotekaMain.snimiBiblioteke(BibliotekaMain.fBiblioteke);
				btnPotvrdi.setEnabled(false);
				btnPotvrdi.setVisible(false);
				txtNaziv.setEditable(false);
				txtAdresa.setEditable(false);
				txtTelefon.setEditable(false);
				txtRadnoVreme.setEditable(false);
				btnEdit.setEnabled(true);
			}
		});
	}

	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
        if(!txtID.getText().contains("BIBLIOTEKA-")){
            poruka += "- ID mora biti u formatu BIBLIOTEKA-xxx\n";
			ok = false;
        }

		if(txtNaziv.getText().trim().equals("")) {
			poruka += "- Unesite naziv\n";
			ok = false;
		}

		if(txtAdresa.getText().trim().equals("")) {
			poruka += "- Unesite adresu\n";
			ok = false;
		}

        if(txtTelefon.getText().trim().equals("")) {
			poruka += "- Unesite telefon\n";
			ok = false;
		}

        if(txtRadnoVreme.getText().trim().equals("")) {
			poruka += "- Unesite radno vreme\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
}
