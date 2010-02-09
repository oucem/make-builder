//package domain;
//
//import nl.jkva.builder.ObjectBuilder;
//
//import java.util.Date;
//
///**
// *
// */
//public final class PersonBuilder2 implements ObjectBuilder<Person>
//{
//    private long id;
//    private String firstName;
//    private String middleName;
//    private String lastName;
//    private Date birthDate;
//    private Address address;
//    private Company employer;
//
//    public PersonBuilder2()
//    {
//    }
//
//    public PersonBuilder2(long id, String firstName, String lastName, Date birthDate, Address address)
//    {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.birthDate = birthDate;
//        this.address = address;
//    }
//
//    public PersonBuilder2 setMiddleName(String middleName)
//    {
//        this.middleName = middleName;
//        return this;
//    }
//
//    public PersonBuilder2 setEmployer(Company employer)
//    {
//        this.employer = employer;
//        return this;
//    }
//
//    public PersonBuilder2 setId(long id)
//    {
//        this.id = id;
//        return this;
//    }
//
//    public PersonBuilder2 setFirstName(String firstName)
//    {
//        this.firstName = firstName;
//        return this;
//    }
//
//    public PersonBuilder2 setLastName(String lastName)
//    {
//        this.lastName = lastName;
//        return this;
//    }
//
//    public PersonBuilder2 setBirthDate(Date birthDate)
//    {
//        this.birthDate = birthDate;
//        return this;
//    }
//
//    public PersonBuilder2 setAddress(Address address)
//    {
//        this.address = address;
//        return this;
//    }
//
//    public Person build()
//    {
//        return new _PersonImpl2(id, firstName, middleName, lastName, birthDate, address, employer);
//    }
//}
