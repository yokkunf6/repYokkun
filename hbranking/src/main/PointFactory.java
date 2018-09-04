package main;

public class PointFactory {

	public static Point create() {
		// 高校野球の場合
		return new HighSchoolBaseballPoint();
	}

}
