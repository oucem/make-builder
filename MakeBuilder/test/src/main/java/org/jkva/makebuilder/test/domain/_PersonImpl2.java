//package domain;
//
//import java.util.Date;
//
///**
// * Created by IntelliJ IDEA.
// * User: Jan-Kees
// * Date: 31-okt-2009
// * Time: 19:52:43
// * To change this template use File | Settings | File Templates.
// */
//public class _PersonImpl2 implements Person
//{
//    private final long id;
//
//    private final String firstName;
//
//    private final String middleName;
//
//    private final String lastName;
//
//    private final Date birthDate;
//
//    private final Address address;
//
//    private final Company employer;
//
//    public _PersonImpl2(long id, String firstName, String middleName, String lastName, Date birthDate, Address address, Company employer)
//    {
//        this.id = id;
//        this.firstName = firstName;
//        this.middleName = middleName;
//        this.lastName = lastName;
//        this.birthDate = birthDate;
//        this.address = address;
//        this.employer = employer;
//    }
//
//    @Override
//    public boolean equals(Object o)
//    {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        _PersonImpl2 person = (_PersonImpl2) o;
//
//        if (id != person.id) return false;
//        if (!address.equals(person.address)) return false;
//        if (!birthDate.equals(person.birthDate)) return false;
//        if (employer != null ? !employer.equals(person.employer) : person.employer != null) return false;
//        if (!firstName.equals(person.firstName)) return false;
//        if (!lastName.equals(person.lastName)) return false;
//        if (middleName != null ? !middleName.equals(person.middleName) : person.middleName != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode()
//    {
//        int result = (int) (id ^ (id >>> 32));
//        result = 31 * result + firstName.hashCode();
//        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
//        result = 31 * result + lastName.hashCode();
//        result = 31 * result + birthDate.hashCode();
//        result = 31 * result + address.hashCode();
//        result = 31 * result + (employer != null ? employer.hashCode() : 0);
//        return result;
//    }
//
//    @Override
//    public String toString()
//    {
//        return "_PersonImpl2{" + "id=" + id + ", firstName='" + firstName + '\'' + ", middleName='" + middleName + '\'' + ", lastName='" + lastName + '\'' + ", birthDate=" + birthDate + ", address=" + address + ", employer=" + employer + '}';
//    }
//
//    public PersonBuilder2 annotations()
//    {
//        return new PersonBuilder2(id, firstName, lastName, birthDate, address).setMiddleName(middleName).setEmployer(employer);
//    }
//
//    public long getId()
//    {
//        return id;
//    }
//
//    public String getFirstName()
//    {
//        return firstName;
//    }
//
//    public String getMiddleName()
//    {
//        return middleName;
//    }
//
//    public String getLastName()
//    {
//        return lastName;
//    }
//
//    public Date getBirthDate()
//    {
//        return birthDate;
//    }
//
//    public Address getAddress()
//    {
//        return address;
//    }
//
//    public Company getEmployer()
//    {
//        return employer;
//    }
//}
