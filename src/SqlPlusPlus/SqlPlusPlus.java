package SqlPlusPlus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Scanner;

/**
 * Un interpr�teur de commande pouvant se connecter � plusieurs types de base de
 * donn�es du d�partement informatique en utilisant les fonctionnalit�s de
 * jdbc<br>
 * Les bases de donn�es pr�vues sont :
 * <ul>
 * <li>Oracle en 'oci8' et 'thin'</li>
 * <li>MySql</li>
 * </ul>
 * 
 * @author Eric Desjardin
 * @version 0.1
 */

public class SqlPlusPlus {

	/* Les champs internes � une instance de la classe */

	/** Connexion en cours */
	private Connection con;
	/** Info sur la connexion */
	private DatabaseMetaData dbmd;

	/** Statement associ� � la connexion */
	private Statement st;

	/** Flux d'�criture des instructions SELECT */
	private PrintStream sortie;
	/** Flux d'�criture par d�faut (System.out) */
	private PrintStream sortie_defaut;
	/** Type de sortie (0:texte , 1:html , 2:xml , 3:csv) */
	private GenreSortie sortie_type;

	/** Flux de lecture de l'interpr�teur (peut �tre redirig� par @ ou run) */
	private BufferedReader entree;

	/** Flux bufferis� pour System.in */
	private BufferedReader entree_defaut;

	/** Nom de l'utilisateur */
	private String login;
	/** Mot de passe de l'utilisateur */
	private String pass;

	/** Type de la base � laquelle on se connecte (bd10,thin-bd10,mysql) */
	private String base;

	/** Invite de commande initiale */
	private String PROMPT1 = "Sql++> ";
	/** Invite de commande de continuation de saisie */
	private String PROMPT2 = "suite...> ";

	/* Les fonctionnalit�s */

	/** Constructeur d'une instance de la classe (initialisation) */
	private SqlPlusPlus() {
		// Initialisation des flux
		entree_defaut = new BufferedReader(new InputStreamReader(System.in));
		entree = entree_defaut;

		sortie_defaut = System.out;
		sortie = sortie_defaut;
		sortie_type = GenreSortie.TXT;
	}

	/**
	 * Pour signaler que des fonctionnalites sont pr�vues mais pas mise en
	 * oeuvre
	 * 
	 * @param texte
	 *            La commande attendue
	 */
	private void under_work(String texte) {
		System.err.println("La commande '" + texte + "' est en cours de d�veloppement");
	}

	/**
	 * La commande pass�e en param�tre peut-elle �tre ex�cut�e dans le contexte
	 * actuel
	 * 
	 * @param commande
	 *            La commande
	 * @param avec_message
	 *            Affichage d'un message d'alerte
	 */
	private boolean commande_active(String commande, boolean avec_message) {
		String s = commande.toUpperCase();
		boolean ok = false;

		/* A faire ... */

		return ok;

	}

	/**
	 * commande_active avec message automatique
	 * 
	 * @param commande
	 *            La commande
	 * @return Est-elle active dans le contexte actuel ?
	 */
	private boolean commande_active(String commande) {
		return commande_active(commande, true);
	}

	/**
	 * Demande de connexion � une des bases de donn�es<br>
	 * 
	 * @param flux
	 *            La base demand�e (si la base n'est pas fournie, on liste les
	 *            bases disponibles
	 * @return <code>true</code> si la connexion a pu �tre �tablie,
	 *         <code>faux sinon</code>
	 * @throws Exception
	 */
	private boolean connect(Scanner flux) {
		boolean ok = false; // R�ussite de la connexion

		String driver = null; // Nom de la classe Driver
		String urlDB = null; // url de connexion � la base

		// Si pas de paramètre
		if (flux.hasNext() == false) {
			// Liste des bases virtuelles
			sortie.println("Les bases sont : bd11, mysql, lege0007");
		}
		// Sinon
		else
			try {
				// Connexion
				// Lire la base, le login, le passwd
				// Nom base
				String base = flux.next().toUpperCase();
				String login = flux.next();
				String passwd = flux.next();
				// Si la base est
				if (base.equals("BD11")) {
					driver = "oracle.jdbc.OracleDriver";
					urlDB = "jdbc:oracle:thin:@bd11:1521:bd11";
				} else if (base.equals("MYSQL")) {
					// Sinon si la base est mysql
					driver = "com.mysql.jdbc.Driver";
					urlDB = "jdbc:mysql://mysql/";
				} else if (base.equals("LEGE0007")) {
					driver = "oracle.jdbc.OracleDriver";
					urlDB = "jdbc:oracle:thin:@lege0007:1521:lege0007";
				} else {
					throw new IllegalArgumentException("La base de données " + base + " n'existe pas");
				}
				// Chargement du driver
				Class.forName(driver);
				// Connexion à la base de données
				Connection conn = DriverManager.getConnection(urlDB,login,passwd);
				// Tout s'est bien passé
				ok = true;
				// Mémorisation dans les attributs de l'instance
				this.con = conn;
				this.base = base;
				this.login = login;
				this.pass = passwd;
				
			} catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println("Une erreur est survenue " + e);
			}
			finally{
				if(ok){
					System.out.println("La connexion à la base de données " + base + " a été établie");
				}
			}
		
		return ok;
	}

	/** Listage des tables de l'utilisateur */
	private void listage_tables() {

		/* A faire ... */

	}

	/**
	 * Listage des �l�ments de la base de donn�es (TABLES, DRIVERS, ...)<br>
	 * Rq : Seules les tables sont trait�es actuellement
	 * 
	 * @param flux
	 *            Type de listage demand�
	 */
	private void listage(Scanner flux) {

		/* A faire ... */

	}

	/** Listage des Drivers enregistr�s par le DriverManager */
	private void listage_drivers() {
		for (Enumeration e = DriverManager.getDrivers(); e.hasMoreElements();) {
			Driver driver = (Driver) e.nextElement();
			System.out.println(
					"Driver = " + driver.getClass() + " v" + driver.getMajorVersion() + "." + driver.getMinorVersion());
		}
	}

	/**
	 * Active la redirection de la sortie vers un fichier
	 * 
	 * @param fichier
	 *            Nom du fichier de redirection
	 * @param genre
	 *            Langage � utiliser pour la sortie : TEXTE, HTML, XML
	 * @return <code>false</code>si une redirection est d�j� active ou PB<br>
	 *         <code>true</code> sinon
	 */
	private boolean spool_on(String fichier, String genre) {
		boolean ok = false;

		/* A faire ... */

		return ok;
	}

	/**
	 * Fin de redirection de la sortie vers un fichier
	 * 
	 * @return <code>false</code> s'il n'y a pas de spool actif ou si erreur<br>
	 *         <code>true</code> sinon
	 */
	private boolean spool_off() {
		boolean ok = false;

		return ok;
	}

	/**
	 * Redirection de l'affichage de la commande SELECT vers un fichier <br>
	 * <kbr>SPOOL OFF</kbr> ou <br>
	 * <kbr>SPOOL fichier</kbr>
	 * 
	 * @param options
	 *            Scanner de lecture des options
	 * @return <code>true</code> si pas de PB, <code>false</code> sinon
	 */
	private boolean spool(Scanner options) {
		boolean ok = false;

		/* A faire ... */

		return ok;
	}

	/**
	 * Changement de langage d'�dition des r�sultats <br>
	 * <kbr>LANGAGE </kbr> Listage du langage courant<br>
	 * <kbr>LANGAGE GENRE</kbr> avec <kbr>GENRE</kbr> dans
	 * {"TEXTE,"HTML","XML","CSV"}
	 * 
	 * @param options
	 *            Scanner de lecture des options
	 * @return <code>true</code> si pas de PB, <code>false</code> sinon
	 */
	private boolean langage(Scanner options) {
		boolean ok = false;

		/* A faire ... */

		return ok;
	}

	/**
	 * Affichage du r�sultat d'une requ�te
	 * 
	 * @param res
	 *            Le curseur � afficher
	 */
	private void affiche_resultat(ResultSet res) {
		try {
			ResultatSQL r = null;

			switch (sortie_type) {
			case TXT:
				r = new ResultatSQL_TXT(res);
				break;
			case HTML:
				r = new ResultatSQL_HTML(res);
				break;
			case XML:
				r = new ResultatSQL_XML(res);
				break;
			case CSV:
				r = new ResultatSQL_CSV(res);
				break;
			default:
				System.err.println("Type d'�dition non g�r� : '" + sortie_type.getLabel());
			}
			sortie.print(r.affiche());
		} catch (SQLException e) {
			System.err.println("Erreur affiche_resultat : " + e);
		}
	}

	/**
	 * Execution d'une commande SQL
	 * 
	 * @param commande
	 *            La commande SQL � ex�cuter
	 * @return Vrai si l'ex�cution s'est bien pass�e
	 */
	private boolean executeSql(String commande) {
		boolean ok = true;

		/* A faire ... */

		return ok;
	}

	/**
	 * Construction de la commande sql (qui doit �tre termin�e par ';')<br>
	 * La requ�te peut �tre abandonn�e par la saisie d'une ligne sans caract�res
	 * 
	 * @param ligne
	 *            La premi�re ligne
	 * @return Chaine lue ou <code>null</code>
	 */
	private String lireCommandeSql(String ligne) {
		StringBuilder sql = new StringBuilder(); // Requete en construction

		boolean abandon = false; // Si l'on rencontre une ligne vide

		// Tq que l'utilisateur n'a pas abandonn� et
		// que le flux n'est pas coup� et
		// que la requ�te n'est pas close par ';'
		if (ligne != null)
			ligne = ligne.trim();
		while (!abandon && ligne != null && !ligne.endsWith(";")) {
			// Abandon
			if (ligne.length() == 0) {
				abandon = true;
				// On sort de la boucle
				break;
			} else {
				// Ajout � la requ�te en cours
				sql.append(ligne + " ");
			}

			// Lecture d'une nouvelle ligne sans les espaces aux extr�mit�s
			try {
				System.out.print(PROMPT2);
				ligne = entree.readLine();
				if (ligne != null)
					ligne = ligne.trim();
			} catch (IOException e) {
				// Rupture de flux
				ligne = null;
			}
		}

		// S'il n'y a pas eu d'accident et que l'utilisateur n'a pas abandonn�
		// sa requ�te
		if (!abandon && ligne != null) {
			// On concat�ne une derni�re fois mais sans le ';'
			sql.append(ligne.substring(0, ligne.length() - 1));
		}

		// On renvoie null ou la requ�te sans espaces aux extr�mit�s
		return abandon ? null : sql.toString().trim();
	}

	/**
	 * Affiche la description de la table
	 * 
	 * @param scanner
	 *            Flux pour lire le nom de la table
	 */
	private void desc(Scanner scanner) {

		/* A faire ... */

	}

	/** Traitement ligne par ligne des commandes pass�es par l'utilisateur */
	public void tourne() {
		boolean fini = false; // Fin du moteur ?

		try {
			do {
				System.out.print(PROMPT1);
				String ligne = entree.readLine();

				if (ligne == null)
					fini = true;
				else {
					ligne = ligne.trim();
					if (ligne.length() != 0) {
						// Le scanner récupère une ligne
						Scanner mots = new Scanner(ligne);
						// On récupère le premier mot de la ligne en majuscule
						String commande = mots.next().toUpperCase();

						// Commande interne � SqlPlusPlus
						if (commande.equals("EXIT") || commande.equals("QUIT"))
							fini = true;
						else if (commande.equals("CONNECT"))
							connect(mots);
						else if (commande.equals("LIST"))
							listage(mots);
						else if (commande.equals("DESC"))
							desc(mots);
						else if (commande.equals("LANGAGE"))
							langage(mots);
						else if (commande.equals("SPOOL"))
							spool(mots);
						else
						// Commande SQL (rq: doit �tre termin�e par ';'
						{
							String sql = lireCommandeSql(ligne);
							if (sql != null)
								executeSql(sql);
						}

						mots.close();
					}
				}
			} while (!fini);
		} catch (IOException e) {
			System.err.println("Fermeture abrupte du flux en entr�e\n" + e);
		}
		// Dans tous les cas, on essaye au moins de fermer la connexion � la
		// base
		finally {
			System.err.print("Fin du moteur...");
			if (con != null) {
				System.err.print("Fermeture de la base...");
				try {
					if (con != null)
						con.close();
					if (st != null)
						st.close();
				} catch (SQLException ignored) {
				}
			}
			if (sortie != sortie_defaut) {
				System.err.print("Fermeture du fichier de spool...");
				sortie.close();
			}
			System.err.println("OK");
		}

	}

	/** D�marrage de l'application */
	public static void main(String[] args) {
		SqlPlusPlus moteur = new SqlPlusPlus();
		moteur.tourne();
	}
} // Fin classe SqlPlusPlus
