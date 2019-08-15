<%@ page import="java.util.List" %>
<%@ page import="com.rectrl.entity.Book" %><%--
  Created by IntelliJ IDEA.
  User: zhanghongen
  Date: 2019/8/15
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${list}
<table>
    <%
        List<Book> list = (List<Book>) request.getAttribute("list");
        for (int i = 0; i < list.size(); i++) {

            Book book = list.get(i);
    %>

    <tr>
        <td><%=book.getName() %>
        </td>
        <td><%=book.getBookId() %>
        </td>
        <td><%=book.getNumber() %>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
