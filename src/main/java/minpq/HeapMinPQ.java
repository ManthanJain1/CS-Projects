package minpq;

import java.util.Comparator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * {@link PriorityQueue} implementation of the {@link MinPQ} interface.
 *
 * @param <E> the type of elements in this priority queue.
 * @see MinPQ
 */
public class HeapMinPQ<E> implements MinPQ<E> {
    /**
     * {@link PriorityQueue} storing {@link PriorityNode} objects representing each element-priority pair.
     */
    private final PriorityQueue<PriorityNode<E>> pq;

    /**
     * Constructs an empty instance.
     */
    public HeapMinPQ() {
        pq = new PriorityQueue<>(Comparator.comparingDouble(PriorityNode::getPriority));
    }

    /**
     * Constructs an instance containing all the given elements and their priority values.
     *
     * @param elementsAndPriorities each element and its corresponding priority.
     */
    public HeapMinPQ(Map<E, Double> elementsAndPriorities) {
        pq = new PriorityQueue<>(elementsAndPriorities.size(), Comparator.comparingDouble(PriorityNode::getPriority));
        for (Map.Entry<E, Double> entry : elementsAndPriorities.entrySet()) {
            add(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void add(E element, double priority) {
        if (contains(element)) {
            throw new IllegalArgumentException("Already contains " + element);
        }
        PriorityNode<E> first = new PriorityNode<>(element, priority);
        pq.add(first);


    }

    @Override
    public boolean contains(E element) {
        for(PriorityNode<E> node : pq) {
            if(node.getElement().equals(element)){
                return true;
            }
        }
        return false;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public double getPriority(E element) {
        for(PriorityNode<E> node : pq) {
            if (node.getElement().equals(element)) {
                return node.getPriority();
            }
        }
        throw new NoSuchElementException();
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public E peekMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
       return pq.peek().getElement();
//        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public E removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        return pq.poll().getElement();
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void changePriority(E element, double priority) {
        if (!contains(element)) {
            throw new NoSuchElementException("PQ does not contain " + element);
        }
        for(PriorityNode<E> Node: pq){
            if(Node.getElement() == element){
                Node.setPriority(priority);
            }
        }
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int size() {
        return pq.size();
        //throw new UnsupportedOperationException("Not implemented yet");
    }
}
