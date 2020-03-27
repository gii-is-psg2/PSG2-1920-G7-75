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
		        <td>
                    <spring:url value="causes/{causeId}/edit" var="causeUrl">
                    <spring:param name="causeId" value="${cause.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(causeUrl)}" class="btn btn-default">Edit Cause</a>
				</td>
                <td>
                    <spring:url value="/cause/{causeId}/delete" var="causeUrl">
                    <spring:param name="causeId" value="${cause.id}"/>
                   	</spring:url>
                   	<a href="${fn:escapeXml(causeUrl)}" class="btn btn-default">Delete Cause</a>
                </td>
               
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/causes.xml" htmlEscape="true" />">View as XML</a>
            </td>            
        </tr>
    </table>
    <a class="btn btn-default" href='<spring:url value="/causes/new"/>'>Add Cause</a>
</petclinic:layout>
