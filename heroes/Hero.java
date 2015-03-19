package heroes;

import game.Field;
/**
 * Clasa abstracta Hero
 * 
 * @author Luciana Viziru
 *
 */
abstract public class Hero {

	private int HP; // HP erou
	protected int XP; // XP erou
	private int level; // level erou
	protected int line; // linie pozitie teren
	protected int column; // coloana pozitie teren
	private boolean blocked; // boolean incapacitare de miscare
	private int roundsBlocked; // numar de runde de incapacitare
	private boolean win; // boolean castigator runda
	private int pastDamage; // damage overtime
	private int roundsPastDamage; // runde damage overtime
	private boolean alive; // boolean erou viu/mort

	/**
	 * constructor fara parametri, initializeaza valorile tuturor membrilor
	 */
	public Hero() {
		setHP(0);
		XP = 0;
		level = 0;
		blocked = false;
		win = false;
		pastDamage = 0;
		roundsPastDamage = 0;
		roundsBlocked = 0;
		alive = true;
	}

	/**
	 * Constructor cu parametri, fixeaza linia si coloana unitatii din teren
	 * 
	 * @param line
	 *            line
	 * @param column
	 *            coloana
	 */
	public Hero(int line, int column) {
		this();
		this.line = line;
		this.column = column;
	}

	/**
	 * metoda recalculeaza XP si level
	 * 
	 * @param opponentLevel
	 *            folosit la calculul XP
	 */
	public void updateHero(int opponentLevel) {
		updateXP(opponentLevel);
		updateLevel();
	}

	/**
	 * metoda calculeaza levelul si in caz de level up, reface HP
	 */
	public void updateLevel() {
		if (level < (XP - 250) / 50 + 1) {
			level = ((XP - 250) / 50 + 1);
			HP = updateHP();
		}
	}

	/**
	 * 
	 * metoda de calculare a HP maxim
	 * 
	 * @return noul HP
	 */
	abstract public int updateHP();

	/**
	 * metoda de calcul al XP in caz de victorie
	 * 
	 * @param opponentLevel
	 *
	 */
	public void updateXP(int opponentLevel) {
		if (win) {
			XP = XP + Math.max(0, 200 - (getLevel() - opponentLevel));
		}
	}

	/**
	 * metoda calculeaza damage-ul aplicat pentru oponent de tip Rogue
	 * 
	 * @param r
	 *            oponent
	 * @param f
	 *            terenul luptei
	 * @return damage-ul final
	 */
	abstract public int harm(Rogue r, Field f);

	/**
	 * metoda calculeaza damage-ul aplicat pentru oponent de tip Knight
	 * 
	 * @param k
	 *            oponent
	 * @param f
	 *            terenul luptei
	 * @return damage-ul final
	 */
	abstract public int harm(Knight k, Field f);

	/**
	 * metoda calculeaza damage-ul aplicat pentru oponent de tip Pyromancer
	 * 
	 * @param p
	 *            oponent
	 * @param f
	 *            terenul luptei
	 * @return damage-ul final
	 */
	abstract public int harm(Pyromancer p, Field f);

	/**
	 * metoda calculeaza damage-ul aplicat pentru oponent de tip Wizard
	 * 
	 * @param w
	 *            oponent
	 * @param f
	 *            terenul luptei
	 * @return damage-ul final
	 */
	abstract public int harm(Wizard w, Field f);

	/**
	 * metoda accepta damage-ul de la oponent
	 * 
	 * @param h
	 *            oponent
	 * @param f
	 *            terenul luptei
	 */
	abstract public void beHarmed(Hero h, Field f);

	/**
	 * metoda de aplicare a damage-ului overtime apelata la inceputul rundei
	 * 
	 * in cazul in care eroul moare, se modifica booleanul alive la fals
	 */
	public void applyPastDamage() {
		if (roundsPastDamage != 0) {

			HP = HP - pastDamage;
			roundsPastDamage--;

			if (HP <= 0) {
				alive = false;
			}
		}
	}

	/**
	 * metoda schimba pozitia eroului in functie de datele citite, verificand
	 * mai intai incapacitarea
	 * 
	 * @param c
	 *            comanda de miscare
	 * @param n
	 *            numarul de linii al terenului
	 * @param m
	 *            numarul de coloane al terenului
	 */
	public void changePosition(Character c, int n, int m) {
		if (blocked) {
			roundsBlocked--;
			if (roundsBlocked == 0) {
				blocked = false;
			}
			return;
		}
		if (c == 'U') {
			line--;
			if (line < 0 || line >= n) {
				this.setAlive(false);
			}
		}
		if (c == 'D') {
			line++;
			if (line < 0 || line >= n) {
				this.setAlive(false);
			}
		}
		if (c == 'L') {
			column--;
			if (column < 0 || column >= m) {
				this.setAlive(false);
			}
		}
		if (c == 'R') {
			column++;
			if (column < 0 || column >= m) {
				this.setAlive(false);
			}
		}
	}

	/**
	 * metoda de afisare a starii eroului eroului
	 * 
	 * @return stringul cu starea eroului
	 */
	abstract public String print();

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getRoundsPastDamage() {
		return roundsPastDamage;
	}

	public void setRoundsPastDamage(int roundsPastDamage) {
		this.roundsPastDamage = roundsPastDamage;
	}

	public int getPastDamage() {
		return pastDamage;
	}

	public void setPastDamage(int pastDamage) {
		this.pastDamage = pastDamage;
	}

	public int getRoundsBlocked() {
		return roundsBlocked;
	}

	public void setRoundsBlocked(int roundsBlocked) {
		this.roundsBlocked = roundsBlocked;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

}
