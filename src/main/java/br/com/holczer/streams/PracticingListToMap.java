package br.com.holczer.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PracticingListToMap {

	private String bigString = """
		The JDK 8 release has changed the way we write Java. With new functional 
		programming idioms and a powerful Stream API, most of the new Java code is written in a 
		functional style. This also means that Stream and Functional programming-related questions 
		are increasing on Java interviews. If you are not familiar with Java 8 changes, then it's 
		difficult to crack a Java interview nowadays. Though it's not stated anywhere, most of the 
		companies, particularly Investment banks like Barclays, Citi, and Goldman Sachs, now expect 
		Java developers to know at least Java 8, which is also good, right? Java 19 is already out, 
		and we are looking forward to Java 21 in a couple of months, it makes sense to know at least 
		Java 8 changes""";
	private final List<Book> books = new ArrayList<>() {{
		add(new Book("Being and Time", "Heidegger", 560, Type.PHILOSOPHY));
		add(new Book("The Trial", "Franz Kafka", 240, Type.NOVEL));
		add(new Book("Death on The Nile", "Agatha Christie", 370, Type.THRILLER));
		add(new Book("Ancient Greece", "Robert F.", 435, Type.HISTORY));
		add(new Book("Ancient Rome", "Robert F.", 860, Type.HISTORY));
		add(new Book("Death of Virgil", "Hermann Broch", 590, Type.NOVEL));
		add(new Book("The Stranger", "Albert Camus", 560, Type.NOVEL));
	}};

	private List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9);

	public static void main(String[] args) {
		PracticingListToMap execute = new PracticingListToMap();
		execute.showBooksByType();
		execute.countBooksByType();
		execute.countingChars();
		execute.filterBooksByTitleWithTwoWords();
		execute.mapReduceListOfIntegers();
		execute.mapReduceBooks();
		execute.getTheBiggerBook();
		execute.totalPagesOfBooks();
		execute.totalPagesWithOptionalInt();
	}

	private void countingChars() {
		bigString.codePoints().mapToObj(c -> c)
				.filter(Character::isAlphabetic)
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
				.entrySet().stream()
				.forEach(c -> System.out.println(" Character [" + ((char)c.getKey().intValue()) + "] Total = " + c.getValue()));
	}
	private void showBooksByType() {
		Map<Type, List<Book>> map = books.stream().collect(Collectors.groupingBy(Book::getType));
		map.entrySet().stream().forEach(e -> System.out.println(e));
	}

	private void countBooksByType() {
		Map<Type, Long> countingBooksByType = books.stream().collect(Collectors.groupingBy(b -> b.getType(), Collectors.counting()));
		countingBooksByType.entrySet().stream().forEach(b -> System.out.println("books by type = " + b.getKey() + " " + b.getValue()));
	}

	private void filterBooksByTitleWithTwoWords() {
		books.stream().filter(b -> b.getTitle().split(" ").length == 2).forEach(book -> System.out.println("" +
				"book " + book.getTitle()));
	}

	private void mapReduceListOfIntegers() {
		numbers.stream().reduce(Integer::sum).stream().forEach(System.out::println);
	}

	private void mapReduceBooks() {
		/**
		 * The reduce allow only a BiFunction so only title and author can be used in this case
		 */
		String reduce = books.stream()
				.reduce("",(title, author) -> title + author, (a, b) -> a + b);
		System.out.println("reduce = " + reduce);
	}

	private void getTheBiggerBook() {
		System.out.print("\nWhat is the biggest book?: ");
		books.stream().map(Book::getPages).reduce(Integer::max).ifPresent(System.out::println);
	}

	private void totalPagesOfBooks() {
		System.out.println("Total of pages of all books: " + books.stream().mapToInt(Book::getPages).sum());
	}

	private void totalPagesWithOptionalInt() {
		OptionalInt optionalInt = books.stream().mapToInt(Book::getPages).max();
		System.out.println("The biggest number of pages: " + optionalInt.orElse(0));
	}

}
