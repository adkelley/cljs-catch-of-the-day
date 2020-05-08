(ns app.components.fish
  (:require
   [helix.core :refer [defnc]]
   [helix.dom :as d]
   ["../helpers.js" :refer (formatPrice)]
   [clojure.spec.alpha :as s]))


(defnc Fish [{:keys [details index add-to-order ]}]

  {:pre [s/explain (s/valid? map? details)
         s/explain (s/valid? keyword? index)
         s/explain (s/valid? fn? add-to-order)]}

  (let [image (get details :image)
        name (get details :name)
        price (formatPrice (get details :price))
        desc (get details :desc)
        is-available (= (get details :status) "available")]

    (d/li {:class "menu-fish"}
          (d/img {:src image :alt name})
          (d/h3 {:class "fish-name"} name
                (d/span {:class "price"} price))
          (d/p desc)
          (d/button {:disabled (not is-available) :onClick #(add-to-order index)} (if is-available "Add To Cart" "Sold Out")))))
