package us.aaronweiss.libsort;

/**
 * A set of core utilities for working with arrays.
 * 
 * @author Aaron Weiss
 * @version 1.1
 * @since 2/6/13
 */
public class ArrayUtils {
	/**
	 * Swap the elements of <code>array</code> at the specified indexes.
	 * 
	 * @param firstIndex
	 *            the index of the first element to swap
	 * @param secondIndex
	 *            the index of the second element to swap
	 * @param array
	 *            the array to swap the elements of
	 */
	public static void swap(int firstIndex, int secondIndex, Object[] array) {
		Object tmp = array[firstIndex];
		array[firstIndex] = array[secondIndex];
		array[secondIndex] = tmp;
	}

	/**
	 * Gets a sub-array from the specified <code>array</code>.
	 * 
	 * @param startingIndex
	 *            the starting index of the sub-array
	 * @param endingIndex
	 *            the ending index of the sub-array
	 * @param array
	 *            the array to take the sub-array from
	 * @return the desired sub-array
	 */
	public static Object[] subarray(int startingIndex, int endingIndex,
			Object[] array) {
		Object[] ret = new Object[endingIndex - startingIndex];
		for (int i = startingIndex; i < endingIndex; i++) {
			ret[i - startingIndex] = array[i];
		}
		return ret;
	}
	
	/**
	 * Appends an element to the end of the <code>array</code>.
	 * 
	 * @param toAppend
	 *            the object to append
	 * @param array
	 *            the array to append to
	 * @return the newly appended array
	 */
	public static Object[] append(Object toAppend, Object[] array) {
		Object[] ret = new Object[array.length + 1];
		for (int i = 0; i < array.length; i++) {
			ret[i] = array[i];
		}
		ret[array.length] = toAppend;
		return ret;
	}

	/**
	 * Prepend an element to the beginning of the <code>array</code>.
	 * 
	 * @param toPrepend
	 *            the object to prepend
	 * @param array
	 *            the array to pretend to
	 * @return the newly prepended array
	 */
	public static Object[] prepend(Object toPrepend, Object[] array) {
		Object[] ret = new Object[array.length + 1];
		ret[0] = toPrepend;
		for (int i = 1; i < array.length + 1; i++) {
			ret[i] = array[i];
		}
		return ret;
	}

	/**
	 * Removes the element (and its index) from the array.
	 * 
	 * @param index
	 *            the index to remove
	 * @param array
	 *            the array to modify
	 * @return the array without the desired element
	 */
	public static Object[] remove(int index, Object[] array) {
		Object[] ret = new Object[array.length - 1];
		for (int i = 0; i < index; i++) {
			ret[i] = array[i];
		}
		for (int i = index; i < array.length - 1; i++) {
			ret[i] = array[i + 1];
		}
		return ret;
	}

	/**
	 * Concatenates two arrays together.
	 * 
	 * @param firstArray
	 *            the first array to add
	 * @param secondArray
	 *            the second array to add
	 * @return the combined array of the two
	 */
	public static Object[] concatenate(Object[] firstArray, Object[] secondArray) {
		Object[] ret = new Object[firstArray.length + secondArray.length];
		for (int i = 0; i < firstArray.length; i++) {
			ret[i] = firstArray[i];
		}
		for (int i = firstArray.length; i < firstArray.length
				+ secondArray.length; i++) {
			ret[i] = secondArray[i];
		}
		return ret;
	}

	/**
	 * Concatenates three arrays together.
	 * 
	 * @param firstArray
	 *            the first array to add
	 * @param secondArray
	 *            the second array to add
	 * @param thirdArray
	 *            the third array to add
	 * @return the combined array of the three
	 */
	public static Object[] concatenate(Object[] firstArray, Object[] secondArray, Object[] thirdArray) {
		return (concatenate(firstArray, concatenate(secondArray, thirdArray)));
	}
}
