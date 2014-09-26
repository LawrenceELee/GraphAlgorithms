/** *************************************************
 * Bag objects are a collection of unique (no duplicates) unordered items.
 * Similar to Set in Java API.
 *
 * We well not be concerned about removing items from the bag, only adding.
 *
 * This will be used to implement the adjcency list in Graph objects.
 *
 * ************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

class Bag<AnyType> implements Iterable<AnyType>{
    private Node<AnyType> first;
    private int N;

    private class Node<AnyType>{
        AnyType data;
        Node<AnyType> next;
    }
    public Bag() {
        first = null;
        N = 0;
    }

    public void add(AnyType d){
        Node oldFirst = first;
        first = new Node();
        first.data = d;
        first.next = oldFirst;
        ++N;
    }

    public int size(){
        return N;
    }

    public Iterator<AnyType> iterator(){
        return new BagIterator<AnyType>(first);
    }

    private class BagIterator<AnyType> implements Iterator<AnyType>{
        private Node<AnyType> runner;

        public BagIterator(Node<AnyType> first){
            runner = first;
        }

        public boolean hasNext(){
            return runner != null;
        }

        public AnyType next(){
            if (!hasNext()) throw new NoSuchElementException();
            AnyType item    = runner.data;
            runner          = runner.next;
            return item;
        }

        public void remove(){    } //null implementation

    }

    public static void main(String[] args) {
        Bag<String> bag = new Bag<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            bag.add(item);
        }

        StdOut.println("size of bag = " + bag.size());
        for (String s : bag) {
            StdOut.println(s);
        }
    }
}
