package onThiGK.dao;

import java.util.List;

import onThiGK.entities.NhanVien;
import onThiGK.entities.PhongBan;

public interface NhanVienDAO {
	public List<NhanVien> findAll();
	public List<NhanVien> findByPhongBan(PhongBan phongBan);
	public boolean themNV(NhanVien nhanVien);
	public boolean xoaNV(int id);
	public NhanVien findNVByID(int id);
	public boolean suaNV(NhanVien nhanVien);
	public List<NhanVien> findTheoNhieuThuocTinh(String thuocTinh);
}
