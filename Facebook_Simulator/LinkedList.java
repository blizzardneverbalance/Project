
public class LinkedList {
	//head of list
	FriendNode head;
	//Node of linked-list
	static class FriendNode {
		Person friend;
		FriendNode next;
		//Constructor
		FriendNode(Person friend) {
			this.friend = friend;
			next = null;
		}
	}
	//Insert a new friend into the list
	public static LinkedList insert(LinkedList list, Person friend) {
		FriendNode newfriend = new FriendNode(friend);
		newfriend.next = null;
		if (list.head == null) list.head = newfriend;
		else {
			FriendNode last = list.head;
			while (last.next != null) {
				last = last.next;
			}
			last.next = newfriend;
		}
		return list;
	}
	//Remove a old friend from the list
	public static LinkedList delete(LinkedList list, Person friend) {
		FriendNode current = list.head;
		FriendNode prev = null;
		if (current != null && current.friend == friend) {
			list.head = current.next;
			return list;
		}
		while (current != null && current.friend != friend) {
			prev = current;
			current = current.next;
		}
		if (current != null) {
			prev.next = current.next;
		}
		if (current == null) {
			
		}
		return list;
	}
	//Check if this person is in the list
	public static boolean exist(LinkedList list, Person friend) {
		FriendNode currentNode = list.head;
		while (currentNode != null) {
			if (currentNode.friend.equals(friend)) return true;
			currentNode = currentNode.next;
		}
		return false;
	}
	//Print the linked-list
	public static void printList(LinkedList list) {
		FriendNode currentNode = list.head;
		//System.out.print("List of friends: ");
		while (currentNode != null) {
			System.out.print(currentNode.friend.name + " ");
			currentNode = currentNode.next;
		}
	}
}
