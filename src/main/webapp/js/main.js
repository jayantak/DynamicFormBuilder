requirejs.config({
    baseUrl: '../libraries',
    paths:{
        bootstrapCollapse : 'bootstrap-collapse',
        bootstrapTab: 'bootstrap-tab',
        bootstrapTransition: 'bootstrap-transition',
        jquery: 'jquery.min',
        owlCarousel: 'owl.carousel',
        owlCarouselMin: 'owl.carousel.min',
        prettify: 'prettify',
        runPrettify: 'run_prettify'
    },
    shim: {
        jQuery:{
            exports: '$'
        }
    }
});

requirejs(["../js/HomePage"]);