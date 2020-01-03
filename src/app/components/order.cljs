(ns app.components.order
  (:require [hx.react :refer [defnc]]
            [hx.hooks :refer [useState]]
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
        [inProp setInProp] (useState "order-enter order-enter-active")
        transition-options {
                            :classNames "order"
                            :timeout {:enter 5000 :exit 5000}
                            }
        render-order (fn [id]
                       (let [lbs (order id)]
                         (if (is-available id)
                           [CSSTransition {:classNames "order" :key id :timeout {:enter 500 :exit 500}}
                            [:li {:key id}
                             (str lbs "lb. " (name id) " "
                                  (formatPrice (price id lbs)))
                             [:button {:onClick #(remove-from-order id)} "x"]]]
                           (remove-from-order id))))
        total (reduce-kv (fn [prev-total id lbs]
                           (if (is-available id)
                             (+ prev-total (price id lbs))
                             prev-total))
                         0 order)]


    [:div {:class "order-wrap"}
     [:h2 "Order"]
     ;; [:ul {:class "order"} (map #(render-order (key %)) order)]
     [TransitionGroup {:component "ul" :className "order"}
      (map #(render-order (key %)) order)]
     [:div {:class "total"}
      [:strong (formatPrice total)]]]))
