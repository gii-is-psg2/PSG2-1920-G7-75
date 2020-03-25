<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="donations">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#date").datepicker({dateFormat: 'yyyy/MM/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2>
            <c:if test="${donation['new']}">New </c:if> Donation
        </h2>
        <form:form modelAttribute="donation"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${donation.id}"/>
            <input type="hidden" name="date" value="${date}"/>
            <div class="form-group has-feedback">
                <petclinic:inputField label="Amount" name="quantity"/>
                <petclinic:inputField label="DonorName" name="donorName"/>
                <petclinic:inputField label="Cause" name="cause"/>       
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
