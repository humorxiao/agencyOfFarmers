package scau.zxck.base.utils;

import java.io.Serializable;

/**
 * A tuple is a simple object that holds a reference to two other objects. It
 * provides hashcode and equality semantics that allow it to be used to combine
 * two objects into a single key (for hashtables, etc.).
 */
public class Tuple<L, R> implements Serializable {
	
	private static final long serialVersionUID = 1771465622058596595L;

	/** The left object. */
	public L left;

	/** The right object. */
	public R right;

	/** Construct a tuple with the specified two objects. */
	public Tuple(L left, R right) {
		this.left = left;
		this.right = right;
	}

	/** Construct a blank tuple. */
	public Tuple() {
		
	}

	/**
	 * Returns the combined hashcode of the two elements.
	 */
	public int hashCode() {
		return left.hashCode() ^ right.hashCode();
	}

	/**
	 * A tuple is equal to another tuple if the left and right elements are
	 * equal to the left and right elements (respectively) of the other tuple.
	 */
	public boolean equals(Object other) {
		if (other instanceof Tuple) {
			@SuppressWarnings("rawtypes")
            Tuple to = (Tuple) other;
			return (left.equals(to.left) && right.equals(to.right));
		} else {
			return false;
		}
	}

	/**
	 * Generates a string representation of this tuple.
	 */
	public String toString() {
		return "[left=" + left + ", right=" + right + "]";
	}
}