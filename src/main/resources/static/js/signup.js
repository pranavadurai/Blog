$(function(){	
	$(".valid-feedback").hide();
	$(".invalid-feedback").hide();
	
	$("#email").on("change",function(){
		if($("#email").val())
		$.get("/check_email?email="+$("#email").val(),{
		}).done(function(data,status){
			if(data){
				$("#email").removeClass("is-invalid");
				$("#email").addClass("is-valid");
				$("#email").siblings(".valid-feedback").show();
				$("#email").siblings(".invalid-feedback").hide();
				$("#submit").attr("disabled",false);
			}
			else{
				$("#email").removeClass("is-valid");
				$("#email").addClass("is-invalid");
				$("#email").siblings(".valid-feedback").hide();
				$("#email").siblings(".invalid-feedback").show();
				$("#submit").attr("disabled",true);
			}
		}).fail(function(xhr,status,errorThrown){
		    alert( "Sorry, there was a problem!" );
		    console.log( "Error: " + errorThrown );
		    console.log( "Status: " + status );
		    console.dir( xhr );
		})
	});

   
});