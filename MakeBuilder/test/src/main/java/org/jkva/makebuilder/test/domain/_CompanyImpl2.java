//package domain;
//
//import java.util.Set;
//import java.util.TreeSet;
//import java.util.Date;
//
///**
// *
// */
//public class _CompanyImpl2 implements Company
//{
//    private final long id;
//
//    private final String name;
//
//    private final Address address;
//
//    private final Address postalAddress;
//
//    private final Date dateFounded;
//
//    private final Set<Person> employees;
//
//    public _CompanyImpl2(long id, String name, Address address, Address postalAddress, Date dateFounded, Set<Person> employees)
//    {
//        this.id = id;
//        this.name = name;
//        this.address = address;
//        this.postalAddress = postalAddress;
//        this.dateFounded = dateFounded;
//        this.employees = employees != null ? new TreeSet<Person>(employees) : new TreeSet<Person>();
//    }
//
//    @Override
//    public boolean equals(Object o)
//    {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        _CompanyImpl2 company = (_CompanyImpl2) o;
//
//        if (id != company.id) return false;
//        if (!address.equals(company.address)) return false;
//        if (!dateFounded.equals(company.dateFounded)) return false;
//        if (employees != null ? !employees.equals(company.employees) : company.employees != null) return false;
//        if (!name.equals(company.name)) return false;
//        if (postalAddress != null ? !postalAddress.equals(company.postalAddress) : company.postalAddress != null)
//            return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode()
//    {
//        int result = (int) (id ^ (id >>> 32));
//        result = 31 * result + name.hashCode();
//        result = 31 * result + address.hashCode();
//        result = 31 * result + (postalAddress != null ? postalAddress.hashCode() : 0);
//        result = 31 * result + dateFounded.hashCode();
//        result = 31 * result + (employees != null ? employees.hashCode() : 0);
//        return result;
//    }
//
//    @Override
//    public String toString()
//    {
//        return "_CompanyImpl2{" + "id=" + id + ", name='" + name + '\'' + ", address=" + address + ", postalAddress=" + postalAddress + ", dateFounded=" + dateFounded + ", employees=" + employees + '}';
//    }
//
//    public CompanyBuilder2 annotations()
//    {
//        return new CompanyBuilder2(id, name, address, dateFounded).setPostalAddress(postalAddress).setEmployees(employees);
//    }
//
//    public long getId()
//    {
//        return id;
//    }
//
//    public String getName()
//    {
//        return name;
//    }
//
//    public Address getAddress()
//    {
//        return address;
//    }
//
//    public Address getPostalAddress()
//    {
//        return postalAddress;
//    }
//
//    public Date getDateFounded()
//    {
//        return dateFounded;
//    }
//
//    public Set<Person> getEmployees()
//    {
//        return new TreeSet<Person>(employees);
//    }
//}
