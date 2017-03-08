package fundation;

import java.util.Arrays;

public class BTree implements Cloneable {

	private static final int MINIMUM = 1;
	private static final int MAXIMUM = MINIMUM * 2;
	int dataCount;
	int[] data = new int[MAXIMUM + 1];
	int childCount;
	BTree[] subset = new BTree[MAXIMUM + 2]; // + Head & Tail

	// Constructor: initialize an empty set
	public BTree() {
		this.dataCount = 0;
		this.childCount = 0;
	}

	// add: add a new element to this set, if the element was already in the
	// set, then there is no change.
	public void add(int element) {
	}

	// clone: generate a copy of this set.
	public BTree clone() {
		return this;
	}

	// contains: determine whether a particular element is in this set
	public boolean contains(int target) {
		int i = firstGE(target);
		if (data[i] == target) {
			return true;
		} else if (childCount == 0) {
			return false;
		} else {
			return subset[i].contains(target);
		}
	}

	// remove: remove a specified element from this set
	public boolean remove(int target) {
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private int firstGE(int target) {
		for (int i = 0; i < data.length; i++) {
			if (data[i] >= target) {
				return data[i];
			}
		}
		return dataCount;
	}

	private void looseAdd(int element){
	    int i = firstGE(element); // find the first index such that data[i] >= element
	    if (data[i] == element){
	    	return; // since there's already a copy in the set
	    }else if (childCount == 0){ // Add the new element to the root at data[i]. (shift array)
	    	dataCount ++;
	    	for (int j = dataCount - 1;  j > i; j--){
	    		data[j] = data[j-1];
	    	}
	    	data[i] = element;
	    }else{
	    	subset[i].looseAdd(element);
	    	if (subset[i].dataCount > MAXIMUM){  // if the root of subset[i] now has an excess element, then fix that problem before returning.
	    		fixExcess(i);
	    	}
	    }
	}
	
	// precondition: (i < childCount) and the entire B-tree is valid except that subset[i] has MAXIMUM + 1 elements.
	// postcondition: the tree is rearranged to satisfy the loose addition rule
	private void fixExcess(int i){
		if (i < childCount && dataCount <= MAXIMUM && subset[i].dataCount == MAXIMUM +1){
			
		}
	}


}
