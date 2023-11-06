<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
<h2>Welcome <% String name = request.getParameter("username");
                out.print(name); %> !</h2>
<%
    if(session.getAttribute("userId") == null) {
        response.sendRedirect(request.getContextPath());
    }
%>

<form action="logout.jsp" method="link">
    <input type="submit" value="Logout"/>
</form>

<form method="post" action="home">
    <div>
        <input type="text" name="item" placeholder="Enter value..." value=""/>
    </div><br>
    <input type="submit" value="Add"/>
</form>

<c:if test="${todos.size() eq 0}">
    <p>No Items to display</p>
</c:if>

<c:if test="${todos.size() gt 0}">
        <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Todo</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="todo" items="${todos}">
                        <tr>
                            <td>
                                <c:out value="${todo.id}" />
                            </td>
                            <td>
                                <c:out value="${todo.todoItem}" />
                            </td>
                            <td><a href="home?id=<c:out value='${todo.id}' />">Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
</c:if>
</body>
</html>