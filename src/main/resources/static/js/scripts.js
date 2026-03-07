$(document).ready(function() {
    // Mobile menu toggle
    $('.mobile-menu-toggler').on('click', function(e) {
        e.preventDefault();
        $('.mobile-menu-body').slideToggle();
    });

    // Mobile collapse sections
    $('.mobile-collapse-header').on('click', function() {
        if ($(window).width() < 992) {
            $(this).parent().find('.mobile-collapse-body').slideToggle();
        }
    });
});
