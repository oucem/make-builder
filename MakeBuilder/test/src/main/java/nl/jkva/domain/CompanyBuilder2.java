//package domain;
//
//import nl.jkva.builder.ObjectBuilder;
//
//import java.util.Date;
//import java.util.Set;
//
///**
// *
// */
//public final class CompanyBuilder2 implements ObjectBuilder<Company>
//{
//    private long id;
//    private String name;
//    private Address address;
//    private Date dateFounded;
//    private Address postalAddress;
//    private Set<Person> employees;
//
//    public CompanyBuilder2()
//    {
//    }
//
//    public CompanyBuilder2(long id, String name, Address address, Date dateFounded)
//    {
//        this.id = id;
//        this.name = name;
//        this.address = address;
//        this.dateFounded = dateFounded;
//    }
//
//    public CompanyBuilder2 setPostalAddress(Address postalAddress)
//    {
//        this.postalAddress = postalAddress;
//        return this;
//    }
//
//    public CompanyBuilder2 setEmployees(Set<Person> employees)
//    {
//        this.employees = employees;
//        return this;
//    }
//
//    public CompanyBuilder2 setId(long id)
//    {
//        this.id = id;
//        return this;
//    }
//
//    public CompanyBuilder2 setName(String name)
//    {
//        this.name = name;
//        return this;
//    }
//
//    public CompanyBuilder2 setAddress(Address address)
//    {
//        this.address = address;
//        return this;
//    }
//
//    public CompanyBuilder2 setDateFounded(Date dateFounded)
//    {
//        this.dateFounded = dateFounded;
//        return this;
//    }
//
//    public Company build()
//    {
//        return new _CompanyImpl2(id, name, address, postalAddress, dateFounded, employees);
//    }
//}
