<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Causes">
    <jsp:attribute name="customScript">

    </jsp:attribute>
    <jsp:body>
        <h2>Cause </h2>
        
        <form:form modelAttribute="cause" class="form-horizontal" action="/causes">
            <div class="form-group has-feedback">
                <petclinic:inputField label="Name" name="name"/>
                <petclinic:inputField label="Description" name="description"/>
               	<petclinic:inputField label="BudgetTarget" name="target"/>
                <petclinic:inputField label="Organization" name="Organization"/>
           </div>
           
           <div class="form-group">
           		<div class="col-sm-offset-2 col-sm-10">
                	<input type="hidden" name="id" value="${cause.id}">
                	<button class="btn btn-default" type="submit">Save Cause</button>
                </div>
           </div>
        </form:form>
    </jsp:body>
</petclinic:layout>
