import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        // Количество несовершеннолетних
        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        // Cписок фамилий призывников
        List<String> conscripts = persons.stream()
                .filter(person -> person.getAge() > 18 && person.getAge() < 27 && person.getSex() == Sex.MAN)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());

        // Список потенциально работоспособных людей с высшим образованием
        List<Person> workers = persons.stream()
                .filter(person -> person.getAge() > 18 && person.getEducation() == Education.HIGHER)
                .filter(person -> person.getSex() == Sex.MAN && person.getAge() < 65 || person.getSex() == Sex.WOMAN && person.getAge() < 60)
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .collect(Collectors.toList());
    }
}
