package main;

public class HighSchoolBaseballPoint extends Point {

	@Override
	public double getWinnerLosePoint(Result result) {
		return getLosePoint(
				result.getWinnerScore(),
				result.getLoserScore(),
				result.getInning(),
				result.getYear(),
				result.getSeason(),
				result.getTournament(),
				result.getTournamentDetail(),
				result.getRound());
	}

	@Override
	public double getLoserLosePoint(Result result) {
		return getLosePoint(
				result.getLoserScore(),
				result.getWinnerScore(),
				result.getInning(),
				result.getYear(),
				result.getSeason(),
				result.getTournament(),
				result.getTournamentDetail(),
				result.getRound());
	}

	/**
	 *
	 * @param ourPoint
	 * @param opponentPoint
	 * @param inning
	 * @param year
	 * @param tournament
	 * @param tournamentDetail
	 * @param round
	 * @return ”負け点”
	 */
	private double getLosePoint(int ourPoint, int opponentPoint, int inning, String year, String season,
			String tournament, String tournamentDetail, String round) {
		double point = 0.0;

		// 点差
		// 敗戦は1.0
		if (ourPoint < opponentPoint) point = 1.0;
		// 引き分けは0.8
		else if (ourPoint == opponentPoint) point = 0.8;
		// 延長勝利は0.7
		else if (inning > 9) point = 0.7;
		// 勝利は1-6点差で 0.5 0.43 0.36 0.29 0.22 0.15
		else {
			double difference = ourPoint - opponentPoint;
			if (difference >= 1 && difference <= 6) {
				point = 0.57 + difference * (-0.07);
			} else point = 0.1;
		}

		// 年度
		if (year.equals("17-18")) point *= 5;
		else if (year.equals("16-17")) point *= 4;
		else if (year.equals("15-16")) point *= 3;
		else if (year.equals("14-15")) point *= 2;
		else if (year.equals("13-14")) point *= 1;
		else point *= 0;

		// 大会
		if (tournament.equals("選手権")) point *= 3;
		else if (tournament.equals("選抜")) point *= 3;
		else if (tournament.equals("神宮")) point *= 2.5;
		else if (tournament.equals("国体")) point *= 2.5;
		else if (tournament.equals("地区")) {
			if (tournamentDetail.equals("北海道")) point *= 1.8;
			else point *= 2;
		} else if (tournament.equals("地方")) {
			if (tournamentDetail.equals("東京") && season.equals("秋")) {
				point *= 1.2;
			} else point *= 1;
		}
		else point *= 0;

		return point;
	}

}
