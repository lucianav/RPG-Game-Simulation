package heroes;

import spells.Backstab;
import spells.Paralysis;
import game.Field;
import game.Map;

/**
 * clasa erou tip Rogue
 * 
 * @author Luciana Viziru
 *
 */
public class Rogue extends Hero {

	protected static Backstab backstab = new Backstab();// spell backstab
	protected static Paralysis paralysis = new Paralysis();// spell paralysis
	protected int criticalHit;// numarator critical hit
	protected boolean isCritical;// boolean runda in care aplica critical hit

	/**
	 * constructor fara parametrii, initializeaza HP-ul si indicatorii de
	 * critical hit
	 */
	public Rogue() {
		super();
		setHP(600);
		criticalHit = 0;
		isCritical = false;
	}

	/**
	 * constructor cu parametrii, fixeaza pozitia eroului si intializeaza HP-ul
	 * si indicatorii de critical hit
	 * 
	 * @param line
	 * @param column
	 */
	public Rogue(int line, int column) {
		super(line, column);
		setHP(600);
		criticalHit = 0;
		isCritical = false;
	}

	/**
	 * metoda de calcul a HP maxim
	 */
	public int updateHP() {
		return 600 + 40 * getLevel();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * se aplica damage-ul overtime pentru paralysis si se verifica toate
	 * conditiile de critical hit
	 */
	@Override
	public int harm(Rogue r, Field f) {
		setLevelType(f);

		r.setPastDamage(Math.round(paralysis.apply(r) * 0.9f));

		int damage = Math.round(paralysis.apply(r) * 0.9f);

		if (criticalHit % 3 == 0 && f.getType() == Map.Woods) {
			damage += Math.round(Math.round(backstab.apply(r) * 1.5f) * 1.2f);
			isCritical = true;
		} else {
			damage += Math.round(backstab.apply(r) * 1.2f);
			isCritical = false;
		}

		criticalHit++;
		return damage;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * se aplica damage-ul overtime pentru paralysis si se verifica toate
	 * conditiile de critical hit
	 */
	@Override
	public int harm(Knight k, Field f) {
		setLevelType(f);

		k.setPastDamage(Math.round(paralysis.apply(k) * 0.8f));

		int damage = Math.round(paralysis.apply(k) * 0.8f);

		if (criticalHit % 3 == 0 && f.getType() == Map.Woods) {
			damage += Math.round(Math.round(backstab.apply(k) * 1.5f) * 0.9f);
			isCritical = true;
		} else {
			damage += Math.round(backstab.apply(k) * 0.9f);
			isCritical = false;
		}

		criticalHit++;
		return damage;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * se aplica damage-ul overtime pentru paralysis si se verifica toate
	 * conditiile de critical hit
	 */
	@Override
	public int harm(Pyromancer p, Field f) {
		setLevelType(f);

		p.setPastDamage(Math.round(paralysis.apply(p) * 1.2f));

		int damage = Math.round(paralysis.apply(p) * 1.2f);

		if (criticalHit % 3 == 0 && f.getType() == Map.Woods) {
			damage += Math.round(Math.round(backstab.apply(p) * 1.5f) * 1.25f);
			isCritical = true;
		} else {
			damage += Math.round(backstab.apply(p) * 1.25f);
			isCritical = false;
		}

		criticalHit++;
		return damage;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * se aplica damage-ul overtime pentru paralysis si se verifica toate
	 * conditiile de critical hit
	 */
	@Override
	public int harm(Wizard w, Field f) {
		setLevelType(f);

		w.setPastDamage(Math.round(paralysis.apply(w) * 1.25f));

		int damage = Math.round(paralysis.apply(w) * 1.25f);

		if (criticalHit % 3 == 0 && f.getType() == Map.Woods) {
			damage += Math.round(Math.round(backstab.apply(w) * 1.5f) * 1.25f);
			isCritical = true;
		} else {
			damage += Math.round(backstab.apply(w) * 1.25f);
			isCritical = false;
		}

		criticalHit++;
		return damage;
	}

	/**
	 * {@inheritDoc}
	 */
	public void beHarmed(Hero h, Field f) {
		this.setHP(this.getHP() - h.harm(this, f));
	}

	/**
	 * metoda seteaza levelul si tipul terenului pt cele doua spelluri folosite
	 * de Rogue
	 * 
	 * @param f
	 *            terenul luptei
	 */
	protected void setLevelType(Field f) {
		backstab.setLevel(this.getLevel());
		backstab.setType(f.getType());
		paralysis.setLevel(this.getLevel());
		paralysis.setType(f.getType());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String print() {
		String s = new String("R ");
		if (isAlive()) {
			s = s + getLevel() + " " + XP + " " + 
						getHP() + " " + line + " " + column;
		} else {
			s = s + "dead";
		}
		return s;
	}
}
