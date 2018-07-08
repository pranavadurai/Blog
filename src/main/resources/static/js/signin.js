$(function(){	
	$(".valid-feedback").hide();
	$(".invalid-feedback").hide();
	
	$("#email").on("change",function(){
		$.get("/check_email?email="+$("#email").val(),{
		}).done(function(data,status){
			if(data){
				$("#email").removeClass("is-valid");
				$("#email").addClass("is-invalid");
				$("#email").siblings(".valid-feedback").hide();
				$("#email").siblings(".invalid-feedback").show();
				$("#submit").attr("disabled",true);
			}
			else{
				$("#email").removeClass("is-invalid");
				$("#email").addClass("is-valid");
				$("#email").siblings(".valid-feedback").show();
				$("#email").siblings(".invalid-feedback").hide();
				$("#submit").attr("disabled",false);
			}
		}).fail(function(xhr,status,errorThrown){
		    alert( "Sorry, there was a problem!" );
		    console.log( "Error: " + errorThrown );
		    console.log( "Status: " + status );
		    console.dir( xhr );
		})
	});
	
	$("#submit").on("click",function(){
		        
		var formData = {email: $("#email").val(),password: $("#password").val()};
        
		$.ajax({
		   type: "POST",
		   contentType : "application/json",
		   url: "/signin",
		   data: JSON.stringify(formData), 
		   dataType: "text",
		   success : function(data){
			if(data != "false")
				window.location.replace(data);
			else{
				$("#password").addClass("is-invalid");
				$("#password").siblings(".invalid-feedback").show();
			 }
		   },
		    error : function(e){
		       alert( "Sorry, there was a problem!" );
		       console.log( "Error: " + e);       
		      }
		   });
	});
});