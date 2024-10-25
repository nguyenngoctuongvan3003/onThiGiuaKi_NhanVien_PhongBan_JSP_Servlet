package onThiGK.daoImpl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import onThiGK.dao.NhanVienDAO;
import onThiGK.entities.NhanVien;
import onThiGK.entities.PhongBan;

public class NhanVienDAOImpl implements NhanVienDAO{
	private EntityManager entityManager;
	
	public NhanVienDAOImpl(EntityManager entityManager) {
		// TODO Auto-generated constructor stub
		this.entityManager=entityManager;
	}
	@Override
	public List<NhanVien> findAll() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from NhanVien").getResultList();
	}

	@Override
	public List<NhanVien> findByPhongBan(PhongBan phongBan) {
		// TODO Auto-generated method stub
		String jpql="select n from NhanVien n where n.phongBan = :phongBan";
		return entityManager.createQuery(jpql,NhanVien.class).setParameter("phongBan", phongBan).getResultList();
	}
	
	@Override
	public List<NhanVien> findTheoNhieuThuocTinh(String thuocTinh) {
	    String jpql = "select n from NhanVien n where n.tenNV like :thuocTinh or n.diaChi like :thuocTinh or n.dienThoai like :thuocTinh";
	    return entityManager.createQuery(jpql, NhanVien.class)
	            .setParameter("thuocTinh", "%" + thuocTinh + "%")
	            .getResultList();
	}
	
	@Override
	public NhanVien findNVByID(int id) {
		return entityManager.find(NhanVien.class, id);
	}

	@Override
	public boolean themNV(NhanVien nhanVien) {
		EntityTransaction transaction=null;
		
		try {
			transaction=entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(nhanVien);
			transaction.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(transaction!=null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean suaNV(NhanVien nhanVien) {
		EntityTransaction transaction=null;
		
		try {
			transaction=entityManager.getTransaction();
			transaction.begin();
			entityManager.merge(nhanVien);
			transaction.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(transaction!=null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean xoaNV(int id) {
EntityTransaction transaction=null;
		
		try {
			transaction=entityManager.getTransaction();
			transaction.begin();
			NhanVien nhanVien=findNVByID(id);
			if(nhanVien==null) {
				return false;
			}
			entityManager.remove(entityManager.contains(nhanVien)?nhanVien:entityManager.merge(nhanVien));
			transaction.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(transaction!=null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

}
