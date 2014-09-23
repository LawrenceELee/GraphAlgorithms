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

class Bag<AnyType> implements Iterable<AnyType>{
    private Node first;

    private class Node{
        AnyType data;
        Node next;
    }

    public void add(AnyType d){
        Node oldFirst = first;
        first = new Node();
        first.data = d;
        first.next = oldFirst;
    }

    public Iterator<AnyType> iterator(){
        return new BagIterator();
    }

    private class BagIterator implements Iterator<AnyType>{
        private Node runner = first;

        public boolean hasNext(){
            return runner != null;
        }

        public AnyType next(){
            AnyType item    = runner.data;
            runner          = runner.next;
            return item;
        }

        public void remove(){    } //null implementation

    }
}
