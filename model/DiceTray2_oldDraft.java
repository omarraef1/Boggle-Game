package model;

import java.util.Random;

// Represent a major piece of Boggle. 
//
// authors: Rick Mercer and Omar R. Gebril
//
public class DiceTray2_oldDraft {

	// Constructor takes a 2D array of characters that represents the
	// Boggle DiceTray with 16 dice already rolled in a known fixed state.
	private Character[][] x;

	public DiceTray2_oldDraft(char[][] array) {
		x = new Character[array.length][array[0].length];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				x[i][j] = Character.toLowerCase(array[i][j]);
			}
		}
	}

	// This constructor creates a a DiceTray that simulates the rolling of the 16
	// Boggle dice to begin a game of Boggle. The same die must not always appear
	// in the same location. Here are the letters of each of the 16 six-sided dice
	// where six consecutive letters represent one die. L R Y T T E are the letters
	// on one Boggle die-the one in the upper left corner-for example:
	//
	// L R Y T T E ... V T H R W E ... E G H W N E ... S E O T I S
	// A N A E E G ... I D S Y T T ... O A T T O W ... M T O I C U
	// A F P K F S ... X L D E R I ... H C P O A S ... E N S I E U
	// Y L D E V R ... Z N R N H L ... N M I H U Qu .. O B B A O J
	//
	public DiceTray2_oldDraft() {
		x = new Character[4][4];
		String possibility[][] = new String[4][4];
		Random gen = new Random();
		int[] rowInts = new Random().ints(0, 4).distinct().limit(4).toArray();
		int[] colInts = new Random().ints(0, 4).distinct().limit(4).toArray();
		possibility[rowInts[0]][colInts[0]] = "LRYTTE";
		possibility[rowInts[0]][colInts[1]] = "VTHRWE";
		possibility[rowInts[0]][colInts[2]] = "EGHWNE";
		possibility[rowInts[0]][colInts[3]] = "SEOTIS";
		possibility[rowInts[1]][colInts[0]] = "ANAEEG";
		possibility[rowInts[1]][colInts[1]] = "IDSYTT";
		possibility[rowInts[1]][colInts[2]] = "OATTOW";
		possibility[rowInts[1]][colInts[3]] = "MTOICU";
		possibility[rowInts[2]][colInts[0]] = "AFPKFS";
		possibility[rowInts[2]][colInts[1]] = "XLDERI";
		possibility[rowInts[2]][colInts[2]] = "HCPOAS";
		possibility[rowInts[2]][colInts[3]] = "ENSIEU";
		possibility[rowInts[3]][colInts[0]] = "YLDEVR";
		possibility[rowInts[3]][colInts[1]] = "ZNRNHL";
		possibility[rowInts[3]][colInts[2]] = "NMIHUQ";
		possibility[rowInts[3]][colInts[3]] = "OBBAOJ";
		for (int r = 0; r < x.length; r++) {
			for (int c = 0; c < x[r].length; c++) {
				int pos = gen.nextInt(6);
				x[r][c] = possibility[r][c].charAt(pos);
				// System.out.print(x[r][c] + " ");
			}
			// System.out.println("");
		}
	}

	// Return true if str is found in the this DiceTray according to Boggle
	// rules. Note: This method does NOT check to see if the word is in the
	// list of words.
	//
	// Precondition: str.length() >= 3

	// String foundStr = "";
	public boolean found(String str) {
		Character[][] y = new Character[x.length][x[0].length];
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[0].length; j++) {
				y[i][j] = Character.toLowerCase(x[i][j]);
			}
		}
		String newStr = str.toLowerCase();
		String foundStr = "";
		int prevI = -1;
		int prevJ = -1;
		for (int h = 0; h < newStr.length(); h++) {
			for (int i = 0; i < y.length; i++) {
				for (int j = 0; j < y[0].length; j++) {
					if (newStr.charAt(h) == y[i][j]) {
						// if prev's are -1's
						if (prevI == -1 && prevJ == -1) {
							if (newStr.charAt(h) == 'q') {
								foundStr += y[i][j];
								foundStr += 'u';
								y[i][j] = ' ';
								prevI = i;
								prevJ = j;
							} else {
								foundStr += y[i][j];
								y[i][j] = ' ';
								prevI = i;
								prevJ = j;
							}
						} else {
							if ((prevI == i - 1 || prevI == i || prevI == i + 1)
									&& (prevJ == j + 1 || prevJ == j || prevJ == j - 1)) {
								if (newStr.charAt(h) == 'q') {
									foundStr += y[i][j];
									foundStr += 'u';
									y[i][j] = ' ';
									prevI = i;
									prevJ = j;
								} else {
									foundStr += y[i][j];
									y[i][j] = ' ';
									prevI = i;
									prevJ = j;
								}
							}
						}
					}
				}
			}
		}
		// System.out.println(foundStr+ " "+newStr);
		if (foundStr.equals(newStr)) {
			return true;
		}
		return false;
	}
}
