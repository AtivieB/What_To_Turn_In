
import java.util.*;

public class DataStructurePerformance {
    public static void main(String[] args) {
        final int N = 50000;
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        Set<Integer> hashSet = new HashSet<>(numbers);
        Set<Integer> linkedHashSet = new LinkedHashSet<>(numbers);
        Set<Integer> treeSet = new TreeSet<>(numbers);
        List<Integer> arrayList = new ArrayList<>(numbers);
        List<Integer> linkedList = new LinkedList<>(numbers);

        System.out.println("Test times for element existence:");
        System.out.println("HashSet: " + getTestTime(hashSet) + " ms");
        System.out.println("LinkedHashSet: " + getTestTime(linkedHashSet) + " ms");
        System.out.println("TreeSet: " + getTestTime(treeSet) + " ms");
        System.out.println("ArrayList: " + getTestTime(arrayList) + " ms");
        System.out.println("LinkedList: " + getTestTime(linkedList) + " ms");

        System.out.println("\nTest times for element removal:");
        System.out.println("HashSet: " + getRemoveTime(hashSet) + " ms");
        System.out.println("LinkedHashSet: " + getRemoveTime(linkedHashSet) + " ms");
        System.out.println("TreeSet: " + getRemoveTime(treeSet) + " ms");
        System.out.println("ArrayList: " + getRemoveTime(arrayList) + " ms");
        System.out.println("LinkedList: " + getRemoveTime(linkedList) + " ms");
    }

    private static long getTestTime(Collection<Integer> collection) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            collection.contains((int)(Math.random() * 50000));
        }
        return System.currentTimeMillis() - startTime;
    }

    private static long getRemoveTime(Collection<Integer> collection) {
        long startTime = System.currentTimeMillis();
        Iterator<Integer> iterator = collection.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        return System.currentTimeMillis() - startTime;
    }
}
