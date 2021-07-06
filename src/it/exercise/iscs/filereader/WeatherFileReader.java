package it.exercise.iscs.filereader;

import java.util.List;

import it.exercise.iscs.model.Weather;

public class WeatherFileReader extends FileReader<Weather> {
	
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
