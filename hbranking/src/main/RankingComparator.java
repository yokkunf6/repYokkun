package main;

import java.util.Comparator;

public class RankingComparator implements Comparator<AbstractRanking>{

	@Override
	public int compare(AbstractRanking ar0, AbstractRanking ar1) {
		if (ar0.getPoint() < ar1.getPoint()) return 1;
		else if (ar0.getPoint() == ar1.getPoint()) return 0;
		else return -1;
	}

}
