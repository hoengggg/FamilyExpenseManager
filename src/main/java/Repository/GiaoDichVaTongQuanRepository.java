package Repository;

import Entity.GiaoDichVaTongQuan;
import helper.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class GiaoDichVaTongQuanRepository {
    //sau này chắc thay cái repository này thành các code để kết nối vs sql còn lại mấy cái entity, controller và view thì vẫn vậy nhỉ
    public List<GiaoDichVaTongQuan> getAll(){
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            return session
                    .createQuery("select gdvtq from GiaoDichVaTongQuan gdvtq",
                            GiaoDichVaTongQuan.class)
                    .getResultList();
        }
    }

    public GiaoDichVaTongQuan Detail(int id){
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            return session.find(GiaoDichVaTongQuan.class, id);
        }
    }

    public void xoa(int id){
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();

            GiaoDichVaTongQuan entity = session.find(GiaoDichVaTongQuan.class, id);
            if(entity != null){
                session.remove(entity);
            }

            transaction.commit();
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void them(GiaoDichVaTongQuan gdvtq){
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.persist(gdvtq);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void sua(GiaoDichVaTongQuan gdvtq){
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.merge(gdvtq);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
