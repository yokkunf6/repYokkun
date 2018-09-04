package util;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class LinearAlgebra {



	/**
	 *
	 * @param voteMatrix 投票マトリックス（n×n）
	 * @return 固有ベクトル（1×n）
	 */
	public static RealVector solveEigenvector(RealMatrix matrix) {
		// 初期 r 作成
		RealVector r = MatrixUtils.createRealVector(new double[matrix.getColumnDimension()]);
		for (int i = 0; i < r.getDimension(); i++) {
			r.setEntry(i, 1.0 / r.getDimension());
		}

		// 初期固有値 0
		double ramda = 0.0;

		int count = 0;
		while (true) {
			RealVector rr1 = matrix.preMultiply(r);
			double ramda1 = rr1.dotProduct(rr1) / rr1.dotProduct(r);
			r = rr1;
			double ramda_d = ramda1 - ramda;

			double log = Math.log(count) / Math.log(2);

			if ((log - new Double(log).intValue()) < Math.pow(10, -4)) {
				System.out.println("ramda_d" + count + "=" + ramda_d);
			}

			if (Math.abs(ramda_d) > Math.pow(10.0, -5)) {
				ramda = ramda1;
				if (count++ > Math.pow(10, 4)) break;
				continue;
			}
			else break;
		}

		return r;

	}

}
