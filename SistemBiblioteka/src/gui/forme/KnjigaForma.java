package gui.forme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import main.BibliotekaMain;
import net.miginfocom.swing.MigLayout;
import biblioteka.Biblioteka;
import knjige.Knjiga;
import knjige.Zanr;

public class KnjigaForma extends JFrame {

	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(10);
	private JLabel lblNaslov = new JLabel("Naslov");
	private JTextField txtNaslov = new JTextField(20);
	private JLabel lblOrigNaslov = new JLabel("Originalni naslov");
	private JTextField txtOrigNaslov = new JTextField(20);
	private JLabel lblAutor = new JLabel("Autor");
	private JTextField txtAutor = new JTextField(20);
    private JLabel lblGodina = new JLabel("Godina");
	private JTextField txtGodina = new JTextField(10);
    private JLabel lblJezik = new JLabel("Jezik");
	private JTextField txtJezik = new JTextField(10);
    private JLabel lblOpis = new JLabel("Opis");
	private JTextField txtOpis = new JTextField(30);
    private JLabel lblZanr = new JLabel("Zanr");
	private JComboBox<String> cbZanr;
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCanel = new JButton("Cancel");
	
	private Biblioteka biblioteka;
	private Knjiga knjiga;
	
	public KnjigaForma(Biblioteka biblioteka, Knjiga knjiga) {
		this.biblioteka = biblioteka;
		this.knjiga = knjiga;
		if(knjiga == null) {
			setTitle("Dodavanje knjige");
		}else {
			setTitle("Izmena podataka - " + knjiga.getId());
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
		
		if(knjiga != null) {
			popuniPolja();
		}
		
        add(lblID);
        add(txtID);
		add(lblNaslov);
		add(txtNaslov);
		add(lblOrigNaslov);
		add(txtOrigNaslov);
		add(lblAutor);
		add(txtAutor);
        add(lblGodina);
		add(txtGodina);
        add(lblJezik);
        add(txtJezik);
        add(lblOpis);
        add(txtOpis);
        add(lblZanr);
        String zanrArr[] = new String[biblioteka.getZanrovi().size()];
        for(int i = 0; i < biblioteka.getZanrovi().size(); i++){
            zanrArr[i] = biblioteka.getZanrovi().get(i).getNaziv();
        }
        cbZanr = new JComboBox<String>(zanrArr);
        add(cbZanr);
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
					String naslov = txtNaslov.getText();
					String origNaslov = txtOrigNaslov.getText();
					String autor = txtAutor.getText();
                    int godina = Integer.parseInt(txtGodina.getText());
                    String jezik = txtJezik.getText();
					String opis = txtOpis.getText();
                    String zanrStr = (String)cbZanr.getSelectedItem();

                    Zanr zanr = null;
                    for(Zanr znr : biblioteka.getZanrovi()){
                        if(znr.getNaziv().equals(zanrStr)){
                            zanr = znr;
                        }
                    }
					
					if(knjiga == null) { // DODAVANJE:
						Knjiga nova = new Knjiga(id, naslov, origNaslov, autor, godina, jezik, opis, zanr);
						biblioteka.dodajKnjigu(nova);
					}else { // IZMENA:
						knjiga.setNaslov(naslov);
                        knjiga.setOriginalniNaslov(origNaslov);
                        knjiga.setPisac(autor);
                        knjiga.setGodinaObjave(godina);
                        knjiga.setJezikOriginala(jezik);
                        knjiga.setOpis(opis);
                        knjiga.setZanr(zanr);
					}
					biblioteka.snimiKnjige(BibliotekaMain.fKnjige);
					KnjigaForma.this.dispose();
					KnjigaForma.this.setVisible(false);
				}
			}
		});
	}
	
	private void popuniPolja() {
        txtID.setEditable(false);
        txtID.setText(knjiga.getId());
		txtNaslov.setText(knjiga.getNaslov());
		txtOrigNaslov.setText(knjiga.getOriginalniNaslov());
        txtAutor.setText(knjiga.getPisac());
        txtGodina.setText(knjiga.getGodinaObjave() + "");
        txtJezik.setText(knjiga.getJezikOriginala());
        txtOpis.setText(knjiga.getOpis());
		//cbZanr.getModel().setSelectedItem(knjiga.getZanr().getNaziv());
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
        if(!txtID.getText().contains("KNJ")){
            poruka += "- ID mora biti u formatu KNJxx\n";
			ok = false;
        }

		if(txtNaslov.getText().trim().equals("")) {
			poruka += "- Unesite naslov\n";
			ok = false;
		}

		if(txtOrigNaslov.getText().trim().equals("")) {
			poruka += "- Unesite originalni naslov\n";
			ok = false;
		}

        if(txtAutor.getText().trim().equals("")) {
			poruka += "- Unesite autora\n";
			ok = false;
		}

        if(txtGodina.getText().trim().equals("")) {
			poruka += "- Unesite godinu\n";
			ok = false;
		}

		if(txtJezik.getText().trim().equals("")) {
			poruka += "- Unesite jezik\n";
			ok = false;
		}

        if(txtOpis.getText().trim().equals("")) {
			poruka += "- Unesite opis\n";
			ok = false;
		}
        
        else if(knjiga == null){
            String id = txtID.getText().trim();
			Knjiga knjiga = null;
            for(Knjiga knj : biblioteka.getKnjige()){
                if(knj.getId().equals(id)){
                    knjiga = knj;
                }
            }
			if(knjiga != null) {
				poruka += "- Knjiga sa tim ID-jem vec postoji\n";
				ok = false;
			}
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
}
