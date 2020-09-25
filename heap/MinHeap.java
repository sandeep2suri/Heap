package heap;

import java.util.Arrays;

public class MinHeap {
	static int heap[];
	static int heapSize;
	public static void main(String[] args) {
		int arr[] = {16, 5, 98, 36, 72, 10, 74, 56, 19, 92};
		//int arr[] = {5, 3, 17, 10, 84, 19, 6, 22, 9, 92};
		heapSort(arr);
	}
	
	public static void convertToMinHeap(int []arr) {
		heap = new int [arr.length];
		for(int loop=0; loop<arr.length; ++loop)
			insert(arr[loop]);
		
		System.out.println("Following is min heap:" + Arrays.toString(heap));
	}
	
	public static void heapSort(int arr[]) {
		convertToMinHeap(arr);
		heapSize = heap.length;
		System.out.print("Output of heap sort: [");
		for(int loop=0; loop<heap.length; ++loop) {
			System.out.print(delete() + ", ");
		}
		System.out.print("]");
	}

	public static int delete() {
		int result = heap[0];
		swap(0, --heapSize);
		heapify(0);
		return result;
	}
	
	public static void heapify(int index) {
		int currentIndex = index;
		int leftChildIndex = (currentIndex*2) + 1;
		int rightChildIndex = (currentIndex*2) + 2;
		while(((leftChildIndex<heapSize) && (rightChildIndex<heapSize)) &&
			  ((heap[currentIndex] > heap[leftChildIndex]) ||
		       (heap[currentIndex] > heap[rightChildIndex]))) {			
			if (heap[leftChildIndex] < heap[rightChildIndex]) {
				swap(currentIndex, leftChildIndex);
				currentIndex = leftChildIndex;
			} else {
				swap(currentIndex, rightChildIndex);
				currentIndex = rightChildIndex;
			}
			leftChildIndex = (currentIndex*2)+1;
			rightChildIndex = (currentIndex*2)+1;
		}
	}

	public static void swap (int pos, int withPos) {
		int t = heap[pos];
		heap[pos] = heap[withPos];
		heap[withPos]=t;
	}
	
	/* parent index = (i-1)/2
	 * left child = (i*2)+1
	 * right child = i*2+2
	 */
	public static void insert(int e) {
		heap[heapSize] = e;
		int currentIndex = heapSize++;
		int parentIndex = (currentIndex-1)/2;
		while(heap[currentIndex] < heap[parentIndex]) {
			swap(currentIndex, parentIndex);
			currentIndex=parentIndex;
			parentIndex=(currentIndex-1)/2;
		}
	}
}
