//package domain;
//
///**
// * Created by IntelliJ IDEA.
// * User: Jan-Kees
// * Date: 31-okt-2009
// * Time: 19:56:26
// * To change this template use File | Settings | File Templates.
// */
//public class _AddressImpl2 implements Address
//{
//    private final String street;
//
//    private final int number;
//
//    private final String suffix;
//
//    private final String city;
//
//    private final Country country;
//
//    public _AddressImpl2(String street, int number, String suffix, String city, Country country)
//    {
//        this.street = street;
//        this.number = number;
//        this.suffix = suffix;
//        this.city = city;
//        this.country = country;
//    }
//
//    @Override
//    public boolean equals(Object o)
//    {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        _AddressImpl2 address = (_AddressImpl2) o;
//
//        if (number != address.number) return false;
//        if (!city.equals(address.city)) return false;
//        if (country != address.country) return false;
//        if (!street.equals(address.street)) return false;
//        if (suffix != null ? !suffix.equals(address.suffix) : address.suffix != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode()
//    {
//        int result = street.hashCode();
//        result = 31 * result + number;
//        result = 31 * result + (suffix != null ? suffix.hashCode() : 0);
//        result = 31 * result + city.hashCode();
//        result = 31 * result + country.hashCode();
//        return result;
//    }
//
//    @Override
//    public String toString()
//    {
//        return "_AddressImpl2{" + "street='" + street + '\'' + ", number=" + number + ", suffix='" + suffix + '\'' + ", city='" + city + '\'' + ", country=" + country + '}';
//    }
//
//    public AddressBuilder2 annotations()
//    {
//        return new AddressBuilder2(street, number, city, country).setSuffix(suffix);
//    }
//
//    public String getStreet()
//    {
//        return street;
//    }
//
//    public int getNumber()
//    {
//        return number;
//    }
//
//    public String getSuffix()
//    {
//        return suffix;
//    }
//
//    public String getCity()
//    {
//        return city;
//    }
//
//    public Country getCountry()
//    {
//        return country;
//    }
//}
