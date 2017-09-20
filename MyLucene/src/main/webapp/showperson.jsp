<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>person list</title>
</head>
<script type="text/javascript" language="javascript">
    	function doSearch(){
    		var x = document.getElementsByClassName("hide");
    		for(var i=0;i<x.length;i++){
    			x[i].style.visibility="hidden";
    		}
    		var search=document.getElementById("search").value;
    		window.location.href="http://localhost:8080/MyLucene/personController/searchPersonq?q="+encodeURIComponent(search);
    	}
    	function update(id){
    		var x = document.getElementsByClassName("hide");
    		for(var i=0;i<x.length;i++){
    			x[i].style.visibility="hidden";
    		}
    		document.getElementById("updatetd"+id).style.visibility="visible";
    	}
    	function _delete(id){
    		window.location.href="http://localhost:8080/MyLucene/personController/delete?id="+encodeURIComponent(id);
    	}
  </script>
<body>
	<input id="search" name="name" />
	<button onclick="javascript:doSearch()">搜索</button>
	<table>
		<tr>
			<th>姓名</th>
			<th>描述</th>
		</tr>
		<c:forEach items="${persons}" var="person">
			<tr>
				<td>${person.name }</td>
				<td>${person.age }</td>
				<td><button onclick="javascript:update(${person.id})"  id="_update">修改</button></td>
				<td><button onclick="javascript:_delete(${person.id})" id="_delete">删除</button></td>
				<td style="visibility:hidden;" id="updatetd${person.id}" class="hide" >
					<form action="/MyLucene/personController/update" method="POST" >
						<input type="hidden" id="id" name="id" value=${person.id} /> 姓名:<input id="name" name="name"  value=${person.name} /> 描述:<input id="age" name="age" value=${person.age} /> 
						<input type="submit" value="提交" />
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<form action="/MyLucene/personController/save" method="POST">
		姓名:<input id="name" name="name" /> 描述:<input id="age" name="age" /> <input type="submit" value="提交" />
	</form>
</body>
</html>