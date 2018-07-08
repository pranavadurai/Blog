<%@ include file="common/header.jspf" %>
<div class = "container">
    <h2 class = "text-center mt-2"> Sign in</h2>
    <div id="signin_form">	
    <div class = "row justify-content-md-center mt-4">
     <div class = "col-md-5 all_border">
     
      <fieldset class ="form-group">
        <label for = "email">Email:</label>
        <input type="email" name = "email" id = "email" class ="form-control" required/>
        <div class="valid-feedback">
                Welcome Back!
            </div>
            <div class="invalid-feedback">
                Email Not Registerrd with us!!!
            </div>
      </fieldset>
      
      <fieldset class ="form-group">
        <label for = "password">Password:</label>
        <input type="password" name = "password" id = "password" class ="form-control"  required/>
        <div class="invalid-feedback">
                Incorrect Password!!!
         </div>
        <small id="forgotpassword" class="form-text text-muted">
               <a href="/forgot_password">Forgot Password ?</a>
        </small> 
      </fieldset>
      
      <div class ="row"><a class="ml-1" href ="/signup">Don't have account Signup!!</a></div>
      
      <div class = "row justify-content-md-center mt-2">
      <button id ="submit" class = "btn btn-primary">Sign in </button>
      </div>
      
     </div>
    </div>
    </div>
</div>
<%@ include file="common/footer.jspf" %>
<script src="js/signin.js"></script>