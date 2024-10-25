<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
</head>
<body>
	<div align="center">
		<table border="1" align="center">
			<tr>
				<h2>30_NguyenNgocTuongVan_21088131</h2>
			</tr>

			<tr>
				<td><a
					href="${pageContext.request.contextPath }/QuanLiNhanVienServlet">Xem
						All NV</a></td>
				<td><a
					href="${pageContext.request.contextPath }/QuanLiNhanVienServlet?action=showInsert">Them
						NV</a></td>
				<td><a
					href="${pageContext.request.contextPath }/QuanLiNhanVienServlet?action=findByPhongBan&id=1">Xem
						Theo Phong Ban id=1</a></td>
				<td><a
					href="${pageContext.request.contextPath }/QuanLiNhanVienServlet?action=findByPhongBan&id=2">Xem
						Theo Phong Ban id=2</a></td>
			</tr>

			<tr>DANH SÁCH NHÂN VIÊN
			</tr>

			<tr>
				<form action="QuanLiNhanVienServlet?action=search" method="post">
					<td>Search theo thông tin:</td>
					<td><input type="text" name="value" ></td>
					<td> <input type="submit" value="Submit"></td>
				</form>

			</tr>
			<tr>
				<th>Tên phòng ban</th>
				<th>Ma mv</th>
				<th>Tên nv</th>
				<th>Email</th>
				<th>Dia chi</th>
				<th>Dien thoai</th>
				<th></th>
			</tr>
			<c:forEach var="nv" items="${listNV }">
				<tr>
					<td>${nv.phongBan.tenPhongBan }</td>
					<td>${nv.maNV }</td>
					<td>${nv.tenNV }</td>
					<td>${nv.email}</td>
					<td>${nv.diaChi }</td>
					<td>${nv.dienThoai }</td>
					<td><a
						href="${pageContext.request.contextPath }/QuanLiNhanVienServlet?action=delete&id=${nv.maNV}" onclick="return confirm('Ban có chac chan khong?')">Xoa
							NV</a><br /> <a
						href="${pageContext.request.contextPath }/QuanLiNhanVienServlet?action=showEdit&id=${nv.maNV}">Edit
							NV</a></td>
				</tr>
			</c:forEach>
		</table>

	</div>
</body>
</html>