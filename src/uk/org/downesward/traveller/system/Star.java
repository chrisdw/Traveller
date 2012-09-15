package uk.org.downesward.traveller.system;

public class Star {
	// Oh, be a fine girl, kiss me
	public enum StellarType {
		A, B, F, G, K, M, O
	};

	public static final int FAR_ORBIT = 99;

	protected int classRoll;
	public CompanionStar[] companionStars = new CompanionStar[2];

	public char decClass;
	public short hZone;
	public char lumClass;
	public double luminosity;
	public String name;
	public int numCompanions;
	public short numOrbits;
	public Orbit[] orbits;
	public double stellarMass;
	public StellarType type;
	protected int typeRoll;
	public CompanionStar[] companions = new CompanionStar[2];
	public String Name;

	public Star() {
		type = starType();
		lumClass = getLumClass();
		decClass = getDecClass();
		stellarMass = getStellarMass();
		luminosity = getLuminosity();
		numOrbits = getNumOrbits();
		orbits = new Orbit[numOrbits];
		numCompanions = 0;
	}

	public Star(StellarType StType, char SClass, char DecimalClass) {
		type = StType;
		lumClass = SClass;
		decClass = DecimalClass;
		stellarMass = getStellarMass();
		luminosity = getLuminosity();
		numOrbits = getNumOrbits();
		orbits = new Orbit[numOrbits];
		numCompanions = 0;
	}

	public char getDecClass() {
		int dieroll;

		dieroll = Charts.d10() - 1;

		if ((type == StellarType.O) && (dieroll < 5)) {
			dieroll = dieroll + 5;
		}

		if ((type == StellarType.K) && (lumClass == '4') && (dieroll > 4)) {
			dieroll = dieroll - 5;
		}

		switch (dieroll) {
		case 0:
			return '0';
		case 1:
			return '1';
		case 2:
			return '2';
		case 3:
			return '3';
		case 4:
			return '4';
		case 5:
			return '5';
		case 6:
			return '6';
		case 7:
			return '7';
		case 8:
			return '8';
		case 9:
			return '9';
		default:
			return '5';
		}

	}

	public char getLumClass() {
		int dieroll;

		// Check for rare values
		dieroll = Charts.d6() + Charts.d6() + Charts.d6();
		classRoll = 2;
		if (dieroll == 3) {
			return 'a';
		}
		if (dieroll == 4) {
			return 'c';
		}

		dieroll = Charts.d6() + Charts.d6();

		classRoll = dieroll;

		switch (dieroll) {
		case 2:
			return '2';
		case 3:
			return '3';
		case 4:
			if (type == StellarType.M) {
				return '5';
			} else {
				return '4';
			}
		case 5:
			return '5';
		case 6:
			return '5';
		case 7:
			return '5';
		case 8:
			return '5';
		case 9:
			return '5';
		case 10:
			return '5';
		case 11:
			return '5';
		case 12:
			return '5';
		default:
			return ' ';
		}
	}

	public StellarType starType() {
		int dieRoll;

		// Check for rare values
		dieRoll = Charts.d6() + Charts.d6() + Charts.d6();
		typeRoll = 2;

		if (dieRoll == 3) {
			return StellarType.O;
		}
		if (dieRoll == 4) {
			return StellarType.B;
		}

		dieRoll = Charts.d6() + Charts.d6();

		typeRoll = dieRoll;

		switch (dieRoll) {
		case 2:
			return StellarType.A;
		case 3:
			return StellarType.M;
		case 4:
			return StellarType.M;
		case 5:
			return StellarType.M;
		case 6:
			return StellarType.M;
		case 7:
			return StellarType.M;
		case 8:
			return StellarType.K;
		case 9:
			return StellarType.G;
		case 10:
			return StellarType.G;
		case 11:
			return StellarType.F;
		case 12:
			return StellarType.F;
		default:
			return StellarType.F;
		}
	}

	public double getStellarMass() {
		int StarSize = 5;
		double A = 0;
		double B = 0;
		double X;

		switch (type) {
		case O:
			StarSize = 0;
			break;
		case B:
			StarSize = 0;
			break;
		case A:
			StarSize = 2;
			break;
		case F:
			StarSize = 4;
			break;
		case G:
			StarSize = 6;
			break;
		case K:
			StarSize = 8;
			break;
		case M:
			StarSize = 10;
			break;
		}

		X = (int) decClass - (int) '0';

		switch (lumClass) {
		case 'a':
			A = Charts.MassTableIa[StarSize];
			B = Charts.MassTableIa[StarSize + 1];
			break;
		case 'b':
			A = Charts.MassTableIb[StarSize];
			B = Charts.MassTableIb[StarSize + 1];
			break;
		case '2':
			A = Charts.MassTableII[StarSize];
			B = Charts.MassTableII[StarSize + 1];
			break;
		case '3':
			A = Charts.MassTableIII[StarSize];
			B = Charts.MassTableIII[StarSize + 1];
			break;
		case '4':
			A = Charts.MassTableIV[StarSize];
			B = Charts.MassTableIV[StarSize + 1];
			break;
		case '5':
			A = Charts.MassTableV[StarSize];
			B = Charts.MassTableV[StarSize + 1];
			break;
		case 'D':
			A = Charts.MassTableD[StarSize];
			B = Charts.MassTableD[StarSize + 1];
			break;
		}

		return ((((B - A) / 5) * X) + A);
	}

	public double getLuminosity() {
		int StarSize = 5;
		double A = 0;
		double B = 0;
		double X;

		switch (type) {
		case O:
			StarSize = 0;
			break;
		case B:
			StarSize = 0;
			break;
		case A:
			StarSize = 2;
			break;
		case F:
			StarSize = 4;
			break;
		case G:
			StarSize = 6;
			break;
		case K:
			StarSize = 8;
			break;
		case M:
			StarSize = 10;
			break;
		}

		X = (int) decClass - (int) '0';

		switch (lumClass) {
		case 'a':
			A = Charts.LumTableIa[StarSize];
			B = Charts.LumTableIa[StarSize + 1];
			if ((type == StellarType.M) && (decClass == '9')) {
				return (Charts.LumTableIa[12]);
			}
			break;
		case 'b':
			A = Charts.LumTableIb[StarSize];
			B = Charts.LumTableIb[StarSize + 1];
			if ((type == StellarType.M) && (decClass == '9')) {
				return (Charts.LumTableIb[12]);
			}
			break;
		case '2':
			A = Charts.LumTableII[StarSize];
			B = Charts.LumTableII[StarSize + 1];
			if ((type == StellarType.M) && (decClass == '9')) {
				return (Charts.LumTableII[12]);
			}
			break;
		case '3':
			A = Charts.LumTableIII[StarSize];
			B = Charts.LumTableIII[StarSize + 1];
			if ((type == StellarType.M) && (decClass == '9')) {
				return (Charts.LumTableIII[12]);
			}
			break;
		case '4':
			A = Charts.LumTableIV[StarSize];
			B = Charts.LumTableIV[StarSize + 1];
			if ((type == StellarType.M) && (decClass == '9')) {
				return (Charts.LumTableIV[12]);
			}
			break;
		case '5':
			A = Charts.LumTableV[StarSize];
			B = Charts.LumTableV[StarSize + 1];
			if ((type == StellarType.M) && (decClass == '9')) {
				return (Charts.LumTableV[12]);
			}
			break;
		case 'D':
			A = Charts.LumTableD[StarSize];
			B = Charts.LumTableD[StarSize + 1];
			if ((type == StellarType.M) && (decClass == '9')) {
				return (Charts.LumTableD[12]);
			}
			break;
		}

		return ((((B - A) / 5) * X) + A);

	}

	public short getNumOrbits() {
		int dieroll;

		dieroll = Charts.d6() + Charts.d6();

		// Class O stars (Blue) are still forming */
		if (type == StellarType.O) {
			return (0);
		}

		if (lumClass == '1') {
			dieroll = dieroll + 8;
		}
		;
		if (lumClass == 'a') {
			dieroll = dieroll + 8;
		}
		if (lumClass == 'b') {
			dieroll = dieroll + 8;
		}
		if (lumClass == '2') {
			dieroll = dieroll + 8;
		}
		if (lumClass == '3') {
			dieroll = dieroll + 4;
		}
		if (lumClass == '4') {
			dieroll = dieroll + 2;
		}

		if (type == StellarType.K) {
			dieroll = dieroll - 2;
		}
		if (type == StellarType.M) {
			dieroll = dieroll - 4;
		}

		if (dieroll < 0) {
			return (0);
		}
		if (dieroll > 19) {
			return (19);
		}

		return (short) dieroll;

	}

	public short fleshOut() {
		double ComLumAddFromPrim = 0.0;
		short SystemHabitability = 0;
		short k;

		if (numCompanions > 0) {
			this.Name = Charts.BaseName + "-A";
		} else {
			this.Name = Charts.BaseName;
		}

		SystemHabitability = fleshOutWorlds(ComLumAddFromPrim);

		for (int i = 0; i < numCompanions; i++) {
			// Set up the Stellar information for the companions
			if (companions[i].orbitNum != FAR_ORBIT) {
				ComLumAddFromPrim = Charts.HABITNUM
						/ Math.sqrt(orbits[companions[i].orbitNum].range);
				orbits[companions[i].orbitNum].world.normal.remarks = companions[i]
						.DisplayString();
			} else {
				ComLumAddFromPrim = 0.0;
			}
			// Initialize System and Worlds */

			k = 0;

			companions[i].Name = Charts.BaseName + "-"
					+ (char) ((int) 'A' + i + 1);
			companions[i].BuildSystem(ComLumAddFromPrim);

			// Flesh Out Worlds */
			k = companions[i].fleshOut(ComLumAddFromPrim);
			if (k > SystemHabitability) {
				SystemHabitability = k;
			}
		}
		return (SystemHabitability);
	}

	public short fleshOut(double ComLumAddFromPrim) {
		short SystemHabitability = 0;
		short k;

		SystemHabitability = fleshOutWorlds(ComLumAddFromPrim);

		for (int i = 0; i < numCompanions; i++) {
			// Set up the Stellar information for the companions
			if (companions[i].orbitNum != FAR_ORBIT) {
				ComLumAddFromPrim = Charts.HABITNUM
						/ Math.sqrt(orbits[companions[i].orbitNum].range);
				orbits[companions[i].orbitNum].world.normal.remarks = companions[i]
						.DisplayString();
			} else {
				ComLumAddFromPrim = 0.0;
			}

			// Initialize System and Worlds */

			k = 0;

			companions[i].BuildSystem(ComLumAddFromPrim);

			// Flesh Out Worlds */
			k = companions[i].fleshOut(ComLumAddFromPrim);
			if (k > SystemHabitability) {
				SystemHabitability = k;
			}
		}
		return (SystemHabitability);
	}

	public void BuildSystem(double comLumAddFromPrim) {
		// TODO Auto-generated method stub

	}

	private short fleshOutWorlds(double comLumAddFromPrim) {
		// TODO Auto-generated method stub
		return 0;
	}
}