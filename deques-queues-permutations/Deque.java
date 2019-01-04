// /* *****************************************************************************
//  *  Name: Michael Wu
//  *  Date: December 30, 2018
//  *  Description: Algorithms project 2
//  **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node next;
        private Node previous;

        private Node(Item item) {
            this.item = item;
            next = null;
            previous = null;
        }
    }

    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("can't add null value");
        Node n = new Node(item);

        if (first == null) {
            first = n;
            last = first;
        } else {
            first.previous = n;
            n.next = first;
            first = n;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("can't add null value");
        Node n = new Node(item);

        if (last == null) {
            last = n;
            first = last;
        } else {
            last.next = n;
            n.previous = last;
            last = n;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("can't remove from empty deque");
        Node n = first;
        if (first == last) {
            first = null;
            last = null;
        } else {
            first = n.next;
            first.previous = null;
        }
        --size;
        return n.item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("can't remove from empty deque");
        Node n = last;
        if (first == last) {
            first = null;
            last = null;
        } else {
            last = n.previous;
            last.next = null;
        }
        --size;
        return n.item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() not implemented");
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("no more items to iterate!");
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    public static void main(String[] args) {

    }

}
