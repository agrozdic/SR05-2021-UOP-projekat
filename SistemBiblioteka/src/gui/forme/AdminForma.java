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
import osobe.Administrator;
import biblioteka.Biblioteka;

public class AdminForma extends JFrame {

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
	private Administrator admin;
	
	public AdminForma(Biblioteka biblioteka, Administrator admin) {
		this.biblioteka = biblioteka;
		this.admin = admin;
		if(admin == null) {
			setTitle("Dodavanje administratora");
		}else {
			setTitle("Izmena podataka - " + admin.getKorisnickoIme());
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
		
		if(admin != null) {
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
					
					if(admin == null) { // DODAVANJE:
						Administrator novi = new Administrator(id, ime, prezime, jmbg, adresa, pol, Double.parseDouble(plata), korisnickoIme, lozinka);
						biblioteka.dodajAdministratora(novi);
					}else { // IZMENA:
						admin.setIme(ime);
						admin.setPrezime(prezime);
						admin.setJmbg(jmbg);
						admin.setPol(pol);
                        admin.setAdresa(adresa);
                        admin.setPlata(Double.parseDouble(plata));
                        admin.setKorisnickoIme(korisnickoIme);
						admin.setLozinka(lozinka);
					}
					biblioteka.snimiZaposlene(BibliotekaMain.fZaposleni);
					AdminForma.this.dispose();
					AdminForma.this.setVisible(false);
				}
			}
		});
		btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminForma.this.dispose();
				AdminForma.this.setVisible(false);
			}
		});
	}
	
	private void popuniPolja() {
        txtID.setEditable(false);
        txtID.setText(admin.getId());
		txtIme.setText(admin.getIme());
		txtPrezime.setText(admin.getPrezime());
        txtJMBG.setText(admin.getJmbg());
        cbPol.setSelectedItem(admin.getPol());
        txtAdresa.setText(admin.getAdresa());
        txtPlata.setText(admin.getPlata() + "");
		txtKorisnickoIme.setText(admin.getKorisnickoIme());
		pfLozinka.setText(admin.getLozinka());
		
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
        if(!txtID.getText().contains("ADM")){
            poruka += "- ID mora biti u formatu ADMxx\n";
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
        else if(admin == null){
            String id = txtID.getText().trim();
			String korisnickoIme = txtKorisnickoIme.getText().trim();
			Administrator pronadjeni = null;
            for(Administrator adm : biblioteka.getAdministratori()){
                if((adm.getId().equals(id)) || (adm.getKorisnickoIme().equals(korisnickoIme))){
                    pronadjeni = adm;
                }
            }
			if(pronadjeni != null) {
				poruka += "- Administrator sa tim ID-jem ili korisnickim imenom vec postoji\n";
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
