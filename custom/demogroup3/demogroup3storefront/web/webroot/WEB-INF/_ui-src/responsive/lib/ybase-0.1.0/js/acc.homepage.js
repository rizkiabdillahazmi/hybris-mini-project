$(document).ready(function(){
    $('.card-product__desc div').each(function () {
        var $desc = $(this);
        var contents = $(this).contents();
        contents.each(function () {
            $desc.append($('<div>').html(this));
            $('br', $desc).remove();
            $('div:gt(2)', $desc).hide();
        })
    })
})
