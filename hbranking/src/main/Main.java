package main;
import java.util.List;

import util.FileUtil;
public class Main {
	public static String RESULT_FILE = "file\\Result.txt";
	public static String BASIC_POINT_FILE = "file\\BasicPointFile.txt";
	public static String BASIC_RANKING_FILE = "file\\BasicRanking.txt";
	public static String PREF_RATING_FILE = "file\\PrefRatingFile.txt";
	public static String RANKING_LOG = "file\\RankingLog.txt";
	public static String RANKING = "file\\Ranking.txt";

	public static String BASEBALL_TEAMNAME_LIST = "file\\TeamnameList.txt";

	public static void main(String[] args) throws Exception {
		List<String> resultFileContent = FileUtil.readFile(RESULT_FILE);
		List<Result> results = Result.getListResults(resultFileContent);

		VoteMatrix vm = VoteMatrix.create(results);
		AllRanking ranking = new AllRanking(vm);
		List<String> outputs = ranking.getFileOutput();
		FileUtil.writeFile(outputs, RANKING);


//		List<BasicPoint> basicPoints = BasicPoint.make(results, BASIC_POINT_FILE);
//		List<BasicRanking> basicRankings = BasicRanking.make(basicPoints, BASIC_RANKING_FILE);
//		List<PrefRating> prefRatings = PrefRating.make(results, PREF_RATING_FILE);
//		List<PrefRating> prefRatings = null;
//		List<Ranking> rankings = Ranking.make(basicPoints, basicRankings, prefRatings,
//				RANKING_LOG, RANKING);
	}
}
