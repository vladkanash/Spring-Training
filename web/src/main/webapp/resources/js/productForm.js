$(document).ready(function() {
    $('.productForm').submit(function (e) {
        e.preventDefault();
        var data = {};
        var headers = {};

        $('.error').html('');
        $.each(this, function(i, v){
            var input = $(v);
            data[input.attr("name")] = input.val();
        });

        var errors = $(this).parent('tr, div').find('.error');

        var csrfToken = $("meta[name='_csrf']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");

        headers[csrfHeader] = csrfToken;

        $.ajax({
            type: "POST",
            contentType : 'application/json; charset=utf-8',
            url: '/addToCart',
            headers: headers,
            data: JSON.stringify(data),
            dataType: 'json',
            success: function() {
                $('input.form-control, input.form-group').val('1');
                $("#cartSummary").load('/productList #cartSummary > *');
            },
            error: function(data) {
                errors.html(data.responseJSON.errors['cartItem']);
            }
        });
    });
});