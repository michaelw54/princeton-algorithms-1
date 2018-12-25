// /* *****************************************************************************
//  *  Name: Michael Wu
//  *  Date: December 24, 2018
//  *  Description: Algorithms project 1
//  **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Scanner;

public class PercolationStats {

    private int numTrials;
    private double[] openOverAll;

    // Performs trials independent experiments on an N-by-N grid.

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("can't be less than zero");
        }
        numTrials = trials;
        openOverAll = new double[numTrials];

        for (int expNum = 0; expNum < numTrials; expNum++) {

            Percolation perc = new Percolation(n);

            while (!perc.percolates()) {
                int i = StdRandom.uniform(1, n + 1);
                int j = StdRandom.uniform(1, n + 1);
                if (!perc.isOpen(i, j)) {
                    perc.open(i, j);
                }
            }

            double fraction = (double) perc.numberOfOpenSites() / (n * n);
            openOverAll[expNum] = fraction;
        }
    }

    // Sample mean of percolation threshold.

    public double mean() {
        return StdStats.mean(openOverAll);
    }

    // Sample standard deviation of percolation threshold.

    public double stddev() {
        return StdStats.stddev(openOverAll);
    }

    // Returns lower bound of the 95% confidence interval.

    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(numTrials));
    }

    // Returns upper bound of the 95% confidence interval.

    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(numTrials));
    }

    public static void main(String[] args) {
        int n;
        int trials;
        Scanner scanner = new Scanner(System.in);
        System.out.println("grid size?");
        n = scanner.nextInt();
        System.out.println("number of trials?");
        trials = scanner.nextInt();

        PercolationStats percStats = new PercolationStats(n, trials);

        String confidence = percStats.confidenceLo() + ", " + percStats.confidenceHi();
        StdOut.println("mean                    = " + percStats.mean());
        StdOut.println("stddev                  = " + percStats.stddev());
        StdOut.println("95% confidence interval = [ " + confidence + " ]");
    }
}
