package gui.forme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
//import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import main.BibliotekaMain;
import net.miginfocom.swing.MigLayout;
import osobe.Clan;
import osobe.Zaposleni;
import biblioteka.Biblioteka;
import knjige.Iznajmljivanje;
import knjige.Primerak;

public class IznajmFormaMultiple extends JFrame {

	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(10);
	private JLabel lblZap = new JLabel("Zaposleni");
	private JComboBox<String> cbZap;
    private JLabel lblClan = new JLabel("Clan");
	private JComboBox<String> cbClan;
    private JLabel lblDatum = new JLabel("Datum");
	private JDateChooser dcDatum = new JDateChooser();
    private JLabel lblPrim = new JLabel("Primerci");
	private List<JCheckBox> cblist = new ArrayList<>();
	private JComboBox<String> cbPrim;
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCanel = new JButton("Cancel");
	
	private Biblioteka biblioteka;
	private Iznajmljivanje iznajmljivanje;
	
	public IznajmFormaMultiple(Biblioteka biblioteka, Iznajmljivanje iznajmljivanje) {
		this.biblioteka = biblioteka;
		this.iznajmljivanje = iznajmljivanje;
		if(iznajmljivanje == null) {
			setTitle("Dodavanje iznajmljivanja");
			for(Primerak prim : biblioteka.getPrimerci()) {
				JCheckBox jcb = new JCheckBox(prim.getId());
				cblist.add(jcb);
				//add(jcb);
			}
			
		}else {
			setTitle("Izmena podataka - " + iznajmljivanje.getId());
			String primerciArr[] = new String[biblioteka.getPrimerci().size()];
        	for(int i = 0; i < biblioteka.getPrimerci().size(); i++){
            primerciArr[i] = biblioteka.getPrimerci().get(i).getId();
        }
        cbPrim = new JComboBox<String>(primerciArr);
		}

		String zaposleniArr[] = new String[biblioteka.getZaposleni().size()];
        for(int i = 0; i < biblioteka.getZaposleni().size(); i++){
            zaposleniArr[i] = biblioteka.getZaposleni().get(i).getId();
        }
        cbZap = new JComboBox<String>(zaposleniArr);

		String clanoviArr[] = new String[biblioteka.getClanovi().size()];
        for(int i = 0; i < biblioteka.getClanovi().size(); i++){
            clanoviArr[i] = biblioteka.getClanovi().get(i).getId();
        }
        cbClan = new JComboBox<String>(clanoviArr);

        

		// String primerciArr[] = new String[biblioteka.getPrimerci().size()];
        // for(int i = 0; i < biblioteka.getPrimerci().size(); i++){
        //     primerciArr[i] = biblioteka.getPrimerci().get(i).getId();
        // }
        // cbPrim = new JComboBox<String>(primerciArr);

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
		
		if(iznajmljivanje != null) {
			popuniPolja();
		}
		
        add(lblID);
        add(txtID);
        
		add(lblZap);
		add(cbZap);
        
		add(lblClan);
		add(cbClan);
        java.util.Date date;
		try {
			date = new SimpleDateFormat("yyyy MMM dd").parse("2022 Jan 01");
			dcDatum.setDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        add(lblDatum);
		add(dcDatum);
        
		add(lblPrim);

		if(iznajmljivanje == null){
			add(new JLabel());
			JPanel cbPanel = new JPanel();
			cbPanel.setLayout(layout);
			cbPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			//test.setPreferredSize(new Dimension( 2000,2000));
			JScrollPane scrollFrame = new JScrollPane(cbPanel);
			cbPanel.setAutoscrolls(true);
			scrollFrame.setPreferredSize(null);
			scrollFrame.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			for(JCheckBox jcb : cblist){
				cbPanel.add(jcb);
			}
			add(scrollFrame);
		}
		else{
			add(cbPrim);
		}

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
					String zapID = (String)cbZap.getSelectedItem();
					String clanID = (String)cbClan.getSelectedItem();
                    LocalDate datum = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(dcDatum.getDate()));
					String primID = "";
					if(iznajmljivanje != null){
						primID = (String)cbPrim.getSelectedItem();
					}

                    Zaposleni zaposleni = null;
                    for(Zaposleni zap : biblioteka.getZaposleni()){
                        if(zap.getId().equals(zapID)){
                            zaposleni = zap;
                        }
                    }
					
                    Clan clan = null;
                    for(Clan cl : biblioteka.getClanovi()){
                        if(cl.getId().equals(clanID)){
                            clan = cl;
                        }
                    }

					if(iznajmljivanje == null) { // DODAVANJE:
						int i = -1;
						for(JCheckBox jcb : cblist){
							if(jcb.isSelected()){
								Primerak primerak = null;
								for(Primerak prim : biblioteka.getPrimerci()){
									if(prim.getId().equals(jcb.getText().toString())){
										primerak = prim;
										i++;
									}
								}
								String numStr = id.substring(id.length() - 2);
								int numInt = Integer.parseInt(numStr) + i;
								if(numInt < 10) {
									numStr = "IZN0" + numInt;
								}
								else{
									numStr = "IZN" + numInt;
								}
								Iznajmljivanje novo = new Iznajmljivanje(numStr, zaposleni, clan, datum, primerak);
								biblioteka.dodajIznajmljivanje(novo);
							}
						}
					}else { // IZMENA:
						Primerak primerak = null;
						for(Primerak prim : biblioteka.getPrimerci()){
							if(prim.getId().equals(primID)){
								primerak = prim;
							}
						}
						iznajmljivanje.setZaposleni(zaposleni);
                        iznajmljivanje.setClan(clan);
                        iznajmljivanje.setDatum(datum);
                        iznajmljivanje.setPrimerak(primerak);
					}
					biblioteka.snimiIznajmljivanja(BibliotekaMain.fIznajmljivanja);
					IznajmFormaMultiple.this.dispose();
					IznajmFormaMultiple.this.setVisible(false);
				}
			}
		});
		btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IznajmFormaMultiple.this.dispose();
				IznajmFormaMultiple.this.setVisible(false);
			}
		});
	}
	
	private void popuniPolja() {
        txtID.setEditable(false);
        txtID.setText(iznajmljivanje.getId());
		for(int i = 0; i < cbZap.getItemCount(); i++){
			if(iznajmljivanje.getZaposleni().getId() == biblioteka.getZaposleni().get(i).getId()){
				cbZap.setSelectedIndex(i);
				break;
			}
		}
		for(int i = 0; i < cbClan.getItemCount(); i++){
			if(iznajmljivanje.getClan().getId() == biblioteka.getClanovi().get(i).getId()){
				cbClan.setSelectedIndex(i);
				break;
			}
		}
		// for(int i = 0; i < cbPrim.getItemCount(); i++){
		// 	if(iznajmljivanje.getPrimerak().getId() == biblioteka.getPrimerci().get(i).getId()){
		// 		cbPrim.setSelectedIndex(i);
		// 		break;
		// 	}
		// }
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
        if(!txtID.getText().contains("IZN")){
            poruka += "- ID mora biti u formatu IZNxx\n";
			ok = false;
        }
        
        else if(iznajmljivanje == null){
            String id = txtID.getText().trim();
			Iznajmljivanje iznajmljivanje = null;
                    for(Iznajmljivanje izn : biblioteka.getIznajmljivanja()){
                        if(izn.getId().equals(id)){
                            iznajmljivanje = izn;
                        }
                    }
			if(iznajmljivanje != null) {
				poruka += "- Iznajmljivanje sa tim ID-jem vec postoji\n";
				ok = false;
			}
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
}
