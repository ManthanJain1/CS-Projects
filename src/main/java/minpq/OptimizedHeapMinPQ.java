package minpq;

import java.util.*;

import static java.util.Collections.swap;

/**
 * Optimized binary heap implementation of the {@link MinPQ} interface.
 *
 * @param <E> the type of elements in this priority queue.
 * @see MinPQ
 */
public class OptimizedHeapMinPQ<E> implements MinPQ<E> {
    /**
     * {@link List} of {@link PriorityNode} objects representing the heap of element-priority pairs.
     */
    private final List<PriorityNode<E>> elements;
    /**
     * {@link Map} of each element to its associated index in the {@code elements} heap.
     */
    private final Map<E, Integer> elementsToIndex;

    /**
     * Constructs an empty instance.
     */
    public OptimizedHeapMinPQ() {
        elements = new ArrayList<>();
        elementsToIndex = new HashMap<>();
        elements.add(null);
    }

    /**
     * Constructs an instance containing all the given elements and their priority values.
     *
     * @param elementsAndPriorities each element and its corresponding priority.
     */
    public OptimizedHeapMinPQ(Map<E, Double> elementsAndPriorities) {
        elements = new ArrayList<>(elementsAndPriorities.size());
        elementsToIndex = new HashMap<>(elementsAndPriorities.size());
        // TODO: Replace with your code
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void add(E element, double priority) {
        if (contains(element)) {
            throw new IllegalArgumentException("Already contains " + element);
        }
        PriorityNode<E> node = new PriorityNode<>(element, priority);
        elements.add(node);
        int index = size()-1;
        elementsToIndex.put(element,index);
        sinkup(index);

        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean contains(E element) {
        return elementsToIndex.containsKey(element);
     //   throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public double getPriority(E element) {
        int index = elementsToIndex.get(element);
        return elements.get(index).getPriority();
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public E peekMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        return elements.get(1).getElement();
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public E removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        swap(1, elements.size()-1);
        elements.remove(elements.size()-1);
        elementsToIndex.remove(elements.get(1).getElement());
        if(!isEmpty()){
            sinkDown(1);
        }
        return elements.get(1).getElement();
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void changePriority(E element, double priority) {
        if (!contains(element)) {
            throw new NoSuchElementException("PQ does not contain " + element);
        }
        int index = elementsToIndex.get(element);
        if(index == 0){
            throw new NoSuchElementException();
        }
        PriorityNode<E> node =  elements.get(index);
        double old =node.getPriority();
        node.setPriority(priority);

        if(priority < old){
            sinkup(index);
        }
        else{
            sinkDown(index);
        }
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int size() {
        return elements.size()-1;
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    private void sinkup(int index) {
        while(index > 1) {
            int ind = index/2;
            if(elements.get(index).getPriority() >= elements.get(ind).getPriority()){
                break;
            }
            swap(index, ind);
            index = ind;
        }
    }
    private void sinkDown(int index){
        int size = elements.size();
        while(2*index < size){
            int left = 2*index;
            int right = left + 1;
            int min = left;

            if(right<size && elements.get(right).getPriority() < elements.get(left).getPriority()) {
                min = right;
            }
            if (elements.get(index).getPriority() <= elements.get(min).getPriority()) {
                break;
            }

            swap(index, min);
            index = min;
        }
    }
    private void swap(int n, int number){
        PriorityNode<E> temporary = elements.get(n);
        elements.set(n, elements.get(number));
        elements.set(number, temporary);
        elementsToIndex.put(elements.get(n).getElement(), n);
        elementsToIndex.put(elements.get(number).getElement(), number);
    }
}
