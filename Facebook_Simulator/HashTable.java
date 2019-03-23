
import java.util.LinkedList;

public class HashTable {
	//Inner class to represent a person
	public static class HashTableFriend {
		public String key;   //key is name
		public Person value; //value is this Person
	}
	
	public static final int size = 128;
	private LinkedList<HashTableFriend>[] array = new LinkedList[size];
	
	//Constructor
	public HashTable() {
		for(int i = 0; i < size; i++) {
			array[i] = null;
		}
	}
	
	//Get the hash table of the Person with given key
	private HashTableFriend getFriend(String key) {
		if(key == null) return null;
		int index = key.hashCode() % size;
		LinkedList<HashTableFriend> friends = array[index];
		if(friends == null) return null;
		for (HashTableFriend f : friends) {
			if(f.key.equals(key)) return f;
		}
		return null;
	}
	
	//Get the Person with given key
	public Person get(String key) {
		HashTableFriend f = getFriend(key);
		if (f == null) return null;
		else return f.value;
	}
	
	//Add a new Person into hash table by name
	public void put(String key, Person value) {
		int index = key.hashCode() % size;
		LinkedList<HashTableFriend> friends = array[index];
		if (friends == null) {
			friends = new LinkedList<HashTableFriend>();
			HashTableFriend f = new HashTableFriend();
			f.key = key;
			f.value = value;
			friends.add(f);
			array[index] = friends;
		}
		else {
			for(HashTableFriend f : friends) {
				if(f.key.equals(key)) {
					f.value = value;
					return;
				}
			}
			HashTableFriend f = new HashTableFriend();
			f.key = key;
			f.value = value;
			friends.add(f);
		}
	}
	
	//Delete a key from hash table
	public void delete(String key) {
		int index = key.hashCode() % size;
		LinkedList<HashTableFriend> friends = array[index];
		if(friends == null) return;
		for(HashTableFriend f : friends) {
			if(f.key.equals(key)) {
				friends.remove(f);
				return;
			}
		}
	}
}
