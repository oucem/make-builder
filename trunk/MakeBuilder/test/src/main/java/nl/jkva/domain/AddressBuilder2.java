//package domain;
//
//import nl.jkva.builder.ObjectBuilder;
//
///**
// * Created by IntelliJ IDEA.
// * User: Jan-Kees
// * Date: 31-okt-2009
// * Time: 20:00:19
// * To change this template use File | Settings | File Templates.
// */
//public final class AddressBuilder2 implements ObjectBuilder<Address>
//{
//    private String street;
//
//    private int number;
//
//    private String suffix;
//
//    private String city;
//
//    private Country country;
//
//    public AddressBuilder2()
//    {
//    }
//
//    public AddressBuilder2(String street, int number, String city, Country country)
//    {
//        this.street = street;
//        this.number = number;
//        this.city = city;
//        this.country = country;
//    }
//
//    public Address build()
//    {
//        return new _AddressImpl2(street, number, suffix, city, country);
//    }
//
//    public AddressBuilder2 setStreet(String street)
//    {
//        this.street = street;
//        return this;
//    }
//
//    public AddressBuilder2 setNumber(int number)
//    {
//        this.number = number;
//        return this;
//    }
//
//    public AddressBuilder2 setSuffix(String suffix)
//    {
//        this.suffix = suffix;
//        return this;
//    }
//
//    public AddressBuilder2 setCity(String city)
//    {
//        this.city = city;
//        return this;
//    }
//
//    public AddressBuilder2 setCountry(Country country)
//    {
//        this.country = country;
//        return this;
//    }
//}
