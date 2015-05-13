/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chuchkhe.domain;

import chuchkhe.entity.Address;
import chuchkhe.entity.Customer;
import chuchkhe.entity.CustomerOrder;
import chuchkhe.entity.Employee;
import chuchkhe.entity.Item;
import chuchkhe.entity.Person;
import chuchkhe.entity.Position;
import chuchkhe.entity.Status;
import chuchkhe.entity.StatusTree;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author alex
 */
public class TestDataIns {

    List<Item> fullItemList = new ArrayList<Item>();

    public TestDataIns() {
    }

    public void createCustomer() {


        EntityManager em = DAO.getEntityManager();
        try {
            em.getTransaction().begin();

            Customer customer = new Customer();
            customer.setPerson(makePerson("Иванова", "Наталья", "Ивановна", "11A240", new Date("05/24/1925")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Ленина", "34", "4", "8-495-123-45-56"));
            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }


            customer = new Customer();
            customer.setPerson(makePerson("Петрова", "Василиса", "Ивановна", "11A547", new Date("09/12/1931")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Салова", "3", "12", "8-495-111-33-56"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }



            customer = new Customer();
            customer.setPerson(makePerson("Сидоров", "Виктор", "Васильевич", "11A111", new Date("11/12/1907")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Борщевая", "45", "44", "8-495-666-77-71"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Лахова", "Нина", "Валерьевна", "11A121", new Date("04/12/1924")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Борщевая", "43", "12", "8-495-545-76-21"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Скрынник", "Федор", "Васильевич", "11A222", new Date("10/12/1918")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Сухова", "23", "33", "8-495-112-56-89"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Саенко", "Анна", "Борисовна", "11A676", new Date("01/11/1937")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Пудовая", "3", "9", "8-495-126-13-44"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Кашкин", "Григорий", "Иванович", "11A589", new Date("11/12/1936")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Борщевая", "15", "34", "8-495-791-28-90"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Власова", "Юлия", "Викторовна", "11A870", new Date("05/09/1927")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Борщевая", "45", "44", "8-495-644-89-02"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Коновалова", "Виктория", "Васильевна", "11A470", new Date("11/04/1922")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Пудовая", "14", "4", "8-495-101-50-63"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Кухлер", "Виктор", "Яковлевич", "11A333", new Date("04/03/1938")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Ленина", "31", "12", "8-495-825-078-14"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Курманбекова", "Асия", "Иагомедовна", "11A145", new Date("11/06/1933")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Ленина", "5", "44", "8-495-332-48-91"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Глухов", "Борис", "Денисович", "11A177", new Date("01/08/1921")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Салова", "34", "18", "8-495-696-58-11"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }


            customer = new Customer();
            customer.setPerson(makePerson("Шухова", "Юлия", "Борисовна", "11A897", new Date("01/01/1921")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Салова", "13", "18", "8-495-645-78-10"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }


            customer = new Customer();
            customer.setPerson(makePerson("Маркова", "Виктория", "Денисовна", "11A123", new Date("02/08/1918")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Борщевая", "3", "18", "8-495-221-38-12"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Марков", "Александр", "Сергеевич", "11A245", new Date("06/12/1938")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Пудовая", "7", "11", "8-495-781-19-40"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Лазутин", "Денис", "Денисович", "11A854", new Date("22/11/1943")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Салова", "7", "12", "8-495-112-17-43"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }


            customer = new Customer();
            customer.setPerson(makePerson("Власова", "Виктория", "Сергеевна", "11A181", new Date("04/09/1931")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Ленина", "5", "28", "8-495-564-12-89"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Мартиросян", "Вазген", "Сергеевич", "11A155", new Date("03/05/1911")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Борщевая", "13", "18", "8-495-228-08-12"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Кикабидзе", "Нина", "Вахтанговна", "11A157", new Date("04/06/1926")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Пудовая", "7", "90", "8-495-171-78-55"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }


            customer = new Customer();
            customer.setPerson(makePerson("Киров", "Влентин", "Николаевич", "11A457", new Date("14/08/1924")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Пудовая", "7", "90", "8-495-171-78-55"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Гуров", "Станислав", "Игоревич", "11A198", new Date("08/08/1935")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Пудовая", "4", "32", "8-495-172-88-58"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Зильбельман", "Яков", "Исаакович", "11A666", new Date("05/05/1921")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Ленина", "16", "26", "8-495-341-98-95"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Фройзман", "Иссаак", "Евгеньевич", "11A432", new Date("07/09/1932")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Пудовая", "17", "40", "8-495-177-38-16"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Погосян", "Кристина", "Суреновна", "11A112", new Date("14/02/1921")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Пудовая", "18", "38", "8-495-177-34-23"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Кухлер", "Сергей", "Фридрихович", "11A212", new Date("24/07/1931")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Салова", "21", "38", "8-495-156-14-73"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Базлер", "Людвиг", "Аристархович", "11A323", new Date("31/12/1941")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Ленина", "1", "9", "8-495-165-34-61"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Торусян", "Славик", "Юрьевич", "11A113", new Date("26/09/1919")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Пудовая", "11", "2", "8-495-198-25-46"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Магомедов", "Магомед", "Магомедович", "11A347", new Date("19/07/1931")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Пудовая", "26", "7", "8-495-121-54-23"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }


            customer = new Customer();
            customer.setPerson(makePerson("Торосян", "Алла", "Суреновна", "11A365", new Date("01/12/1918")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Пудовая", "17", "4", "8-495-367-76-28"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }


            customer = new Customer();
            customer.setPerson(makePerson("Волкова", "Алена", "Николаевна", "11A178", new Date("21/06/1922")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Ленина", "29", "56", "8-495-254-30-13"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }


            customer = new Customer();
            customer.setPerson(makePerson("Лахова", "Снежанна", "Денисовна", "11A489", new Date("29/09/1941")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Ленина", "16", "23", "8-495-676-32-17"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Самохина", "Нина", "Денисовна", "11A678", new Date("30/11/1931")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Ленина", "25", "29", "8-495-432-12-89"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }

            customer = new Customer();
            customer.setPerson(makePerson("Сурова", "Виктория", "Павловна", "11A554", new Date("31/07/1942")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Пудовая", "6", "23", "8-495-431-51-77"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }
            customer = new Customer();
            customer.setPerson(makePerson("Вересова", "Виктория", "Александровна", "11A589", new Date("26/05/1931")));
            customer.setAddress(makeAddress("Москва", "Бутово", "Ленина", "44", "2", "8-495-474-22-19"));

            if (DAO.findCustomer(customer) == null) {
                em.persist(customer);
                em.flush();
            }


            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    public void createEmployee() {


        EntityManager em = DAO.getEntityManager();
        try {
            em.getTransaction().begin();

            Position pos = makePos();
            Employee employee = new Employee();
            employee.setPerson(makePerson("Ухов", "Алексадр", "Аристархович", "22A100", new Date("05/24/1982")));
            employee.setUsername("AlexU");
            employee.setPos(pos);
            if (DAO.getEmployeeByUsername(employee.getUsername()) == null) {
                em.persist(employee);
            }


            employee = new Employee();
            employee.setPerson(makePerson("Зуева", "Василиса", "Сергеевна", "22A600", new Date("06/26/1981")));
            employee.setUsername("VasilZ");
            employee.setPos(pos);
            if (DAO.getEmployeeByUsername(employee.getUsername()) == null) {
                em.persist(employee);
            }


            employee = new Employee();
            employee.setPerson(makePerson("Самохвалов", "Антон", "Борисович", "22A111", new Date("08/16/1979")));
            employee.setUsername("AntSa");
            employee.setPos(pos);
            if (DAO.getEmployeeByUsername(employee.getUsername()) == null) {
                em.persist(employee);
            }
            employee = new Employee();
            employee.setPerson(makePerson("Чернышева", "Валерия", "Михайловна", "22A900", new Date("12/11/1976")));
            employee.setUsername("ValCh");
            employee.setPos(pos);
            if (DAO.getEmployeeByUsername(employee.getUsername()) == null) {
                em.persist(employee);
            }
            employee = new Employee();
            employee.setPerson(makePerson("Семшова", "Анна", "Сергеевна", "22A341", new Date("05/24/1983")));
            employee.setUsername("AnnSe");
            employee.setPos(pos);
            if (DAO.getEmployeeByUsername(employee.getUsername()) == null) {
                em.persist(employee);
            }


            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    public void fillStatus() {
        EntityManager em = DAO.getEntityManager();
        Status status = null;

        try {
            em.getTransaction().begin();
            status = new Status(Status.ST_Draft, "Draft", "customerorder");
            if (em.find(Status.class, Status.ST_Draft) == null) {
                em.persist(status);
            }

            status = new Status(Status.ST_ReadyToAssign, "ReadyToAssign", "customerorder");
            if (em.find(Status.class, Status.ST_ReadyToAssign) == null) {
                em.persist(status);
            }

            status = new Status(Status.ST_Assigned, "Assigned", "customerorder");
            if (em.find(Status.class, Status.ST_Assigned) == null) {
                em.persist(status);
            }

            status = new Status(Status.ST_Active, "Active", "customerorder");
            if (em.find(Status.class, Status.ST_Active) == null) {
                em.persist(status);
            }

            status = new Status(Status.ST_Closed, "Closed", "customerorder");
            if (em.find(Status.class, Status.ST_Closed) == null) {
                em.persist(status);
            }

            status = new Status(Status.ST_Offline, "Offline", "employee");
            if (em.find(Status.class, Status.ST_Offline) == null) {
                em.persist(status);
            }

            status = new Status(Status.ST_Online, "Online", "employee");
            if (em.find(Status.class, Status.ST_Online) == null) {
                em.persist(status);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            LOG.throwing(this.getClass().getName(), "fillStatus", ex);
        } finally {
            em.close();
        }

    }
    private static final Logger LOG = Logger.getLogger(TestDataIns.class.getName());

    public void fillSTree() {
        EntityManager em = DAO.getEntityManager();

        try {
            em.getTransaction().begin();
//            makeStatusTree(em, null,Status.ST_Draft);
//            makeStatusTree(em, null,Status.ST_ReadyToAssign);
            makeStatusTree(em, Status.ST_Draft, Status.ST_ReadyToAssign);
            makeStatusTree(em, Status.ST_ReadyToAssign, Status.ST_Assigned);
            makeStatusTree(em, Status.ST_Assigned, Status.ST_Active);
            makeStatusTree(em, Status.ST_Active, Status.ST_Closed);
            makeStatusTree(em, Status.ST_ReadyToAssign, Status.ST_Draft);
            makeStatusTree(em, Status.ST_Offline, Status.ST_Online);
            makeStatusTree(em, Status.ST_Online, Status.ST_Offline);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    public Person makePerson(String lname, String fname, String mname, String SocialID, Date birthdate) {
        Person person = new Person();
        person.setFname(fname);
        person.setLname(lname);
        person.setMname(mname);
        person.setSocialID(SocialID);
        person.setBirthdate(birthdate);

        return person;
    }

    public Address makeAddress(String city, String district, String street, String House_no, String apt_no, String phone) {
        Address address = new Address();
        address.setCity(city);
        address.setDistrict(district);
        address.setStreet(street);
        address.setHouse_no(House_no);
        address.setApartment_no(apt_no);
        address.setPhone(phone);
        return address;
    }

    public Position makePos() {

        Position pos = new Position();
        pos.setPos_name("Разносчик");
        EntityManager em = DAO.getEntityManager();
        try {
            Position posF = null;
            try {
                posF = em.createQuery("select p from Position p WHERE p.pos_name = :name", Position.class).setParameter("name", pos.getPos_name()).getSingleResult();
            } catch (Exception ex) {
            }

            if (posF == null) {
                em.getTransaction().begin();
                em.persist(pos);
                em.getTransaction().commit();
            } else {
                pos = posF;
            }
            return pos;
        } finally {
            em.close();
        }
    }

    private void makeStatusTree(EntityManager em, Long prevId, Long nextId) {
        if (DAO.findMove(prevId, nextId) == null) {
            Status prevSt = em.find(Status.class, prevId);
            Status nextSt = em.find(Status.class, nextId);
            StatusTree stt = new StatusTree(prevSt, nextSt);
            em.persist(stt);
        }
    }

    private Employee getAvailableEmployee(EntityManager em) {
        try {
            TypedQuery<Employee> q = em.createQuery("SELECT emp FROM Employee emp ORDER BY SIZE(emp.customerlist) ASC", Employee.class);
            q.setMaxResults(10);
            List<Employee> res = q.getResultList();
            return res.get(0);
        } catch (NoResultException ex) {
            return null;
        }
    }

    public void dispatchEmployee() {

        EntityManager em = DAO.getEntityManager();
        try {
            TypedQuery<Customer> q = em.createQuery("SELECT cust FROM Customer cust WHERE cust.employee IS NULL", Customer.class);
            List<Customer> res = q.getResultList();
            for (Customer c : res) {
                em.getTransaction().begin();
                c.setEmployee(getAvailableEmployee(em));

                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }

    }

    public void initItem() {

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());


        Item it = new Item();
        it.setItem_name("Сосиски");
        it.setManufacturer("Ку");
        it.setPrice(1 + rand.nextInt(100));
        it.setQuantity(Math.ceil(rand.nextDouble() * 10));
        it.setUom("уп");
        it.setShop_name("Ашан");
        fullItemList.add(it);

        it = new Item();

        it.setItem_name("Картошка");
        it.setManufacturer("");
        it.setPrice(1 + rand.nextInt(40));
        it.setQuantity(Math.ceil(rand.nextDouble() * 10));
        it.setUom("кг");
        it.setShop_name("Ашан");
        fullItemList.add(it);

        it = new Item();
        it.setItem_name("Хлеб");
        it.setManufacturer("Ашан");
        it.setPrice(1 + rand.nextInt(20));
        it.setQuantity(Math.ceil(rand.nextDouble() * 10));
        it.setUom("шт");
        it.setShop_name("Ашан");
        fullItemList.add(it);

        it = new Item();
        it.setItem_name("Молоко");
        it.setManufacturer("Домик в деревне");
        it.setPrice(1 + rand.nextInt(60));
        it.setQuantity(Math.ceil(rand.nextDouble() * 10));
        it.setUom("л");
        it.setShop_name("Ашан");
        fullItemList.add(it);

        it = new Item();
        it.setItem_name("Потхан");
        it.setManufacturer("IDF");
        it.setPrice(1 + rand.nextInt(170));
        it.setQuantity(Math.ceil(rand.nextDouble() * 10));
        it.setUom("уп");
        it.setShop_name("Ашан");
        fullItemList.add(it);

        it = new Item();
        it.setItem_name("Рис");
        it.setManufacturer("Мистраль");
        it.setPrice(1 + rand.nextInt(80));
        it.setQuantity(Math.ceil(rand.nextDouble() * 10));
        it.setUom("кг");
        it.setShop_name("Ашан");
        fullItemList.add(it);

        it = new Item();
        it.setItem_name("Огурцы");
        it.setManufacturer("");
        it.setPrice(1 + rand.nextInt(40));
        it.setQuantity(Math.ceil(rand.nextDouble() * 10));
        it.setUom("кг");
        it.setShop_name("Ашан");
        fullItemList.add(it);

        it = new Item();
        it.setItem_name("Сало");
        it.setManufacturer("");
        it.setPrice(1 + rand.nextInt(150));
        it.setQuantity(Math.ceil(rand.nextDouble() * 10));
        it.setUom("кг");
        it.setShop_name("Ашан");
        fullItemList.add(it);


    }

    private Item cloneItem(Item item) {
        Item it = new Item();
        it.setItemName(item.getItemName());
        it.setManufacturer(item.getManufacturer());
        it.setPrice(item.getPrice());
        it.setQuantity(item.getQuantity());
        it.setUom(item.getUom());
        it.setShop_name(item.getShop_name());
        return it;
    }

    public void createOrder() {
        initItem();

        EntityManager em = DAO.getEntityManager();
        List<Item> curItems = new ArrayList<Item>();

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        try {
            TypedQuery<Customer> q = em.createQuery("SELECT cust FROM Customer cust ", Customer.class);
            List<Customer> res = q.getResultList();

            for (int j = 0; j < 150; j++) {
                em.getTransaction().begin();
                CustomerOrder co = new CustomerOrder();
                co.setCustomer(res.get(rand.nextInt(res.size())));
                int top = Math.max(rand.nextInt(7), 1);
                curItems.clear();
                for (int i = 0; i < top; i++) {
                    int k = rand.nextInt(fullItemList.size());
                    Item it = cloneItem(fullItemList.get(k));
                    it.setOrder(co);
                    curItems.add(it);
                }
                co.setItems(curItems);
                em.persist(co);
                em.getTransaction().commit();
                DAO.changeOrderStat(co, Status.ST_ReadyToAssign);
            }
            TypedQuery<CustomerOrder> q1 = em.createQuery("SELECT custord FROM CustomerOrder custord ", CustomerOrder.class);
            List<CustomerOrder> res1 = q1.getResultList();
            for (int i = 0; i < 40; i++) {
                CustomerOrder co = res1.get(rand.nextInt(res1.size()));
                DAO.changeOrderStat(co, Status.ST_Assigned);
                DAO.changeOrderStat(co, Status.ST_Active);
                DAO.changeOrderStat(co, Status.ST_Closed);

            }

            
            
        } catch (NoResultException ex) {
            return;
        }

    }
    
    
    public List showHistory(){
        EntityManager em = DAO.getEntityManager();
        Query q = em.createNativeQuery("select DISTINCT p.lname,p.fname,p.mname from statushistory sth,customerorder co,customer cust,person p where status_id = 5 AND sth.objectid =co.id and sth.object_name = 'customerorder' and co.customer_id = cust.id and cust.person_id = p.id");
        return q.getResultList();
    }
    
     public List showUsers(){
        EntityManager em = DAO.getEntityManager();
        Query q = em.createNativeQuery("select username from employee ");
        return q.getResultList();
    }
    
}
