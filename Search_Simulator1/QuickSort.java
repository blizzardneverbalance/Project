
public class QuickSort {
	public static void quicksort(int A[], int p, int r) {
		if (p < r) {
			int q = partition(A, p, r);
			quicksort(A, p, q - 1);
			quicksort(A, q + 1, r);
		}
	}
	
	public static int partition(int A[], int p, int r) {
		int x = A[r];
		int i = p - 1;
		for (int j = p; j <= r - 1; j++) {
			if (A[j] <= x) {
				i++;
				int temp = A[i];
				A[i] = A[j];
				A[j] = temp;
			}
		}
		int temp = A[i + 1];
		A[i + 1] = A[r];
		A[r] = temp;
		return i + 1;
	}
	
	public static void printArray(int A[]) {
        int n = A.length;
        for (int i = 0; i < n; i++)
            System.out.print(A[i] + " ");
 
        System.out.println();
    }
 
    public static void main(String args[]) {       
    	long startTime = System.nanoTime();
        int A[] = {1, 2, 4, 5, 3, 7, 8, 10, 9, 6, 12, 13, 15, 14, 17, 18, 20, 19, 16, 11};
        System.out.print("Best case: ");
        printArray(A);
        QuickSort ob = new QuickSort();        
        ob.quicksort(A, 0, A.length - 1);
         System.out.print("Sorted: ");
        printArray(A);
        
        long endTime   = System.nanoTime();
        long totalTime = (endTime - startTime) / 1000;
        System.out.println(totalTime + "ms");
    }
}
