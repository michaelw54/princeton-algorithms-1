/* *****************************************************************************
 *  Name: Michael Wu
 *  Date: December 23, 2018
 *  Description: Algorithms project 1
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class Percolation {

    private WeightedQuickUnionUF grid;
    private int numRows;
    private int numCols;
    private boolean[][] open;

    public Percolation(int n) {
        // create n-by-n grid, with all sites blocked
        if (n <= 0) {
            throw new IllegalArgumentException("smallest value of n must be >= 1");
        }
        else {
            // create one node to represent top and one node to represent bottom
            grid = new WeightedQuickUnionUF((int) Math.pow(n, 2) + 2);
            System.out.println(grid.count());
            numRows = n;
            numCols = n;
            open = new boolean[numRows][numCols];
            for (boolean[] row : open)
                Arrays.fill(row, false);
        }

        open[0][0] = true;

    }

    public void open(int row, int col) {

        open[row - 1][col - 1] = true;

        if (row == 1) {
            grid.union((row - 1) * numCols + (col - 1), 0);
        }

        if (row != 1) {
            if (isOpen(row - 1, col))
                grid.union((row - 1) * numCols + (col - 1), (row - 2) * numCols + (col - 1));
        }

        if (col != 1) {
            if (isOpen(row, col - 1))
                grid.union((row - 1) * numCols + (col - 1), (row - 1) * numCols + (col - 2));
        }

        if (col != numCols) {
            if (isOpen(row, col + 1))
                grid.union((row - 1) * numCols + (col - 1), (row - 1) * numCols + (col));
        }

        if (row != numRows) {
            if (isOpen(row + 1, col))
                grid.union((row - 1) * numCols + (col - 1), (row) * numCols + (col - 1));
        }

        if (row == numRows) {
            grid.union((row - 1) * numCols + (col - 1), (int) Math.pow(numRows, 2) + 1);
        }

    }

    public boolean isOpen(int row, int col) {
        // is site (row, col) open?
        return open[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        // is site (row, col) full?
        if (grid.connected((row - 1) * numCols + (col - 1), 0)) {
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        // number of open sites
        int trueCount = Arrays.deepToString(open).replaceAll("[^t]", "").length();
        // System.out.println(trueCount);
        return trueCount;
    }

    public boolean percolates() {
        // does the system percolate?

        // for (int i = 0; i < numCols; i++) {
        //     if (isFull(numRows, i)) {
        //         return true;
        //     }
        // }

        return grid.connected(0, (int) Math.pow(numRows, 2) + 1);
    }
}
