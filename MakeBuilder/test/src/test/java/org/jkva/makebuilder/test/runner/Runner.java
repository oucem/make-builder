package org.jkva.makebuilder.test.runner;

import org.jkva.makebuilder.generated.*;
import org.jkva.makebuilder.test.domain.Address;
import org.jkva.makebuilder.test.domain.Company;
import org.jkva.makebuilder.test.domain.Country;
import org.jkva.makebuilder.test.domain.Person;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 */
public class Runner {
    private static final AtomicReference<Person> P = new AtomicReference<Person>();

    private static final int noOfRunners = 1000;

    private static final CountDownLatch START = new CountDownLatch(1);

    private static final CountDownLatch STOP = new CountDownLatch(noOfRunners);

    public static void main(String[] args) throws InterruptedException {
        SetUpData setUpData = setUpData();

        Person person1 = setUpData.getPerson1();
        Person person2 = setUpData.getPerson2();
        Random random = setUpData.getRandom();
        Address addressOther = setUpData.getAddress();
        AtomicLong counter = setUpData.getCounter();

        ExecutorService threadPool = Executors.newFixedThreadPool(noOfRunners);

        for (int i = 0; i < noOfRunners; i++) {
            threadPool.execute(new MyRunnable(person1, person2, random, addressOther, counter));
        }
        START.countDown();
        STOP.await();

        System.out.println("counter.get() = " + counter.get());

        if (!threadPool.isTerminated()) {
            threadPool.shutdown();
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        }
    }

    private static class MyRunnable implements Runnable {
        private final Person person1;
        private final Person person2;
        private final Random random;
        private final Address address;
        private final AtomicLong counter;

        public MyRunnable(Person person1, Person person2, Random random, Address address, AtomicLong counter) {
            this.person1 = person1;
            this.person2 = person2;
            this.random = random;
            this.address = address;
            this.counter = counter;
        }

        public void run() {
            try {
                START.await();
                runBatch();
            }
            catch (InterruptedException e) {
                Thread.interrupted();
            }
            finally {
                STOP.countDown();
            }
        }

        private void runBatch() {
            for (int i = 0; i < 10000; i++) {
                runner();
            }
        }

        private void runner() {
            Person person = P.get();
            assertion(person);

            addRandomness();

            counter.incrementAndGet();
        }

        private void addRandomness() {
            if (random.nextBoolean()) {
                P.set(person1);
            } else {
                P.set(person2);
            }

            if (random.nextBoolean()) {
                P.set(((PersonBuilderImpl)P.get().builder()).setId(-1L).setAddress(new AddressBuilderImpl().build()).build());
            }

            if (random.nextBoolean()) {
                P.set(((PersonBuilderImpl)P.get().builder()).setId(-1L).setFirstName("" + random.nextInt()).build());
            }

            if (random.nextBoolean()) {
                P.set(((PersonBuilderImpl)P.get().builder()).setId(-1L).setEmployer(new CompanyImpl(1L, "1", address, null, new Date(), null)).build());
            }

            if (random.nextInt(100) < 10) {
                P.set(((PersonBuilderImpl)P.get().builder()).setId(1L).build());
            } else if (random.nextInt(100) < 10) {
                P.set(((PersonBuilderImpl) P.get().builder()).setId(2L).build());
            }
        }

        private void assertion(Person person) {
            if (person.getId() == 1L) {
                assert person.equals(person1);
            } else if (person.getId() == 2L) {
                assert person.equals(person2);
            }
        }
    }

    private static class SetUpData {
        private Address address;
        private Person person1;
        private Person person2;
        private AtomicLong counter;
        private Random random;

        public Address getAddress() {
            return address;
        }

        public Person getPerson1() {
            return person1;
        }

        public Person getPerson2() {
            return person2;
        }

        public AtomicLong getCounter() {
            return counter;
        }

        public Random getRandom() {
            return random;
        }
    }

    private static SetUpData setUpData() {
        SetUpData setUpData = new SetUpData();
        Address addressMe = new AddressBuilderImpl("dummyFromSuperInterface", "Some Street", 2, null, "Some town", Country.NETHERLANDS).build();
        assert addressMe.getStreet().equals("Some Street");

        addressMe = ((AddressBuilderImpl) addressMe.builder()).setNumber(2).setSuffix("B").build();
        assert addressMe.getStreet().equals("Some Street");
        assert addressMe.getNumber() == 2;
        assert addressMe.getSuffix().equals("B");
        assert addressMe.getCity().equals("Some Town");
        assert addressMe.getCountry().equals(Country.NETHERLANDS);

        setUpData.address = new AddressImpl("dummyFromSuperInterface", "Other Street", 12, "A", "Utrecht", Country.NETHERLANDS);

        final Address addressOrdina = new AddressImpl("dummyFromSuperInterface", "Ringwade", 1, null, "Nieuwegein", Country.NETHERLANDS);
        Company ordina = new CompanyBuilderImpl(1L, "Ordina", addressOrdina, addressOrdina, new Date(), null).build();
        assert ordina.getId() == 1L;
        assert ordina.getName().equals("Ordina");

        final Address addressOtherCompany = new AddressImpl("dummyFromSuperInterface", "Street", 1, null, "Utrecht", Country.NETHERLANDS);
        Company otherCompany = ((CompanyBuilderImpl)ordina.builder()).setId(2L).setName("Yada yada").setAddress(addressOtherCompany).setPostalAddress(addressOtherCompany).build();
        assert otherCompany.getId() == 2L;
        assert otherCompany.getName().equals("Yada yada");

        setUpData.person1 = new PersonBuilderImpl().setId(1L).setFirstName("Jan-Kees").setMiddleName("van").setLastName("Andel").setBirthDate(new Date()).setAddress(addressMe).setEmployer(ordina).build();
        assert setUpData.person1.getFirstName().equals("Jan-Kees");

        setUpData.person2 = new PersonBuilderImpl().setId(2L).setFirstName("FirstName").setLastName("LastName").setBirthDate(new Date()).setAddress(setUpData.address).setEmployer(otherCompany).build();
        assert setUpData.person2.getFirstName().equals("FirstName");

        P.set(setUpData.person1);
        setUpData.counter = new AtomicLong(0);

        setUpData.random = new Random();
        return setUpData;
    }
}
