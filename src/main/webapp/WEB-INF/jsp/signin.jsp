<%@ include file="common/header.jspf" %>
<div class = "container">
    <h2 class = "text-center mt-2"> Sign in</h2>
    <form:form method = "post" modelAttribute = "Authentication">
    <div class = "row justify-content-md-center mt-4">
     <div class = "col-md-5 all_border">
     
      <fieldset class ="form-group">
        <form:label path = "email">Email:</form:label>
        <form:input type="email" path = "email" class ="form-control" required= "true"/>
      </fieldset>
      
      <fieldset class ="form-group">
        <form:label path = "password">Password:</form:label>
        <form:input type="password" path = "password" class ="form-control"  required = "true"/>
      </fieldset>
      
      <div class = "row justify-content-md-center">
      <button type = "submit" class = "btn btn-primary">Sign in </button>
      </div>
      
     </div>
    </div>
    </form:form>
</div>
<%@ include file="common/footer.jspf" %>