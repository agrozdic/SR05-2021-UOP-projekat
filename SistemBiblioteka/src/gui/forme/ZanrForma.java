package gui.forme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import main.BibliotekaMain;
import net.miginfocom.swing.MigLayout;
import biblioteka.Biblioteka;
import knjige.Zanr;

public class ZanrForma extends JFrame {

	private JLabel lblNaziv = new JLabel("Naziv");
	private JTextField txtNaziv = new JTextField(15);
	private JLabel lblID = new JLabel("Oznaka");
	private JTextField txtID = new JTextField(10);
	private JLabel lblOpis = new JLabel("Opis");
	private JTextField txtOpis = new JTextField(30);
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCanel = new JButton("Cancel");
	
	private Biblioteka biblioteka;
	private Zanr zanr;
	
	public ZanrForma(Biblioteka biblioteka, Zanr zanr) {
		this.biblioteka = biblioteka;
		this.zanr = zanr;
		if(zanr == null) {
			setTitle("Dodavanje zanra");
		}else {
			setTitle("Izmena podataka - " + zanr.getNaziv());
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
		
		if(zanr != null) {
			popuniPolja();
		}
		
        add(lblNaziv);
        add(txtNaziv);
        add(lblID);
        add(txtID);
		add(lblOpis);
        add(txtOpis);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCanel);
	}
	
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
                    String naziv = txtNaziv.getText();
					String id = txtID.getText().trim();
					String opis = txtOpis.getText();
					
					if(zanr == null) { // DODAVANJE:
						Zanr novi = new Zanr(naziv, id, opis);
						biblioteka.dodajZanr(novi);;
					}else { // IZMENA:
						zanr.setNaziv(naziv);
                        zanr.setOznaka(id);
                        zanr.setOpis(opis);
					}
					biblioteka.snimiZanrove(BibliotekaMain.fZanrovi);
					ZanrForma.this.dispose();
					ZanrForma.this.setVisible(false);
				}
			}
		});
	}
	
	private void popuniPolja() {
        txtID.setEditable(false);
        txtID.setText(zanr.getOznaka());
		txtNaziv.setText(zanr.getNaziv());
        txtOpis.setText(zanr.getOpis());
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
        if(!txtID.getText().contains("ZNR")){
            poruka += "- ID mora biti u formatu ZNRxx\n";
			ok = false;
        }

		if(txtNaziv.getText().trim().equals("")) {
			poruka += "- Unesite naziv\n";
			ok = false;
		}

		if(txtOpis.getText().trim().equals("")) {
			poruka += "- Unesite opis\n";
			ok = false;
		}
        
        else if(zanr == null){
            String id = txtID.getText().trim();
			Zanr zanr = null;
            for(Zanr znr : biblioteka.getZanrovi()){
                if(znr.getOznaka().equals(id)){
                    zanr = znr;
                }
            }
			if(zanr != null) {
				poruka += "- Zanr sa tim ID-jem vec postoji\n";
				ok = false;
			}
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
}
