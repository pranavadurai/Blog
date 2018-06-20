<%@ include file="common/header.jspf" %>
<div class = "container">
    <h2 class = "text-center"> Sign Up</h2>
          
	    <form method = "post">
	    <div class = "row justify-content-md-center mt-4">
	     <div class ="col-md-5 all_border">
	      <div class ="form-group">
	        <label for = "email">Email:</label>
	        <input type="email" name = "email" class ="form-control" id = "email" required/>
	      </div>
	      
	      <div class ="form-group">
	        <label for = "name">Name:</label>
	        <input type="text" name= "name" class ="form-control" id = "name" required/>
	      </div>
	      
	      <div class ="form-group">
	        <label for = "password">Password:</label>
	        <input type="password" name = "password" class ="form-control" id = "password" required/>
	      </div>
	      
	      <div class = "row justify-content-md-center">
	      <button type = "submit" class = "btn btn-primary">Signup</button>
	      </div>
	      
	     </div>
	    </div>
	    </form>    
</div>
<%@ include file="common/footer.jspf" %>