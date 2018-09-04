package main;

import java.util.ArrayList;
import java.util.List;

public class Result {

	private String year;
	private String season;
	private String tournament;
	private String tournamentDetail;
	private String round;
	private String winnerPref;
	private String loserPref;
	private String winner;
	private String loser;

	private Team winTeam;
	private Team loseTeam;

	private int winnerScore;
	private int loserScore;
	private int inning;

	public Result(String content) {
		try {
			String[] split = content.split("\t");
			this.year = split[0];
			this.season = split[1];
			this.tournament = split[2];
			this.tournamentDetail = split[3];
			this.round = split[4];
			this.winnerPref = split[5];
			this.loserPref = split[6];
			this.winner = split[7];
			this.loser = split[8];
			this.winnerScore = new Integer(split[9]);
			this.loserScore = new Integer(split[10]);
			this.inning = new Integer(split[11]);

			this.winTeam = new Team(winner, winnerPref);
			this.loseTeam = new Team(loser, loserPref);

			if (inning == 0) {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			System.out.println(content);
			throw e;
		}
	}

	public static List<Result> getListResults(List<String> resultFileContent) {
		List<Result> ret = new ArrayList<>();
		for (String content : resultFileContent) {
			ret.add(new Result(content));
		}
		return ret;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(year + "\t");
		sb.append(season + "\t");
		sb.append(tournament + "\t");
		sb.append(tournamentDetail + "\t");
		sb.append(round + "\t");
		sb.append(winnerPref + "\t");
		sb.append(loserPref + "\t");
		sb.append(winner + "\t");
		sb.append(loser + "\t");
		sb.append(winnerScore + "\t");
		sb.append(loserScore + "\t");
		sb.append(inning);
		return sb.toString();
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getTournament() {
		return tournament;
	}

	public void setTournament(String tournament) {
		this.tournament = tournament;
	}

	public String getTournamentDetail() {
		return tournamentDetail;
	}

	public void setTournamentDetail(String tournamentDetail) {
		this.tournamentDetail = tournamentDetail;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public String getWinnerPref() {
		return winnerPref;
	}

	public void setWinnerPref(String winnerPref) {
		this.winnerPref = winnerPref;
	}

	public String getLoserPref() {
		return loserPref;
	}

	public void setLoserPref(String loserPref) {
		this.loserPref = loserPref;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getLoser() {
		return loser;
	}

	public void setLoser(String loser) {
		this.loser = loser;
	}

	public Team getWinTeam() {
		return winTeam;
	}

	public Team getLoseTeam() {
		return loseTeam;
	}



	public int getWinnerScore() {
		return winnerScore;
	}

	public void setWinnerScore(int winnerScore) {
		this.winnerScore = winnerScore;
	}

	public int getLoserScore() {
		return loserScore;
	}

	public void setLoserScore(int loserScore) {
		this.loserScore = loserScore;
	}

	public int getInning() {
		return inning;
	}

	public void setInning(int inning) {
		this.inning = inning;
	}

}
