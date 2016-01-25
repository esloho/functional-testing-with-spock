$(function () {

    const $cancelButton = $('#cancel-button');
    const $saveButton = $('#save-button');
    const $productForm = $('.product-form');

    $cancelButton.click(function (event) {
        event.preventDefault();
        $productForm.attr('action', '/products');
        $productForm.attr('method', 'GET');
        $productForm.submit();
    });

    $saveButton.click(function (event) {
        event.preventDefault();
        $productForm.submit();
    });

    $productForm.submit(function() {
        return true;
    })

});
