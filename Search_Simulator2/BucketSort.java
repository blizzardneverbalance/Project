
import java.util.HashMap;
import java.util.Vector;

public class BucketSort {
	public static void bucksort(String A[]) {
		//If the array is empty, return
		if (A.length == 0) return;
		//Find the length of the link with the biggest length
		int length = 0;
		for (String link : A) {
			if (length < link.length()) length = link.length();
		}
		//Create 26 buckets represent 26 Latin character
		int theBuckets = 26;
		HashMap<Character, Vector<String>> buckets = new HashMap<Character, Vector<String>>(theBuckets);
		char Chars = 'a';
		for (int i = 0; i <= theBuckets; i++) {
			buckets.put(Chars, new Vector<String>());
			Chars++;
		}
		//Put links into buckets
		for (String link : A) {
			char letter = link.toLowerCase().charAt(0);
			buckets.get(letter).add(link);
		}
		//Put links back into array
		int index = 0;
		for (char key = 'a'; key <= 'z'; key++) {
			Vector<String> bucket = buckets.get(key);
			//Insertion sort
			for (int i = 1; i < bucket.size(); i++) {
				String word = bucket.get(i);
				bucket.remove(i);
				int j = 0;
				for (j = i - 1; j >= 0 && bucket.get(j).compareToIgnoreCase(word) > 0; j--) {
					bucket.add(j + 1, bucket.get(j));
					bucket.remove(j);
				}
				bucket.add(j + 1, word);
			}
			for (int k = 0; k < bucket.size(); k++) {
				A[index++] = bucket.get(k);
			}
		}
	}
}
