package spells;

import game.Map;
import heroes.Hero;

/**
 * clasa spell tip Backstab
 * 
 * @author Luciana Viziru
 *
 */
public class Backstab extends Spell {

	private int damage; // damage ce va fi aplicat

	/**
	 * constructor fara parametrii, seteaza damage-ul de baza
	 */
	public Backstab() {
		baseDamage = 200;
		damage = 0;
	}

	/**
	 * metoda modifica damage-ul in functie de level si teren
	 * 
	 * @return damage
	 */
	public int levelFieldDamage() {
		damage = baseDamage + 20 * level;
		if (type == Map.Woods) {
			damage = Math.round(damage * 1.15f);
		}
		return damage;
	}

	/**
	 * metoda aplica damage-ul total
	 * 
	 * @param h
	 *            oponent
	 * @return damage-ul total
	 */
	public int apply(Hero h) {
		return this.levelFieldDamage();
	}
}