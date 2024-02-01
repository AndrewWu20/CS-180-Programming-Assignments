public final class Employee extends Person {
    /**
     * The hourly rate of this employee.
     */
    private final int hourlyRate;

    /**
     * Constructs a newly allocated {@code Employee} object with the specified name, age, and hourly rate.
     *
     * @param name the name to be used in construction
     * @param age the age to be used in construction
     * @param hourlyRate the hourly rate to be used in construction
     * @throws NullPointerException if the specified name is {@code null}
     * @throws IllegalArgumentException if the specified age or hourly rate is negative
     */
    public Employee(String name, int age, int hourlyRate) {
        super(name, age);

        if (hourlyRate < 0) {
            throw new IllegalArgumentException("the specified hourly rate is negative");
        } //end if

        this.hourlyRate = hourlyRate;
    } //Employee

    /**
     * Constructs a newly allocated {@code Employee} object that is a copy of the specified employee.
     *
     * @param employee the employee to be used in construction
     * @throws NullPointerException if the specified employee is {@code null}
     */
    public Employee(Employee employee) {
        super(employee);

        this.hourlyRate = employee.hourlyRate;
    } //Employee

    /**
     * Returns the hourly rate of this employee.
     *
     * @return the hourly rate of this employee
     */
    public int getHourlyRate() {
        return this.hourlyRate;
    } //getHourlyRate

    /**
     * Returns the income of this employee for the specified number of hours.
     *
     * @param hours the hours to be used in the operation
     * @return the income of this employee for the specified number of hours
     * @throws IllegalArgumentException if the specified hours are negative
     */
    public int calculateIncome(int hours) {
        if (hours < 0) {
            throw new IllegalArgumentException("the specified hours are negative");
        } //end if

        return this.hourlyRate * hours;
    } //calculateIncome

    /**
     * Determines whether or not the specified object is equal to this employee. {@code true} is returned if and only
     * if the specified object is an instance of {@code Employee} and its name, age, and hourly rate are equal to this
     * employee's. Name comparisons are case-sensitive.
     *
     * @param object the object to be used in the comparisons
     * @return {@code true}, if the specified object is equal to this employee and {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Employee) {
            boolean equal;

            equal = super.equals(object);

            equal &= (this.hourlyRate == ((Employee) object).hourlyRate);

            return equal;
        } //end if

        return false;
    } //equals

    /**
     * Returns the {@code String} representation of this employee.
     *
     * Example:
     * name = "Bernie"
     * age = 79
     * hourlyRate = 15
     * toString() = "Employee{name=Bernie, age=78, hourlyRate=15}"
     *
     * @return the {@code String} representation of this employee
     */
    @Override
    public String toString() {
        String name;
        int age;
        String format = "Employee{name=%s, age=%d, hourlyRate=%d}";

        name = this.getName();

        age = this.getAge();

        return String.format(format, name, age, this.hourlyRate);
    } //toString
}