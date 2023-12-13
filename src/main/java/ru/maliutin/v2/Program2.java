package ru.maliutin.v2;

import ru.maliutin.domain.Course;
import ru.maliutin.domain.Person;

/**
 * Попробовал решить с применением "универсального" репозитория.
 * JPARepository на минималках :).
 */
public class Program2 {
    public static void main(String[] args) {

        // region Работа с сущностью Course
        EntityRepository<Course, Long> repoCourse = new EntityRepository<>();
        Course course1 = new Course("Hibernate", 3);

        repoCourse.addEntity(course1);
        System.out.println("Сущность добавлена");

        Course courseDB = repoCourse.getEntityById(Course.class, course1.getId());
        System.out.println("Сущность получена из БД: " + courseDB);

        courseDB.setDuration(6);
        repoCourse.updateEntity(courseDB);
        Course courseAfterUpdate = repoCourse.getEntityById(Course.class, courseDB.getId());
        System.out.println("Сущность после изменения полученная из БД: " + courseAfterUpdate);

        repoCourse.deleteEntity(courseAfterUpdate);
        System.out.println("Сущность удалена из БД");

        // endregion

        // region Работа с сущностью Person
        EntityRepository<Person, Long> repoPerson = new EntityRepository<>();

        Person person = new Person("Вася");

        repoPerson.addEntity(person);
        System.out.println("Сущность добавлена");

        Person personDB = repoPerson.getEntityById(Person.class, person.getId());
        System.out.println("Сущность получена из БД: " + personDB);

        personDB.setName("Иван");
        repoPerson.updateEntity(personDB);
        Person personAfterUpdate = repoPerson.getEntityById(Person.class, personDB.getId());
        System.out.println("Сущность после изменения полученная из БД: " + personAfterUpdate);

        repoPerson.deleteEntity(personAfterUpdate);
        System.out.println("Сущность удалена из БД");
        // endregion

    }
}
