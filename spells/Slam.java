package spells;

import game.Map;
import heroes.Hero;
/**
 * clasa spell tip Slam
 * 
 * @author Luciana Viziru
 *
 */
public class Slam extends Spell {

	private int damage;// damage ce va fi aplicat
	/**
	 * constructor fara parametrii, seteaza damage-ul de baza
	 */
	public Slam() {
		baseDamage = 100;
		damage = 0;
	}
	/**
	 * metoda modifica damage-ul in functie de level si teren
	 * 
	 * @return damage
	 */
	public int levelFieldDamage() {
		damage = baseDamage + 40 * level;
		if (type == Map.Land) {
			damage = Math.round(damage * 1.15f);
		}
		return damage;
	}
	/**
	 * metoda aplica incapacitarea si damage-ul curent 
	 * 
	 * @return damage
	 */
	public int apply(Hero h) {
		h.setBlocked(true);
		h.setRoundsBlocked(1);
		return this.levelFieldDamage();
	}
}
