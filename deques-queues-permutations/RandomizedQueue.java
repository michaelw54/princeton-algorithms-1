// /* *****************************************************************************
//  *  Name: Michael Wu
//  *  Date: December 30, 2018
//  *  Description: Algorithms project 2
//  **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int N;
    // N is always indexed 1 higher than the array index of the most recently queued element

    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("can't add null value");
        if (N < queue.length) {
            queue[N] = item;
            N++;
        } else {
            Item[] newArray = (Item[]) new Object[N * 2];
            System.arraycopy(queue, 0, newArray, 0, N);
            queue = newArray;
            queue[N] = item;
            N++;
        }
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("can't dequeue from empty queue");
        int idx = StdRandom.uniform(N);
        Item removedItem = queue[idx];

        for (int i = idx; i < N; i++) {
            if (i == N - 1) {
                queue[i] = null;
            } else {
                queue[i] = queue[i + 1];
            }
        }

        --N;

        if (N <= queue.length / 4) {
            Item[] newArray = (Item[]) new Object[N * 2];
            System.arraycopy(queue, 0, newArray, 0, N);
            queue = newArray;
        }

        return removedItem;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("can't sample from empty deque");
        int idx = StdRandom.uniform(N);
        return queue[idx];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int idx;
        private int[] rand;

        public RandomizedQueueIterator() {
            idx = 0;
            rand = StdRandom.permutation(N);
        }

        public boolean hasNext() {
            return (idx < N);
        }

        public void remove() {
            throw new UnsupportedOperationException("remove not supported");
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("no more items to iterate!");
            else {
                idx++;
                return queue[rand[idx - 1]];
            }
        }
    }


    public static void main(String[] args) {

    }
}
