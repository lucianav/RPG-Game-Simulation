package spells;

import game.Map;
import heroes.Hero;

/**
 * clasa spell tip Ignite
 * 
 * @author Luciana Viziru
 *
 */
public class Ignite extends Spell {

	private int damage; // damage ce va fi aplicat
	private int futureDamage; // damage overtime ce va fi aplicat

	/**
	 * constructor fara parametrii, seteaza damage-ul de baza
	 */
	public Ignite() {
		baseDamage = 150;
		damage = 0;
		futureDamage = 0;
	}

	/**
	 * metoda modifica damage-ul curent si cel overtime in functie de level si
	 * teren
	 * 
	 * @return damage
	 */
	public int levelFieldDamage() {
		damage = baseDamage + 20 * level;
		futureDamage = 50 + 30 * level;
		if (type == Map.Volcanic) {
			damage = Math.round(damage * 1.25f);
			futureDamage = Math.round(futureDamage * 1.25f);
		}
		return damage;
	}

	/**
	 * metoda aplica damage-ul total si pe cel overtime
	 * 
	 * @param h
	 *            oponent
	 * @return damage
	 */
	public int apply(Hero h) {
		this.levelFieldDamage();
		h.setRoundsPastDamage(2);
		h.setPastDamage(futureDamage);
		return damage;
	}

}
