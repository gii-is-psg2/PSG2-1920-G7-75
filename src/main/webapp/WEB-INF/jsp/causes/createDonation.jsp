<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#donationDate").datepicker({dateFormat: 'yy/mm/dd'});
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
            <div class="form-group has-feedback">
                <petclinic:inputField label="Donation Date" name="donationDate"/>
                <petclinic:inputField label="Amount" name="amount"/>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Client</label>
                    <div class="col-sm-10">
                        <c:out value="${donation.client.firstName} ${donation.client.lastName}"/>
                    </div>
                </div>
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