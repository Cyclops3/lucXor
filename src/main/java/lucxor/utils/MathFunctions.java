/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lucxor.utils;

import java.util.ArrayList;
import java.util.List;

import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * Math and Statistical Functions
 * @author dfermin
 */
public class MathFunctions {

	/**
	 * Function returns the number of combinations of (n choose k)
	 * @param n
	 * @param k
	 * @return
	 */
	public static double combinatorial(double n, double k) {
		double ret = 1.0;

		// n = number of characters in alphabet
		// k = desired length of the words to be constructed

		if(n <= 1.0) ret = 1.0;
		else {
			double diff = n - k;
			double facN = factorial(n);
			double facK = factorial(k);
			double facDiff = factorial(diff);

			ret = facN / ( facK * facDiff );
		}
		return ret;
	}


	/**
	 * Recursive function to compute the Factorial (X!) of a given number
	 * @param x
	 * @return
	 */
	public static double factorial(double x) {
		double ret = 1.0;
		if( x <= 1 )
			ret = 1;
		else
			for(double i = 1; i <= x; i++) ret *= i;
		return ret;
	}


	/**
	 * Get all combinations for all PTMs
	 * @param candModSites Candidate Modification sites
	 * @param k Number of combinations
	 * @return
	 */
	public static List<TIntList> getAllCombinations(TIntList candModSites, int k) {

		// http://code.google.com/p/combinatoricslib/#3._Simple_combinations

		ArrayList<Integer> vec = new ArrayList<>(candModSites.size());
		for(int i : candModSites.toArray())
		    vec.add(i);

		ICombinatoricsVector<Integer> initialVector = Factory.createVector( vec );

		Generator<Integer> gen = Factory.createSimpleCombinationGenerator(initialVector, k);
        List<TIntList> ret = new ArrayList<>(initialVector.getSize());

        for(ICombinatoricsVector<Integer> combination : gen) {
			TIntArrayList curList = new TIntArrayList(combination.getSize());
			for(int i : combination)
				curList.add(i);
			ret.add(curList);
		}

		return ret;
	}

	public static double logGaussianProb(double mu, double sigma2, double x) {
		return  -0.5 * Math.pow((x-mu), 2.0) / sigma2 - 0.5 * Math.log( (2.0 * Math.PI * sigma2) );
	}

	/**
	 * Round double with decimal places
	 * @param value Double value
	 * @param numPlaces number of decimal places
	 * @return new Double.
	 */
	public static double roundDouble(double value, int numPlaces) {
		double ret;
		double N = Math.pow(10, numPlaces);
		ret = (double)Math.round( (value * N) ) / N;
		return ret;
	}
}
