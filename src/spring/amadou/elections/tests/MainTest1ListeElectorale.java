package spring.amadou.elections.tests;

import spring.amadou.elections.ElectionsException;
import spring.amadou.elections.ListeElectorale;

public class MainTest1ListeElectorale {
	public static void main(String[] args) {
		// cr�ation d'une liste �lectorale
		ListeElectorale listeElectorale1 = new ListeElectorale(1, "A", 32000, 0, false);
		// affichage identit� liste
		System.out.println("listeElectorale1=" + listeElectorale1);
		// modification du nombre de si�ges
		listeElectorale1.setSieges(2);
		// affichage identit� liste 1
		System.out.println("listeElectorale1=" + listeElectorale1);
		// une nouvelle liste �lectorale
		ListeElectorale listeElectorale2 = listeElectorale1;
		// affichage identit� liste 2
		System.out.println("listeElectorale2=" + listeElectorale2);
		// modification du nombre de si�ges
		listeElectorale2.setSieges(3);
		// affichage identit� des 2 listes
		System.out.println("listeElectorale2=" + listeElectorale2);
		System.out.println("listeElectorale1=" + listeElectorale1);
		// test exception
		try {
			listeElectorale2.setSieges(-3);
		} catch (ElectionsException ex) {
			System.err.println("L'exception suivante s'est produite : [" + ex.toString() + "]");
		}

	}
}