package gui.forme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.BibliotekaMain;
import net.miginfocom.swing.MigLayout;
import osobe.Pol;
import osobe.Bibliotekar;
import biblioteka.Biblioteka;

public class BibForma extends JFrame {

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
    private JLabel lblPlata = new JLabel("Plata");
	private JTextField txtPlata = new JTextField(10);
	private JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	private JLabel lblLozinka = new JLabel("Lozinka");
	private JPasswordField pfLozinka = new JPasswordField(20);
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCanel = new JButton("Cancel");
	
	private Biblioteka biblioteka;
	private Bibliotekar bibliotekar;
	
	public BibForma(Biblioteka biblioteka, Bibliotekar bibliotekar) {
		this.biblioteka = biblioteka;
		this.bibliotekar = bibliotekar;
		if(bibliotekar == null) {
			setTitle("Dodavanje bibliotekara");
		}else {
			setTitle("Izmena podataka - " + bibliotekar.getKorisnickoIme());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][]20[]");
		setLayout(layout);
		
		if(bibliotekar != null) {
			popuniPolja();
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
        add(lblPlata);
        add(txtPlata);
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblLozinka);
		add(pfLozinka);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCanel);
	}
	
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					String id = txtID.getText().trim();
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					String jmbg = txtJMBG.getText().trim();
                    Pol pol = (Pol)cbPol.getSelectedItem();
                    String adresa = txtAdresa.getText();
                    String plata = txtPlata.getText();
					String korisnickoIme = txtKorisnickoIme.getText().trim();
					String lozinka = new String(pfLozinka.getPassword()).trim();
					
					if(bibliotekar == null) { // DODAVANJE:
						Bibliotekar novi = new Bibliotekar(id, ime, prezime, jmbg, adresa, pol, Double.parseDouble(plata), korisnickoIme, lozinka);
						biblioteka.dodajBibliotekara(novi);
					}else { // IZMENA:
						bibliotekar.setIme(ime);
						bibliotekar.setPrezime(prezime);
						bibliotekar.setJmbg(jmbg);
						bibliotekar.setPol(pol);
                        bibliotekar.setAdresa(adresa);
                        bibliotekar.setPlata(Double.parseDouble(plata));
                        bibliotekar.setKorisnickoIme(korisnickoIme);
						bibliotekar.setLozinka(lozinka);
					}
					biblioteka.snimiZaposlene(BibliotekaMain.fZaposleni);
					BibForma.this.dispose();
					BibForma.this.setVisible(false);
				}
			}
		});
		btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BibForma.this.dispose();
				BibForma.this.setVisible(false);
			}
		});
	}
	
	private void popuniPolja() {
        txtID.setEditable(false);
        txtID.setText(bibliotekar.getId());
		txtIme.setText(bibliotekar.getIme());
		txtPrezime.setText(bibliotekar.getPrezime());
        txtJMBG.setText(bibliotekar.getJmbg());
        cbPol.setSelectedItem(bibliotekar.getPol());
        txtAdresa.setText(bibliotekar.getAdresa());
        txtPlata.setText(bibliotekar.getPlata() + "");
		txtKorisnickoIme.setText(bibliotekar.getKorisnickoIme());
		pfLozinka.setText(bibliotekar.getLozinka());
		
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
        if(!txtID.getText().contains("BIB")){
            poruka += "- ID mora biti u formatu BIBxx\n";
			ok = false;
        }

		if(txtIme.getText().trim().equals("")) {
			poruka += "- Unesite ime\n";
			ok = false;
		}

		if(txtPrezime.getText().trim().equals("")) {
			poruka += "- Unesite prezime\n";
			ok = false;
		}

        if(txtJMBG.getText().trim().equals("")) {
			poruka += "- Unesite JMBG\n";
			ok = false;
		}

        if(txtAdresa.getText().trim().equals("")) {
			poruka += "- Unesite adresu\n";
			ok = false;
		}

        if(txtPlata.getText().trim().equals("")) {
			poruka += "- Unesite platu\n";
			ok = false;
		}

		if(txtKorisnickoIme.getText().trim().equals("")) {
			poruka += "- Unesite korisnicko ime\n";
			ok = false;
		}
        else if(bibliotekar == null){
            String id = txtID.getText().trim();
			String korisnickoIme = txtKorisnickoIme.getText().trim();
			Bibliotekar pronadjeni = null;
            for(Bibliotekar bib : biblioteka.getBibliotekari()){
                if((bib.getId().equals(id)) || (bib.getKorisnickoIme().equals(korisnickoIme))){
                    pronadjeni = bib;
                }
            }
			if(pronadjeni != null) {
				poruka += "- Bibliotekar sa tim ID-jem ili korisnickim imenom vec postoji\n";
				ok = false;
			}
		}
		
		String lozinka = new String(pfLozinka.getPassword()).trim();
		if(lozinka.equals("")) {
			poruka += "- Unesite lozinku\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
}
