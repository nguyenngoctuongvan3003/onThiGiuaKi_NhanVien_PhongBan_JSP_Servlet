package onThiGK.dao;

import java.util.List;

import onThiGK.entities.NhanVien;
import onThiGK.entities.PhongBan;

public interface PhongBanDAO {
	public PhongBan findById(int id);

	public boolean themPhongBan(PhongBan phongBan);

	public List<NhanVien> findAll();
}
