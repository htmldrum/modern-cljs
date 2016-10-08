(ns modern-cljs.shopping
  (:require [cljs.reader :refer [read-string]]
            [domina.core :refer [append!
                                 by-class
                                 by-id
                                 destroy!
                                 value
                                 set-value!]]
            [domina.events :refer [listen!]]
            [hiccups.runtime]
            [shoreleave.remotes.http-rpc :refer [remote-callback]])
  (:require-macros [hiccups.core :refer [html]]
                   [shoreleave.remotes.macros :as macros]))

(def quantity-id "quantity")
(def price-id "price")
(def tax-id "tax")
(def discount-id "discount")
(def total-id "total")

(defn calculate []
  (let [quantity (read-string (value (by-id quantity-id)))
        price (read-string (value (by-id price-id)))
        tax (read-string (value (by-id tax-id)))
        discount (read-string (value (by-id discount-id)))]
    (remote-callback :calculate
                     [quantity price tax discount]
                     #(set-value! (by-id "total") (.toFixed % 2)))))

(defn ^:export init []
  (when (and js/document
             (.-getElementById js/document))
    (listen! (by-id "calc")
             :click
             calculate)
    (listen! (by-id "calc")
             :mouseover
             (fn []
               (append! (by-id "shoppingForm")
                        (html [:div.help "Click to eat my nut!"]))))
    (listen! (by-id "calc")
             :mouseout
             (fn []
               (destroy! (by-class "help"))))))
