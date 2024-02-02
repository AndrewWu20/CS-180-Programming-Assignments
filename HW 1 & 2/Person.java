import java.util.Objects;

public class Person implements Identifiable {
    /**
     * The name of this person.
     */
    private final String name;

    /**
     * The age of this person.
     */
    private final int age;

    /**
     * Constructs a newly allocated {@code Person} object with the specified name and age.
     *
     * @param name the name to be used in construction
     * @param age the age to be used in construction
     * @throws NullPointerException if the specified name is {@code null}
     * @throws IllegalArgumentException if the specified age is negative
     */
    public Person(String name, int age) {
        Objects.requireNonNull(name, "the specified name is null");

        if (age < 0) {
            throw new IllegalArgumentException("the specified age is negative");
        } //end if

        this.name = name;

        this.age = age;
    } //Person

    /**
     * Constructs a newly allocated {@code Person} object that is a copy of the specified person.
     *
     * @param person the person to be used in construction
     * @throws NullPointerException if the specified person is {@code null}
     */
    public Person(Person person) {
        Objects.requireNonNull(person, "the specified person is null");

        this.name = person.name;

        this.age = person.age;
    } //Person

    /**
     * Returns the name of this person.
     *
     * @return the name of this person
     */
    @Override
    public String getName() {
        return this.name;
    } //getName

    /**
     * Returns the age of this person.
     *
     * @return the age of this person
     */
    public int getAge() {
        return this.age;
    } //getAge

    /**
     * Determines whether or not the specified object is equal to this person. {@code true} is returned if and only if
     * the specified object is an instance of {@code Person} and its name and age are equal to this person's. Name
     * comparisons are case-sensitive.
     *
     * @param object the object to be used in the comparisons
     * @return {@code true}, if the specified object is equal to this person and {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Person) {
            boolean equal;

            equal = Objects.equals(this.name, ((Person) object).name);

            equal &= (this.age == ((Person) object).age); // & is bit-wise and operator

            return equal;
        } //end if

        return false;
    } //equals

    /**
     * Returns the {@code String} representation of this person.
     *
     * Example:
     * name = "Joe"
     * age = 78
     * toString() = "Person{name=Joe, age=78}"
     *
     * @return the {@code String} representation of this person
     */
    @Override
    public String toString() {
        String format = "Person{name=%s, age=%d}";

        return String.format(format, this.name, this.age);
    } //toString
}