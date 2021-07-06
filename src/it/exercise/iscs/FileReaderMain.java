package it.exercise.iscs;

import java.io.FileNotFoundException;
import java.util.Scanner;

import it.exercise.iscs.filereader.FileReader;
import it.exercise.iscs.filereader.GetFileReaderFactory;

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
