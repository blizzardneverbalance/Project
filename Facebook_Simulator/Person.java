
public class Person {
	String name;			//name of this Person
	LinkedList friendList;	//Friends of this Person
	//Constructor
	public Person(String name, LinkedList friendList) {
		this.name = name;
		this.friendList = friendList;
	}
	//Get name of Person a
	public String getName(Person a) {
		return a.name;
	}
	//Print out the list of friends of Person a
	public static void getFriends(Person a) {
		System.out.print(a.name + "'s friends: ");
		LinkedList.printList(a.friendList);
		System.out.println("");
	}
	//a and b are now friend. Congratulations!
	public static void addFriend(Person a, Person b) {
		LinkedList.insert(a.friendList, b);
		LinkedList.insert(b.friendList, a);
	}
	//a and b are no longer friend. Unfortunately!
	public static void removeFriend(Person a, Person b) {
		LinkedList.delete(a.friendList, b);
		LinkedList.delete(b.friendList, a);
	}
	//Check if a and b are friend
	public static boolean isFriend(Person a, Person b) {
		return LinkedList.exist(a.friendList, b);
	}
	
	public static void main(String[] args) {
		LinkedList jackfriends = new LinkedList();
		LinkedList jasonfriends = new LinkedList();
		LinkedList mikefriends = new LinkedList();
		LinkedList linafriends = new LinkedList();
		LinkedList lionfriends = new LinkedList();
		Person jack = new Person("jack", jackfriends);
		Person jason = new Person("jason", jasonfriends);
		Person mike = new Person("mike", mikefriends);
		Person lina = new Person("lina", linafriends);
		Person lion = new Person("lion", lionfriends);
		System.out.println("--------------    Test    -------------------");
		System.out.println("Add jason, mike, lina as jack's friend.");
		addFriend(jack, jason);		//Jack and Jason are now friend.
		addFriend(mike, jack);		//Mike and Jack are now friend.
		addFriend(lina, jack);		//Lina and Jack are now friend.
		getFriends(jack);
		System.out.println("Jack and Mike are friend? " + isFriend(jack, mike));
		System.out.println("Remove lina from Jack's friend.");
		removeFriend(jack, lina);	//Jack and Lina are no longer friend.
		getFriends(jack);
		isFriend(jack, lina);
		System.out.println("Jack and Lina are friend? " + isFriend(jack, lina));
	}
}
