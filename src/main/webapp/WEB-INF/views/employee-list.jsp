<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>

<h2>Employee List: Shraddha | Satish </h2>

<!-- Navigation -->
<a href="${pageContext.request.contextPath}/department/add">
    Add Department
</a>
&nbsp; | &nbsp;
<a href="${pageContext.request.contextPath}/employee/add">
    Add Employee
</a>

<br/><br/>

<table border="1" cellpadding="5" cellspacing="0">
    <tr style="background-color:#f2f2f2">
        <th>Employee ID</th>
        <th>Name</th>
        <th>Salary</th>
        <th>Department</th>
        <th>Action</th>
    </tr>

    <c:forEach var="e" items="${employees}">
        <tr>
            <td>${e.empId}</td>
            <td>${e.empName}</td>
            <td>${e.salary}</td>

            <!-- SAFE Department display -->
            <td>
                <c:choose>
                    <c:when test="${e.department != null}">
                        ${e.department.deptName}
                    </c:when>
                    <c:otherwise>
                        <span style="color:red">Not Assigned</span>
                    </c:otherwise>
                </c:choose>
            </td>

            <td>
                <a href="${pageContext.request.contextPath}/employee/edit/${e.empId}">
                    Edit
                </a>
                |
                <a href="${pageContext.request.contextPath}/employee/delete/${e.empId}"
                   onclick="return confirm('Are you sure you want to delete this employee?');">
                    Delete
                </a>
            </td>
        </tr>
    </c:forEach>

    <c:if test="${empty employees}">
        <tr>
            <td colspan="5" align="center">
                No employees found
            </td>
        </tr>
    </c:if>

</table>

</body>
</html>
