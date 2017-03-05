package fr.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import fr.jerome.Level;

public class LevelUtils {
	private static final String LEVEL_DIRECTORY="levels";
	
	
	/**
	 * Charge un niveau dont le nom est nameLevel
	 * Charge directement le niveau dans les arraylist static de World et Decor
	 * 
	 * @param nameLevel -> nom du niveau
	 * @return false or true -> succes du chargement;
	 */
	public static Level loadLevel(String nameLevel) {
		File f=new File(LEVEL_DIRECTORY+File.separator+nameLevel);
		if(!f.exists()){
			System.out.println("Le niveau "+nameLevel+" n'existe pas dans le repertoire "+LEVEL_DIRECTORY);
			return null;
		}
		Level level = null;
		try {
			
			BufferedReader br=new BufferedReader(new FileReader(f));
			String ligne;
			String levelStr="";
			
			while((ligne=br.readLine())!=null){
				levelStr+=ligne+"\n";
			}
			br.close();
			level=Level.parseLevel(levelStr);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return level;
	}


	public static String[] getAllCreatedLevels() {
		ArrayList<String> niveaux=new ArrayList<String>();
		String[] fileName=new File("levels/").list();
		for(int i=0;i<fileName.length;i++)
		{
			if(!fileName[i].startsWith("."))niveaux.add(fileName[i]);
		}
		
		return niveaux.toArray(new String[niveaux.size()]);
	}

	public static boolean saveLevel(Level level, String name) {
		return saveLevel(level,name,false);
	}
	public static boolean saveLevel(Level level, String name,boolean eraseIfExist) {
		System.out.println("TENTATIVE SAUVEGARDE DU NIVEAU");

		File f=new File(LEVEL_DIRECTORY+File.separator+name);
		if(f.exists() && !eraseIfExist){
			System.out.println("ECHEC SAUVEGARDE DU NIVEAU : le fichier existe deja. Veuillez confirmer");
			return false;
		}

		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(f));
			bw.write(level.toString());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ECHEC SAUVEGARDE DU NIVEAU");
			return false;
		}		
		
		System.out.println("SAUVEGARDE DU NIVEAU REUSSI");
		return true;
	}
}
