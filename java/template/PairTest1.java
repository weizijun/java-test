package template;

public class PairTest1 {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		String[] words = {"aa", "bb", "cc", "dd"};
		Pair<String> mm = ArrayAlg.minmax(words);
		System.out.println(mm.getFirst());
		System.out.println(mm.getSecond());
		
		String middle = ArrayAlg.getMiddle(words);
		System.out.println(middle);
		
		Pair<Manager> managerBuddies = makePair(Manager.class);
		//Pair<Employee> employeeBuddies = managerBuddies;
		
		//printBuddies(managerBuddies);
	}

	public static <T extends Employee> void printBuddies(Pair<T> p) {
		
	}
	
	public static void minmaxBonus(Manager[] a, Pair<? super Manager> result) {
		if (a == null || a.length == 0) {
			return ;
		}
		
		Manager min = a[0];
		Manager max = a[0];
		result.setFirst(min);
		result.setSecond(max);
	}
	
	public static <T> Pair<T> makePair(Class<T> c) throws InstantiationException, IllegalAccessException {
		return new Pair<T>(c.newInstance(), c.newInstance());
	}
}

class ArrayAlg {
	public static Pair<String> minmax(String[] a) {
		if (a == null || a.length == 0) {
			return null;
		}
		
		String min = a[0];
		String max = a[0];
		for (int i = 1; i < a.length; ++i) {
			if (min.compareTo(a[i]) > 0) {
				min = a[i];
			}
			
			if (max.compareTo(a[i]) < 0) {
				max = a[i];
			}
		}
		
		return new Pair<String>(min, max);
	}
	
	public static <T> T getMiddle(T[] a) {
		return a[a.length / 2];
	}
	
	public static <T extends Comparable<T>> T Min(T[] a) {
		if (a == null || a.length == 0) {
			return null;
		}
		
		T min = a[0];
		for (int i = 1; i < a.length; ++i) {
			if (min.compareTo(a[i]) > 0) {
				min = a[i];
			}
		}
		
		return min;
	}
}

class Employee {
	
}

class Manager extends Employee {
	
}