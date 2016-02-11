Immutability in Java is hot. Books like Joshua Bloch's "Effective Java" and Brian Goetz' "Java Concurrency in Practice" also highly recommend the usage of immutable objects, claiming that "final" is the new "private".

But, few realize that immutable objects become quite cumbersome to use when the object model starts to grow and the constructors start growing along.

# Usage #
MakeBuilder makes it easy to use immutables in Java. Just write an interface for your immutable type, like this:
```
@Immutable
public interface Person extends ImmutableObject<Person> {
  String getFirstName();
  String getMiddleName();
  String getLastName();
}
```
When compiling, MakeBuilder will generate two classes, an immutable implementation and a builder, like this:
```
public class PersonImpl implements Person {
  final String firstName;
  final String middleName;
  final String lastName;

  // A full constructor

  // Getters

  public PersonBuilder builder() {
    // Return a new builder, populated with all fields of this person
  }
}
```
```
public class PersonBuilder implements ObjectBuilder<Person> {

  // Non-final fields for all properties

  // An empty constructor and a full constructor

  // Getters and setters

  public Person build() {
    // Return a new Person instance, with all fields populated
  }
}
```
So, in your client code, you can simply invoke:
```
new PersonBuilder().setFirstName("Jan-Kees").setMiddleName("van").setLastName("Andel").build();
```
to create an object without having to call that bigass constructor.

The same way, you can grab the builder for any existing object, like this:
```
Person newPerson = ((PersonBuilder)somePerson.builder()).setFirstName("JK").build();
```
to create a new object with only a different firstName.

Finally, cloning objects is even easier:
```
Person newPerson = somePerson.builder().build();
```
The project uses standard Java mechanisms to do its job, the only requirement is a Java 6 or higher compiler and MakeBuilder on the compiler classpath. Because of this, it works on both the Sun and IBM compiler (tested).

The project is fully functional, but I'm still working on the API part. When I'm satisfied, I will start working towards a release.

# TODO #
  * API polishing
  * Extra configuration parameters, like controlling the target package for the Impl and Builder classes
  * Documentation of course ;-)
  * Maybe Maven and Ant plugins/tasks, to make it easier to use and configure in these build systems
  * Support for annotating (abstract) classes
  * Support for @Required and @Optional members
  * Support for JCiP annotations
  * Probably more...

So, stay in touch. You can expect an update soon!