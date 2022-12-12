ACC.carouselCustom = {

	_autoload: [
		"bannerCarousel",
		"productCarousel"
	],

	bannerCarousel: function(){
		$(document).ready(function () {
                $('.carousel-banner').owlCarousel({
                    margin: 10,
                    lazyLoad: true,
                    nav: true,
                    loop: true,
                    items: 1,
                })
            })
	},

	productCarousel: function(){
    	$(document).ready(function () {
                $('.carousel-product').owlCarousel({
                        margin: 20,
                        lazyLoad: true,
                        dots: false,
                        nav: true,
                        loop: true,
                        items: 3,
                        responsiveClass:true,
                        responsive:{
                                0:{
                                    items:2,
                                    margin: 5,
                                },
                                600:{
                                    items:3,
                                },
                                1000:{
                                    items:3,
                                }
                            }
                    })
                })

    	}

};