/**
 * 
 */
package SqlPlusPlus;

/** Un champ du ResultSet avec les infos n�cessaires � son affichage
 * @author Dej
 *
 */
public class Champ {

	/** Son num�ro d'ordre du champ dans le ResultSet ( -1 si non issu d'un ResultSet ) */
	private int numero;
	/** Son nom */
	private String nom;
	/** Son libell� */
	private String libelle;
	/** Son type */
	private int type;
	/** Son format d'affichage */
	private String format;
	/** Sa longueur maximale d'affichage */
	private int longueur;
	/** Sa pr�cision */
	private int precision;
	
	/** Champ vide */
	public Champ(){ numero = -1; }
	
	/** Champ construit � partir de la valeur de ses param�tres
	 * @param numero 
	 * @param nom
	 * @param libelle
	 * @param type
	 * @param format
	 * @param longueur
	 * @param precision
	 */
	public Champ(int numero, String nom, String libelle, int type, String format, int longueur, int precision) {
		this.numero = numero;
		this.nom = nom;
		this.libelle = libelle;
		this.type = type;
		this.format = format;
		this.longueur = longueur;
		this.precision = precision;
	}
	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}


	/** Recalcul de son format d'affichage � partir de ses infos */
	public void resetFormat()
	{
		format = String.format("%%%d.%d%s", longueur, precision, "s");
	}
	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}
	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * @return the longueur
	 */
	public int getLongueur() {
		return longueur;
	}
	/**
	 * @param longueur the longueur to set
	 */
	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the precision
	 */
	public int getPrecision() {
		return precision;
	}
	/**
	 * @param precision the precision to set
	 */
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	/** Pour tester la classe
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
