package gui.forme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
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
	private JDateChooser dcDatumPoslUpl = new JDateChooser();
	private JLabel lblBrUplMeseci = new JLabel("Broj uplacenih meseci");
	private JTextField txtBrUplMeseci = new JTextField(10);
	private JLabel lblAktivnost = new JLabel("Aktivnost");
	private JRadioButton rbTrue = new JRadioButton("True", true);
	private JRadioButton rbFalse = new JRadioButton("False", false);
	private ButtonGroup rgAktivnost = new ButtonGroup();
	
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
		initActions();
		setResizable(false);
		pack();
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][]20[]");
		setLayout(layout);
		
		if(clan != null) {
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
        add(lblBrClKarte);
        add(txtBrClKarte);
        add(lblTipCl);
        add(cbTipCl);
		add(lblDatumPoslUpl);
		add(dcDatumPoslUpl);
		Date date;
		try {
			date = new SimpleDateFormat("yyyy MMM dd").parse("2022 Jan 01");
			dcDatumPoslUpl.setDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		add(lblBrUplMeseci);
		add(txtBrUplMeseci);
		add(lblAktivnost);
		add(rbTrue);
		add(rbFalse);
		rbTrue.setActionCommand("true");
		rbFalse.setActionCommand("false");
		rgAktivnost.add(rbTrue);
		rgAktivnost.add(rbFalse);
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
                    String brClKarte = txtBrClKarte.getText();
					TipClanarine tipClanarine = (TipClanarine)cbTipCl.getSelectedItem();
					LocalDate datumPoslUpl = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(dcDatumPoslUpl.getDate()));
 					int brUplMeseci = Integer.parseInt(txtBrUplMeseci.getText().trim());
					Boolean aktivnost = Boolean.parseBoolean(rgAktivnost.getSelection().getActionCommand());
					
					if(clan == null) { // DODAVANJE:
						Clan novi = new Clan(id, ime, prezime, jmbg, adresa, pol, brClKarte, tipClanarine, datumPoslUpl, brUplMeseci, aktivnost);
						biblioteka.dodajClana(novi);;
					}else { // IZMENA:
						clan.setIme(ime);
						clan.setPrezime(prezime);
						clan.setJmbg(jmbg);
						clan.setPol(pol);
                        clan.setAdresa(adresa);
						clan.setBrojClanskeKarte(brClKarte);
						clan.setTipClanarine(tipClanarine);
						clan.setDatumPoslednjeUplateClanarine(datumPoslUpl);
						clan.setBrojUplacenihMeseci(brUplMeseci);
						clan.setAktivnost(aktivnost);
					}
					biblioteka.snimiClanove(BibliotekaMain.fClanovi);
					ClanForma.this.dispose();
					ClanForma.this.setVisible(false);
				}
			}
		});
	}
	
	private void popuniPolja() {
        txtID.setEditable(false);
        txtID.setText(clan.getId());
		txtIme.setText(clan.getIme());
		txtPrezime.setText(clan.getPrezime());
        txtJMBG.setText(clan.getJmbg());
        cbPol.setSelectedItem(clan.getPol());
        txtAdresa.setText(clan.getAdresa());
        txtBrClKarte.setText(clan.getBrojClanskeKarte());
		cbTipCl.setSelectedItem(clan.getTipClanarine());
		dcDatumPoslUpl.setDate(java.sql.Date.valueOf(clan.getDatumPoslednjeUplateClanarine()));
		txtBrUplMeseci.setText(clan.getBrojUplacenihMeseci() + "");
		if(clan.isAktivnost() == true){
			rbTrue.setSelected(true);
			rbFalse.setSelected(false);
		}
		else{
			rbTrue.setSelected(false);
			rbFalse.setSelected(true);
		}
		
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
        if(!txtID.getText().contains("CLA")){
            poruka += "- ID mora biti u formatu CLAxx\n";
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

        if(!txtBrClKarte.getText().contains("BCK")) {
			poruka += "- Broj clanske karte mora biti u formatu BCKxxx\n";
			ok = false;
		}

		if(txtBrUplMeseci.getText().trim().equals("")) {
			poruka += "- Unesite broj uplacenih meseci\n";
			ok = false;
		}
        else if(clan == null){
            String id = txtID.getText().trim();
			String br = txtBrClKarte.getText().trim();
			Clan pronadjeni = null;
            for(Clan cl : biblioteka.getClanovi()){
                if((cl.getId().equals(id)) || (cl.getBrojClanskeKarte().equals(br))){
                    pronadjeni = cl;
                }
            }
			if(pronadjeni != null) {
				poruka += "- Clan sa tim ID-jem ili brojem clanske karte vec postoji\n";
				ok = false;
			}
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
}
