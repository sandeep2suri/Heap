package heap;

public class RunningIntegerMedian {
	private int SIZE = 1024;
	private Heap min = new Heap(SIZE, HeapType.Min);
	private Heap max = new Heap(SIZE, HeapType.Max);
	private int median = 0;
	
	static enum HeapType {
		Min,
		Max
	}
	
	static class Heap {
		int heap[];
		int heapSize;
		HeapType type;
		
		public Heap(int heapSize, HeapType type) {
			this.type = type;
			heap = new int [heapSize];
			this.heapSize = 0;
		}
		
		public void insert(int e) {
			int index = heapSize++;
			int parent = getParent(index);
			// insert at last
			heap[index] = e;
			// place newly insert element in heap
			switch (this.type) {
			case Min:
				while(heap[index]<heap[parent]) {
					swap(index, parent);
					index = parent;
					parent = getParent(index);
				}
				break;
			case Max:
				while(heap[index]>heap[parent]) {
					swap(index, parent);
					index = parent;
					parent = getParent(index);
				}
			}
		}
		
		public int extractTop() {
			int result = getTop();
			// make last element as root
			heap[0] = heap[--heapSize];
			// heapify
			heapify(0);
			return result;
		}
		
		public int getTop() {
			// return top
			return heap[0];
		}
		
		public void heapify (int index) {
			int l = getLeftChild(index);
			int r = getRightChild(index);
			switch (this.type) {
			case Min:
				while (((l < heapSize) && (heap[index] > heap[l]) || ((r < heapSize) && (heap[index] > heap[r])))) {
					if (r < heapSize) { // compare right and left child only if right child is present
						// make smaller one as root
						if (heap[l] < heap[r]) {
							swap(index, l);
							index = l;
						} else {
							swap(index, r);
							index = r;
						}
					} else { // in case there is only left child make him as parent
						swap(index, l);
						index = r;
					}
					l = getLeftChild(index);
					r = getRightChild(index);
				}
				break;
			case Max:
				while (((l < heapSize) && (heap[index] < heap[l]) || ((r < heapSize) && (heap[index] < heap[r])))) {
					if (r < heapSize) { // compare right and left child only if right child is present
						// make smaller one as root
						if (heap[l] > heap[r]) {
							swap(index, l);
							index = l;
						} else {
							swap(index, r);
							index = r;
						}
					} else { // in case there is only left child make him as parent
						swap(index, l);
						index = r;
					}
					l = getLeftChild(index);
					r = getRightChild(index);
				}
				break;
			}
		}
		
		private void swap(int pos, int withPos) {
			int temp = heap[pos];
			heap[pos] = heap[withPos];
			heap[withPos] = temp;
		}
		
		private int getParent(int index) {
			return (index-1)/2;
		}
		
		private int getLeftChild(int index) {
			return ((index * 2) + 1);
		}
		
		private int getRightChild(int index) {
			return ((index * 2) + 2);
		}
	}
	
	private int getSignum() {
		if (this.min.heapSize == this.max.heapSize)
			return 0;
		
		return this.min.heapSize < this.max.heapSize ? 1 : -1;
	}
	
	private int insert(int e) {
		int i = getSignum();
		switch (i) {
		case 1: // max heap
			if (e < median) {
				min.insert(max.extractTop());
				max.insert(e);
			} else {
				min.insert(e);
			}
			median = (min.getTop() + max.getTop())/2;
			break;
		case 0: // equal
			if (e < median) {
				max.insert(e);
				median = max.getTop();
			} else {
				min.insert(e);
				median = min.getTop();
			}
			break;
		case -1: // min heap
			if (e < median) {
				max.insert(e);
			} else {
				max.insert(min.extractTop());
				min.insert(e);
			}
			median = (min.getTop() + max.getTop())/2;
			break;
		default:
			throw new RuntimeException("Error");
		}
		return median;
	}
	
	public static void main (String args[]) {
		int arr[] = {5, 15, 1, 3};
		
		RunningIntegerMedian inst = new RunningIntegerMedian();
		
		for (int l = 0; l < arr.length; ++l)
			System.out.println(inst.insert(arr[l]));
	}
	
}
