package spells;

import game.Map;
import heroes.Hero;

/**
 * clasa spell tip Paralysis
 * 
 * @author Luciana Viziru
 *
 */
public class Paralysis extends Spell {

	private int damage;// damage ce va fi aplicat

	/**
	 * constructor fara parametrii, seteaza damage-ul de baza
	 */
	public Paralysis() {
		baseDamage = 40;
		damage = 0;
	}

	/**
	 * metoda modifica damage-ul in functie de level si teren
	 * 
	 * @return damage
	 */
	public int levelFieldDamage() {
		damage = baseDamage + 10 * level;
		if (type == Map.Woods) {
			damage = Math.round(damage * 1.15f);
		}
		return damage;
	}

	/**
	 * metoda seteaza numarul de runde in care actioneaza damage overtime
	 * */
	public void setDamage(Hero h) {
		if (type == Map.Woods) {
			h.setRoundsPastDamage(6);
			h.setRoundsBlocked(6);
		} else {
			h.setRoundsPastDamage(3);
			h.setRoundsBlocked(3);
		}
	}

	/**
	 * metoda aplica damage-ul overtime si damage-ul din runda curenta
	 * 
	 * @param h
	 *            oponent
	 * @return damage
	 */
	public int apply(Hero h) {
		this.levelFieldDamage();
		this.setDamage(h);
		return damage;
	}
}
