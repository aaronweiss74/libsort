package us.aaronweiss.libsort.example;

import us.aaronweiss.libsort.Sortable;

public class SortableByte implements Sortable {
	public final byte value;
	
	public SortableByte(byte value) {
		this.value = value;
	}

	@Override
	public int sortValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.value);
	}
}
