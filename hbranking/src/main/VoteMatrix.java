package main;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.StatUtils;

public class VoteMatrix {

	private List<Team> teams = new ArrayList<Team>();
	private RealMatrix array = null;

	public static VoteMatrix create(List<Result> results) {
		VoteMatrix vm = new VoteMatrix();
		List<Team> teams = vm.getTeams();
		for (Result result : results) {
			if (!teams.contains(result.getWinTeam())) {
				teams.add(result.getWinTeam());
			}
			if (!teams.contains(result.getLoseTeam())) {
				teams.add(result.getLoseTeam());
			}
		}
		RealMatrix array = MatrixUtils.createRealMatrix(new double[teams.size()][teams.size()]);

		// 負け点の投票
		for (Result result : results) {
			double point = array.getEntry(teams.indexOf(result.getWinTeam()), teams.indexOf(result.getLoseTeam()));
			point += PointFactory.create().getWinnerLosePoint(result);
			array.setEntry(teams.indexOf(result.getWinTeam()), teams.indexOf(result.getLoseTeam()), point);

			point = array.getEntry(teams.indexOf(result.getLoseTeam()), teams.indexOf(result.getWinTeam()));
			point += PointFactory.create().getLoserLosePoint(result);
			array.setEntry(teams.indexOf(result.getLoseTeam()), teams.indexOf(result.getWinTeam()), point);
		}

		// 各行の要素の和を1にする
		for (int i = 0; i < array.getRowDimension(); i++) {
			double[] row = array.getRow(i);
			double sum = StatUtils.sum(row);
			for (int j = 0; j < array.getColumnDimension(); j++) {
				double v = array.getEntry(i, j) / sum;
				array.setEntry(i, j, v);
			}
		}

		vm.setArray(array);

		return vm;

	}

	private void setArray(RealMatrix array) {
		this.array = array;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public RealMatrix getVoteMatrix() {
		return array;
	}

}
