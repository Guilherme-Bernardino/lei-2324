package pt.pa.adts;

import pt.pa.model.BookmarkInvalidOperation;

import java.util.Collection;

/**
 * A tree is made up of a root that is a tree node that can have an arbitrary number of children.
 * Each child is a root tree node too.
 *
 * @param <E> Type of element stored at a tree node
 *
 */
public interface Tree<E> {


  /**
   * @return the number of nodes in the tree.
   */
  int size();


  /**
   *
   * @return whether the tree is empty.
   */
  boolean isEmpty();


  /**
   *
   * @return an iterable collection of the positions of the elements of the tree.
   */
  Iterable<Position<E>> positions();


  /**
   *
   * @return an iterable collection of the elements.
   */
  Iterable<E> elements();


  /** Replaces the element stored at a given node.
   *
   * @param position of the treeNode to replace the element
   * @param elem the new element
   * @return the replaced element
   * @throws InvalidPositionException if position is null or is not referring to a treeNode
   */
  E replace(Position<E> position, E elem)
    throws InvalidPositionException;


  /**
   *
   * @return the position of root
   * @throws EmptyTreeException if root is null
   */
  Position<E> root() throws EmptyTreeException;


  /**
   *
   * @param position of the tree node to determine the parent
   * @return the parent of the given position
   * @throws InvalidPositionException if position is null or is not referring to a treeNode
   * @throws BoundaryViolationException if there is no parent
   */
  Position<E> parent(Position<E> position)
    throws InvalidPositionException, BoundaryViolationException;


  /**
   *
   * @param position the position of the tree node to calculate the children tree nodes
   * @return an iterable collection of the children of a given node.
   * @throws InvalidPositionException if position is null or is not referring to a treeNode
   */
  Iterable<Position<E>> children(Position<E> position)
    throws InvalidPositionException;


  /** Returns whether a given node is internal.
   *
   * @param position of the tree node
   * @return true if the tree node is an internal node
   * @throws InvalidPositionException if position is null or is not referring to a treeNode
   */
  boolean isInternal(Position<E> position)
    throws InvalidPositionException;


  /**
   *
   * @param position of the tree node
   * @return true if the tree node is a external node
   * @throws InvalidPositionException if position is null or is not referring to a treeNode
   */
  boolean isExternal(Position<E> position)
    throws InvalidPositionException;


  /** Returns whether a given node is the root of the tree.
   *
   * @param position of the tree node
   * @return true if the tree node is a root
   * @throws InvalidPositionException if position is null or is not referring to a treeNode
   */
  boolean isRoot(Position<E> position)   throws InvalidPositionException;


  /**
   *
   * @param parent - Position of the ascendant node
   * @param elem  - elem to insert in the tree
   * @param order - order of the child
   * @return inserted element
   * @throws InvalidPositionException position of parent is null or is not referring to a treeNode
   * @throws BoundaryViolationException if order is greater than the number of children of the ascendant node, or less than 0.
   */
  Position<E> insert(Position<E> parent, E elem, int order)throws InvalidPositionException,BoundaryViolationException;


  /**
   *
   * @param parent - Position of the ascendant node
   * @param elem  - elem to insert in the tree
   * @return inserted element
   * @throws InvalidPositionException position of parent is null or is not referring to a treeNode
   */
  Position<E> insert(Position<E> parent, E elem) throws InvalidPositionException;


  /**
   *
   * @param position of the treeNode to be removed
   * @return the element removed
   * @throws InvalidPositionException if position is null or is not referring to a treeNode
   */
  E remove(Position<E> position)throws InvalidPositionException;


  /**
   *
   * @return the height of the tree ( -1  if is empty, 0 if has just the root) that is equal to the higher level of the tree
   */
  int height();

  /**
   * Move um nó da àrvore. Remove o nó e insere-o como descendente de outro nó.
   * @param existingPosition
   * @param newParent
   * @throws BookmarkInvalidOperation se o newParent for um link
   */
  void move(Position<E> existingPosition, Position<E> newParent);

  /**
   * Verifica se child é filho direto de parent
   * @param child
   * @param parent
   * @return valor lógico que indica se child é descendente direito de parent
   */
  boolean isParent(Position<E> child, Position<E> parent);

  /**
   * Verifica se lowerItem é descendente de upperItem (pode não ser descendente direto).
   * @param lowerItem nó "descendente"
   * @param upperItem nó "ascendente"
   * @return boolean indicando se existe uma relação de descendência entre p1 e p1.
   */
  boolean isAncestor(Position<E> lowerItem, Position<E> upperItem);
}

