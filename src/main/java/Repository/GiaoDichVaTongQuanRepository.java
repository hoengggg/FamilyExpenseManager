package Repository;

import Entity.GiaoDichVaTongQuan;
import Entity.ViCaNhan;
import helper.HibernateConfig;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;


public class GiaoDichVaTongQuanRepository {
    public List<GiaoDichVaTongQuan> getAll(){
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            return session
                    .createQuery("select gdvtq from GiaoDichVaTongQuan gdvtq order by gdvtq.ngayThang desc",
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

    //order by ft.ngayThang desc   là để hiển thị theo ngày gần nhất
    //:loai này là mik đặt còn ft.loai mới là của entity
    public List<GiaoDichVaTongQuan> loctheoloai(String loai){
        Session session = HibernateConfig.getFACTORY().openSession();
        //bước này là để tạo câu query
        Query query = session.createQuery("select ft from GiaoDichVaTongQuan ft where (:loai is null or ft.loai = :loai) order by ft.ngayThang desc", GiaoDichVaTongQuan.class);
        query.setParameter("loai", loai);
        List<GiaoDichVaTongQuan> list = query.getResultList(); //dòng này tác dụng là chạy câu query ở trên và nhận lấy danh sách mà db trả về
        session.close();
        return list;
    }

    public List<GiaoDichVaTongQuan> timkiem(String moTa){
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery("select gdvtq from GiaoDichVaTongQuan gdvtq where gdvtq.moTa like :moTa");
        query.setParameter("moTa", "%" + moTa + "%");
        return query.getResultList();
    }

    public List<Integer> getAllYear() {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Query<Integer> query = session.createQuery(
                    "select distinct function('year', g.ngayThang) " +
                            "from GiaoDichVaTongQuan g",
                    Integer.class
            );
            List<Integer> years = query.getResultList();
            years.sort(Integer::compareTo); // sắp xếp tăng dần
            return years;
        }
    }

    public Double tongThuTheoNam(int year) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {

            LocalDate start = LocalDate.of(year, 1, 1);
            LocalDate end = LocalDate.of(year, 12, 31);

            Query<Double> query = session.createQuery(
                    "select sum(g.soTien) " +
                            "from GiaoDichVaTongQuan g " +
                            "where g.ngayThang >= :start " +
                            "and g.ngayThang <= :end " +
                            "and trim(g.loai) = :loai",
                    Double.class
            );

            query.setParameter("start", start);
            query.setParameter("end", end);
            query.setParameter("loai", "Thu vào");

            Double result = query.getSingleResult();
            return result == null ? 0 : result;
        }
    }
    public Double tongChiTheoNam(int year) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {

            LocalDate start = LocalDate.of(year, 1, 1);
            LocalDate end = LocalDate.of(year, 12, 31);

            Query<Double> query = session.createQuery(
                    "select sum(g.soTien) " +
                            "from GiaoDichVaTongQuan g " +
                            "where g.ngayThang >= :start " +
                            "and g.ngayThang <= :end " +
                            "and trim(g.loai) = :loai",
                    Double.class
            );

            query.setParameter("start", start);
            query.setParameter("end", end);
            query.setParameter("loai", "Chi ra");

            Double result = query.getSingleResult();
            return result == null ? 0 : result;
        }
    }

    public Double tongTien(int year) {
        return tongThuTheoNam(year) - tongChiTheoNam(year);
    }

}
