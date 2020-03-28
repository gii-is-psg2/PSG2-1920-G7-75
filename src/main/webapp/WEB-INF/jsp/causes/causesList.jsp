<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="causes">
	<h2>Causes</h2>

	<table id="causesTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Name</th>
				<th style="width: 200px;">Total budget</th>
				<th style="width: 120px">Budget target</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${selections}" var="cause">
				<tr>
					<td><c:out value="${cause.name}" /></td>
					<td><c:out value="${cause.totalBudget}" /></td>
					<td><c:out value="${cause.budgetTarget}" /></td>
					<td><spring:url value="causes/{causeId}" var="causeUrl">
							<spring:param name="causeId" value="${cause.id}" />
						</spring:url> <a href="${fn:escapeXml(causeUrl)}" class="btn btn-default">Cause	Details</a></td>
					<td><spring:url value="causes/{causeId}/newDonation" var="donationUrl">
							<spring:param name="causeId" value="${cause.id}" />
						</spring:url> <a href="${fn:escapeXml(donationUrl)}" class="btn btn-default">Create	Donation</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<br>
	<br>
	<a class="btn btn-default"
		href='<spring:url value="/causes/new" htmlEscape="true"/>'>Add
		Cause</a>
</petclinic:layout>
