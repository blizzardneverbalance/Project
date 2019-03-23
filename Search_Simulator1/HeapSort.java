
public class HeapSort {
	private static int heapsize;
	
	//To heapify a subtree root with node i as an index in A[]
	public HeapSort(int A[]) {
		heapsize = A.length;
	}
	
	//MaxHeapify lets the value at A[i] float down in the max-heap
	//The subtree rooted at index i obeys the max-heap property
	public static void MaxHeapify(int A[], int i) {
		int l = 2 * i; //left child
		int r = 2 * i + 1; //right child
		int largest = i;
		if (l < heapsize && A[l] > A[i])
			largest = l;
		else largest = i;
		
		if (r < heapsize && A[r] > A[largest])
			largest = r;
		
		if (largest != i) {
			//exchange(A[i], A[largest]);
			int swap = A[i]; 
	         A[i] = A[largest]; 
	         A[largest] = swap; 
			MaxHeapify(A, largest);
		}
	}
	
	//Rearrange A[]
	public static void BuildMaxHeap(int A[]){
		for (int i = A.length / 2 - 1; i >= 0; i--) {
			MaxHeapify(A, i);
		}
	}
	
	//Use heapsort to sort A[]
	public static void sort(int A[]) {
		heapsize = A.length;
		BuildMaxHeap(A);
		//Extract an element from heap
		for (int i = heapsize - 1; i >= 0; i--) {
			//exchange(A[0], A[i]);
			int temp = A[0]; 
	         A[0] = A[i]; 
	         A[i] = temp;
	         //call MaxHeapify on reduced heap
			heapsize--; 
			MaxHeapify(A, 0);
		}
	}
	
	//Return maximum element from A[]
	public int HeapMaximum(int A[]) {
		return A[1];
	}
	
	//Remove and return maximum element from A[]
	public int HeapExtractMax(int A[]) {
		if (heapsize < 1)
			throw new Error("heap underflow.");
		int max = A[1];
		A[1] = A[heapsize];
		MaxHeapify(A, 1);
		return max;
	}
	
	//Increase the key of element stored at index i in A[]
	public static void HeapIncreaseKey(int A[], int i, int key) {
		if (key < A[i])
			throw new Error("new key is smaller than current key.");
		A[i] = key;
		while (i > 1 && A[i / 2] < A[i]) {
			//exchange(A[i], A[i / 2]);
			int temp = A[i]; 
	         A[i] = A[i / 2]; 
	         A[i / 2] = temp; 
	         i = i / 2;
		}
	}
	
	//Insert the element with value key in A[]
	public void MaxHeapInsert(int A[], int key) {
		heapsize++;
		A[heapsize] = Integer.MIN_VALUE;
		HeapIncreaseKey(A, heapsize, key);
	}
	
	//Print A[]
	public static void printArray(int A[]) {
        int n = A.length;
        for (int i = 0; i < n; i++)
            System.out.print(A[i] + " ");
 
        System.out.println();
    }
	
	//Test heapsort
    public static void main(String args[]) {       
    	long startTime = System.nanoTime();
        int A[] = {2, 8, 7, 5, 3, 15, 17, 13, 18, 12, 16, 4, 6, 14, 20, 1, 11, 9, 19, 15};
        System.out.print("Origin: ");
        printArray(A);
        HeapSort ob = new HeapSort(A);
        ob.sort(A);
         System.out.print("Sorted: ");
        printArray(A);
        
        long endTime   = System.nanoTime();
        long totalTime = (endTime - startTime) / 1000;
        System.out.println(totalTime + "ms");
    }
}
