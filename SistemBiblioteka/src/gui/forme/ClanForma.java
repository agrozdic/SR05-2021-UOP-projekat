package gui.forme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import main.BibliotekaMain;
import net.miginfocom.swing.MigLayout;
import osobe.Pol;
import osobe.TipClanarine;
import osobe.Clan;
import biblioteka.Biblioteka;

public class ClanForma extends JFrame {

	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(10);
	private JLabel lblIme = new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	private JLabel lblJMBG = new JLabel("JMBG");
	private JTextField txtJMBG = new JTextField(13);
    private JLabel lblPol = new JLabel("Pol");
	private JComboBox<Pol> cbPol = new JComboBox<Pol>(Pol.values());
    private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
    private JLabel lblBrClKarte = new JLabel("Broj clanske karte");
	private JTextField txtBrClKarte = new JTextField(10);
    private JLabel lblTipCl = new JLabel("Tip clanarine");
	private JComboBox<TipClanarine> cbTipCl = new JComboBox<TipClanarine>(TipClanarine.values());
	private JLabel lblDatumPoslUpl = new JLabel("Datum poslednje uplate");
	private JDateChooser dateChooser = new JDateChooser();
	private JLabel lblBrUplMeseci = new JLabel("Broj uplacenih meseci");
	private JTextField txtBrUplMeseci = new JTextField(20);
	private JLabel lblAktivnost = new JLabel("Aktivnost");
	private JCheckBox cbTrue = new JCheckBox("True");
	private JCheckBox cbFalse = new JCheckBox("False");
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCanel = new JButton("Cancel");
	
	private Biblioteka biblioteka;
	private Clan clan;
	
	public ClanForma(Biblioteka biblioteka, Clan clan) {
		this.biblioteka = biblioteka;
		this.clan = clan;
		if(clan == null) {
			setTitle("Dodavanje clana");
		}else {
			setTitle("Izmena podataka - " + clan.getId());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		//initActions();
		setResizable(false);
		pack();
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][]20[]");
		setLayout(layout);
		
		if(clan != null) {
			//popuniPolja();
		}
		
        add(lblID);
        add(txtID);
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblJMBG);
		add(txtJMBG);
        add(lblPol);
		add(cbPol);
        add(lblAdresa);
        add(txtAdresa);
        add(lblBrClKarte);
        add(txtBrClKarte);
        add(lblTipCl);
        add(cbTipCl);
		add(lblDatumPoslUpl);
		add(dateChooser);
		add(lblBrUplMeseci);
		add(txtBrUplMeseci);
		add(lblAktivnost);
		add(cbTrue);
		add(cbFalse);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCanel);
	}
	
	// private void initActions() {
	// 	btnOk.addActionListener(new ActionListener() {
	// 		@Override
	// 		public void actionPerformed(ActionEvent e) {
	// 			if(validacija()) {
	// 				String id = txtID.getText().trim();
	// 				String ime = txtIme.getText().trim();
	// 				String prezime = txtPrezime.getText().trim();
	// 				String jmbg = txtJMBG.getText().trim();
    //                 Pol pol = (Pol)cbPol.getSelectedItem();
    //                 String adresa = txtAdresa.getText();
    //                 String plata = txtPlata.getText();
	// 				String korisnickoIme = txtKorisnickoIme.getText().trim();
	// 				String lozinka = new String(pfLozinka.getPassword()).trim();
					
	// 				if(bibliotekar == null) { // DODAVANJE:
	// 					Bibliotekar novi = new Bibliotekar(id, ime, prezime, jmbg, adresa, pol, Double.parseDouble(plata), korisnickoIme, lozinka);
	// 					biblioteka.dodajBibliotekara(novi);
	// 				}else { // IZMENA:
	// 					bibliotekar.setIme(ime);
	// 					bibliotekar.setPrezime(prezime);
	// 					bibliotekar.setJmbg(jmbg);
	// 					bibliotekar.setPol(pol);
    //                     bibliotekar.setAdresa(adresa);
    //                     bibliotekar.setPlata(Double.parseDouble(plata));
    //                     bibliotekar.setKorisnickoIme(korisnickoIme);
	// 					bibliotekar.setLozinka(lozinka);
	// 				}
	// 				biblioteka.snimiZaposlene(BibliotekaMain.fZaposleni);
	// 				BibForma.this.dispose();
	// 				BibForma.this.setVisible(false);
	// 			}
	// 		}
	// 	});
	// }
	
	// private void popuniPolja() {
    //     txtID.setEditable(false);
    //     txtID.setText(bibliotekar.getId());
	// 	txtIme.setText(bibliotekar.getIme());
	// 	txtPrezime.setText(bibliotekar.getPrezime());
    //     txtJMBG.setText(bibliotekar.getJmbg());
    //     cbPol.setSelectedItem(bibliotekar.getPol());
    //     txtAdresa.setText(bibliotekar.getAdresa());
    //     txtPlata.setText(bibliotekar.getPlata() + "");
	// 	txtKorisnickoIme.setText(bibliotekar.getKorisnickoIme());
	// 	pfLozinka.setText(bibliotekar.getLozinka());
		
	// }
	
	// private boolean validacija() {
	// 	boolean ok = true;
	// 	String poruka = "Molimo popravite sledece greske u unosu:\n";
		
    //     if(!txtID.getText().contains("BIB")){
    //         poruka += "- ID mora biti u formatu Bibxx\n";
	// 		ok = false;
    //     }

	// 	if(txtIme.getText().trim().equals("")) {
	// 		poruka += "- Unesite ime\n";
	// 		ok = false;
	// 	}

	// 	if(txtPrezime.getText().trim().equals("")) {
	// 		poruka += "- Unesite prezime\n";
	// 		ok = false;
	// 	}

    //     if(txtJMBG.getText().trim().equals("")) {
	// 		poruka += "- Unesite JMBG\n";
	// 		ok = false;
	// 	}

    //     if(txtAdresa.getText().trim().equals("")) {
	// 		poruka += "- Unesite adresu\n";
	// 		ok = false;
	// 	}

    //     if(txtPlata.getText().trim().equals("")) {
	// 		poruka += "- Unesite platu\n";
	// 		ok = false;
	// 	}

	// 	if(txtKorisnickoIme.getText().trim().equals("")) {
	// 		poruka += "- Unesite korisnicko ime\n";
	// 		ok = false;
	// 	}
    //     else if(bibliotekar == null){
    //         String id = txtID.getText().trim();
	// 		String korisnickoIme = txtKorisnickoIme.getText().trim();
	// 		Bibliotekar pronadjeni = null;
    //         for(Bibliotekar bib : biblioteka.getBibliotekari()){
    //             if((bib.getId().equals(id)) || (bib.getKorisnickoIme().equals(korisnickoIme))){
    //                 pronadjeni = bib;
    //             }
    //         }
	// 		if(pronadjeni != null) {
	// 			poruka += "- Bibliotekar sa tim ID-jem ili korisnickim imenom vec postoji\n";
	// 			ok = false;
	// 		}
	// 	}
		
	// 	String lozinka = new String(pfLozinka.getPassword()).trim();
	// 	if(lozinka.equals("")) {
	// 		poruka += "- Unesite lozinku\n";
	// 		ok = false;
	// 	}
		
	// 	if(ok == false) {
	// 		JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
	// 	}
		
	// 	return ok;
	// }
}
