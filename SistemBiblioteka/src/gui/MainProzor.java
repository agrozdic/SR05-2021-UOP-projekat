package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import biblioteka.Biblioteka;
import gui.prikaz.*;
import osobe.Administrator;
import osobe.Zaposleni;
import biblioteka.Biblioteka;

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
	private JMenuItem tipClItem = new JMenuItem("Tipovi clanarine");
	
	private Biblioteka biblioteka;
	private Zaposleni zaposleni;
	
	public MainProzor(Biblioteka biblioteka, Zaposleni zaposleni) {
		this.biblioteka = biblioteka;
		this.zaposleni = zaposleni;
		setTitle("Zaposleni: " + zaposleni.getKorisnickoIme());
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
		initActions();
	}
	
	private void initMenu() {
		setJMenuBar(mainMenu);
		mainMenu.add(zaposleniMenu);
		zaposleniMenu.add(adminItem);
		zaposleniMenu.add(bibItem);
		mainMenu.add(clanoviMenu);
		clanoviMenu.add(clanoviItem);
		clanoviMenu.add(tipClItem);
		mainMenu.add(BibliotekaMenu);
		BibliotekaMenu.add(knjigeItem);
		BibliotekaMenu.add(primerciItem);
		BibliotekaMenu.add(zanroviItem);
		BibliotekaMenu.add(iznajmItem);
	}
	
	private void initActions() {
		adminItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminProzor ap = new AdminProzor(biblioteka);
				ap.setVisible(true);
			}
		});

		bibItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BibProzor bp = new BibProzor(biblioteka);
				bp.setVisible(true);
			}
		});

		clanoviItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClanProzor cp = new ClanProzor(biblioteka);
				cp.setVisible(true);
			}
		});

		tipClItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TipClProzor tcp = new TipClProzor(biblioteka);
				tcp.setVisible(true);
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
	}
}
