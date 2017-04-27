package spring.amadou.elections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainElections {

  static List<ListeElectorale>listes= new ArrayList <ListeElectorale> ();
	public static void main(String[] args) {

		// Nombre de sieges à pouvoir

		boolean saisieOK = false;

		String valSieges = null;

		int nbSiegesAPourvoir = 0;

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Nombre de Sièges à pourvoir?");

		saisieOK = false;

		while (!saisieOK) {

			if (nbSiegesAPourvoir < 0) {

				System.out.println("Erreur: Tapez un nombre supérieur à zéro");

				try {

					valSieges = input.readLine();

					nbSiegesAPourvoir = Integer.parseInt(valSieges);

				} catch (IOException e) {

					e.printStackTrace();
				}

			} else {

				try {

					valSieges = input.readLine();

					nbSiegesAPourvoir = Integer.parseInt(valSieges);

					System.out.println("Le nombre de sièges à pourvoir est:" + " " + nbSiegesAPourvoir);

					saisieOK = true;

				} catch (IOException e) {

					e.printStackTrace();
				}
			}

		}
		/*// Nombre de liste en compétition
		saisieOK = false;

		System.out.println("Saisissez le nombre de liste en compétion");

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String listes = null;
		
		int nbListes = 0;

		while (!saisieOK) {

			try {
				listes = in.readLine();

				nbListes++;

				System.out.println("nombre de liste en compétition est: " + " " + nbListes);

				saisieOK = true;
			} catch (IOException e) {

				e.printStackTrace();
			}
        if(listes ==null || listes.equalsIgnoreCase("*")){
        	
        	break;
        }
		}*/

		// Dimensions des tableaux

		// saisie des voix des listes
		saisieOK = false;
 
		int nbListes= new Random().nextInt(100);
	 
		int totalVoix = 0;
		
		BufferedReader inputListes = new BufferedReader(new InputStreamReader(System.in));
		
		String nomListes = null ;

		// Saisie du nom de la liste i
		ArrayList <String> nomtabListes= new ArrayList<String>();
		
		while (!saisieOK) {
      
			
			
			for (int i = 0; i <nbListes; i++) {
				try {
				
					System.out.println("saisissez le nom de la liste" + " " + i + ":");
					
					nomListes= inputListes.readLine();

					System.out.println(nomListes);
					
					nomtabListes.add(nomListes);
					
															
					System.out.println(" tableau liste:" + listes);
					
				
				} catch (IOException e) {

					e.printStackTrace();
				}
				String[] result = new String[nomtabListes.size()];
				
				for (int j = 0; j < result.length; j++) {
					 
					result[i] = nomtabListes.get(i);
					
					System.out.println(result [i]); 
				}
				  System.out.println(result); 
			
				  if (nomListes ==null ) {

					System.out.println("Erreur: Tappez un nom non vide");
				} else {

					saisieOK = true;
				}
				
				if( nomListes.equalsIgnoreCase("*")){
					
					break;
				}
			}

			}

			// Saisie du nombre de voix de la liste i

			BufferedReader inputVoix = new BufferedReader(new InputStreamReader(System.in));

			String nbVoixListes = "";
			int[] voixListes = new int[nomtabListes.size()];

			saisieOK = false;

			while (!saisieOK) {

				for (int i = 0; i <voixListes.length; i++) {
					
					System.out.println("Saisissez le nombre de voix de la liste" + " " + i + ":");

					try {
						nbVoixListes = inputVoix.readLine();

						voixListes[i] = Integer.parseInt(nbVoixListes);

						System.out.println("Le nombre de voix de la liste" + " " + i + " " + "est: " + " "
							
								+ voixListes[i]);

					} catch (IOException e) {
				
						e.printStackTrace();
					}

					if (voixListes[i] <= 0) {

						System.out.println("Erreur:Tapez un nombre supérieur à zéro");
					} else {

						saisieOK = true;
					}
				}

			}
			// on increment les voix des listes

			for (int i = 0; i <voixListes.length; i++) {

				totalVoix = totalVoix + voixListes[i];

			}
			System.out.println("le nombre de votant est de :" + " " + totalVoix);
			// calcul des voix utiles c'est � dire le cumul des voix des listes
			// ayant obtenues plus de 5% du suffrage exprim�.
			boolean[] elimineListe = new boolean[voixListes.length];

			int nbVoixUtiles = 0;

			for (int i = 0; i < nbListes; i++) {

				if (((voixListes[i]) * 1.0 / totalVoix) < 0.05) {

					elimineListe[i] = true;

				} else {

					elimineListe[i] = false;

					nbVoixUtiles = nbVoixUtiles + voixListes[i];
				}

			}
			System.out.println("le nombre de voix utiles est:" + " " + nbVoixUtiles);

			// Y-a-il des listes �limin�es

			if (nbListes == 0) {

				System.out.println("toutes les listes sont éliminées");

				System.exit(0);
			}

			// répartition des sièges  électorales

			double quotientElectoral = nbVoixUtiles / nbSiegesAPourvoir;

			int nbSiegesPourvus = 0;

			double[] moyenneListes = new double[nbListes];

			int[] sigesListes = new int[nbListes];

			for (int i = 0; i < nbListes; i++) {

				if (!elimineListe[i]) {

					sigesListes[i] = (int) (voixListes[i] / quotientElectoral);

					moyenneListes[i] = (voixListes[i] / (sigesListes[i] + 1));

					nbSiegesPourvus = nbSiegesPourvus + sigesListes[i];

				} else {

					sigesListes[i] = 0;
				}
			}

			// repartition des sièges restants  à  la plus forte moyenne
			// 1 siège est attribuée  chaque tour de boucle
			double moyenneMax;
			int iMax = 0;
			int iMax2 = 0; // le second plus grand
			for (int iSieges = 0; iSieges <= (nbSiegesAPourvoir - nbSiegesPourvus - 1); iSieges++) {
				// recherche de la liste ayant la plus forte moyenne
				moyenneMax = -1.0;
				for (int i = 0; i <= nbListes - 1; i++) {

					if (!elimineListe[i]) {
						if (moyenneListes[i] > moyenneMax) {

							moyenneMax = moyenneListes[i];

							iMax = i;

						}
					}

				}

			}

			for (int i = 0; i <= nbListes - 1; i++) {

				if (moyenneListes[i] > moyenneListes[iMax]) {

					iMax2 = iMax;
					iMax = i;
				} else if ((moyenneListes[i] > moyenneListes[iMax2]) && (moyenneListes[i] < moyenneListes[iMax])) {

					iMax2 = i;
				}

			}
			// On attribue 1 siege à la liste de la plus forte moyenne

			sigesListes[iMax] = sigesListes[iMax] + 1;

			// On attribue 1 siege à la seconde liste qui a la plus forte
			// moyenne
			sigesListes[iMax2] = sigesListes[iMax2] + 1;

			// et on change sa moyenne

			moyenneListes[iMax] = voixListes[iMax] / (sigesListes[iMax]) + 1;

			// et on change la moyenne de la seconde liste qui a eu deuxi�me
			// plus forte moyenne
			moyenneListes[iMax2] = voixListes[iMax2] / (sigesListes[iMax2] + 1);

			// affichage résultats sans tri

			for (int i = 0; i < nbListes; i++) {

				if (elimineListe[i]) {

					System.out.println("La liste" + " " + i+ " " + "a  été éliminée");
				} else {

					System.out.println("La liste" + " " + i + " " + "a obenu" + " " + sigesListes[i] + " "
							+ "siège(s).");

				}
			}
		}
	
	

}

	
