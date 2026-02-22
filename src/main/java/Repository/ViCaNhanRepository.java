package Repository;

import Entity.DangNhap;
import Entity.MucTieu;
import Entity.ViCaNhan;
import helper.HibernateConfig;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class ViCaNhanRepository {
    public List<ViCaNhan> getAll(DangNhap user){
        try (Session session = HibernateConfig.getFACTORY().openSession()) {

            Query query = session.createQuery(
                    "select vcn from ViCaNhan vcn where vcn.createById = :user",
                    ViCaNhan.class
            );

            query.setParameter("user", user);

            return query.getResultList();
        }
    }

    public ViCaNhan Detail(Long id, DangNhap user){
        try (Session session = HibernateConfig.getFACTORY().openSession()) {

            Query query = session.createQuery(
                    "select vcn from ViCaNhan vcn where vcn.id = :id and vcn.createById = :user",
                    ViCaNhan.class
            );

            query.setParameter("id", id);
            query.setParameter("user", user);

            List<ViCaNhan> list = query.getResultList();
            return list.isEmpty() ? null : list.get(0);
        }
    }

    public void xoa(Long id, DangNhap user){
        Transaction transaction = null;

        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();

            Query query = session.createQuery(
                    "select vcn from ViCaNhan vcn where vcn.id = :id and vcn.createById = :user",
                    ViCaNhan.class
            );

            query.setParameter("id", id);
            query.setParameter("user", user);

            List<ViCaNhan> list = query.getResultList();

            if(!list.isEmpty()){
                session.delete(list.get(0));
            }

            transaction.commit();
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void them(ViCaNhan vcn){
        Transaction transaction = null;

        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(vcn);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void sua(ViCaNhan vcn){
        Transaction transaction = null;

        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.merge(vcn);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
