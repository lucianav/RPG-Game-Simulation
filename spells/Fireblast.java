package spells;

import game.Map;
import heroes.Hero;

import java.lang.Math;

/**
 * clasa spell tip Fireblast
 * 
 * @author Luciana Viziru
 *
 */
public class Fireblast extends Spell {

	private int damage;// damage ce va fi aplicat

	/**
	 * constructor fara parametrii, seteaza damage-ul de baza
	 */
	public Fireblast() {
		baseDamage = 350;
		damage = 0;
	}

	/**
	 * metoda modifica damage-ul in functie de level si teren
	 * 
	 * @return damage
	 */
	public int levelFieldDamage() {
		damage = baseDamage + 50 * level;
		if (type == Map.Volcanic) {
			damage = Math.round(damage * 1.25f);
		}
		return damage;
	}

	/**
	 * metoda aplica damage curent
	 * 
	 * @param h
	 *            oponent
	 * @return damage
	 */
	public int apply(Hero h) {
		return this.levelFieldDamage();
	}

}
