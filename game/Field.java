package game;

import heroes.Hero;

/**
 * Clasa a unitatii de teren pe care de desfasoara lupta
 * 
 * @author Luciana Viziru
 *
 */
public class Field {

	private Map type;
	private Hero[] h;
	private boolean fight;

	/**
	 * Constructor fara parametri creeaza un vector de referinte la obiecte de
	 * tip erou si seteaza booleanul fight pe valorea fals
	 */
	public Field() {
		h = new Hero[2];
		h[0] = null;
		h[1] = null;
		fight = false;
	}

	/**
	 * Constructor cu parametri
	 *
	 * @param type
	 *            seteaza tipul terenului
	 */
	public Field(Map type) {
		this();
		this.type = type;
	}

	/**
	 * metoda ce adauga un obiect de tip hero in vectorul terenului, cu
	 * certitudinea ce se vor adauga maxim 2 eroi
	 * 
	 * daca s-au adaugat 2 eroi, terenul poate sustine o lupta
	 * 
	 * @param h
	 *            eroul de adaugat
	 */
	public void addHero(Hero h) {
		if (this.h[0] == null) {
			this.h[0] = h;
		} else {
			this.h[1] = h;
			if (this.h[0].isAlive() && this.h[1].isAlive()) {
				fight = true;

			}
		}
	}

	/**
	 * metoda intoarce tipul terenului
	 * 
	 * @return type
	 */
	public Map getType() {
		return type;
	}

	/**
	 * metoda verifica daca terenul este gol, nu exista eroi
	 */
	public boolean isEmpty() {
		return h[0] == null;
	}

	/**
	 * metoda aplica damage-ul overtime din rundele precedente, daca este cazul
	 * 
	 * daca unul dintre eroi moare din cauza acestui damage, nu se mai
	 * desfasoara lupta
	 */
	public void prepareFight() {
		h[0].applyPastDamage();
		if (!h[0].isAlive()) {
			fight = false;
		}
		if (h[1] != null) {
			h[1].applyPastDamage();
			if (!h[1].isAlive()) {
				fight = false;
			}
		}
	}

	/**
	 * metoda verifica daca terenul este pregatit pentru o lupta
	 * 
	 * @return boolean fight
	 */
	public boolean isFight() {
		return fight;
	}

	/**
	 * metoda desfasoara lupta dintre cei doi eroi si verifica daca au ramas in
	 * viata si daca exista castigatori
	 */
	public void Fight() {
		h[0].beHarmed(h[1], this);
		h[1].beHarmed(h[0], this);

		if (h[0].getHP() <= 0) {
			h[0].setAlive(false);
			h[1].setWin(true);
		}
		if (h[1].getHP() <= 0) {
			h[1].setAlive(false);
			h[0].setWin(true);
		}
	}

	/**
	 * metoda incheie lupta, recalculand XP si level si
	 * reseteaza unitatea de teren
	 */
	public void finishRound() {

		if (fight) {
			if (h[0].isAlive()) {
				h[0].updateHero(h[1].getLevel());
			}
			if (h[1].isAlive()) {
				h[1].updateHero(h[0].getLevel());
			}
		}

		h[0] = null;
		h[1] = null;
		fight = false;
	}

}
