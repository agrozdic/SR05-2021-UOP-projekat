package gui.forme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


import main.BibliotekaMain;
import net.miginfocom.swing.MigLayout;
import biblioteka.Biblioteka;
import knjige.Knjiga;
import knjige.Primerak;
import knjige.TipPoveza;

public class PrimForma extends JFrame {

	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(10);
	private JLabel lblKnjiga = new JLabel("Knjiga");
	private JComboBox<String> cbKnjiga;
	private JLabel lblTipPoveza = new JLabel("Tip poveza");
	private JComboBox<TipPoveza> cbTipPoveza = new JComboBox<TipPoveza>(TipPoveza.values());
    private JLabel lblGodina = new JLabel("Godina");
	private JTextField txtGodina = new JTextField(10);
    private JLabel lblIzdat = new JLabel("Izdat");
	private JRadioButton rbTrue = new JRadioButton("True", true);
	private JRadioButton rbFalse = new JRadioButton("False", false);
	private ButtonGroup rgIzdat = new ButtonGroup();
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCanel = new JButton("Cancel");
	
	private Biblioteka biblioteka;
	private Primerak primerak;
	
	public PrimForma(Biblioteka biblioteka, Primerak primerak) {
		this.biblioteka = biblioteka;
		this.primerak = primerak;
		if(primerak == null) {
			setTitle("Dodavanje primerka");
		}else {
			setTitle("Izmena podataka - " + primerak.getId());
		}
		
		String knjigarArr[] = new String[biblioteka.getKnjige().size()];
        for(int i = 0; i < biblioteka.getKnjige().size(); i++){
            knjigarArr[i] = biblioteka.getKnjige().get(i).getNaslov();
        }
        cbKnjiga = new JComboBox<String>(knjigarArr);

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
		
		if(primerak != null) {
			popuniPolja();
		}
		
        add(lblID);
        add(txtID);
        
		add(lblKnjiga);
		add(cbKnjiga);
		add(lblTipPoveza);
		add(cbTipPoveza);
        add(lblGodina);
		add(txtGodina);
        add(lblIzdat);
        add(rbTrue);
        add(rbFalse);
        rbTrue.setActionCommand("true");
		rbFalse.setActionCommand("false");
		rgIzdat.add(rbTrue);
		rgIzdat.add(rbFalse);
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
					String knjigaStr = (String)cbKnjiga.getSelectedItem();
					TipPoveza tipPoveza = (TipPoveza)cbTipPoveza.getSelectedItem();
                    int godina = Integer.parseInt(txtGodina.getText());
                    Boolean izdat = Boolean.parseBoolean(rgIzdat.getSelection().getActionCommand());

                    Knjiga knjiga = null;
                    for(Knjiga knj : biblioteka.getKnjige()){
                        if(knj.getNaslov().equals(knjigaStr)){
                            knjiga = knj;
                        }
                    }
					
					if(primerak == null) { // DODAVANJE:
						Primerak novi = new Primerak(id, knjiga, tipPoveza, godina, izdat);
						biblioteka.dodajPrimerak(novi);
					}else { // IZMENA:
						primerak.setKjniga(knjiga);
                        primerak.setTipPoveza(tipPoveza);
                        primerak.setGodinaStampe(godina);
                        primerak.setIzdat(izdat);
					}
					biblioteka.snimiPrimerke(BibliotekaMain.fPrimerci);
					PrimForma.this.dispose();
					PrimForma.this.setVisible(false);
				}
			}
		});
		btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrimForma.this.dispose();
				PrimForma.this.setVisible(false);
			}
		});
	}
	
	private void popuniPolja() {
        txtID.setEditable(false);
        txtID.setText(primerak.getId());
		for(int i = 0; i < cbKnjiga.getItemCount(); i++){
			if(primerak.getKnjiga().getNaslov() == biblioteka.getKnjige().get(i).getNaslov()){
				cbKnjiga.setSelectedIndex(i);
				break;
			}
		} 
		//cbKnjiga.setSelectedItem(primerak.getKnjiga().getNaslov());
		cbTipPoveza.setSelectedItem(primerak.getTipPoveza());
        txtGodina.setText(primerak.getGodinaStampe() + "");
        if(primerak.isIzdat() == true){
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
		
        if(!txtID.getText().contains("PRM")){
            poruka += "- ID mora biti u formatu PRMxx\n";
			ok = false;
        }

		if(txtGodina.getText().trim().equals("")) {
			poruka += "- Unesite godinu\n";
			ok = false;
		}
        
        else if(primerak == null){
            String id = txtID.getText().trim();
			Primerak primerak = null;
            for(Primerak prim : biblioteka.getPrimerci()){
                if(prim.getId().equals(id)){
                    primerak = prim;
                }
            }
			if(primerak != null) {
				poruka += "- Primerak sa tim ID-jem vec postoji\n";
				ok = false;
			}
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
}
