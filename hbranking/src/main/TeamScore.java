package main;

public class TeamScore implements Comparable<TeamScore> {

	private Team team;
	private double score;

	public TeamScore(Team team, double score) {
		this.team = team;
		this.score = score;
	}

	@Override
	public int compareTo(TeamScore t) {
		if (this.score > t.score) return -1;
		else if (this.score == t.score) return 0;
		else if (this.score < t.score) return 1;
		return 0;
	}

	public Team getTeam() {
		return team;
	}

	public double getScore() {
		return score;
	}

}
