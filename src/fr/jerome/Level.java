package fr.jerome;

import java.util.ArrayList;
import java.util.regex.Pattern;

import fr.bonus.BatBonus;
import fr.bonus.Bonus;
import fr.bonus.GunBonus;
import fr.bonus.LevelEnd;
import fr.characters.enemies.Enemy1;
import fr.characters.enemies.Ennemy;
import fr.characters.enemies.EnnemyShooter;
import fr.decor.DeathBloc;
import fr.decor.ElevatorTrap;
import fr.decor.Plateform;

public class Level {

	private ArrayList<Plateform> plateforms=new ArrayList<Plateform>();
	private ArrayList<Ennemy> enemys=new ArrayList<Ennemy>();
	private ArrayList<Bonus> bonus=new ArrayList<Bonus>();

	public void addPlateform(Plateform plateform) {
		plateforms.add(plateform);
	}

	public void addEnemy(Ennemy enemy) {
		enemys.add(enemy);		
	}

	public void addBonus(Bonus bonusLoc) {
		bonus.add(bonusLoc);	
	}
	
	public ArrayList<Plateform> getPlateforms() {
		return plateforms;
	}
	
	public ArrayList<Ennemy> getEnemys() {
		return enemys;
	}
	
	public ArrayList<Bonus> getBonus() {
		return bonus;
	}
	
	
	@Override
	public String toString(){
		String levelStr="";
		levelStr+="// PLATEFORMES \n";
		for(int i=0;i<plateforms.size();i++){
			levelStr+=plateforms.get(i).parseString()+"\n";
		}
		levelStr+="// ENNEMIS \n";
		
		for(int i=0;i<enemys.size();i++){
			levelStr+=enemys.get(i).parseString()+"\n";
		}
		
		return levelStr;
	}

	public static Level parseLevel(String levelStr) {
		Level level=new Level();
		String[] splitLevel=levelStr.split(Pattern.quote("\n"));
		for(String ligne:splitLevel){
			if(ligne.startsWith("//")){
				System.out.println("CHARGEMENTS DES "+ligne.substring(2));
			}
			if(ligne.startsWith("Plateform")){
				level.addPlateform(new Plateform(ligne));
			}
			else if(ligne.startsWith("DeathBloc"))
			{
				level.addPlateform(new DeathBloc(ligne));
			}
			else if(ligne.startsWith("ElevatorTrap"))
			{
				level.addPlateform(new ElevatorTrap(ligne));
			}
			//Def des ennemies
			else if(ligne.startsWith("EnnemyShooter")){
				level.addEnemy(new Enemy1(new EnnemyShooter(ligne)));
			}
			//Def des bonus
			else if(ligne.startsWith("LevelEnd")){
				String[] ligne2 = ligne.split(" ");
				level.addBonus(new LevelEnd(
						Double.parseDouble(ligne2[1]), 
						Double.parseDouble(ligne2[2]),
						Double.parseDouble(ligne2[3]), 
						Double.parseDouble(ligne2[4])));
			}
			else if (ligne.startsWith("BatBonus")){
				String[] ligne2 = ligne.split(" ");
				level.addBonus(new BatBonus(
						Double.parseDouble(ligne2[1]),
						Double.parseDouble(ligne2[2]),
						Double.parseDouble(ligne2[3]),
						Double.parseDouble(ligne2[4])));					
			}
			else if (ligne.startsWith("GunBonus")){
				String[] ligne2 = ligne.split(" ");
				level.addBonus(new GunBonus(
						Double.parseDouble(ligne2[1]),
						Double.parseDouble(ligne2[2]),
						Double.parseDouble(ligne2[3]),
						Double.parseDouble(ligne2[4])));
			}
		}
		return level;
	}

	public void setEnemys(ArrayList<Ennemy> enemys) {
		this.enemys=enemys;
	}

	public void setPlateforms(ArrayList<Plateform> plateforms) {
		this.plateforms=plateforms;
		
	}

	

}
