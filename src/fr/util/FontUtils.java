package fr.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class FontUtils {

	
	public static TrueTypeFont chargerFont(String name, int type, int size, boolean isSystemFont) {
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

}
