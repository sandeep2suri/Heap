package heap;

import java.util.Arrays;

public class MaxHeap {
	static int heap[];
	static int heapSize;
			
	public static void main(String[] args) {
		int arr[] = {16, 5, 98, 36, 72, 10, 74, 56, 19, 92};
		
		heapSort(arr);
		completeMaxHeapify(arr);
	}
	
	private static void completeMaxHeapify(int[] arr) {
		heap = new int[arr.length];
		for (int loop=0; loop<arr.length; ++loop, ++heapSize) heap[loop] = arr[loop];
		
		for (int loop=0, currIndex = (heapSize-1)/2; loop<(heapSize/2); ++loop, --currIndex)
			heapify(currIndex);

		System.out.println("CompleteMax Heapify: " + Arrays.toString(heap));
	}

	public static void convertToMaxHeap(int []arr) {
		heap = new int[arr.length];
		for (int loop=0; loop < arr.length; ++loop)
			insert(arr[loop]);
		System.out.println("Following is heap:" + Arrays.toString(heap));
	}
	
	/*  parent: heap[(currIndex-1)/2]
	 * child->left: heap[currIndex*2]
	 * child->right: heap[(currIndex*2)+1]
	 * sibling: heap[currIndex++]
	 */
	public static void insert(int e) {
		// insert element to end of heap
		heap[heapSize] = e;
		int currIndex = heapSize++;
		int parentIndex = (currIndex-1)/2;
		while (heap[currIndex]>heap[parentIndex]) {
			swap(currIndex, parentIndex);
			currIndex = parentIndex;
			parentIndex = (currIndex-1)/2;
		}
	}
	
	public static void heapSort(int arr[]) {
		convertToMaxHeap(arr);
		System.out.print("Heapsort output:[");
		for (int loop=0; loop < heap.length; ++loop) {
			System.out.print(delete());
			System.out.print(", ");
		}
		System.out.print("]\n");
	}
	
	public static int delete() {
		// remove root
		int result = heap[0];
		swap(0, --heapSize);
		// heapify
		heapify(0);
		return result;
	}
	
	public static void swap (int pos, int withPos) {
		int t = heap[pos];
		heap[pos] = heap[withPos];
		heap[withPos] = t;
	}
	
	public static void heapify(int index) {
		int currentIndex = index;
		int leftChildIndex = (currentIndex*2) + 1;
		int rightChildIndex = (currentIndex*2) + 2;
		
		while (((leftChildIndex<heapSize) &&						// if left child is present in heap 
				(heap[currentIndex] < heap[leftChildIndex])) ||		// swap left child?
			   ((rightChildIndex<heapSize) &&						// if right child is present in heap 
			    (heap[currentIndex] < heap[rightChildIndex]))) {	// swap right child?
			if (rightChildIndex < heapSize) {
				if (heap[rightChildIndex] < heap[leftChildIndex]) {
					// swap left child
					swap(currentIndex, leftChildIndex);
					currentIndex = leftChildIndex;
				} else {
					swap(currentIndex, rightChildIndex);
					currentIndex = rightChildIndex;
				}
			} else if (leftChildIndex < heapSize) {
				// swap right child
				swap(currentIndex, leftChildIndex);
				currentIndex = leftChildIndex;
			} else {
				break;
			}
			leftChildIndex = (currentIndex*2) + 1;
			rightChildIndex = (currentIndex*2) + 2;
		}
	}
}
