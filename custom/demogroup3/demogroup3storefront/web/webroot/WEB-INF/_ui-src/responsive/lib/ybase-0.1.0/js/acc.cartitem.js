ACC.cartitem = {

	_autoload: [
	"bindCartItem",
	    "initPageEvents"

	],

	submitTriggered: false,

	bindCartItem: function ()
	{

		$('.js-execute-entry-action-button').on("click", function ()
		{
			var entryAction = $(this).data("entryAction");
			var entryActionUrl =  $(this).data("entryActionUrl");
			var entryProductCode =  $(this).data("entryProductCode");
			var entryInitialQuantity =  $(this).data("entryInitialQuantity");
			var actionEntryNumbers =  $(this).data("actionEntryNumbers");

			if(entryAction == 'REMOVE')
			{
				ACC.track.trackRemoveFromCart(entryProductCode, entryInitialQuantity);
			}

			var cartEntryActionForm = $("#cartEntryActionForm");
			var entryNumbers = actionEntryNumbers.toString().split(';');
			entryNumbers.forEach(function(entryNumber) {
				var entryNumbersInput = $("<input>").attr("type", "hidden").attr("name", "entryNumbers").val(entryNumber);
				cartEntryActionForm.append($(entryNumbersInput));
			});
			cartEntryActionForm.attr('action', entryActionUrl).submit();
		});

		$('.js-update-entry-quantity-input').on("blur", function (e)
		{
			ACC.cartitem.handleUpdateQuantity(this, e);

		}).on("keyup", function (e)
		{
			return ACC.cartitem.handleKeyEvent(this, e);
		}
		).on("keydown", function (e)
		{
			return ACC.cartitem.handleKeyEvent(this, e);
		}
		);
	},

	handleKeyEvent: function (elementRef, event)
	{
		//console.log("key event (type|value): " + event.type + "|" + event.which);

		if (event.which == 13 && !ACC.cartitem.submitTriggered)
		{

			ACC.cartitem.submitTriggered = ACC.cartitem.handleUpdateQuantity(elementRef, event);
			return false;
		}
		else 
		{
			// Ignore all key events once submit was triggered
			if (ACC.cartitem.submitTriggered)
			{
				return false;
			}
		}

		return true;
	},

	handleUpdateQuantity: function (elementRef, event)
	{

		var form = $(elementRef).closest('form');

		var productCode = form.find('input[name=productCode]').val();
		var initialCartQuantity = form.find('input[name=initialQuantity]').val();
		var newCartQuantity = form.find('input[name=quantity]').val();

		if(initialCartQuantity != newCartQuantity)
		{
			ACC.track.trackUpdateCart(productCode, initialCartQuantity, newCartQuantity);
			form.submit();

			return true;
		}

		return false;
	},

	checkQtySelector: function (self, mode) {
        	var $qtySelector = $(document).find(self).parents(".js-qty-selector");
            var input = $qtySelector.find(".js-qty-selector-input-cart-det");
            var inputVal = parseInt(input.val());
            var max = input.data("max");
            var minusBtn = $qtySelector.find(".js-qty-selector-minus-cart-det");
            var plusBtn = $qtySelector.find(".js-qty-selector-plus-cart-det");

            $qtySelector.find(".btn").removeAttr("disabled");

            if (mode == "minus") {
                if (inputVal != 1) {
                    ACC.cartitem.updateQtyValue(self, inputVal - 1)
                    if (inputVal - 1 == 1) {
                        minusBtn.attr("disabled", "disabled")
                    }

                } else {
                    minusBtn.attr("disabled", "disabled")
                }
            } else if (mode == "reset") {
                ACC.cartitem.updateQtyValue(self, 1)

            } else if (mode == "plus") {
            	if(max == "FORCE_IN_STOCK") {
            		ACC.cartitem.updateQtyValue(self, inputVal + 1)
            	} else if (inputVal <= max) {
                    ACC.cartitem.updateQtyValue(self, inputVal + 1)
                    if (inputVal + 1 == max) {
                        plusBtn.attr("disabled", "disabled")
                    }
                } else {
                    plusBtn.attr("disabled", "disabled")
                }
            } else if (mode == "input") {
                if (inputVal == 1) {
                    minusBtn.attr("disabled", "disabled")
                } else if(max == "FORCE_IN_STOCK" && inputVal > 0) {
                	ACC.cartitem.updateQtyValue(self, inputVal)
                } else if (inputVal == max) {
                    plusBtn.attr("disabled", "disabled")
                } else if (inputVal < 1) {
                    ACC.cartitem.updateQtyValue(self, 1)
                    minusBtn.attr("disabled", "disabled")
                } else if (inputVal > max) {
                    ACC.cartitem.updateQtyValue(self, max)
                    plusBtn.attr("disabled", "disabled")
                }
            } else if (mode == "focusout") {
            	if (isNaN(inputVal)){
                    ACC.cartitem.updateQtyValue(self, 1);
                    minusBtn.attr("disabled", "disabled");
            	} else if(inputVal >= max) {
                    plusBtn.attr("disabled", "disabled");
                }
            }

        },

    updateQtyValue: function (self, value) {
            //var input = $(document).find(self).parents("#cart-item-count").find(".js-qty-selector").find(".js-qty-selector-input-cart-det");
            var form = $(self).closest('form');
            var productCode = form.find('input[name=productCode]').val();
            var initialCartQuantity = form.find('input[name=initialQuantity]').val();
            var newCartQuantity = form.find('input[name=quantity]').val(value);

            if(initialCartQuantity != newCartQuantity)
            {
                ACC.track.trackUpdateCart(productCode, initialCartQuantity, newCartQuantity);
                form.submit();

                return true;
            }

            return false;
    },

    initPageEvents: function () {
            $(document).on("click", '.js-qty-selector .js-qty-selector-minus-cart-det', function (e) {
                ACC.cartitem.checkQtySelector(this, "minus");
            })

            $(document).on("click", '.js-qty-selector .js-qty-selector-plus-cart-det', function (e) {
                ACC.cartitem.checkQtySelector(this, "plus");
            })

//            $(document).on("keydown", '.js-qty-selector .js-qty-selector-input-cart-det', function (e) {
//
//                if (($(this).val() != " " && ((e.which >= 48 && e.which <= 57 ) || (e.which >= 96 && e.which <= 105 ))  ) || e.which == 8 || e.which == 46 || e.which == 37 || e.which == 39 || e.which == 9) {
//                }
//                else if (e.which == 38) {
//                    ACC.cartitem.checkQtySelector(this, "plus");
//                }
//                else if (e.which == 40) {
//                    ACC.cartitem.checkQtySelector(this, "minus");
//                }
//                else {
//                    e.preventDefault();
//                }
//            })
//
//            $(document).on("keyup", '.js-qty-selector .js-qty-selector-input-cart-det', function (e) {
//                ACC.cartitem.checkQtySelector(this, "input");
//                ACC.cartitem.updateQtyValue(this, $(this).val());
//
//            })
//
//            $(document).on("focusout", '.js-qty-selector .js-qty-selector-input-cart-det', function (e) {
//                ACC.cartitem.checkQtySelector(this, "focusout");
//                ACC.cartitem.updateQtyValue(this, $(this).val());
//            })

            $('.js-qty-selector-input-cart-det').on("blur", function (e)
            {
                ACC.cartitem.checkQtySelector(this, "input");

            }).on("keyup", function (e)
            {
                return ACC.cartitem.handleKeyEvent(this, e);
            }
            ).on("keydown", function (e)
            {
                return ACC.cartitem.handleKeyEvent(this, e);
            }
            ).on("input", function (e)
            {
                let form = $(this).closest('form');
                form.find('input[name=quantity]').val($(this).val());
            });
        }
};

$(document).ready(function() {
    $('.js-cartItemDetailBtn').click(function(event) {
        event.stopPropagation();
        var thisDetailGroup =  $(this).parent('.js-cartItemDetailGroup');
        $(thisDetailGroup).toggleClass('open'); //only in its parent
        if ( $(thisDetailGroup).hasClass('open') )  {
            //close all if not this parent
            $('.js-cartItemDetailGroup').not( thisDetailGroup ).removeClass('open');
            //change aria
            $('.js-cartItemDetailBtn').attr('aria-expanded', 'true');

        } else {
            $('.js-cartItemDetailBtn').attr('aria-expanded', 'false');
        }
        $(document).click( function(){
            $(thisDetailGroup).removeClass('open');
        }); // closes when clicking outside this div
    });

    //enable comment for this item only
    $('.js-entry-comment-button').click(function(event) {
        event.preventDefault();
        var linkID = $(this).attr('href');
        $( linkID ).toggleClass('in');
        $( thisDetailGroup ).removeClass('open');
    });
});

