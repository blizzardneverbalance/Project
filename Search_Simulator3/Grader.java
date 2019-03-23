
import java.util.ArrayList;

import simulator.RBTree.Node;

public class Grader {
	//ArrayList to show how long the Web page has existed
		private static ArrayList<Integer> GradebyTime = new ArrayList<Integer>();
		//ArrayList to show the number of other Web pages that link to the page in question
		private static ArrayList<Integer> GradebyNumbers = new ArrayList<Integer>();
		//ArrayList to show how much the webpage owner has paid to Google for advertisement purpose
		private static ArrayList<Integer> GradebyPaid = new ArrayList<Integer>();
		//ArrayList of total score
		private static ArrayList<Integer> TotalGrade = new ArrayList<Integer>();
		
		//RANDOM NUMBER GENERATOR
		public static ArrayList<Integer> AutoGrader(ArrayList<String> grademe, ArrayList<Integer> grades) {
			int random;
			//The point is from 1 to 50
			for (int i = 0; i < grademe.size(); i++) {
				random = (int)(Math.random() * 100 + 1);
				grades.add(random);
			}
			return grades;
		}
		
		//Calculate the sum of ArrayList from gradeA to gradeD and store as gradeE.
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
		
		//Sort A[] by QuickSort.
		public int[] sort(int A[]) {
			QuickSort.quicksort(A, 0, A.length - 1);
			return A;
		}
		
		public static void main(String[] args) {
			Spider spider = new Spider();
			RBTree tree = new RBTree();
	  	  	//Previous is the target website while latter one is keyword
	        spider.search("http://www.sjsu.edu/", "computer science");
	        //Initatize the ArrayList of GradebyPaid
	        for (int i = 0; i < SpiderLeg.urls.size(); i++) {
	        	GradebyPaid.add(0);
	        }
	        AutoGrader(SpiderLeg.urls, GradebyTime);
	        AutoGrader(SpiderLeg.urls, GradebyNumbers);
	        //Where you increase the score by 'key' on the website with 'index' by paying
	        AutoGrader(SpiderLeg.urls, GradebyPaid);
	        sum(SpiderLeg.GradebyKeywords, GradebyTime, GradebyTime, GradebyTime, TotalGrade);
	        //Add all nodes into binary search tree
	        for (int i = 0; i < SpiderLeg.urls.size() && i < TotalGrade.size(); i++) {
	        	RBTree.Insert(tree, new Node(i + 1, TotalGrade.get(i), SpiderLeg.urls.get(i), 0, "RED"));
	        }
	        //Give rank to the node in tree
	        RBTree.giveRank(tree);
	        RBTree.InorderTreeWalk(tree.root);
	        //Test
	        //System.out.println("-----------------TEST------------------");
	        //RBTree.Insert(tree, new Node(31, 10, "31. http://studentunion.sjsu.edu", 0, "RED"));
	        //RBTree.giveRank(tree);
	        //RBTree.InorderTreeWalk(tree.root);
		}
}
