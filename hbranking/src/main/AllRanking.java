package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.linear.RealVector;

import util.LinearAlgebra;

public class AllRanking {
	private VoteMatrix vm;
	private RealVector rr;

	public AllRanking(VoteMatrix vm) {
		this.vm = vm;
		this.rr = LinearAlgebra.solveEigenvector(vm.getVoteMatrix());
	}

	public List<String> getFileOutput() {
		List<String> ret = new ArrayList<String>();

		List<TeamScore> teamScores = new ArrayList<TeamScore>();
		for (int i = 0; i < rr.getDimension(); i++) {
			TeamScore ts = new TeamScore(vm.getTeams().get(i), rr.getEntry(i));
			teamScores.add(ts);
		}
		Collections.sort(teamScores);

		int rank = 0;
		for (TeamScore ts : teamScores) {
			rank++;
			StringBuilder line = new StringBuilder();
			line.append(String.format("%04d", rank));
			line.append("\t");
			line.append(new BigDecimal(ts.getScore() * rr.getDimension()).setScale(6, BigDecimal.ROUND_DOWN));
			line.append("\t");
			line.append(ts.getTeam().getPref());
			line.append("\t");
			line.append(ts.getTeam().getNewestTeamName());
			ret.add(line.toString());
		}

		return ret;
	}

	public RealVector getRR() {
		return rr;
	}

}
