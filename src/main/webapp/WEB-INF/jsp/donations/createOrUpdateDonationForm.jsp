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
        
        <form:form method="POST" modelAttribute="donation" class="form-horizontal">
            <form:input type="hidden" path="id" value="${donation.id}"/>
            
            <div class="form-group has-feedback">
            
                <petclinic:inputField label="quantity" name="quantity"/>
                
                <select name="owner_id">
					<c:forEach items="${listOwners}" var="item">
							<option value="${item.id}" label="${item.lastName}, ${item.firstName}"></option>
					</c:forEach>
				</select> 
				   
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
        <c:if test="${!donation['new']}">
        </c:if>
    </jsp:body>
</petclinic:layout>
