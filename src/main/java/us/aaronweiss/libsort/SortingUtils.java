package us.aaronweiss.libsort;

/**
 * A set of utilities for sorting 
 * 
 * @author Aaron
 *
 */
public class SortingUtils {
	/**
	 * Sorts an array by iterating over each adjacent pair.
	 * Average case performance: O(n²)
	 * 
	 * @param toSort
	 *            the array to sort
	 * @see http://en.wikipedia.org/wiki/Bubble_sort
	 */
	public static void bubblesort(Sortable[] toSort) {
		for (int i = 1; i < toSort.length - 2; i++) {
			for (int k = 0; k < toSort.length - 1; k++) {
				if (toSort[k + 1].sortValue() < toSort[k].sortValue()) {
					ArrayUtils.swap(k, k + 1, toSort);
				}
			}
		}
	}

	/**
	 * Sorts an array using a fast divide-and-conquer technique.
	 * Average case performance: O(n log n)
	 * 
	 * @param toSort
	 *            the array to sort
	 * @param stable
	 *            whether or not to use a stable sort (unstable is faster)
	 * @see http://en.wikipedia.org/wiki/Quicksort
	 * @see http://en.wikipedia.org/wiki/Sorting_algorithm#Stability
	 */
	public static void quicksort(Sortable[] toSort, boolean stable) {
		if (stable)
			stableQuicksort(toSort);
		else
			quicksort(toSort);
	}
	
	/**
	 * Sorts an array using a fast, stable divide-and-conquer technique.
	 * Average case performance: O(n log n)
	 * Note: slower than unstable quicksort
	 * 
	 * @param toSort
	 *            the array to sort
	 * @return the sorted array
	 * @see http://en.wikipedia.org/wiki/Quicksort
	 * @see http://en.wikipedia.org/wiki/Sorting_algorithm#Stability
	 */
	public static void stableQuicksort(Sortable[] toSort) {
		toSort = quicksortStable(toSort);
	}
	
	
	/**
	 * Sorts an array using a fast, stable divide-and-conquer technique.
	 * Average case performance: O(n log n)
	 * Note: slower than unstable quicksort
	 * 
	 * @param toSort
	 *            the array to sort
	 * @return the sorted array
	 * @see http://en.wikipedia.org/wiki/Quicksort
	 * @see http://en.wikipedia.org/wiki/Sorting_algorithm#Stability
	 */
	private static Sortable[] quicksortStable(Sortable[] toSort) {
		if (toSort.length <= 1)
			return toSort;
		Sortable[] pivotArray = { toSort[toSort.length / 2] };
		int pivot = toSort[toSort.length / 2].sortValue();
		Sortable[] less = new Sortable[0];
		Sortable[] greater = new Sortable[0];
		for (Sortable s : toSort) {
			if (s.sortValue() <= pivot)
				less = (Sortable[]) ArrayUtils.append(s, less);
			else
				greater = (Sortable[]) ArrayUtils.append(s, greater);
		}
		return (Sortable[]) ArrayUtils.concatenate(quicksortStable(less), pivotArray, quicksortStable(greater));
	}

	/**
	 * Sorts an array using a fast, but unstable divide-and-conquer technique.
	 * Average case performance: O(n log n)
	 * 
	 * @param toSort
	 *            the array to sort
	 * @see http://en.wikipedia.org/wiki/Quicksort
	 */
	public static void quicksort(Sortable[] toSort) {
		quicksort(0, toSort.length - 1, toSort);
	}

	/**
	 * Sorts an array using a fast, but unstable divide-and-conquer technique.
	 * Average case performance: O(n log n)
	 * 
	 * @param startingIndex
	 *            the leftmost index to sort from
	 * @param endingIndex
	 *            the rightmost index to sort to
	 * @param toSort
	 *            the array to sort
	 * @see http://en.wikipedia.org/wiki/Quicksort
	 */
	private static void quicksort(int startingIndex, int endingIndex, Sortable[] toSort) {
		if (startingIndex < endingIndex) {
			int pivotIndex = (startingIndex + endingIndex) / 2;
			pivotIndex = quicksortPartition(startingIndex, endingIndex, pivotIndex, toSort);
			quicksort(startingIndex, pivotIndex - 1, toSort);
			quicksort(pivotIndex + 1, endingIndex, toSort);
		}
	}

	/**
	 * Partitions an array for quicksort.
	 * 
	 * @param startingIndex
	 *            the leftmost index of the partition
	 * @param endingIndex
	 *            the rightmost index of the partition
	 * @param pivotIndex
	 *            the index of the pivot value
	 * @param toPartition
	 *            the array to partition
	 * @return the partitioned array
	 * @see http://en.wikipedia.org/wiki/Quicksort
	 */
	private static int quicksortPartition(int startingIndex, int endingIndex, int pivotIndex, Sortable[] toPartition) {
		int pivotValue = toPartition[pivotIndex].sortValue();
		ArrayUtils.swap(pivotIndex, endingIndex, toPartition);
		int storeIndex = startingIndex;
		for (int i = startingIndex; i < endingIndex; i++)
			if (toPartition[i].sortValue() < pivotValue)
				ArrayUtils.swap(i, storeIndex++, toPartition);
		ArrayUtils.swap(storeIndex, endingIndex, toPartition);
		return storeIndex;
	}
	
	/**
	 * Sorts an array by inserting unsorted elements into their proper positions.
	 * Average case performance: O(n²)
	 * 
	 * @param toSort
	 *            the array to sort
	 * @see http://en.wikipedia.org/wiki/Insertion_sort
	 */
	public static void insertionSort(Sortable[] toSort) {
		for (int i = 1; i < toSort.length; i++) {
			Sortable valueToInsert = toSort[i];
			int holePosition = i;
			while (holePosition > 0 && valueToInsert.sortValue() < toSort[holePosition - 1].sortValue()) {
				toSort[holePosition] = toSort[--holePosition];
			}
			toSort[holePosition] = valueToInsert;
		}
	}
	
	/**
	 * Sorts an array by breaking it apart into smallest parts and merging them back together.
	 * Average case performance: O(n log n) or O(n)
	 * 
	 * @param toSort
	 *            the array to sort
	 * @see http://en.wikipedia.org/wiki/Merge_sort
	 */
	public static void mergeSort(Sortable[] toSort) {
		if (toSort.length <= 1) 
			return;
		Sortable[] left = new Sortable[0];
		Sortable[] right = new Sortable[0];
		int middle = toSort.length / 2;
		for (int i = 0; i < middle; i++)
			ArrayUtils.append(toSort[i], left);
		for (int i = middle; i < toSort.length; i++)
			ArrayUtils.append(toSort[i], right);
		mergeSort(left);
		mergeSort(right);
		toSort = (Sortable[]) ArrayUtils.concatenate(left, right);	
	}
	
	/**
	 * Sorts an array by scanning through it and moving the elements to a sorted position.
	 * Average case performance: O(n²)
	 * 
	 * @param toSort
	 *            the array to sort
	 * @see http://en.wikipedia.org/wiki/Cocktail_sort
	 */
	public static void cocktailSort(Sortable[] toSort) {
		int begin = -1;
		int end = toSort.length - 2;
		boolean swapped;
		do {
			swapped = false;
			begin++;
			for (int i = begin; i <= end; i++) {
				if (toSort[i].sortValue() > toSort[i + 1].sortValue()) {
					ArrayUtils.swap(i, i + 1, toSort);
					swapped = true;
				}
			}
			if (!swapped)
				break;
			swapped = false;
			end--;
			for (int i = end; i >= begin; i--) {
				if (toSort[i].sortValue() > toSort[i + 1].sortValue()) {
					ArrayUtils.swap(i, i + 1, toSort);
					swapped = true;
				}
			}
		} while (swapped);
	}
	
	/**
	 * Sorts an array by scanning it to find the smallest element and putting it in the front.
	 * Average case performance: O(N^2)
	 * 
	 * @param toSort
	 * 		array to be sorted.
	 * @see http://en.wikipedia.org/wiki/Selection_sort
	 */
	 public static void sort( Comparable[] toSort ) {
        
        	int N = toSort.length;
        
        	for( int i = 0; i < N; i++ ) {
            
            		int min = i;
            
            		for( int j = i + 1; j < N; j++ ) {
                
                		if( toSort[ j ].compareTo( toSort[ min ] ) == -1 )
                    			min = j;
            		}
            
            		Comparable temp = toSort[ i ];
            		toSort[ i ] = toSort[ min ];
            		toSort[ min ] = temp;
            
        	}

    	}
}
