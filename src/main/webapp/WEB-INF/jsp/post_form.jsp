<%@ include file="common/header.jspf" %>
<div class = "container">
    <h2 class = "text-center mt-2">Post delete </h2>
    <form:form method = "post" modelAttribute = "Post">
    <div class = "row justify-content-md-center mt-4">
     <div class = "col-md-5 all_border">
      <fieldset class ="form-group">
        <form:label path = "tweet">What you think ${name} ?</form:label>
        <form:textarea path = "tweet" class ="form-control"  required = "true"/>
      </fieldset>
      
      <div class = "row justify-content-md-center">
      <button type = "submit" class = "btn btn-primary">Post </button>
      </div>
      
     </div>
    </div>
    </form:form>
</div>
<%@ include file="common/footer.jspf" %>