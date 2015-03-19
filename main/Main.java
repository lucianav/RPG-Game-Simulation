package main;

import game.*;
import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import api.main.*;

/**
 * Clasa de implementare a metodei de main
 * 
 * @author Luciana Viziru
 *
 */
public class Main {

	public static void main(String[] args) {

		int n, m, i, j, k, p, r;

		/**
		 * deschidere fisiere
		 */
		FileIO in = new FileIO(args[0], true);
		FileIO out = new FileIO(args[1], false);

		String line = in.readLine();

		String[] v = line.split(" ");

		/**
		 * citirea dimensiunilor campului de lupta
		 */
		n = Integer.valueOf(v[0]);
		m = Integer.valueOf(v[1]);

		/**
		 * crearea matricei campului
		 */
		Field field[][] = new Field[n][m];

		for (i = 0; i < n; i++) {

			line = in.readLine();

			for (j = 0; j < m; j++) {

				if (line.charAt(j) == 'L') {
					field[i][j] = new Field(Map.Land);
				}
				if (line.charAt(j) == 'V') {
					field[i][j] = new Field(Map.Volcanic);
				}
				if (line.charAt(j) == 'D') {
					field[i][j] = new Field(Map.Desert);
				}
				if (line.charAt(j) == 'W') {
					field[i][j] = new Field(Map.Woods);
				}
			}
		}

		line = in.readLine();
		v = line.split(" ");

		/**
		 * crearea vectorului de referinte la obiecte de tip erou si citirea
		 * eroilor
		 */
		p = Integer.valueOf(v[0]);
		Hero heroes[] = new Hero[p];

		for (i = 0; i < p; i++) {

			line = in.readLine();
			v = line.split(" ");

			if (line.charAt(0) == 'W') {
				heroes[i] = new Wizard(Integer.valueOf(v[1]),
										Integer.valueOf(v[2]));
			}
			if (line.charAt(0) == 'R') {
				heroes[i] = new Rogue(Integer.valueOf(v[1]),
										Integer.valueOf(v[2]));
			}
			if (line.charAt(0) == 'P') {
				heroes[i] = new Pyromancer(Integer.valueOf(v[1]), 
											Integer.valueOf(v[2]));
			}
			if (line.charAt(0) == 'K') {
				heroes[i] = new Knight(Integer.valueOf(v[1]), 
											Integer.valueOf(v[2]));
			}
		}

		/**
		 * simularea fiecarei runde
		 */
		line = in.readLine();

		v = line.split(" ");
		r = Integer.valueOf(v[0]);

		for (i = 0; i < r; i++) {

			line = in.readLine();
			// modificarea pozitiilor eroilor
			for (j = 0; j < p; j++) {
				heroes[j].changePosition(line.charAt(j), n, m);
			}
			// aseazarea eroilor in viata pe harta
			for (j = 0; j < p; j++) {
				if (heroes[j].isAlive()) {
					field[heroes[j].getLine()][heroes[j].getColumn()].
															addHero(heroes[j]);
				}
			}
			// aplicarea damage-ului overtime si curent
			for (j = 0; j < n; j++) {
				for (k = 0; k < m; k++) {
					if (!field[j][k].isEmpty()) {
						field[j][k].prepareFight();
					}
					if (field[j][k].isFight()) {
						field[j][k].Fight();
					}
					field[j][k].finishRound();

				}
			}
		}

		/**
		 * afisarea starii finale a eroilor
		 */
		for (j = 0; j < p; j++) {
			out.writeLine(heroes[j].print());
		}

		/**
		 * inchiderea fisierelor
		 */
		in.close();
		out.close();

	}

}