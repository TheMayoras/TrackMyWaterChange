"use strict";

angular
    .module('app')
    .config([ "$routeProvider",
          function config($routeProvider) {
            $routeProvider
                .when('/', {
                    template: "<index></index>"
                })
                .otherwise("/");
          },
    ]);

