var allTestFiles = [];
var TEST_REGEXP = /(spec|test)\.js$/i;

Object.keys(window.__karma__.files).forEach(function(file) {
  if (TEST_REGEXP.test(file)) {
    // Normalize paths to RequireJS module names.
    allTestFiles.push(file);
  }
});

require.config({
    // Karma serves files under /base, which is the basePath from your config file
    baseUrl: 'src/main/webapp/libraries',
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
            exports: 'jquery'
        },
        validate: {
            deps: ['jquery']
        }
    },

    // dynamically load all test files
    deps: allTestFiles,

    // we have to kickoff jasmine, as it is asynchronous
    callback: window.__karma__.start
});
