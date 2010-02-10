//package bla;
//
//import nl.jkva.domain.Person;
//import nl.jkva.domain.Address;
//import nl.jkva.domain.Country;
//import nl.jkva.domain.Company;
//
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicLong;
//import java.util.concurrent.atomic.AtomicReference;
//import java.util.Random;
//import java.util.Date;
//
///**
// *
// */
//public class Runner {
//    private static final AtomicReference<Person> P = new AtomicReference<Person>();
//
//    private static final int noOfRunners = 1000;
//
//    private static final CountDownLatch START = new CountDownLatch(1);
//
//    private static final CountDownLatch STOP = new CountDownLatch(noOfRunners);
//
//    public static void main(String[] args) throws InterruptedException {
//        SetUpData setUpData = setUpData();
//
//        Person person1 = setUpData.getPerson1();
//        Person person2 = setUpData.getPerson2();
//        Random random = setUpData.getRandom();
//        Address addressJoris = setUpData.getAddressJoris();
//        AtomicLong counter = setUpData.getCounter();
//
//        ExecutorService threadPool = Executors.newFixedThreadPool(noOfRunners);
//
//        for (int i = 0; i < noOfRunners; i++) {
//            threadPool.execute(new MyRunnable(person1, person2, random, addressJoris, counter));
//        }
//        START.countDown();
//        STOP.await();
//
//        System.out.println("counter.get() = " + counter.get());
//
//        if (!threadPool.isTerminated()) {
//            threadPool.shutdown();
//            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
//        }
//    }
//
//    private static class MyRunnable implements Runnable {
//        private final Person person1;
//        private final Person person2;
//        private final Random random;
//        private final Address addressJoris;
//        private final AtomicLong counter;
//
//        public MyRunnable(Person person1, Person person2, Random random, Address addressJoris, AtomicLong counter) {
//            this.person1 = person1;
//            this.person2 = person2;
//            this.random = random;
//            this.addressJoris = addressJoris;
//            this.counter = counter;
//        }
//
//        public void run() {
//            try {
//                START.await();
//                runBatch();
//            }
//            catch (InterruptedException e) {
//                Thread.interrupted();
//            }
//            finally {
//                STOP.countDown();
//            }
//        }
//
//        private void runBatch() {
//            for (int i = 0; i < 10000; i++) {
//                runner();
//            }
//        }
//
//        private void runner() {
//            Person person = P.get();
//            assertion(person);
//
//            addRandomness();
//
//            counter.incrementAndGet();
//        }
//
//        private void addRandomness() {
//            if (random.nextBoolean()) {
//                P.set(person1);
//            } else {
//                P.set(person2);
//            }
//
//            if (random.nextBoolean()) {
//                P.set((((nl.jkva.builder.generated.PersonBuilderImpl)P.get().builder()).setId(-1L).setAddress(new nl.jkva.builder.generated.AddressBuilderImpl().build()).build()));
//            }
//
//            if (random.nextBoolean()) {
//                P.set(((nl.jkva.builder.generated.PersonBuilderImpl)P.get().builder()).setId(-1L).setFirstName("" + random.nextInt()).build());
//            }
//
//            if (random.nextBoolean()) {
//                P.set(((nl.jkva.builder.generated.PersonBuilderImpl)P.get().builder()).setId(-1L).setEmployer(new nl.jkva.builder.generated.CompanyImpl(1L, "1", addressJoris, null, new Date(), null)).build());
//            }
//
//            if (random.nextInt(100) < 10) {
//                P.set(((nl.jkva.builder.generated.PersonBuilderImpl)P.get().builder()).setId(1L).setFirstName(person1.getFirstName()).setMiddleName(person1.getMiddleName()).setLastName(person1.getLastName()).setBirthDate(person1.getBirthDate()).setAddress(person1.getAddress()).setEmployer(person1.getEmployer()).build());
//            } else if (random.nextInt(100) < 10) {
//                P.set(((nl.jkva.builder.generated.PersonBuilderImpl) P.get().builder()).setId(2L).setFirstName(person2.getFirstName()).setMiddleName(person2.getMiddleName()).setLastName(person2.getLastName()).setBirthDate(person2.getBirthDate()).setAddress(person2.getAddress()).setEmployer(person2.getEmployer()).build());
//            }
//        }
//
//        private void assertion(Person person) {
//            if (person.getId() == 1L) {
//                assert person.equals(person1);
//            } else if (person.getId() == 2L) {
//                assert person.equals(person2);
//            }
//        }
//    }
//
//    private static class SetUpData {
//        private Address addressJoris;
//        private Person person1;
//        private Person person2;
//        private AtomicLong counter;
//        private Random random;
//
//        public Address getAddressJoris() {
//            return addressJoris;
//        }
//
//        public Person getPerson1() {
//            return person1;
//        }
//
//        public Person getPerson2() {
//            return person2;
//        }
//
//        public AtomicLong getCounter() {
//            return counter;
//        }
//
//        public Random getRandom() {
//            return random;
//        }
//    }
//
//    private static SetUpData setUpData() {
//        SetUpData setUpData = new SetUpData();
//        Address addressJanKees = new nl.jkva.builder.generated.AddressBuilderImpl("dummyFromSuperInterface", "A van der Kolkstraat", 2, null, "Eethen", Country.NETHERLANDS).build();
//        assert addressJanKees.getStreet().equals("A van der Kolkstraat");
//
//        addressJanKees = ((nl.jkva.builder.generated.AddressBuilderImpl) addressJanKees.builder()).setStreet("Raadhuisstraat").setNumber(12).setSuffix("B").build();
//        assert addressJanKees.getStreet().equals("Raadhuisstraat");
//        assert addressJanKees.getNumber() == 12;
//        assert addressJanKees.getSuffix().equals("B");
//        assert addressJanKees.getCity().equals("Eethen");
//        assert addressJanKees.getCountry().equals(Country.NETHERLANDS);
//
//        setUpData.addressJoris = new nl.jkva.builder.generated.AddressImpl("dummyFromSuperInterface", "Kerkstraat", 12, "A", "Gemert", Country.NETHERLANDS);
//
//        final Address addressOrdina = new nl.jkva.builder.generated.AddressImpl("dummyFromSuperInterface", "Ringwade", 1, null, "Nieuwegein", Country.NETHERLANDS);
//        Company ordina = new nl.jkva.builder.generated.CompanyBuilderImpl(1L, "Ordina", addressOrdina, addressOrdina, new Date(), null).build();
//        assert ordina.getId() == 1L;
//        assert ordina.getName().equals("Ordina");
//
//        final Address addressSNS = new nl.jkva.builder.generated.AddressImpl("dummyFromSuperInterface", "Croeselaan", 1, null, "Utrecht", Country.NETHERLANDS);
//        Company sns = ((nl.jkva.builder.generated.CompanyBuilderImpl)ordina.builder()).setId(2L).setName("SNS Reaal").setAddress(addressSNS).setPostalAddress(addressSNS).build();
//        assert sns.getId() == 2L;
//        assert sns.getName().equals("SNS Reaal");
//
//        setUpData.person1 = new nl.jkva.builder.generated.PersonBuilderImpl().setId(1L).setFirstName("Jan-Kees").setMiddleName("van").setLastName("Andel").setBirthDate(new Date()).setAddress(addressJanKees).setEmployer(ordina).build();
//        assert setUpData.person1.getFirstName().equals("Jan-Kees");
//
//        setUpData.person2 = new nl.jkva.builder.generated.PersonBuilderImpl().setId(2L).setFirstName("Joris").setLastName("Niessen").setBirthDate(new Date()).setAddress(setUpData.addressJoris).setEmployer(sns).build();
//        assert setUpData.person2.getFirstName().equals("Joris");
//
//        P.set(setUpData.person1);
//        setUpData.counter = new AtomicLong(0);
//
//        setUpData.random = new Random();
//        return setUpData;
//    }
//}
