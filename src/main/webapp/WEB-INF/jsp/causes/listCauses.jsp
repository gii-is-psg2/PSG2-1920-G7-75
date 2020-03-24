<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">
    <h2>Causes</h2>

    <table id="causesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Name</th>
            <th style="width: 200px;">Description</th>
            <th>Budget target</th>
            <th>Non profit organization</th>
           
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${causes}" var="cause">
            <tr>
                <td>
                   <c:out value="${cause.name}"/>
                </td>
                <td>
                    <c:out value="${cause.description}"/>
                </td>
                <td>
                    <c:out value="${cause.target}"/>
                </td>
                <td>
                    <c:out value="${cause.organization}"/>
                </td>
               
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
