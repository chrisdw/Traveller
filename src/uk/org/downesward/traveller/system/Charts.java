package uk.org.downesward.traveller.system;

import uk.org.downesward.utiliites.Dice;

public final class Charts {

	public static final int MAX_ORBITS = 20;
	public static final double HABITNUM = 374.025;
	public static final double EARTHMASS = 1086781291301.061;
	public static final double EARTHGRAV = 26716.103896;
	public static final int NUM_HEX_ROWS = 11;
	public static final double MKM_PER_AU = 149.6;
	public static final double DAYS_PER_YEAR = 365.25;

	public static enum OutputMode {
		VERBOSE, UPP, XML
	}

	public static enum StarPortTableType {
		BACKWATER, STANDARD, MATURE, CLUSTER
	}

	public static Dice objD3 = new Dice(3);
	public static Dice objD6 = new Dice(6);
	public static Dice objD10 = new Dice(10);

	// Configuration flags
	public static OutputMode CurrentOutputMode;
	public static StarPortTableType StarPortTable;
	public static boolean GaiaFactor;
	public static boolean FDegrees;
	public static boolean Collapse;
	public static boolean TravInfo;
	public static String BaseName;
	public static boolean ShouldDoCollapse;
	public static boolean HammersSlammers;
	public static boolean SpaceOpera;
	public static boolean HardScience;



	// Stellar Mass
	public static double MassTableIa[] = { 16, 16, 14, 11, 10, 8.1, 8.1, 10,
			11, 14, 16, 16 };
	// B0 B5 A0 A5 F0 F5 G0 G5 K0 K5 M0 M5

	public static double MassTableIb[] = { 16, 16, 14, 11, 10, 8.1, 8.1, 10,
			11, 14, 16, 16 };
	// B0 B5 A0 A5 F0 F5 G0 G5 K0 K5 M0 M5

	public static double MassTableII[] = { 16, 16, 14, 11, 10, 8.1, 8.1, 10,
			11, 14, 16, 16 };
	// B0 B5 A0 A5 F0 F5 G0 G5 K0 K5 M0 M5

	public static double MassTableIII[] = { 14, 14, 12, 9, 8, 5, 2.5, 3.2, 4,
			5, 6.3, 7.4 };
	// B0 B5 A0 A5 F0 F5 G0 G5 K0 K5 M0 M5

	public static double MassTableIV[] = { 10, 8, 6, 4, 2.5, 2, 1.75, 2, 2.3,
			0, 0, 0 };
	// B0 B5 A0 A5 F0 F5 G0 G5 K0 K5 M0 M5

	public static double MassTableV[] = { 6, 4, 3.2, 2.1, 1.7, 1.3, 1.04, 0.94,
			0.825, 0.57, 0.489, 0.331 };
	// B0 B5 A0 A5 F0 F5 G0 G5 K0 K5 M0 M5

	public static double MassTableD[] = { 0.3, 0.3, 0.36, 0.36, 0.42, 0.42,
			0.63, 0.63, 0.83, 0.83, 1.11, 1.11 };
	// B0 B5 A0 A5 F0 F5 G0 G5 K0 K5 M0 M5

	// Luminosiy Factor
	public static double LumTableIa[] = { 11.0, 10.0, 6.85, 5.4, 4.95, 4.75,
			4.86, 5.22, 5.46, 7.04, 8.24, 11.05, 11.28 };
	// B0 B5 A0 A5 F0 F5 G0 G5 K0 K5 M0 M5 M9 */

	public static double LumTableIb[] = { 11.0, 10.0, 6.85, 5.4, 4.95, 4.75,
			4.86, 5.22, 5.46, 7.04, 8.24, 11.05, 11.28 };
	// B0 B5 A0 A5 F0 F5 G0 G5 K0 K5 M0 M5 M9 */

	public static double LumTableII[] = { 11.0, 10.0, 6.85, 5.4, 4.95, 4.75,
			4.86, 5.22, 5.46, 7.04, 8.24, 11.05, 11.28 };
	// B0 B5 A0 A5 F0 F5 G0 G5 K0 K5 M0 M5 M9 */

	public static double LumTableIII[] = { 8.0, 6.0, 4.09, 3.08, 2.7, 2.56,
			2.66, 2.94, 3.12, 4.23, 4.65, 6.91, 7.2 };
	// B0 B5 A0 A5 F0 F5 G0 G5 K0 K5 M0 M5 M9 */

	public static double LumTableIV[] = { 6.0, 4.0, 3.53, 2.47, 2.09, 1.86,
			1.6, 1.49, 1.47, 1.27, 1.25, 1.1, 1.02 };
	// B0 B5 A0 A5 F0 F5 G0 G5 K0 K5 M0 M5 M9 */

	public static double LumTableV[] = { 4.2, 3.5, 3.08, 2.0, 1.69, 1.37, 1.05,
			0.9, 0.81, 0.53, 0.45, 0.29, 0.18 };
	// B0 B5 A0 A5 F0 F5 G0 G5 K0 K5 M0 M5 M9 */

	public static double LumTableD[] = { 0.37, 0.31, 0.27, 0.27, 0.13, 0.13,
			0.09, 0.09, 0.08, 0.08, 0.07, 0.07, 0.07 };
	// B0 B5 A0 A5 F0 F5 G0 G5 K0 K5 M0 M5 M9 */

	// Energy Absorption Factors */
	public static double EnergyAbsorbHZ[][] = { { 0.9, 0.9, 0.74, 0.9 },
			{ 0.829, 0.9, 0.697, 0.9 }, { 0.803, 0.86, 0.672, 0.882 },
			{ 0.811, 0.86, 0.676, 0.883 }, { 0.782, 0.82, 0.648, 0.866 },
			{ 0.789, 0.78, 0.613, 0.85 }, { 0.795, 0.74, 0.577, 0.836 },
			{ 0.802, 0.7, 0.539, 0.821 }, { 0.808, 0.66, 0.5, 0.807 },
			{ 0.814, 0.62, 0.5, 0.793 }, { 0.818, 0.619, 0.5, 0.773 } };

	public static double EnergyAbsorbNHZ[][] = { { 0.8, 0.8, 0.68, 0.8 },
			{ 0.744, 0.811, 0.646, 0.811 }, { 0.736, 0.789, 0.635, 0.807 },
			{ 0.752, 0.799, 0.644, 0.817 }, { 0.738, 0.774, 0.625, 0.813 },
			{ 0.753, 0.747, 0.599, 0.809 }, { 0.767, 0.718, 0.57, 0.805 },
			{ 0.782, 0.687, 0.537, 0.8 }, { 0.796, 0.654, 0.5, 0.794 },
			{ 0.81, 0.619, 0.5, 0.787 }, { 0.818, 0.619, 0.5, 0.773 } };

	// Greenhouse Factor */
	public static double GreenHouse[] = { 1.0, 1.0, 1.0, 1.0, 1.05, 1.05, 1.1,
			1.1, 1.15, 1.15, 1.15, 1.0, 1.1, 1.15, 1.0, 1.1 };

	// Latitude Temperature Modifiers */
	public static double LatitudeMods[][] = {
			{ 6, 9, 12, 13, 15, 16, 18, 17, 21, 22, 24 },
			{ 4, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16 },
			{ 2, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ -2, -3, -4, -4, -5, -5, -6, -6, -7, -7, -8 },
			{ -4, -6, -8, -9, -10, -11, -12, -13, -14, -15, -16 },
			{ -6, -9, -12, -13, -15, -16, -18, -17, -21, -22, -24 },
			{ -8, -12, -16, -18, -20, -22, -24, -26, -28, -30, -32 },
			{ -10, -15, -20, -22, -25, -27, -30, -32, -35, -37, -40 },
			{ -12, -18, -24, -27, -30, -33, -36, -39, -42, -45, -48 },
			{ -14, -21, -28, -31, -35, -38, -42, -45, -49, -52, -56 } };

	// Axial Tilt Effects
	public static double AxialTiltEffects[][] = {
			{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.25, 0.5, 0.75, 1.0 },
			{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.25, 0.5, 0.75, 1.0, 1.0 },
			{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.25, 0.5, 0.75, 1.0, 1.0, 1.0 },
			{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.25, 0.5, 0.75, 1.0, 1.0, 1.0, 1.0 },
			{ 0.0, 0.0, 0.0, 0.0, 0.25, 0.5, 0.75, 1.0, 1.0, 1.0, 1.0, 1.0 },
			{ 0.0, 0.0, 0.0, 0.25, 0.5, 0.75, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 },
			{ 0.0, 0.0, 0.25, 0.5, 0.75, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 },
			{ 0.0, 0.0, 0.5, 0.75, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 },
			{ 0.0, 0.25, 0.75, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 },
			{ 0.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 },
			{ 0.25, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 } };

	public static Integer d3() {
		return objD3.roll();
	}

	public static Integer d6() {
		return objD6.roll();
	}

	public static Integer d10() {
		return objD10.roll();
	}

	public static double calcGaiaFactor(double l, double o, double g, double e) {
		// Calculate Ideal E
		double Ideal;
		double TheFactor;

		Ideal = 288.0 / l / o / g;
		TheFactor = (Ideal - e) / 2;

		if (TheFactor > 0.0) {
			if (TheFactor > 0.1) {
				TheFactor = 0.1;
			}
		} else {
			if (TheFactor < -0.1) {
				TheFactor = -0.1;
			}
		}

		return (e + TheFactor);
	}

	public static double Change(double Original) {
		double X;
		double Y;

		X = (d10() * 10) + d10();
		Y = X / 100;

		if (d6() < 4) {
			// ' reduce value
			return (Original * Y);
		} else {
			return (Original * (1.0 + Y));
		}
	}

	public static double CtoF(double CDegrees) {
		return ((CDegrees * 1.8) + 32);
	}
}
