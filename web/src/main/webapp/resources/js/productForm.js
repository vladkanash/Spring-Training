$(document).ready(function() {
    $('.productForm').submit(function (e) {
        e.preventDefault();
        var data = {};

        $('.error').html('');
        $.each(this, function(i, v){
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        var errors = $('input#' + data['productKey']).siblings('.error');

        $.ajax({
            type: "POST",
            contentType : 'application/json; charset=utf-8',
            url: '/addToCart',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function(data) {
                if(data.validationStatus === "SUCCESS") {
                    $('input.form-control, input.form-group').val('0');
                    $("#cartSummary").load('/productList #cartSummary > *');
                } else {
                    errors.html(data.errors['productForm']);
                }
            }
        });
    })
});