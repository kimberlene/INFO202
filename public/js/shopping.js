/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

"use strict";
class SaleItem {

    constructor(product, quantity) {
        // only set the fields if we have a valid product
        if (product) {
            this.product = product;
            this.quantityPurchased = quantity;
            this.salePrice = product.listPrice;
        }
    }

    getItemTotal() {
        return this.salePrice * this.quantityPurchased;
    }

}

class ShoppingCart {

    constructor() {
        this.saleItems = new Array();
    }

    reconstruct(sessionData) {
        for (let item of sessionData.saleItems) {
            this.addItem(Object.assign(new SaleItem(), item));
        }
    }

    getItems() {
        return this.saleItems;
    }

    addItem(item) {
        this.saleItems.push(item);
    }

    setCustomer(customer) {
        this.customer = customer;
    }

    getTotal() {
        let total = 0;
        for (let item of this.saleItems) {
            total += item.getItemTotal();
        }
        return total;
    }

}

// create a new module, and load the other pluggable modules
var module = angular.module('ShoppingApp', ['ngResource', 'ngStorage']);

module.factory('productDAO', function ($resource) {
    return $resource('/api/products/:id');
});

module.factory('categoryDAO', function ($resource) {
    return $resource('/api/categories/:cat');
});

module.factory('registerDAO', function ($resource) {
    return $resource('/api/register');
});

module.factory('signInDAO', function ($resource) {
    return $resource('/api/customers/:username');
});

module.factory('cart', function ($sessionStorage) {
    let cart = new ShoppingCart();

    // is the cart in the session storage?
    if ($sessionStorage.cart) {

        // reconstruct the cart from the session data
        cart.reconstruct($sessionStorage.cart);
    }

    return cart;
});

module.factory('saleDAO', function ($resource) {
    return $resource('/api/sales');
});


module.controller('ProductController', function (productDAO, categoryDAO) {
    this.products = productDAO.query();
    this.categories = categoryDAO.query();

    this.selectCategory = function (selectedCat) {
        this.products = categoryDAO.query({"cat": selectedCat});
    };

    this.allProducts = function (pro) {
        this.products = productDAO.query();
    };
});

module.controller('CustomerController', function (registerDAO, signInDAO, $sessionStorage, $window) {
    this.registerCustomer = function (customer) {
        registerDAO.save(null, customer);
        console.log(customer);
    };
    this.signInMessage = "Please sign in to continue.";


    // alias 'this' so that we can access it inside callback functions
    let ctrl = this;
    this.signIn = function (username, password) {
        // get customer from web service
        signInDAO.get({'username': username},
                // success
                        function (customer) {
                            // also store the retrieved customer
                            $sessionStorage.customer = customer;
                            // redirect to home
                            $window.location.href = '.';
                        },
                        // fail
                                function () {
                                    ctrl.signInMessage = 'Sign in failed. Please try again.';
                                }
                        );
                    };

            this.checkSignIn = function () {
                if ($sessionStorage.customer) {
                    ctrl.signedIn = true;
                    ctrl.welcome = "Welcome " + $sessionStorage.customer.firstname;
                } else {
                    ctrl.signedIn = false;
                }
            };
            
            this.signOut = function() {
                $sessionStorage.$reset();
                 $window.location.href = 'index.html';
            };

        });

module.controller('ShoppingCartController', function (cart, $sessionStorage, $window, saleDAO) {
    this.items = cart.getItems();
    this.total = cart.getTotal();
    this.selectedProduct = $sessionStorage.selectedProduct;

    this.display = function (product) {
        $sessionStorage.selectedProduct = product;
        $window.location.href = 'cart.html';
    };
    
    this.addToCart = function (quantity) {
        let product = $sessionStorage.selectedProduct;
        let saleItem = new SaleItem(product, quantity);
        cart.addItem(saleItem);
        $sessionStorage.cart = cart;
        $window.location.href = 'products.html';
    };
    
    this.checkOut = function() {
        cart.setCustomer($sessionStorage.customer);
        saleDAO.save(cart);
        
        delete $sessionStorage.cart;
        $window.location.href = 'orderConfirmation.html';
        
    };
});