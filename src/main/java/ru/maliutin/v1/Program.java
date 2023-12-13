package ru.maliutin.v1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.maliutin.domain.Course;

/**
 * Создайте базу данных (например, SchoolDB).
 * В этой базе данных создайте таблицу Course с полями id (ключ), title, и duration.
 * Настройте Hibernate для работы с вашей базой данных.
 * Создайте Java-класс Course, соответствующий таблице Course, с необходимыми аннотациями Hibernate.
 * Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Course.
 * Убедитесь, что каждая операция выполняется в отдельной транзакции.
 */
public class Program {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Course.class);

        Course course = new Course("Java Junior", 5);
        Course courseWithDb;

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            // Сохранение данных в БД
            session.persist(course);
            session.getTransaction().commit();
            System.out.println("Добавление произведено");
        } finally {
            sessionFactory.close();
        }

        configuration = new Configuration().addAnnotatedClass(Course.class);
        sessionFactory = configuration.buildSessionFactory();
        try (Session session = sessionFactory.getCurrentSession()) {
            // Получение из БД
            session.beginTransaction();
            courseWithDb = session.get(Course.class, course.getId());
            session.getTransaction().commit();
            System.out.println("Из БД получен объект: " + courseWithDb);
        } finally {
            sessionFactory.close();
        }

        configuration = new Configuration().addAnnotatedClass(Course.class);
        sessionFactory = configuration.buildSessionFactory();
        try (Session session = sessionFactory.getCurrentSession()) {
            // Изменение данных в БД
            courseWithDb.setDuration(6);
            session.beginTransaction();
            session.merge(courseWithDb);
            session.getTransaction().commit();
            System.out.println("Изменение данных в БД произведено");
        } finally {
            sessionFactory.close();
        }

        configuration = new Configuration().addAnnotatedClass(Course.class);
        sessionFactory = configuration.buildSessionFactory();
        try (Session session = sessionFactory.getCurrentSession()) {
            // Удаление данных из БД
            session.beginTransaction();
            session.remove(courseWithDb);
            session.getTransaction().commit();
            System.out.println("Удаление данных из БД произведено");

        } finally {
            sessionFactory.close();
        }
    }
}
