package autocomplete;

import java.util.*;

/**
 * Ternary search tree (TST) implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class TernarySearchTreeAutocomplete implements Autocomplete {
    /**
     * The overall root of the tree: the first character of the first autocompletion term added to this tree.
     */
    private Node overallRoot;

    /**
     * Constructs an empty instance.
     */
    public TernarySearchTreeAutocomplete() {
        overallRoot = null;
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        if (terms == null) {
            throw new IllegalArgumentException();
        }
        for(CharSequence term: terms) {

            overallRoot = add(overallRoot,term,0 );
        }
    }

    private Node add(Node root, CharSequence term, int x){
        if(term == null || term.length() == 0){
            throw new IllegalArgumentException();
        }
        char node = term.charAt(x);
        if(root == null) {
            root = new Node(node);
        }
        if(node < root.data){
            root.left = add(root.left, term, x);
        }
        else if(node > root.data) {
            root.right = add(root.right, term, x);
        }
        else if(x < term.length()-1) {
            root.mid = add(root.mid, term, x+1);
        }
        else{
            root.isTerm = true;
        }
        return root;

    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        List<CharSequence> result = new ArrayList<>();
        Deque<String> queue = new ArrayDeque<>();
        Node node = overallRoot;
        int i = 0;
        while(node != null && i < prefix.length()) {
            char c = prefix.charAt(i);
            if(c < node.data) {
                node = node.left;
            }
            else if(c > node.data) {
                node = node.right;
            }
            else {
                i++;
                if(i < prefix.length()) {
                    node = node.mid;
                }
            }
        }
        if(node != null){
            if(node.isTerm){
                queue.add(prefix.toString());
            }
            collect(node.mid, new StringBuilder(prefix), queue);
        }
        while (!queue.isEmpty()) {
            result.add(queue.remove());
        }
        return result;

    }
    private void collect(Node node, StringBuilder prefix, Queue<String> queue) {
        if(node == null) {
            return;
        }
        collect(node.left, prefix, queue);
        if(node.isTerm){
            queue.add(prefix.toString() + node.data);
        }
        prefix.append(node.data);
        collect(node.mid,prefix, queue);
        prefix.deleteCharAt(prefix.length()-1);
        collect(node.right, prefix, queue );
    }

    /**
     * A search tree node representing a single character in an autocompletion term.
     */
    private static class Node {
        private final char data;
        private boolean isTerm;
        private Node left;
        private Node mid;
        private Node right;

        public Node(char data) {
            this.data = data;
            this.isTerm = false;
            this.left = null;
            this.mid = null;
            this.right = null;
        }
    }
}

