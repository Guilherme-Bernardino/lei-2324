package pt.pa.adts;
import java.util.*;

/**
 * @param <E> type of elements of the tree
 * @author patricia.macedo
 */
public class TreeLinked<E> implements Tree<E> {

    //** TreeNode implemented as an inner class at the end **/
    private TreeNode root;

    public TreeLinked() {
        this.root = null;
    }

    public TreeLinked(E root) {
        this.root = new TreeNode(root);
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        } else {
            return size(root);
        }
    }

    public int size(TreeNode treeNode) {
        if (treeNode.children.isEmpty()) {
            return 1;
        } else {
            int s = 1;
            for (TreeNode child : treeNode.children) {
                s += size(child);
            }
            return s;
        }
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Position<E> root() throws EmptyTreeException {
        return root;
    }

    /**
     *   auxiliary method to check if Position is valid and cast to a treeNode
     */
    private TreeNode checkPosition(Position<E> position)
            throws InvalidPositionException {
        if (position == null) {
            throw new InvalidPositionException();
        }

        try {
            TreeNode treeNode = (TreeNode) position;
            if (treeNode.children == null) {
                throw new InvalidPositionException("The position is invalid");
            }
            return treeNode;
        } catch (ClassCastException e) {
            throw new InvalidPositionException();
        }
    }


    @Override
    public Position<E> parent(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
        TreeNode node = checkPosition(position);
        return node.parent;
    }


    @Override
    public E replace(Position<E> position, E e) throws InvalidPositionException {
        TreeNode node = checkPosition(position);
        E replacedElem = node.element;
        node.element = e;
        return replacedElem;
    }

    @Override
    public boolean isRoot(Position<E> position) throws InvalidPositionException {
        TreeNode aux = checkPosition(position);
        return this.root == aux;
    }

    @Override
    public boolean isExternal(Position<E> position) throws InvalidPositionException {
        TreeNode aux = checkPosition(position);
        return aux.children.isEmpty();
    }

    @Override
    public boolean isInternal(Position<E> position) throws InvalidPositionException {
        TreeNode aux = checkPosition(position);
        return !aux.children.isEmpty() && aux != this.root;
    }

    @Override
    public boolean isParent(Position<E> child, Position<E> parent) {
        TreeNode childNode = checkPosition(child);
        TreeNode parentNode = checkPosition(parent);
        return (childNode.parent == parentNode);
    }

    @Override
    public boolean isAncestor(Position<E> posDesc, Position<E> posAsc) {
        if (posDesc==null || posAsc==null) return false;

        TreeNode descendantNode = checkPosition(posDesc);  // possível descendente
        TreeNode ascendantNode = checkPosition(posAsc);   // possível ascendente

        if (isRoot(descendantNode)) return false;     // a raiz não tem ascendentes
        if (descendantNode.parent==ascendantNode) return true;
        return isAncestor(descendantNode.parent, ascendantNode);
    }


    @Override
    public Iterable<Position<E>> children(Position<E> position) throws InvalidPositionException {
        TreeNode node = checkPosition(position);
        ArrayList<Position<E>> list = new ArrayList<>();
        for (Position<E> pos : node.children) {
            list.add(pos);
        }
        return list;
    }

    /**
     * auxiliary recursive method for positions() method
     **/
    private void positions(Position<E> position, ArrayList<Position<E>> list) {
        for (Position<E> w : children(position)) {
            positions(w, list);
        }
        list.add(list.size(), position); // visit (position)
    }

    @Override
    public Iterable<Position<E>> positions() {
        ArrayList<Position<E>> list = new ArrayList<>();
        if (!isEmpty()) {
            positions(root, list);
        }
        return list;
    }

    /**
     * auxiliary recursive method for elements() method
     **/
    private void elements(Position<E> position, ArrayList<E> lista) {
        lista.add(lista.size(), position.element()); // visit (position) primeiro, pre-order
        for (Position<E> w : children(position)) {
            elements(w, lista);
        }
    }

    @Override
    public Iterable<E> elements() {
        ArrayList<E> list = new ArrayList<>();
        if (!isEmpty()) {
            elements(root, list);
        }
        return list;
    }

    /////////////////////////////////////////////////////////////////////

    @Override
    public Position<E> insert(Position<E> parent, E elem)
            throws BoundaryViolationException, InvalidPositionException {
        if (isEmpty()) {
            if (parent != null) throw new InvalidPositionException("Pai não é nulo");
            this.root = new TreeNode(elem);
            return root;
        }
        TreeNode parentNode = checkPosition(parent);
        TreeNode node = new TreeNode(elem, parentNode);
        parentNode.children.add(node);
        return node;
    }


    @Override
    public Position<E> insert(Position<E> parent, E elem, int order)
            throws BoundaryViolationException, InvalidPositionException {
        if (isEmpty()) {
            if (parent != null) throw new InvalidPositionException("Pai não é nulo");
            if (order != 0) throw new BoundaryViolationException("Fora de limites");
            this.root = new TreeNode(elem);
            return root;
        }

        TreeNode parentNode = checkPosition(parent);
        if (order < 0 || order > parentNode.children.size()) {
            throw new BoundaryViolationException("Fora de limites");
        }

        TreeNode node = new TreeNode(elem, parentNode);
        parentNode.children.add(order, node);
        return node;
    }

    @Override
    public E remove(Position<E> position) throws InvalidPositionException {
        TreeNode aux = checkPosition(position);
        E elem = aux.element;
        if (aux == root) {
            root = null;
            return elem;
        }

        aux.parent.children.remove(aux);
        return elem;
    }

    public Iterable<Position<E>> breadthOrder() {
        List<TreeNode> nodeQueue = new LinkedList<>();
        List<Position<E>> elements = new LinkedList<>();
        if (isEmpty()) {
            return elements;
        }

        nodeQueue.add(this.root);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove(0);
            elements.add(node);
            for (TreeNode child : node.children) {
                nodeQueue.add(child);
            }
        }

        return elements;
    }

    public Iterable<Position<E>> depthOrder() {
        List<TreeNode> nodeStack = new LinkedList<>();
        List<Position<E>> elements = new LinkedList<>();
        if (isEmpty()) {
            return elements;
        }

        nodeStack.add(0, this.root);
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.remove(0);
            elements.add(node);
            for (TreeNode child : node.children) {
                nodeStack.add(0, child);
            }
        }
        return elements;
    }

    private int height(TreeNode treeRoot) {
        if (treeRoot == null) return -1;
        if (treeRoot.children.isEmpty()) return 0; /* important */

        /* select maximum value */
        int childMaxHeight = Integer.MIN_VALUE;
        for (TreeNode childRoot : treeRoot.children) {
            int childHeight = height(childRoot);
            if (childHeight > childMaxHeight) {
                childMaxHeight = childHeight;
            }
        }
        return 1 + childMaxHeight;
    }

    public int height() {
        return height(this.root);
    }

    private String toStringPreOrder(Position<E> position) {
        StringBuilder sb = new StringBuilder(position.element().toString()); // visit (position)

        for (Position<E> w : children(position)) {
            sb.append(",").append(toStringPreOrder(w));
        }
        return sb.toString();
    }

    public String toString() {
        String str = "";
        if (!isEmpty()) {
            str = toStringPreOrderLevels(root, 1);
        }
        return str;
    }

    /**
     *  auxiliary method to write Tree, using preorder approach
     *  */
    private String toStringPreOrderLevels(Position<E> position, int level) {
        StringBuilder sb = new StringBuilder(position.element().toString()); // visit (position)

        for (Position<E> w : children(position)) {
            sb.append("\n").append(printLevel(level)).append(toStringPreOrderLevels(w, level + 1));
        }
        return sb.toString();
    }

    /**
     * auxiliary method to format a level of the tree
     */
    private String printLevel(int level) {
        return Collections.nCopies(level,"  ") + "-";
    }

    /**
     * Move (remove e insere) um nó da árvore e torna-o descendente de outro nó.
     * @param existingPosition Elemento a mover
     * @param newParent Destino
     */
    @Override
    public void move(Position<E> existingPosition, Position<E> newParent) {
        TreeNode oldParentNode = checkPosition(parent(existingPosition));
        TreeNode newParentNode = checkPosition(newParent);

        oldParentNode.children.remove(existingPosition);

        newParentNode.children.add((TreeNode)existingPosition);
        ((TreeNode) existingPosition).parent = newParentNode;
    }

    /**
     * inner class - represent a node of a tree. Each node have a list of children, that can be empty.
     */
    private class TreeNode implements Position<E> {

        private E element;  // element stored at this node
        private TreeNode parent;  // adjacent node
        private List<TreeNode> children;  // children nodes

        TreeNode(E element) {
            this.element = element;
            parent = null;
            children = new ArrayList<>();
        }

        TreeNode(E element, TreeNode parent) {
            this.element = element;
            this.parent = parent;
            this.children = new ArrayList<>();
        }

        public E element() {
            if (element == null) {
                throw new InvalidPositionException();
            }
            return element;
        }
    }
}
