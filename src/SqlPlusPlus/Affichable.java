/**
 * ggfsdgfdgfsdgf fdgs  fds 
 */ 
package SqlPlusPlus;

/** Donne les fonctionnalit�s d'affichage : affiche, entete, ligne, fin
 * @author Dej
 *
 */
public interface Affichable {
	/** Affichage de l'ent�te */
	public String entete();
	/** Affichage de la ligne courante */
	public String ligne();
	/** Affichage de la fin */
	public String fin();
	/** Affichage de l'ensemble */
	public String affiche();
}

