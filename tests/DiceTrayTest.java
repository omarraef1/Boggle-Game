package tests;

// A unit test for class DiceTray
//
// authors: Rick Mercer and Omar R. Gebril
//
import static org.junit.Assert.*;
import org.junit.Test;

import model.DiceTray;
import model.DiceTray2_oldDraft;

public class DiceTrayTest {

	private static char[][] tray = { // Always use upper case letters in the dice tray
			{ 'A', 'B', 'C', 'D' },

			{ 'E', 'F', 'G', 'H' },

			{ 'I', 'J', 'K', 'L' },

			{ 'M', 'N', 'O', 'P' } };

	@Test
	public void testStringFindWhenThereStartingInUpperLeftCorner() {
		DiceTray2_oldDraft bt = new DiceTray2_oldDraft(tray);
		assertFalse(bt.found("zld"));
		assertTrue(bt.found("PON"));
		assertTrue(bt.found("POJ"));
		assertFalse(bt.found("POf"));
		assertTrue(bt.found("ABC"));
		assertTrue(bt.found("AbC")); // Must be case insensitive
		assertTrue(bt.found("abE"));
		assertTrue(bt.found("abF"));
		assertTrue(bt.found("abG"));
		assertTrue(bt.found("bGk"));
		assertTrue(bt.found("ABCD"));
		assertFalse(bt.found("zld"));
		assertFalse(bt.found("ABD"));
		assertFalse(bt.found("pld"));
		assertFalse(bt.found("ABA"));

		// ...
		assertTrue(bt.found("ABFEJINM"));
		assertTrue(bt.found("AbCdHgFeIjKLpONm"));
		assertTrue(bt.found("ABCDHLPOKJNMIEFG"));
	}

	private static char[][] tray2 = { // Always use upper case letters in the dice tray
			{ 'A', 'B', 'C', 'D' },

			{ 'E', 'F', 'G', 'H' },

			{ 'I', 'J', 'K', 'L' },

			{ 'M', 'N', 'O', 'Q' } };

	@Test
	public void test2withQ() {
		DiceTray2_oldDraft st = new DiceTray2_oldDraft(tray2);
		assertTrue(st.found("Quo"));
		assertTrue(st.found("Quk"));
		assertFalse(st.found("PON"));
		assertFalse(st.found("POJ"));
		assertFalse(st.found("oqurr"));
		assertFalse(st.found("POf"));
		assertTrue(st.found("ABC"));
		assertTrue(st.found("AbC")); // Must be case insensitive
		assertTrue(st.found("abE"));
		assertTrue(st.found("abF"));
		assertTrue(st.found("abG"));
	}

	@Test
	public void diceTray() {
		DiceTray2_oldDraft wt = new DiceTray2_oldDraft();
	}

	@Test
	public void diceTray2() {
		DiceTray wt = new DiceTray();
		System.out.print(wt.toString());
	}
}