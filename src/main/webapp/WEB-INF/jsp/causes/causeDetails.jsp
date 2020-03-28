<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">

    <h2>Cause Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${cause.name} "/></b></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><c:out value="${cause.desc}"/></td>
        </tr>
        <tr>
            <th>Budget target</th>
            <td><c:out value="${cause.budgetTarget}"/></td>
        </tr>
        <tr>
            <th>Organization</th>
            <td><c:out value="${cause.organization}"/></td>
        </tr>
    </table>
    
    <spring:url value="{causeId}/newDonation" var="donationUrl">
		<spring:param name="causeId" value="${cause.id}" />
		</spring:url> 
		<a href="${fn:escapeXml(donationUrl)}" class="btn btn-default">Create Donation</a>
				

    <br/>
    <br/>
    <br/>
    <h2>Donations</h2>

    <table class="table table-striped">
        <c:forEach var="donation" items="${donations}">

            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Date</dt>
                        <dd><petclinic:localDate date="${donation.date}" pattern="yyyy/MM/dd"/></dd>
                        <dt>Amount</dt>
                        <dd><c:out value="${donation.quantity}"/></dd>
                        <dt>Client</dt>
                        <dd><c:out value="${donation.donorName}"/></dd>
                    </dl>
                </td>
            </tr>

        </c:forEach>
    </table>
</petclinic:layout>
