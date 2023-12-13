package ru.maliutin.v2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Репозиторий для осуществления запросов к БД.
 *
 * @param <T> тип объекта сущности.
 * @param <K> тип первичного ключа сущности.
 */
public class EntityRepository<T, K> {
    /**
     * Добавление сущности в БД.
     *
     * @param obj объект для добавления.
     */
    public void addEntity(T obj) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(obj.getClass());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.persist(obj);
            session.getTransaction().commit();
        }
    }

    /**
     * Получение сущности из БД.
     *
     * @param clazz тип сущности.
     * @param id    первичный ключ.
     * @return сущность полученная из БД или null в случае ее отсутствия.
     */
    public T getEntityById(Class<T> clazz, K id) {
        T obj = null;
        Configuration configuration = new Configuration()
                .addAnnotatedClass(clazz);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            obj = session.get(clazz, id);
            session.getTransaction().commit();
        }
        return obj;
    }

    /**
     * Обновление сущности в БД.
     *
     * @param obj объект для обновления.
     */
    public void updateEntity(T obj) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(obj.getClass());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.merge(obj);
            session.getTransaction().commit();
        }
    }

    /**
     * Удаление сущности из БД.
     *
     * @param obj объект для удаления.
     */
    public void deleteEntity(T obj) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(obj.getClass());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.remove(obj);
            session.getTransaction().commit();
        }
    }
}
