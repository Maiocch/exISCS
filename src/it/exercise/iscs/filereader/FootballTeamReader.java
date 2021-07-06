package it.exercise.iscs.filereader;

import java.util.List;

import it.exercise.iscs.model.FootbalTeam;

public class FootballTeamReader extends FileReader<FootbalTeam> {
	
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
