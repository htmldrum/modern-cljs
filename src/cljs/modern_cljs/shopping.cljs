(ns modern-cljs.shopping
  (:require [domina.core :refer [by-id value set-value!]]))

(def quantity-id "quantity")
(def price-id "price")
(def tax-id "tax")
(def discount-id "discount")
(def total-id "total")

(defn calculate []
  (let [quantity (value (by-id quantity-id))
        price (value (by-id price-id))
        tax (value (by-id tax-id))
        discount (value (by-id discount-id))]
    (set-value! (by-id total-id) (-> (* quantity price)
                                     (* (+ 1 (/ tax 100)))
                                     (- discount)
                                     (.toFixed 2)))
    false))

(defn init []
  (if (and js/document
           (.-getElementById js/document))
    (let [the-form (by-id "shoppingForm")]
      (set! (.-onsubmit the-form) calculate))))

(set! (.-onload js/window) init)
