<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询用户列表</title>
</head>
<body> 
<%--<form action="${pageContext.request.contextPath }/deleteAll.action" method="post">--%>
	<form action="${pageContext.request.contextPath }/updateAll.action" method="post">
查询条件：
<table width="100%" border=1>
<tr>
<%--<td><input type="submit" value="批量删除"/></td>--%>
	<td><input type="submit" value="批量修改"/></td>
</tr>
</table>
用户列表：
<table width="100%" border=1>
<tr>
	<td></td>
	<td>用户名</td>
	<td>生日</td>
	<td>性别</td>
	<td>所在地区</td>
	<td>操作</td>
</tr>
<c:forEach items="${userList }" var="item" varStatus="status">
<tr>
	<td><input type="checkbox" name="ids" value="${item.id}"></td>
	<td><input type="text" name="users[${status.index}].username" value="${item.username }"></td>
	<td><input type="text" name="users[${status.index}].birthday" value="<fmt:formatDate value="${item.birthday}" pattern="yyyy-MM-dd"/>"></td>
	<td><input type="text" name="users[${status.index}].sex" value="${item.sex }"></td>
	<td><input type="text" name="users[${status.index}].address" value="${item.address }"></td>
	
	<td><a href="${pageContext.request.contextPath }/itemEdit.action?id=${item.id}">修改</a></td>

</tr>
</c:forEach>

</table>
</form>
</body>

</html>