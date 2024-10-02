
(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner(0);


    // Fixed Navbar
    $(window).scroll(function () {
        if ($(window).width() < 992) {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow');
            } else {
                $('.fixed-top').removeClass('shadow');
            }
        } else {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow').css('top', -55);
            } else {
                $('.fixed-top').removeClass('shadow').css('top', 0);
            }
        }
    });


    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({ scrollTop: 0 }, 1500, 'easeInOutExpo');
        return false;
    });


    // Testimonial carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 2000,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav: true,
        navText: [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0: {
                items: 1
            },
            576: {
                items: 1
            },
            768: {
                items: 1
            },
            992: {
                items: 2
            },
            1200: {
                items: 2
            }
        }
    });


    // vegetable carousel
    $(".vegetable-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1500,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav: true,
        navText: [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0: {
                items: 1
            },
            576: {
                items: 1
            },
            768: {
                items: 2
            },
            992: {
                items: 3
            },
            1200: {
                items: 4
            }
        }
    });


    // Modal Video
    $(document).ready(function () {
        var $videoSrc;
        $('.btn-play').click(function () {
            $videoSrc = $(this).data("src");
        });

        $('#videoModal').on('shown.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc + "?autoplay=1&amp;modestbranding=1&amp;showinfo=0");
        })

        $('#videoModal').on('hide.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc);
        })
    });


    // Product Quantity
    $('.quantity button').on('click', function () {
        var button = $(this);
        var changer = 0;
        var oldValue = button.parent().parent().find('input').val();

        const id = button.parent().parent().find('input').attr('data-product-id')
        const inputValueItem = $(`input[data-product-id='${id}']`)
        console.log(inputValueItem)
        const priceOfProduct = button.parent().parent().find('input').attr('data-price')
        const totalId = $(`p[data-total-id='${id}']`);
        const quantityForm = $('.quantity_form')
        const quantity_dynamic = $(`input[quantity-dynamic-id='${id}']`)
        const totalPriceCheckOut = $(".totalPrice")
        console.log(totalPriceCheckOut)

        if (button.hasClass('btn-plus')) {
            var newVal = parseFloat(oldValue) + 1;
            changer = 1;
        } else {
            if (oldValue > 1) {
                var newVal = parseFloat(oldValue) - 1;
                changer = -1;
            } else {
                newVal = 1;
                changer = 0;
            }
        }
        button.parent().parent().find('input').val(newVal);
        quantityForm.val(newVal);
        var a = priceOfProduct * newVal;
        totalId.text(formatCurrency(a))

        const totalPrice = $('p[data-total-cart-price]')
        let currenctPrice = $('p[data-total-cart-price]').first().attr('data-total-cart-price');
        let newTotal = +currenctPrice
        if (changer === 0) {
            newTotal = +currenctPrice
        } else {
            newTotal = changer * (+priceOfProduct) + (+currenctPrice)
        }

        changer = 0;

        totalPrice?.each((index, item) => {
            $(totalPrice[index]).text(formatCurrency(newTotal))

            $(totalPrice[index]).attr('data-total-cart-price', newTotal)
        })
        quantity_dynamic.val(inputValueItem.val())
        totalPriceCheckOut.val(newTotal)
    });



    function formatCurrency(number) {
        return new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND',
            minimumFractionDigits: 0,
            maximumFractionDigits: 0
        }).format(number).replace(/\s?₫/, 'đ'); // Thay thế "₫" bằng "đ"
    }

    // Sử dụng hàm
    let number = 49990000;
    console.log(formatCurrency(number)); // 49.990.000đ


})(jQuery);


