package it.exercise.iscs.filereader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class FileReader<T> {

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
