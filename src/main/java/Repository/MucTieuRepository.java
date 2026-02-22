package Repository;

import Entity.MucTieu;
import helper.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MucTieuRepository {
    public List<MucTieu> getAll(){
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            return session
                    .createQuery("select mt from MucTieu mt", MucTieu.class)
                    .getResultList();
        }
    }

    public MucTieu Detail(int id_Tim){
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            return session.find(MucTieu.class, id_Tim);
        }
    }

    public void xoa(int id){
        Transaction transaction = null;

        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();

            MucTieu entity = session.find(MucTieu.class, id);
            if(entity != null){
                session.delete(entity);
            }

            transaction.commit();
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void them(MucTieu mt){
        Transaction transaction = null;

        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(mt);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void sua(MucTieu mt){
        Transaction transaction = null;

        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.merge(mt);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
