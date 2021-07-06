package it.exercise.iscs.filereader;

//factory design pattern
//con questa classe scelgo a run-time la classe da ritornare sul main
public class GetFileReaderFactory {
	
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
