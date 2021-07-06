package it.exercise.iscs.filereader;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class FileReaderTest {

	@Test
	void testReadAndSplitFileFootball() {
		
		String checkRowRegex = "\\s+\\d+\\. \\w+\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+-\\s+\\d+\\s+\\d+";
		String row = "    3. Manchester_U    38    24   5   9    87  -  45    77";
		String row2 = "      Team            P     W    L   D    F      A     Pts";
		
		assertTrue(Pattern.matches(checkRowRegex, row));
		assertFalse(Pattern.matches(checkRowRegex, row2));
		
		String splitRowRegex = "([\\s]+-[\\s]+|\\.[\\s]+|[\\s]+)";
		
		String[] splitRow = row.trim().split(splitRowRegex);
		String[] splitRowTest = {"3", "Manchester_U", "38", "24", "5", "9", "87", "45", "77"};
		
		assertEquals(splitRowTest, splitRow);
	}
	
	@Test
	void testReadAndSplitFileWeather() {
		
		String checkRowRegex =  "\\s+\\d+\\s+\\d+\\s+\\d+\\*?\\s+\\d+\\s+\\d?\\d?[\\s]{0,}\\d+\\.\\d\\s+\\d+\\.\\d\\d\\s+[\\w]{0,}\\s+\\d+\\s+\\d+\\.\\d \\d+\\s+\\d+\\*?\\s+\\d+\\.\\d\\s+\\d+\\s+\\d+\\s+\\d+\\.\\d\\s?";
		String row = "   9  86    32*   59       6  61.5       0.00         240  7.6 220  12  6.0  78 46 1018.6";
		String row2 = "  11  91    59    75          66.3       0.00 H       250  7.1 230  12  2.5  93 45 1012.6";
		
		assertTrue(Pattern.matches(checkRowRegex, row));
		assertTrue(Pattern.matches(checkRowRegex, row2));
		
		
		String splitRowRegex = "\\s+";
		
		String[] splitRow = row.trim().split(splitRowRegex);
		String[] splitRowTest = {"9", "86", "32*", "59", "6", "61.5", "0.00", "240", "7.6", "220", "12", "6.0", "78", "46", "1018.6"};
		
		assertEquals(splitRowTest, splitRow);
		
	}

}
