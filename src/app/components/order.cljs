(ns app.components.order
  (:require [helix.core :refer [defnc $]]
            [helix.hooks :refer [use-state]]
            [helix.dom :as d]
            [clojure.spec.alpha :as s]
            ["react-transition-group" :refer [TransitionGroup CSSTransition]]
            ["../helpers.js" :refer (formatPrice)]))

(defnc Order [{:keys [fishes order remove-from-order]}]

  {:pre [(s/conform (s/or :map map? :nil nil?) fishes)
         (s/conform (s/or :map map? :nil nil?) order)
         s/explain (s/valid? fn? remove-from-order)]}

  (let [details #(get fishes % {})
        price (fn [id lbs] (* lbs (get (details id) :price 0)))
        name #(get (details %) :name "fish")
        is-available #(= ((details %) :status) "available")
        [inProp setInProp] (use-state "order-enter order-enter-active")
        transition-options {
                            :classNames "order"
                            :timeout {:enter 5000 :exit 5000}
                            }
        render-order (fn [id]
                       (let [lbs (order id)]
                         (if (is-available id)
                           ;; TODO: CSSTransitions not working!
                           ($ CSSTransition {:classNames "order" :key id :timeout {:enter 500 :exit 500}}
                              (d/li {:key id}
                                    (str lbs "lb. " (name id) " "
                                         (formatPrice (price id lbs)))
                                    (d/button {:onClick #(remove-from-order id)} "x")))
                           (remove-from-order id))))
        total (reduce-kv (fn [prev-total id lbs]
                           (if (is-available id)
                             (+ prev-total (price id lbs))
                             prev-total))
                         0 order)]


    (d/div {:class "order-wrap"}
           (d/h2 "Order")
           ;; [:ul {:class "order"} (map #(render-order (key %)) order)]
           ($ TransitionGroup {:component "ul" :className "order"}
              (map #(render-order (key %)) order))
           (d/div {:class "total"}
                  (d/strong (formatPrice total))))))
