package prova;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

abstract class FileReader<T> {

	private final String REGEX_TO_SPLIT;
	private final String REGEX_TO_CHECK_ROW;

	public FileReader(String regexToSplit, String regexToCheckRow) {
		REGEX_TO_SPLIT = regexToSplit;
		REGEX_TO_CHECK_ROW = regexToCheckRow;
	}

	public abstract T createT(String[] as);

	//metodo per cercare una riga in particolare richiesta dagli esercizi 
	public abstract void findAndPrintRow(List<T> listRows);

	public void readAndSplitFile(String pathName) throws FileNotFoundException {
		//cerchiamo il file nel path
		File file = new File(pathName);

		// usiamo lo scanner per scannerizzare una riga alla volta del file.dat
		Scanner scanner = new Scanner(file);

		String row;
		List<T> rowsList = new ArrayList<>();

		while (scanner.hasNextLine()) {
			try {
				row = scanner.nextLine();
				if(!Pattern.matches(REGEX_TO_CHECK_ROW, row))
					continue;
				String[] splitRow = row.trim().split(REGEX_TO_SPLIT);
				T t = createT(splitRow);

				rowsList.add(t);
			} catch (Exception e) {
				continue;
			}
		}

		scanner.close();

		findAndPrintRow(rowsList);
	}

}



class FootbalTeam {
	
	private int id;
	private String name;
	private int golF;
	private int golA;
	
	public FootbalTeam() {
	}
	
	public FootbalTeam(int id, String name, int golF, int golA) {
		this.id = id;
		this.name = name;
		this.golF = golF;
		this.golA = golA;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGolF() {
		return golF;
	}
	public void setGolF(int golF) {
		this.golF = golF;
	}
	public int getGolA() {
		return golA;
	}
	public void setGolA(int golA) {
		this.golA = golA;
	}
	
	public int getDiffGolFA() {
		return  golF-golA;
	}

	@Override
	public String toString() {
		return "FootbalTeam [id=" + id + ", name=" + name + ", golF=" + golF + ", golA=" + golA + "]";
	}
}

class Weather {
	
	private short day;
	private int MxT;
	private int MnT;
	
	public Weather (short day, int mxT, int mnT) {
		this.day = day;
		MxT = mxT;
		MnT = mnT;
	}

	public short getDay() {
		return day;
	}

	public void setDay(short day) {
		this.day = day;
	}

	public int getMxT() {
		return MxT;
	}

	public void setMxT(int mxT) {
		MxT = mxT;
	}

	public int getMnT() {
		return MnT;
	}

	public void setMnT(int mnT) {
		MnT = mnT;
	}

	@Override
	public String toString() {
		return "Weather [day=" + day + ", MxT=" + MxT + ", MnT=" + MnT + "]";
	}
}



class FootballTeamReader extends FileReader<FootbalTeam> {
	
	public FootballTeamReader(String regexToSplit, String regexToCheckRow) {
		super(regexToSplit, regexToCheckRow);
	}

	@Override
	public FootbalTeam createT(String[] splitRow) {
		FootbalTeam ft = new FootbalTeam(Integer.parseInt(splitRow[0]), splitRow[1], Integer.parseInt(splitRow[6]),
				Integer.parseInt(splitRow[7]));
		return ft;
	}

	@Override
	public void findAndPrintRow(List<FootbalTeam> listRows) {
		// ritorniamo la squadra con la minor differenza fra goal segnati e subiti.
		
		if(listRows.isEmpty()) {
			System.out.println("Nessuna squadra trovata");
			return;
		}
		
		FootbalTeam teamWithMinDiffGoalFA = listRows.get(0);

		for (FootbalTeam f : listRows) {
			if (f.getDiffGolFA() < teamWithMinDiffGoalFA.getDiffGolFA())
				teamWithMinDiffGoalFA = f;
		}

		System.out.println("Team con minor differenza tra goal segnati e goal subiti:");
		System.out.println(teamWithMinDiffGoalFA.toString());
		System.out.println("Differenza reti fatte e subite: " + teamWithMinDiffGoalFA.getDiffGolFA());
	}

}



class WeatherFileReader extends FileReader<Weather> {
	
	public WeatherFileReader(String regexToSplit, String regexToCheckRow) {
		super(regexToSplit, regexToCheckRow);
	}

	@Override
	public Weather createT(String[] as) {
		Weather ft = new Weather(Short.parseShort(as[0]), Integer.parseInt(as[1]),
				Integer.parseInt(as[2]));
		return ft;
	}

	@Override
	public void findAndPrintRow(List<Weather> listRows) {
		// ritorniamo il giorno con l'escursione termica più piccola.
		
		if(listRows.isEmpty()) {
			System.out.println("Nessun dato meteo trovato");
			return;
		}
			
		
		Weather minTemperatureRange = listRows.get(0);

		for (Weather d : listRows) {
			int diffMaxTMinT = d.getMxT() - d.getMnT();

			if (diffMaxTMinT < minTemperatureRange.getMxT() - minTemperatureRange.getMnT())
				minTemperatureRange = d;

		}

		System.out.println("Giorno del mese con escursione termica più piccola:");
		System.out.println(minTemperatureRange.toString());
		System.out.println("Differenza tra temperatura massima e minima: "
				+ (minTemperatureRange.getMxT() - minTemperatureRange.getMnT()));
	}

}


//factory design pattern
//con questa classe scelgo a run-time la classe da ritornare sul main
class GetFileReaderFactory {
	
	public static FileReader<?> getReader(String esercizio) {
		
		String regexToCheckWeaher = "\\s+\\d+\\s+\\d+\\s+\\d+\\*?\\s+\\d+\\s+\\d?\\d?[\\s]{0,}\\d+\\.\\d\\s+\\d+\\.\\d\\d\\s+[\\w]{0,}\\s+\\d+\\s+\\d+\\.\\d \\d+\\s+\\d+\\*?\\s+\\d+\\.\\d\\s+\\d+\\s+\\d+\\s+\\d+\\.\\d\\s?";
		String regexToCheckFootball = "\\s+\\d+\\. \\w+\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+-\\s+\\d+\\s+\\d+";
		
		
		if(esercizio.equalsIgnoreCase("dati meteo"))
			return new WeatherFileReader("\\s+", regexToCheckWeaher);
			
		if(esercizio.equalsIgnoreCase("lega football"))
			return new FootballTeamReader("([\\s]+-[\\s]+|\\.[\\s]+|[\\s]+)", regexToCheckFootball);
		
		else
			return null;
		
	}
}



public class FileReaderMain {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Digitare esercizio da eseguire (es: \"dati meteo\" o \"lega football\")");
		String nomeEsercizio =scan.nextLine();
		
		FileReader<?> fileReader = GetFileReaderFactory.getReader(nomeEsercizio);
		if(fileReader == null) {
			System.out.println("Nome esercizio non trovato");
			scan.close();
			return;
		}
		
		System.out.println("Digitare path del file da leggere (es: \"C:/Users/vlrmc/OneDrive/Desktop/football.dat\")");
		String pathFile =scan.nextLine();
		
		try{
			fileReader.readAndSplitFile(pathFile);
		}catch (FileNotFoundException e) {
			System.out.println("Impossibile trovare il percorso specificato.");
		}
		
		
		scan.close();
	}

}



//unit test con JUnit 4
//import java.util.regex.Pattern;
//import org.junit.jupiter.api.Test;
//class FileReaderTest {
//
//	@Test
//	void testReadAndSplitFileFootball() {
//		
//		String checkRowRegex = "\\s+\\d+\\. \\w+\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+-\\s+\\d+\\s+\\d+";
//		String row = "    3. Manchester_U    38    24   5   9    87  -  45    77";
//		String row2 = "      Team            P     W    L   D    F      A     Pts";
//		
//		assertTrue(Pattern.matches(checkRowRegex, row));
//		assertFalse(Pattern.matches(checkRowRegex, row2));
//		
//		String splitRowRegex = "([\\s]+-[\\s]+|\\.[\\s]+|[\\s]+)";
//		
//		String[] splitRow = row.trim().split(splitRowRegex);
//		String[] splitRowTest = {"3", "Manchester_U", "38", "24", "5", "9", "87", "45", "77"};
//		
//		assertEquals(splitRowTest, splitRow);
//	}
//	
//	@Test
//	void testReadAndSplitFileWeather() {
//		
//		String checkRowRegex =  "\\s+\\d+\\s+\\d+\\s+\\d+\\*?\\s+\\d+\\s+\\d?\\d?[\\s]{0,}\\d+\\.\\d\\s+\\d+\\.\\d\\d\\s+[\\w]{0,}\\s+\\d+\\s+\\d+\\.\\d \\d+\\s+\\d+\\*?\\s+\\d+\\.\\d\\s+\\d+\\s+\\d+\\s+\\d+\\.\\d\\s?";
//		String row = "   9  86    32*   59       6  61.5       0.00         240  7.6 220  12  6.0  78 46 1018.6";
//		String row2 = "  11  91    59    75          66.3       0.00 H       250  7.1 230  12  2.5  93 45 1012.6";
//		
//		assertTrue(Pattern.matches(checkRowRegex, row));
//		assertTrue(Pattern.matches(checkRowRegex, row2));
//		
//		
//		String splitRowRegex = "\\s+";
//		
//		String[] splitRow = row.trim().split(splitRowRegex);
//		String[] splitRowTest = {"9", "86", "32*", "59", "6", "61.5", "0.00", "240", "7.6", "220", "12", "6.0", "78", "46", "1018.6"};
//		
//		assertEquals(splitRowTest, splitRow);
//		
//	}
//
//}


