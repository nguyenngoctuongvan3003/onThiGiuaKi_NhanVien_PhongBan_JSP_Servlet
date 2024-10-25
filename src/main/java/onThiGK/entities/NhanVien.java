package onThiGK.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class NhanVien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maNV;
	
	@NotNull(message = "Ten khong duoc null")
	@NotEmpty(message = "Ten khong duoc de trong")
	@Size(max = 50, message = "Khong duoc nhap qua 50 ki tu")
	private String tenNV;
	
	@Email(message = "Phai nhap dung dinh dang email")
	@NotNull(message = "Email khong duoc null")
	@NotEmpty(message = "Email khong duoc de trong")
	private String email;
	
	private String diaChi;
	
	@NotNull(message = "sdt khong duoc null")
	@NotEmpty(message = "sdt khong duoc de trong")
	private String dienThoai;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maPhongBan")
	private PhongBan phongBan;

	public NhanVien(int maNV, String tenNV, String email, String diaChi, String dienThoai, PhongBan phongBan) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.email = email;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.phongBan = phongBan;
	}
	public NhanVien(String tenNV, String email, String diaChi, String dienThoai, PhongBan phongBan) {
		super();
		this.tenNV = tenNV;
		this.email = email;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.phongBan = phongBan;
	}

	public NhanVien() {
		// TODO Auto-generated constructor stub
	}

	public int getMaNV() {
		return maNV;
	}

	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	public PhongBan getPhongBan() {
		return phongBan;
	}

	public void setPhongBan(PhongBan phongBan) {
		this.phongBan = phongBan;
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", email=" + email + ", diaChi=" + diaChi
				+ ", dienThoai=" + dienThoai + ", phongBan=" + phongBan + "]";
	}
	
	
	
}
