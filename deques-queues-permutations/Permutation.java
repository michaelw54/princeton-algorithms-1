import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {

    public static void main(String[] args) {

        RandomizedQueue<String> randqueue = new RandomizedQueue<>();

        int k = Integer.parseInt(args[0]);

        System.out.println("Give a sequence of strings and press CMD+D >> ");
        while (!StdIn.isEmpty()) {
            randqueue.enqueue(StdIn.readString());
        }

        Iterator<String> randIterator = randqueue.iterator();
        while (randIterator.hasNext() && k > 0) {
            System.out.println(randIterator.next());
            --k;
        }
    }
}
