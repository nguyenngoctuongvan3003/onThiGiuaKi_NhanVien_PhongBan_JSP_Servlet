package onThiGK.daoImpl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import onThiGK.dao.PhongBanDAO;
import onThiGK.entities.NhanVien;
import onThiGK.entities.PhongBan;

public class PhongBanDAOImpl implements PhongBanDAO{
	private EntityManager entityManager;
	
	public PhongBanDAOImpl(EntityManager entityManager) {
		// TODO Auto-generated constructor stub
		this.entityManager=entityManager;
	}
	@Override
	public List<NhanVien> findAll() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from PhongBan").getResultList();
	}
	@Override
	public PhongBan findById(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(PhongBan.class, id);
	}
	@Override
	public boolean themPhongBan(PhongBan phongBan) {
		EntityTransaction transaction=null;
		
		try {
			transaction=entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(phongBan);
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
