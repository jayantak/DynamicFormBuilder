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
        runPrettify: 'run_prettify',
        FormAdd: '../js/FormAdd',
        FormSubmitted: '../js/FormSubmitted',
        HomePage: '../js/HomePage',
        HomePageFunctions: '../js/HomePageFunctions',
        TableAdd: '../js/TableAdd',
        ObjectCompare: '../js/test/ObjectCompare',
        createForm: '../js/createForm',
        createFormAdd: '../js/createFormAdd',
        currentFormJSON: '../js/currentFormJSON',
        currentForm: '../js/currentForm'
    },
    shim: {
        jQuery:{
            exports: '$'
        },
        FormAdd: {
            deps: ['jquery']
        },
        FormSubmitted: {
            deps: ['jquery']
        },
        HomePage: {
            deps: ['jquery']
        },
        HomePageFunctions: {
            deps: ['jquery']
        },
        TableAdd: {
            deps: ['jquery']
        },
        createForm: {
            deps: ['jquery']
        },
        createFormAdd: {
            deps: ['jquery']
        }
    }
});

requirejs(["../js/currentFormJSON"]);
