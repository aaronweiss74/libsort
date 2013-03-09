package us.aaronweiss.libsort.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import us.aaronweiss.libsort.ArrayUtils;
import us.aaronweiss.libsort.Sortable;
import us.aaronweiss.libsort.SortingUtils;

/**
 * A basic benchmark for testing sorting algorithms.
 * 
 * @author Aaron Weiss
 * @version 1.0
 * @since 3/8/13
 */
public class SortingBenchmark {
	public static void main(String[] args) throws IOException, InterruptedException {
		SortableByte[] data;
		{
			System.out.println("load: input.dat: start");
			long start_nano = System.nanoTime(), start_millis = System.currentTimeMillis();
			data = load("input.dat");
			long end_nano = System.nanoTime(), end_millis = System.currentTimeMillis();
			System.out.println("load: sorted: " + isSorted(data));
			System.out.println("load: input.dat: end");
			System.out.println("took " + (end_millis - start_millis) + "ms " + (end_nano - start_nano) + "µs");
		}
		Thread.sleep(1000);
		System.out.println();
		{
			SortableByte[] work = data.clone();
			System.out.println("algo: quicksort: start");
			long start_nano = System.nanoTime(), start_millis = System.currentTimeMillis();
			SortingUtils.quicksort(work);
			long end_nano = System.nanoTime(), end_millis = System.currentTimeMillis();
			System.out.println("algo: sorted: " + isSorted(work));
			System.out.println("algo: quicksort: end");
			System.out.println("took " + (end_millis - start_millis) + "ms " + (end_nano - start_nano) + "µs");
		}
		Thread.sleep(1000);
		System.out.println();
		{
			SortableByte[] work = data.clone();
			System.out.println("algo: stable quicksort: start");
			long start_nano = System.nanoTime(), start_millis = System.currentTimeMillis();
			try {
				SortingUtils.quicksort(work, true);
			} catch (StackOverflowError e) {
				System.out.println("algo: stable quicksort: recursion too deep - increase stack size for this data set");
			}
			long end_nano = System.nanoTime(), end_millis = System.currentTimeMillis();
			System.out.println("algo: sorted: " + isSorted(work));
			System.out.println("algo: stable quicksort: end");
			System.out.println("took " + (end_millis - start_millis) + "ms " + (end_nano - start_nano) + "µs");
		}
		Thread.sleep(1000);
		System.out.println();
		{
			SortableByte[] work = data.clone();
			System.out.println("algo: bubblesort: start");
			long start_nano = System.nanoTime(), start_millis = System.currentTimeMillis();
			SortingUtils.bubblesort(work);
			long end_nano = System.nanoTime(), end_millis = System.currentTimeMillis();
			System.out.println("algo: sorted: " + isSorted(work));
			System.out.println("algo: bubblesort: end");
			System.out.println("took " + (end_millis - start_millis) + "ms " + (end_nano - start_nano) + "µs");
		}
		Thread.sleep(1000);
		System.out.println();
		{
			SortableByte[] work = data.clone();
			System.out.println("algo: insertion sort: start");
			long start_nano = System.nanoTime(), start_millis = System.currentTimeMillis();
			SortingUtils.insertionSort(work);
			long end_nano = System.nanoTime(), end_millis = System.currentTimeMillis();
			System.out.println("algo: sorted: " + isSorted(work));
			System.out.println("algo: insertion sort: end");
			System.out.println("took " + (end_millis - start_millis) + "ms " + (end_nano - start_nano) + "µs");
		}
		Thread.sleep(1000);
		System.out.println();
		{
			SortableByte[] work = data.clone();
			System.out.println("algo: merge sort: start");
			long start_nano = System.nanoTime(), start_millis = System.currentTimeMillis();
			SortingUtils.mergeSort(work);
			long end_nano = System.nanoTime(), end_millis = System.currentTimeMillis();
			System.out.println("algo: sorted: " + isSorted(work));
			System.out.println("algo: merge sort: end");
			System.out.println("took " + (end_millis - start_millis) + "ms " + (end_nano - start_nano) + "µs");
		}
		Thread.sleep(1000);
		System.out.println();
		{
			SortableByte[] work = data.clone();
			System.out.println("algo: selection sort: start");
			long start_nano = System.nanoTime(), start_millis = System.currentTimeMillis();
			SortingUtils.selectionSort(work);
			long end_nano = System.nanoTime(), end_millis = System.currentTimeMillis();
			System.out.println("algo: sorted: " + isSorted(work));
			System.out.println("algo: selection sort: end");
			System.out.println("took " + (end_millis - start_millis) + "ms " + (end_nano - start_nano) + "µs");
		}
		System.out.println();
		{
			SortableByte[] work = data.clone();
			System.out.println("algo: cocktail sort: start");
			long start_nano = System.nanoTime(), start_millis = System.currentTimeMillis();
			SortingUtils.cocktailSort(work);
			long end_nano = System.nanoTime(), end_millis = System.currentTimeMillis();
			System.out.println("algo: sorted: " + isSorted(work));
			System.out.println("algo: cocktail sort: end");
			System.out.println("took " + (end_millis - start_millis) + "ms " + (end_nano - start_nano) + "µs");
		}
		Thread.sleep(1000);
		System.out.println();
		{
			SortableByte[] work = data.clone();
			System.out.println("algo: shellsort: start");
			long start_nano = System.nanoTime(), start_millis = System.currentTimeMillis();
			SortingUtils.shellsort(work);
			long end_nano = System.nanoTime(), end_millis = System.currentTimeMillis();
			System.out.println("algo: sorted: " + isSorted(work));
			System.out.println("algo: shellsort: end");
			System.out.println("took " + (end_millis - start_millis) + "ms " + (end_nano - start_nano) + "µs");
		}
	}
	
	public static boolean isSorted(Sortable[] array) {
		for (int i = 1; i < array.length; i++)
			if (array[i].sortValue() < array[i - 1].sortValue())
				return false;
		return true;
	}
	
	public static void printArray(String name, Sortable[] array) {
		System.out.print(name + ": ");
		for (Sortable s : array) {
			System.out.print(s + " ");
		}
		System.out.println();
	}
	
	public static SortableByte[] load(String filename) throws IOException {
		File file = new File(filename);
		byte[] load = Files.readAllBytes(file.toPath());
		Object[] data = new SortableByte[0];
		for (byte b : load) {
			data = ArrayUtils.append(new SortableByte(b), data);
		}
		SortableByte[] ret = new SortableByte[data.length];
		for (int i = 0; i < data.length; i++) {
			ret[i] = (SortableByte) data[i];
		}
		return ret;
	}
}
