<%@ page language="java" contentType="text/html; charset=utf-8"
     pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>person list</title>
    </head>
    <script type="text/javascript"  language="javascript">
    	function doSearch(){
    		var search=document.getElementById("search").value;
    		console.log(search);
    		window.location.href="http://localhost:8080/MyLucene/personController/searchPersonq?q="+encodeURIComponent(search);
    	}
  </script>
    <body>
    	<input  id="search" name="name"/>
    	<button  onclick="javascript:doSearch()">搜索</button>
        <table>
            <tr>
                <th>姓名</th>
                <th>年龄</th>
            </tr>
            <c:forEach items="${persons}" var="person">
                <tr>
                    <td>${person.name }</td>
                    <td>${person.age }</td>
                </tr>
            </c:forEach>
        </table>
        <form action="/MyLucene/personController/save" method="POST">
        <input  id="name" name="name"/>
        <input  id="age" name="age"/>
        <input type="submit" value="提交" />
        </form>
    </body>
</html>