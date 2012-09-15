package uk.org.downesward.traveller.language;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;

import uk.org.downesward.utiliites.Dice;


public class Language {
	private String mstrLanguage;
	private Integer mintTables;
	private Dice mDie = new Dice(6);
	private String mstrEndSyllable;
	private String mstrDescription;
	private String mstrAfter;
	private String mastrSyllables[][][] = new String[3][6][6];
	private String mastrInitial[][][] = new String[6][6][6];
	private String mastrVowel[][][] = new String[6][6][6];
	private String mastrFinal[][][] = new String[6][6][6];

	public Language(InputStream lang) throws IOException {
		Reader r = new InputStreamReader(lang);
		LineNumberReader input = new LineNumberReader(r);
		load(input);
	}

	public void setLanguage(String mstrLanguage) {
		this.mstrLanguage = mstrLanguage;
	}

	public String getLanguage() {
		return mstrLanguage;
	}

	public void setDescription(String mstrDescription) {
		this.mstrDescription = mstrDescription;
	}

	public String getDescription() {
		return mstrDescription;
	}

	private void load(LineNumberReader input) throws IOException {
		String strLine;
		String astrElements[];

		// Get the description
		strLine = getLine(input);
		astrElements = strLine.split("=");
		mstrDescription = astrElements[1].trim();

		// Get the number of Syllable tables
		strLine = getLine(input);
		astrElements = strLine.split("=");
		mintTables = Integer.parseInt(astrElements[1].trim());

		// Get the end Syllable
		strLine = getLine(input);
		astrElements = strLine.split("=");
		mstrEndSyllable = astrElements[1].trim();

		// Load the Syllable tables
		for (int i = 0; i < mintTables; i++) {
			if (mintTables == 2 && i == 1) {
				strLine = getLine(input);
				astrElements = strLine.split("=");
				mstrAfter = astrElements[1].trim();
			}
			for (int j = 0; j < 6; j++) {
				strLine = getLine(input);
				astrElements = strLine.split(",");
				for (int k = 0; k < astrElements.length; k++) {
					mastrSyllables[i][j][k] = astrElements[k].trim();
				}
			}
		}

		// Load the initial table
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				strLine = getLine(input);
				astrElements = strLine.split(",");
				for (int k = 0; k < astrElements.length; k++) {
					mastrInitial[i][j][k] = astrElements[k].trim();
				}
			}
		}

		// Load the vowel table
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				strLine = getLine(input);
				astrElements = strLine.split(",");
				for (int k = 0; k < astrElements.length; k++) {
					mastrVowel[i][j][k] = astrElements[k].trim();
				}
			}
		}

		// Load the final table
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				strLine = getLine(input);
				astrElements = strLine.split(",");
				for (int k = 0; k < astrElements.length; k++) {
					mastrFinal[i][j][k] = astrElements[k].trim();
				}
			}
		}
	}

	private String getLine(LineNumberReader input) throws IOException {
		String result = input.readLine();
		while (result != null && (result.startsWith("//") || result.trim().length() == 0)) {
			result = input.readLine();
		}
		return result;
	}

	public String generateWord() {
		return generateWord(mDie.roll());
	}

	public String generateWord(Integer syllables) {
		String strStructure;
		String strWord = "";

		// 1st Syllable is always on the basic table
		strStructure = mastrSyllables[0][mDie.roll() - 1][mDie.roll() - 1];

		strWord = generateSyllable(strStructure);

		for (int i = 2; i < syllables; i++) {
			switch (mintTables) {
			case 1:
				strStructure = mastrSyllables[0][mDie.roll() - 1][mDie.roll() - 1];
				strWord = strWord + generateSyllable(strStructure);
				break;
			case 2:
				if (strStructure.endsWith(mstrAfter)) {
					strStructure = mastrSyllables[0][mDie.roll() - 1][mDie
							.roll() - 1];
					strWord = strWord + generateSyllable(strStructure);
				} else {
					strStructure = mastrSyllables[1][mDie.roll() - 1][mDie
							.roll() - 1];
					strWord = strWord + generateSyllable(strStructure);
				}
				break;
			case 3:
				if (strStructure.endsWith("C")) {
					strStructure = mastrSyllables[2][mDie.roll() - 1][mDie
							.roll() - 1];
					strWord = strWord + generateSyllable(strStructure);
				} else {
					strStructure = mastrSyllables[1][mDie.roll() - 1][mDie
							.roll() - 1];
					strWord = strWord + generateSyllable(strStructure);
				}
				break;
			}
			if (strStructure.equals(mstrEndSyllable)) {
				break;
			}
		}

		return strWord;
	}

	private String generateSyllable(String struct) {
		String strSyllable = "";
		Boolean blnInitial = false;
		Boolean blnVowel = false;
		Boolean blnFinal = false;

		blnInitial = struct.startsWith("C");
		blnFinal = struct.endsWith("C");
		blnVowel = struct.contains("V");

		if (blnInitial) {
			strSyllable = strSyllable
					+ mastrInitial[mDie.roll() - 1][mDie.roll() - 1][mDie
							.roll() - 1];
		}
		if (blnVowel) {
			strSyllable = strSyllable
					+ mastrVowel[mDie.roll() - 1][mDie.roll() - 1][mDie.roll() - 1];
		}
		if (blnFinal) {
			strSyllable = strSyllable
					+ mastrFinal[mDie.roll() - 1][mDie.roll() - 1][mDie.roll() - 1];
		}

		return strSyllable;
	}
}
