import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JavaCollections {

    public static void main(String[] args) {
        
        // nCopies -> create n copy of a specific string and return in a form of list
        List<String> stringList = Collections.nCopies(5, "Java");

        stringList.forEach(System.out::println);

        // This list is immutable, i.e. we can't modify it
        try {
            stringList.set(0, "Python");
        } catch (Exception e) {
            System.out.println("stringList is immutable");
        }

        // frequency -> calculate no of times an element present in a list and return a Long value
        List<Integer> numList = Arrays.asList(1,2,3,4,3,2,1,7,4,5,9,8,2,7,5);
        long count = Collections.frequency(numList, 2);
        System.out.println("2 present "+count+" times");

        // calculate frequency of each element in a list
        Map<Integer, Integer> countMap = numList.stream()
                .collect(Collectors.toMap(
                    num -> num,
                    num -> Collections.frequency(numList, num),
                    (existingKey, newKey) -> existingKey
                ));
        
        System.out.println(countMap);

        // addAll -> add numbers into an existing list
        List<Integer> list1 = new ArrayList<>();
        Collections.addAll(list1, 1,2,3,4,5);

        List<Integer> list2 = new ArrayList<>();
        Collections.addAll(list2, 5,6,7,8,9);

        // disjoint -> checks if two set have any element common or not and returns a boolean value
        boolean isDisjoint = Collections.disjoint(list1, list2);

        System.out.println(isDisjoint ? "Both lists have no elements in common" :
                                        "Both lists have some elements in common");

        // singleton -> create a singleton set
        Set<String> singleton = Collections.singleton("Java");

        System.out.println(singleton);

        // rotate -> rotates a list to either left or right side to a particular step
        List<Integer> numbers = Arrays.asList(23,54,76,24,12,6,45,17);

        Collections.rotate(numbers, 3); // rotate right to 3 steps
        System.out.println(numbers);

        Collections.rotate(numbers, -4); // rotate left to 4 steps
        System.out.println(numbers);

        // sort -> sorts a list to either ascending or descending order
        Collections.sort(numbers);
        System.out.println("Sorted list in ascending order :"+numbers);

        Collections.sort(numbers, Collections.reverseOrder());
        System.out.println("Sorted list in descending order :"+numbers);

        // copy -> copies content of one list into another but size of 2 list must be same
        List<Integer> numbers2 = new ArrayList<>(Collections.nCopies(numbers.size(), 0));

        Collections.copy(numbers2, numbers);
        System.out.println(numbers2);

        // fill -> fill a list with a specific content
        // normally nCopies are immutable but if we put them inside ArrayList then we can modify them
        List<String> strings = new ArrayList<>(Collections.nCopies(5,""));

        Collections.fill(strings, "filled");
        System.out.println(strings);

        // shuffle -> shuffles list element in a random manner
        Collections.shuffle(numbers);
        System.out.println(numbers);

        // max -> gives maximum element from a list
        Integer maxNum = Collections.max(numbers);
        System.out.println(maxNum);

        // min -> gives minimum element from a list
        Integer minNum = Collections.min(numbers);
        System.out.println(minNum);
    }
}