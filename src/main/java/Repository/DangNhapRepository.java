package Repository;

import Entity.DangNhap;

import helper.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DangNhapRepository {
    public DangNhap login(String tenDangNhap, String matKhau) {
        Transaction tx = null;
        DangNhap user = null;

        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            tx = session.beginTransaction();

            String hql = "select dn from DangNhap dn where dn.tenDangNhap = :username AND dn.matKhau = :password";

            Query<DangNhap> query = session.createQuery(hql, DangNhap.class);
            query.setParameter("username", tenDangNhap);
            query.setParameter("password", matKhau);

            user = query.uniqueResult();

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
