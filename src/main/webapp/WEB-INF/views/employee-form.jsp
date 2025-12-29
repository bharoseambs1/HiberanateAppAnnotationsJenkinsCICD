<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>

<h2>${employee.empId == 0 ? 'Add Employee' : 'Edit Employee'}</h2>

<form action="${pageContext.request.contextPath}/employee/save" method="post">

    Employee ID:
    <input type="text" name="empId"
           value="${employee.empId}"
           ${employee.empId != 0 ? 'readonly' : ''} />
    <br/>

    Employee Name:
    <input type="text" name="empName" value="${employee.empName}" required/>
    <br/>

    Salary:
    <input type="text" name="salary" value="${employee.salary}" required/>
    <br/>

    Department:
    <select name="department.deptId" required>
        <option value="">-- Select Department --</option>

        <c:forEach var="d" items="${departments}">
            <option value="${d.deptId}"
                <c:if test="${employee.department != null && employee.department.deptId == d.deptId}">
                    selected
                </c:if>>
                ${d.deptName}
            </option>
        </c:forEach>
    </select>
    <br/>

    <input type="submit" value="Update"/>
</form>

<br/>
<a href="${pageContext.request.contextPath}/employee/list">Back to List</a>

</body>
</html>
