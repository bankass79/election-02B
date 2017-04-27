package spring.amadou.elections;

public class ListeElectorale {

	// champs
	private int id;
	private String nom;
	private int voix;
	private int sieges;
	private boolean elimine;

	
private int length; // nombre d'élements significatifs
	
	private String [] listesElectorales;

	// constructeurs
	public ListeElectorale() {
	}

	public ListeElectorale(int id, String nom, int voix, int sieges, boolean elimine) {
	}

	// getters et setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getVoix() {
		return voix;
	}

	public void setVoix(int voix) {
		this.voix = voix;
	}

	public int getSieges() {
		return sieges;
	}

	public void setSieges(int sieges) {
		this.sieges = sieges;
	}

	public boolean isElimine() {
		return elimine;
	}

	public void setElimine(boolean elimine) {
		this.elimine = elimine;
	}

	@Override
	public String toString() {
		return "id=" + id + ", nom=" + nom + ", voix=" + voix + ", sieges=" + sieges + ", elimine="
				+ elimine + "";
	}

	/*@Override
	public String toString() {
		return  String.format( "[%s  %d  %s  %s   %d]",  new Object [] { id, nom, voix, sieges, elimine} );
	}
   */
	
	public void entrerNomNbVoix(String nomListe, int voixListe){
		
		nom=nomListe;
		
		voix=voixListe;
		
		
	}
	



	

}
