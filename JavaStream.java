import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStream {
    public static boolean checkPrime(int num){
        for(int i=2; i<=num/2 + 1; i++){
            if(num % i == 0){
                return false;
            }
        }

        return true;
    }
    public static void main(String[] args) {
        //Operation 1 : converting list into stream
        List<String> fruits = Arrays.asList("Apple", "Banana", "Mango");
        fruits.stream().forEach(System.out::println);

        // Operation 2 : Converting array into Stream
        int[] nums = {23, 56, 43, 82, 9, 74};
        List<Integer> numList = Arrays.stream(nums)
                                      .boxed()
                                      .toList();

        numList.forEach(System.out::println);

        // Operation 3 : List filtering (String)
        List<String> filteredFruits = fruits.stream()
                                            .filter(fruit -> fruit.startsWith("A"))
                                            .toList();

        filteredFruits.forEach(System.out::println);

        // Operation 4 : List filtering (Integer)
        List<Integer> evenList = numList.stream()
                         .filter(num -> num%2==0)
                         .toList();
        
        evenList.forEach(System.out::println);

        // Operation 5 : List Mapping
        List<Integer> oddList = numList.stream()
                         .map(num -> num+2)
                         .toList();

        oddList.forEach(System.out::println);

        // Operation 6 : sorting prime numbers
        List<Integer> sortedPrime = numList.stream()
                                        .filter(num -> checkPrime(num))
                                        .sorted()
                                        .toList();

        sortedPrime.forEach(System.out::println);

        // Operation 7 : list reducing
        int sumOfNums = numList.stream()
                                .reduce(0, Integer::sum);

        System.out.println(sumOfNums);

        // Operation 8 : list reducing (finding minimum number)
        int minNum = numList.stream()
                            .reduce(Integer.MAX_VALUE, Integer::min);

        System.out.println(minNum);

        // Operation 9 : list reducing (finding maximum number)
        int maxNum = numList.stream()
                            .reduce(Integer.MIN_VALUE, Integer::max);

        System.out.println(maxNum);

        // Operation 10 : list reducing (custom function for multiplication)
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        int product = numbers.stream()
                            .reduce(1, (a,b) -> a * b);

        System.out.println(product);

        // Operation 11 : Concatenate strings
        List<String> words = Arrays.asList("Hello","my","name","is","Subhadeep");

        String sentence = words.stream()
                            .reduce("", (w1, w2) -> w1+" "+w2);

        System.out.println(sentence);

        // Operation 12 : list reducing with conditions
        int evenProduct = numList.stream()
                            .reduce(1, (a,b) -> (a%2==1 && b%2==1) ? a * b : 1);

        System.out.println(evenProduct);

        // Operation 13 : list reducing (Sum of word length)
        int sumOfWordLength = words.stream()
                                    .map( word -> word.length())
                                    .reduce(0, Integer::sum);

        System.out.println(sumOfWordLength);

        // another way
        int sumOfWordLength2 = words.stream()
                                .map(word -> word.length())
                                .reduce(0, (w1, w2) -> Integer.sum(w1, w2));

        System.out.println(sumOfWordLength2);

        // Operation 14 : Avoid null values from a list
        List<String> names = Arrays.asList("Samrat","Subhadeep",null,"Sanjit","Ram",null,"Sham");

        List<String> nameResult = names.stream()
                                        .flatMap(Stream::ofNullable)
                                        .collect(Collectors.toList());
        System.out.println(nameResult);

        // Operation 15 : Generate a list based on specific condition or pattern
        List<Integer> evenIntList = Stream.iterate(2, n -> n+2)
                .limit(10)
                .collect(Collectors.toList());

        System.out.println(evenIntList);

        // Operation 16 : Collecting and then method
        // useful when performing an operation on a reduced data (like calculate avg salary then round it up)
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 50000),
            new Employee("Bob", 65000),
            new Employee("Charlie", 78000),
            new Employee("Sarah", 45000)
        );

        Long reduce = employees.stream()
                .map(emp -> emp.getSalary())
                .collect(Collectors.collectingAndThen(
                    Collectors.averagingDouble(Double::doubleValue),
                    Math::round
                    ));
        System.out.println(reduce);

        // Operation 17 : Collection.teeing() **
        // this method allow 2 collectors to run parallel and perform operation on their result
        Integer collect = numList.stream()
                .collect(Collectors.teeing(
                    Collectors.maxBy(Integer::compareTo),
                    Collectors.minBy(Integer::compareTo),
                    (res1, res2) -> res1.get() - res2.get()
                ));
                
        System.out.println(collect);

        // Operation 18 : Concat 2 stream and product a new stream
        Stream<Integer> stream1 = Stream.of(1,2,3);
        Stream<Integer> stream2 = Stream.of(4,5,6);

        Stream<Integer> stream3 = Stream.concat(stream1, stream2);

        // convert stream to int then calculate sum
        int sum = stream3.mapToInt(Integer::intValue).sum();

        System.out.println(sum);

        // Operation 19 : Partition a list based on condition
        // example break a list into 2, one for even numbers and another for odd numbers
        System.out.println(numList);
        Map<Boolean, List<Integer>> mapEvenOdd = numList.stream()
                .collect(Collectors.partitioningBy(num -> num % 2 == 0));

        System.out.println(mapEvenOdd);
        System.out.println("Even list : "+mapEvenOdd.get(true));
        System.out.println("Odd list : "+mapEvenOdd.get(false));

        // Operation 20 : IntStream() for creating stream of integer value
        List<Integer> list = IntStream.range(1, 21).boxed().toList();
        System.out.println(list);
    }
}
