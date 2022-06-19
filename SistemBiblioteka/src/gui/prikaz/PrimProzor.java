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
import biblioteka.Biblioteka;
import gui.forme.PrimForma;
import knjige.Primerak;

public class PrimProzor extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable primTabela;
	
	private Biblioteka biblioteka;
	
	public PrimProzor(Biblioteka biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Primerci");
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
		
		String[] zaglavlja = new String[] {"ID", "Knjiga", "Tip poveza", "Godina", "Izdat"};
		Object[][] sadrzaj = new Object[biblioteka.getPrimerci().size()][zaglavlja.length];
		
		for(int i = 0; i < biblioteka.getPrimerci().size(); i++) {
			Primerak primerak = biblioteka.getPrimerci().get(i);
			sadrzaj[i][0] = primerak.getId();
			sadrzaj[i][1] = primerak.getKnjiga().getNaslov();
			sadrzaj[i][2] = primerak.getTipPoveza();
			sadrzaj[i][3] = primerak.getGodinaStampe();
			sadrzaj[i][4] = primerak.isIzdat();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		primTabela = new JTable(tableModel);
		
		primTabela.setRowSelectionAllowed(true);
		primTabela.setColumnSelectionAllowed(false);
		primTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		primTabela.setDefaultEditor(Object.class, null);
		primTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(primTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = primTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = tableModel.getValueAt(red, 0).toString();
                    Primerak primerak = null;
                    for(Primerak prim : biblioteka.getPrimerci()){
                        if(prim.getId().equals(id)){
                            primerak = prim;
                        }
                    }
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete primerk?", 
							primerak.getId() + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						biblioteka.ukloniPrimerak(primerak);
						tableModel.removeRow(red);
						biblioteka.snimiPrimerke(BibliotekaMain.fPrimerci);
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrimForma pf = new PrimForma(biblioteka, null);
				pf.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = primTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = tableModel.getValueAt(red, 0).toString();
                    Primerak primerak = null;
                    for(Primerak prim : biblioteka.getPrimerci()){
                        if(prim.getId().equals(id)){
                            primerak = prim;
                        }
                    }
					if(primerak == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja primerka sa tim ID-jem", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						PrimForma pf = new PrimForma(biblioteka, primerak);
				        pf.setVisible(true);
					}
				}
			}
		});
	}
}
