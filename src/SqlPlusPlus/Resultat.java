package SqlPlusPlus;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Classe abstraite donnant les fonctionnalit�s d'affichage � un ResulSet
 * 
 * @author Dej
 *
 */
abstract class ResultatSQL implements Affichable {
	/** R�f�rence au ResultSet fourni � la construction */
	ResultSet rs = null;
	/** Les infos sur le ResultSet */
	ResultSetMetaData rsmd = null;
	/** Les champs du ResultSet */
	Vector<Champ> champs = null;

	// ResultatSQL(){}

	/**
	 * Initialisation � partir d'un ResultSet
	 * 
	 * @param res
	 *            Le ResultSet de base
	 * @exception Si
	 *                erreur dans l'acc�s � un champ
	 */
	ResultatSQL(ResultSet res) throws SQLException {
		rs = res;
		rsmd = rs.getMetaData();
		try {
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
								
				Champ champ = new Champ (i,rsmd.getColumnName(i), "lib"+rsmd.getColumnName(i), rsmd.getColumnType(i),"utf8",10,1); 
				champ.resetFormat();
				this.champs.addElement(champ);
			

			}

		} catch (SQLException e) {
			System.err.println("Une erreur SQL est survenue:" + e.getMessage());
		}

	}

	/** Nombre de champs de la requ�te */
	public int nb_elements() {
		return champs.size();
	}

	/**
	 * Affichage d'une requ�te (commun � tout objet h�ritant de cette classe
	 * 
	 * @return La chaine cr��e
	 */
	public String affiche() {
		StringBuffer s = new StringBuffer();

		try {

			// affichage de l'entête
			s.append(entete());

			// Tant qu'il y'a un enregistrement
			while (rs.next()) {
				// afficher ligne
				s.append(ligne());
			}
			// affichage de la fin
			s.append(fin());

		} catch (SQLException e) {
			System.err.println("Une erreur SQL est survenue:" + e.getMessage());
		}

		return s.toString();
	}

	/**
	 * Construction d'une cha�ne par r�p�tition (utilitaire)
	 * 
	 * @param qui
	 *            La chaine � r�p�ter
	 * @param combien
	 *            Le nombre de r�p�tition
	 * @return La chaine construite
	 */
	protected String stringRepeat(String qui, int combien) {
		StringBuffer s = new StringBuffer(combien < 0 ? 0 : combien);
		while (combien-- > 0)
			s.append(qui);
		return s.toString();
	}

}

/* ========================================================================= */
/**
 * ResultSet affichable en mode TEXTE
 * --------------------------------------------------------------------------
 * 
 * +------------+------------+ | ID | NUMERO | +------------+------------+ | ...
 * | ... | +------------+------------+
 * 
 * 
 * @author Dej
 */

class ResultatSQL_TXT extends ResultatSQL {
	String premiere; // ligne memoris�e car elle apparait 3 fois, construite
						// dans entete()

	final String coin = "+";
	final String tiret = "-";
	final String barre = "|";

	/**
	 * @param res
	 * @throws SQLException
	 */
	public ResultatSQL_TXT(ResultSet res) throws SQLException {
		super(res);
	}

	/**
	 * Entete
	 * 
	 * @see SqlPlusPlus.Affichable#entete()
	 */
	public String entete() {
		StringBuffer s = new StringBuffer();

		/* TODO ... */

		return s.toString();
	}

	/*
	 * Fin
	 * 
	 * @see SqlPlusPlus.Affichable#fin()
	 */
	public String fin() {
		return premiere;
	}

	/*
	 * Ligne
	 * 
	 * @see SqlPlusPlus.Affichable#ligne()
	 */
	public String ligne() {
		StringBuffer s = new StringBuffer();

		/* TODO ... */

		return s.toString();
	}

}

/* ========================================================================= */
/**
 * ResultSet affichable en mode HTML
 * 
 * <pre>
 *    <TABLE>
 *    	<THEAD>
 *    	  <TR>
 *    	    <TH>ID</TH>
 *    	    <TH>NUMERO</TH>
 *    	  </TR>
 *    	</THEAD>
 *    	  <TR>
 *    	    <TD>...</TD>
 *    	    <TD>...</TD>
 *    	  </TR>
 *    	<TBODY>
 *    	</TBODY>
 *    </TABLE>
 * </pre>
 * 
 * @author Dej
 *
 */
class ResultatSQL_HTML extends ResultatSQL {

	/**
	 * @param res
	 * @throws SQLException
	 */
	public ResultatSQL_HTML(ResultSet res) throws SQLException {
		super(res);
	}

	/*
	 * Entete
	 * 
	 * @see SqlPlusPlus.Affichable#entete()
	 */
	public String entete() {
		StringBuffer s = new StringBuffer();

		/* TODO ... */

		return s.toString();
	}

	/*
	 * Fin
	 * 
	 * @see SqlPlusPlus.Affichable#fin()
	 */
	public String fin() {
		/* TODO ... */
		return null;
	}

	/*
	 * Ligne
	 * 
	 * @see SqlPlusPlus.Affichable#ligne()
	 */
	public String ligne() {
		StringBuffer s = new StringBuffer();

		/* TODO ... */

		return s.toString();
	}

}

/* ========================================================================= */
/**
 * ResultSet affichable en mode XML
 * 
 * <pre>
 * 	<rowset>
 * 	   <row>
 * 	      <ID>...</ID>
 * 	      <NUMERO>...</NUMERO>
 * 	   </row>
 * 	</rowset>
 * </pre>
 * 
 * @author Dej
 *
 */
class ResultatSQL_XML extends ResultatSQL {

	/**
	 * @param res
	 * @throws SQLException
	 */
	public ResultatSQL_XML(ResultSet res) throws SQLException {
		super(res);
	}

	/*
	 * Entete
	 * 
	 * @see SqlPlusPlus.Affichable#entete()
	 */
	public String entete() {
		/* TODO ... */
		return null;
	}

	/*
	 * Fin
	 * 
	 * @see SqlPlusPlus.Affichable#fin()
	 */
	public String fin() {
		/* TODO ... */
		return null;
	}

	/*
	 * Ligne
	 * 
	 * @see SqlPlusPlus.Affichable#ligne()
	 */
	public String ligne() {
		StringBuffer s = new StringBuffer();

		/* TODO ... */

		return s.toString();
	}

}

/* ========================================================================= */
/**
 * ResultSet affichable en mode CSV
 * 
 * <pre>
 * 
 *    "...";"..."
 *
 * </pre>
 * 
 * @author Dej
 *
 */
class ResultatSQL_CSV extends ResultatSQL {
	final String sep = ";";
	final String quote = "\"";

	/**
	 * Construction � partir d'un ResultSet
	 * 
	 * @param res
	 *            Le ResultSet
	 * @throws SQLException
	 */
	ResultatSQL_CSV(ResultSet res) throws SQLException {
		super(res);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SqlPlusPlus.Affichable#entete()
	 */
	public String entete() {
		StringBuffer s = new StringBuffer();
		/* TODO ... */
		return s.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SqlPlusPlus.Affichable#fin()
	 */
	public String fin() {
		/* TODO ... */
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SqlPlusPlus.Affichable#ligne()
	 */
	public String ligne() {
		StringBuffer s = new StringBuffer();
		/* TODO ... */
		return s.toString();
	}

}
