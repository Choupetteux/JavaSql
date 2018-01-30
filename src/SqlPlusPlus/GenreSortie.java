package SqlPlusPlus;

/** Type enum�r� pour g�rer les types de sortie possibles
 * 
 * @author Dej
 *
 */
public  enum GenreSortie { 
	/** Sortie mode TEXTE */
	TXT("TEXTE"), 
	/** Sortie mode HTML */
	HTML("HTML"),
	/** Sortie mode XML */
	XML("XML"),
	/** Sortie mode CSV */
	CSV("CSV");

	/** Libelle associ� au genre */
	private String libelle;

	/** Acc�s au libell� */
	public String getLabel(){ return libelle; }

	/** Construction de la liste des valeurs du type enum�r�
	 * @param simple <code>true</code> ("TEXTE",...), <code>false</code> (TXT=>"TEXTE",...)
	 * @return La cha�ne cr��e
	 *  */
	public static String liste(boolean simple){
		StringBuffer s=new StringBuffer();
		String sep = "";
		s.append("(");
		for (GenreSortie g : GenreSortie.values())
		{
			s.append(sep);
			if (!simple) s.append(g.toString()+"=>");
			s.append("\""+ g.libelle+"\"");
			sep=",";
		}
		s.append(")");

		return s.toString();
	}
	/**  Construction de la liste version longue des valeurs du type enum�r� (liste(true) c�d (TXT=>"TEXTE",...) )
	 * @return La cha�ne cr��e
	 * */ 
	public static String liste() { return liste(false); }
	
	/** Acc�s au type �num�r� � partir de son libell�
	 * 
	 * @param qui Le libell� recherch�
	 * @return Le type (<code>null</code> si pas trouv�)
	 */
	public static GenreSortie lequel(String qui)
	{
		for (GenreSortie e : GenreSortie.values() )
			if (qui.equalsIgnoreCase(e.libelle))
				return e;
		return null;
	}
	
	/** Constructeur avec le libell�
	 * @param s Le libell�
	 */
	GenreSortie(String s){libelle=s;}
}
