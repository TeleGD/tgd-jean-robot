package fr.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class FontUtils {

	private static final String FONT_DIRECTORY = "font";

	/**
	 * Charge une police a etre utilisé dans le jeu
	 * @param name : le nom de la police a utilisé (pour les polices custom précisé le directory relative font/[nom_font].ttf
	 * @param type : Font.BOLD, Font.PLAIN, Font.ITALIC 
	 * @param size : taille de la police a charger
	 * @param isSystemFont : precise si la police est système ou custom
	 * @return
	 */
	public static TrueTypeFont loadFont(String name, int type, int size, boolean isSystemFont) {
		if(isSystemFont){
			Font fontTemp = new Font(name, type, size);
			return new TrueTypeFont(fontTemp, false);
		}else{
			Font fontTemp = null;
			try {
				fontTemp = Font.createFont(java.awt.Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream(name));
			} catch (FontFormatException | IOException e) {
				e.printStackTrace();
			}

			return new TrueTypeFont(fontTemp.deriveFont(java.awt.Font.PLAIN, size),false);
		
		}

		
	}

	public static TrueTypeFont loadCustomFont(String name, int type, int size) {
		return loadFont(FONT_DIRECTORY+File.separator+name,type,size,false);
	}

}
