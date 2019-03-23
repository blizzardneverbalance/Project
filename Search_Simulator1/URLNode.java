
import java.util.ArrayList;

class URLNode {
	//Build data structure (node) to collect the data of URLs and Scores
	private static URLNode next;
	private static URLNode prev;
	private String link;
	private int point;
	
	URLNode(String link, int point) {
		this.link = link;
		this.point = point;
	}
	
	URLNode getNext() {
		return next;
	}
	
	URLNode getPrev() {
		return prev;
	}
	
	String getURL() {
		return link;
	}
	
	int getPoint() {
		return point;
	}
}
