<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add</title>
</head>
<body>

	<div align="center">
		<form action="QuanLiNhanVienServlet?action=add" method="post">
			<c:if test="${loi!=null }">
				<table border="0">
					<tr>
						<h3 color="red">
							<c:out value="${loi }" escapeXml="false"></c:out>
						</h3>
					</tr>
				</table>
			</c:if>
			<c:if test="${loi!=null }">
				<input type="hidden" name="maNV" value='<c:out value="${nv.maNV }"></c:out>'>
			</c:if>
			<table border="0">
				<tr>
					<th>Ma phòng ban (1 hoặc 2)</th>
					<td><input type="text" name="maPhongBan"
						value='<c:out value="${nv.phongBan.maPhongBan}"></c:out>'></td>
				</tr>

				<tr>
					<th>Tên nv</th>
					<td><input type="text" name="tenNV"
						value='<c:out value="${nv.tenNV}"></c:out>'></td>
				</tr>

				<tr>
					<th>Email</th>
					<td><input type="text" name="email"
						value='<c:out value="${nv.email}"></c:out>'></td>
				</tr>
				
				<tr>
					<th>Dia chi</th>
					<td><input type="text" name="diaChi"
						value='<c:out value="${nv.diaChi}"></c:out>'></td>
				</tr>
				
				<tr>
					<th>Dien thoai</th>
					<td><input type="text" name="dienThoai"
						value='<c:out value="${nv.dienThoai}"></c:out>'></td>
				</tr>
				
				<tr>
					<td> <input type="submit" value="Insert"> </td>
				</tr>
			</table>

		</form>
	</div>
</body>
</html>