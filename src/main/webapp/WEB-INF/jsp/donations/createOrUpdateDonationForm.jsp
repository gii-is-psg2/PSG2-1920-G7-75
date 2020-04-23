<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="donations">
    <jsp:body>
    	
    	<h2>
            <c:if test="${donation['new']}">New </c:if> Donation
        </h2>
    	
    	<b>Cause</b>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Budget target</th>
                <th>Total budget</th>
                <th>Organization</th>
            </tr>
            </thead>
            <tr>
                <td><c:out value="${cause.name}"/></td>
                <td><c:out value="${cause.desc}"/></td>
                <td><c:out value="${cause.budgetTarget}"/></td>
                <td><c:out value="${cause.totalBudget}"/></td>
                <td><c:out value="${cause.organization}"/></td>
            </tr>
        </table>
        
        <form:form method="POST" modelAttribute="donation" class="form-horizontal">
            <form:input type="hidden" path="id" value="${donation.id}"/>
            
            <div class="form-group has-feedback">
            	
            	<c:set var="cssGroup" value="form-group ${status.error ? 'error' : '' }"/>
            	
            	<div class="${cssGroup}">
            		<label class="col-sm-2 control-label">Owner: </label>
            		
            		<div class="col-sm-10">
		                <select class="form-control" name="owner_id">
							<c:forEach items="${listOwners}" var="item">
									<option value="${item.id}" label="${item.lastName}, ${item.firstName}"></option>
							</c:forEach>
						</select>
					</div>
					
            	</div>

                <petclinic:inputField label="Quantity: " name="quantity"/>
     
                
				   
            </div>
            
             <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${donation['new']}">
                            <button class="btn btn-default" type="submit">Add Donation</button>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </form:form>
        
        <b>Previous donations</b>
        <table class="table table-striped">
            <tr>
             	<th>Owner</th>
                <th>Date</th>
                <th>Quantity</th>
            </tr>
            <c:forEach var="donation" items="${cause.donations}">
                <c:if test="${!donation['new']}">
                    <tr>
                    	<td><c:out value="${donation.owner.lastName}, ${donation.owner.firstName}"/></td>
                        <td><petclinic:localDate date="${donation.date}" pattern="yyyy/MM/dd"/></td>
                        <td><c:out value="${donation.quantity}"/></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
        
        <c:if test="${!donation['new']}">
        </c:if>
    </jsp:body>
</petclinic:layout>
