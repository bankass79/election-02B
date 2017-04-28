package spring.amadou.elections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class MainElections {

	static List<ListeElectorale>listes= new ArrayList <ListeElectorale> ();
	public static void main(String[] args) throws IOException {

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

		// Dimensions des tableaux

		// saisie des voix des listes
		saisieOK = false;

		int nbListes= new Random().nextInt(100);

		int totalVoix = 0;

		BufferedReader inputListes = new BufferedReader(new InputStreamReader(System.in));

		// Saisie du nom de la liste i

		ArrayList <String> nomtabListes= new ArrayList<String>();
		
		String [] result;
		while (!saisieOK) {

			String nomListes = null ;



			try {
				for (int i = 0; i <nbListes; i++) {

					System.out.println("saisissez le nom de la liste" + " " + i + ":");

					nomListes= inputListes.readLine(); 

					nomtabListes.add(nomListes);


					System.out.println(nomtabListes);

					/*	if (nomListes ==null ) {

						System.out.println("Erreur: Tappez un nom non vide");
					} else {

						saisieOK = true;
					}*/

					if( nomListes.equalsIgnoreCase("*")){
						saisieOK=true;
						break;
					}


				} 

				result = new String [nomtabListes.size()-1];

				for(int j=0; j<result.length; j++){

					result [j]= nomtabListes.get(j);
					
					System.out.println("Result:"+ result[j]);
					
					
				}
				result = nomtabListes.toArray(result);

			}catch (IOException e) {

				e.printStackTrace();


			}


		}


		// Saisie du nombre de voix de la liste i

		 Scanner inputVoix = new Scanner(System.in);

		int nbVoixListes = 0;

		int[] voixListes = new int[nomtabListes.size()-1];

		saisieOK = false;

		while (!saisieOK) {

			for (int i = 0; i <nomtabListes.size()-1; i++) {

				System.out.println("Saisissez le nombre de voix de la liste" + " " + i + ":");

				nbVoixListes = inputVoix.nextInt();

				voixListes[i] =nbVoixListes;

				System.out.println("Le nombre de voix de la liste" + " " + i + " " + "est: " + " "

							+ nbVoixListes);

				if (voixListes[i] <= 0) {

					System.out.println("Erreur:Tapez un nombre supérieur à zéro");
				} else {

					saisieOK = true;
				}
			}

		}
		inputListes.close();
		inputVoix.close();
		// on increment les voix des listes

		for (int i = 0; i <nomtabListes.size()-1; i++) {

			totalVoix = totalVoix + voixListes[i];

		}
		System.out.println("le nombre de votant est de :" + " " + totalVoix);
		// calcul des voix utiles c'est à dire le cumul des voix des listes
		// ayant obtenues plus de 5% du suffrage exprimé.
	
		boolean[] elimineListe = new boolean[nomtabListes.size()-1];

		int nbVoixUtiles = 0;

		for (int i = 0; i <  elimineListe.length; i++) {

			if (((voixListes[i]) * 1.0 / totalVoix) < 0.05) {

				elimineListe[i] = true;

			} else {

				elimineListe[i] = false;

				nbVoixUtiles = nbVoixUtiles + voixListes[i];
			}

		}
		System.out.println("le nombre de voix utiles est:" + " " + nbVoixUtiles);

		// Y-a-il des listes  éliminées

		if ((nomtabListes.size()-1) == 0) {

			System.out.println("toutes les listes sont éliminées");

			System.exit(0);
		}

		// répartition des sièges  électorales

		double quotientElectoral = nbVoixUtiles / nbSiegesAPourvoir;

		int nbSiegesPourvus = 0;

		double[] moyenneListes = new double[nomtabListes.size()-1];

		int[] sigesListes = new int[nomtabListes.size()-1];

		for (int i = 0; i < nomtabListes.size()-1; i++) {

			if (!elimineListe[i]) {

				sigesListes[i] = (int) (voixListes[i] / quotientElectoral);

				moyenneListes[i] = (voixListes[i] / (sigesListes[i] +1));

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
			for (int i = 0; i <nomtabListes.size()-1; i++) {

				if (!elimineListe[i]) {
					if (moyenneListes[i] > moyenneMax) {

						moyenneMax = moyenneListes[i];

						iMax = i;

					}
				}

			}

		}

		for (int i = 0; i <nomtabListes.size()-1; i++) {

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

		moyenneListes[iMax] = voixListes[iMax] / ((sigesListes[iMax]) + 1);

		// et on change la moyenne de la seconde liste qui a eu deuxième
		// plus forte moyenne
		moyenneListes[iMax2] = voixListes[iMax2] / (sigesListes[iMax2] + 1);

		// affichage résultats sans tri

		for (int i = 0; i < nomtabListes.size()-1; i++) {

			if (elimineListe[i]) {

				System.out.println("La liste" + " " + i+ " " + "a  été éliminée");
			} else {

				System.out.println("La liste" + " " + i + " " + "a obenu" + " " + sigesListes[i] + " "
						+ "siège(s).");

			}
		}
	}


  
}


