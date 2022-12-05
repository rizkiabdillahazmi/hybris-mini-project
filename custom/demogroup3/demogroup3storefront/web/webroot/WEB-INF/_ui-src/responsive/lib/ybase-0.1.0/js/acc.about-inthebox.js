ACC.aboutinthebox = {

	_autoload: [
	    "aboutExtend"
	],

	aboutExtend: function (){
	    $('#question0').on('click', function (){
    	    if($("#faq0").hasClass("hide")) {
    	        $("#question0 .expanded").addClass("hide");
    	        $("#question0 .collapsed").removeClass("hide");
    	        $("#faq0").removeClass("hide");
    	        $("#faq0").addClass("show");

    	    }
    	    else if($("#faq0").hasClass("show")) {
    	        $("#question0 .collapsed").addClass("hide");
    	        $("#question0 .expanded").removeClass("hide");
    	        $("#faq0").removeClass("show");
    	        $("#faq0").addClass("hide");
            }
	    });
	    $('#question1').on('click', function (){
    	    if($("#faq1").hasClass("hide")) {
    	        $("#question1 .expanded").addClass("hide");
    	        $("#question1 .collapsed").removeClass("hide");
    	        $("#faq1").removeClass("hide");
    	        $("#faq1").addClass("show");

    	    }
    	    else if($("#faq1").hasClass("show")) {
    	        $("#question1 .collapsed").addClass("hide");
    	        $("#question1 .expanded").removeClass("hide");
    	        $("#faq1").removeClass("show");
    	        $("#faq1").addClass("hide");
            }
	    });
	    $('#question2').on('click', function (){
    	    if($("#faq2").hasClass("hide")) {
    	        $("#question2 .expanded").addClass("hide");
    	        $("#question2 .collapsed").removeClass("hide");
    	        $("#faq2").removeClass("hide");
    	        $("#faq2").addClass("show");

    	    }
    	    else if($("#faq2").hasClass("show")) {
    	        $("#question2 .collapsed").addClass("hide");
    	        $("#question2 .expanded").removeClass("hide");
    	        $("#faq2").removeClass("show");
    	        $("#faq2").addClass("hide");
            }
	    });
	    $('#question3').on('click', function (){
    	    if($("#faq3").hasClass("hide")) {
    	        $("#question3 .expanded").addClass("hide");
    	        $("#question3 .collapsed").removeClass("hide");
    	        $("#faq3").removeClass("hide");
    	        $("#faq3").addClass("show");

    	    }
    	    else if($("#faq3").hasClass("show")) {
    	        $("#question3 .collapsed").addClass("hide");
    	        $("#question3 .expanded").removeClass("hide");
    	        $("#faq3").removeClass("show");
    	        $("#faq3").addClass("hide");
            }
	    });
	}
};