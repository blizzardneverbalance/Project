
import java.util.ArrayList;
import java.util.Scanner;

public class Grader {
	//ArrayList to show how long the Web page has existed
	private static ArrayList<Integer> GradebyTime = new ArrayList<Integer>();
	//ArrayList to show the number of other Web pages that link to the page in question
	private static ArrayList<Integer> GradebyNumbers = new ArrayList<Integer>();
	//ArrayList to show how much the webpage owner has paid to Google for advertisement purpose
	private static ArrayList<Integer> GradebyPaid = new ArrayList<Integer>();
	//ArrayList of total score
	private static ArrayList<Integer> TotalGrade = new ArrayList<Integer>();
	//Array of total score
	private static int[] gradeArray = null;
	//Top-10 URLs
	private static ArrayList<String> top10 = new ArrayList<String>();
	
	private static URLNode node;
	private static ArrayList<URLNode> nodeList = new ArrayList<URLNode>();
	private static String keyword = new String();
	
	//RANDOM NUMBER GENERATOR
	public static ArrayList<Integer> AutoGrader(ArrayList<String> grademe, ArrayList<Integer> grades) {
		int random;
		//The point is from 1 to 100
		for (int i = 0; i < grademe.size(); i++) {
			random = (int)(Math.random() * 100 + 1);
			grades.add(random);
		}
		return grades;
	}
	
	//Increase the key of element stored at index i in ArrayList payMe
	public static void Paid(ArrayList<Integer> payMe, int i, int key) {
		int[] payArray = toArray(payMe);
		HeapSort.HeapIncreaseKey(payArray, i, key);
	}
	
	//Calculate the sum of ArrayList from gradeA to gradeD and store as gradeE
	public static ArrayList<Integer> sum(ArrayList<Integer> gradeA, ArrayList<Integer> gradeB, 
			ArrayList<Integer> gradeC, ArrayList<Integer> gradeD, ArrayList<Integer> gradeE) {
		for (int i = 0; i < gradeA.size() && i < gradeB.size() && i < gradeC.size() && i < gradeD.size(); i++) {
			gradeE.add(gradeA.get(i)+ gradeB.get(i) + gradeC.get(i)+ gradeD.get(i));
		}
		return gradeE;
	}
	
	//Convert list to an array
	private static int[] toArray(ArrayList<Integer> list) {
		int[] arr = new int[list.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = list.get(i);
		}
		return arr;
	}
	
	//Sort A[] by heapsort
	public static int[] sort(int A[]) {
		QuickSort.quicksort(A, 0, A.length - 1);
		return A;
	}
	
	public static void main(String[] args)
    {
		//Scanner scan = new Scanner(System.in);
		//System.out.println("Key word: ");
		//if (scan.hasNextLine()) {
		//	String keyword = scan.nextLine();
		//}
  	  	Spider spider = new Spider();
  	  	//Previous is the target website while latter one is keyword
        spider.search("http://www.sjsu.edu/", "computer science");
        //Initatize the ArrayList of GradebyPaid
        for (int i = 0; i < SpiderLeg.urls.size(); i++) {
        	GradebyPaid.add(0);
        }
        int index = 0; //The index of URL which paid to Google
        int key = 0; //How many score to add
        AutoGrader(SpiderLeg.urls, GradebyTime);
        AutoGrader(SpiderLeg.urls, GradebyNumbers);
        //Where you increase the score by 'key' on the website with 'index' by paying
        Paid(GradebyPaid, index, key);
        sum(SpiderLeg.GradebyKeywords, GradebyTime, GradebyNumbers, GradebyPaid, TotalGrade);
        //Add all nodes into nodeList
        for (int i = 0; i < SpiderLeg.urls.size() && i < TotalGrade.size(); i++) {
        	node = new URLNode(SpiderLeg.urls.get(i), TotalGrade.get(i));
        	nodeList.add(node);
        	System.out.println(nodeList.get(i).getURL() + " Score: " + nodeList.get(i).getPoint());
        }
        //Sort totalGrade by Heapsort
        gradeArray = sort(toArray(TotalGrade));
        //Get Top10
        for (int i = 0; i < gradeArray.length; i++) {
        	int maxnumber = gradeArray[gradeArray.length - i - 1];
        	for (int j = 0; j < nodeList.size(); j++) {
        		if (nodeList.get(j).getPoint() == maxnumber && top10.size() < 10) {
        			top10.add(nodeList.get(j).getURL() + " Score: " + nodeList.get(j).getPoint());
        			nodeList.remove(nodeList.get(j));
        		}
        	}
        }
        System.out.println("-------------------TOP10: -------------------");
        //Print Top10
        for (int i = 0; i < top10.size(); i++) {
        	System.out.println(top10.get(i));
        }
        //scan.close();
    }
}
