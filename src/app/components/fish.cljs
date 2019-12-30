(ns app.components.fish
  (:require [hx.react :as hx :refer [defnc]]
            ["../helpers.js" :refer (formatPrice)]))


(defnc Fish [{:keys [details index add-to-order ]}]
  (let [image (get details :image)
        name (get details :name)
        price (formatPrice (get details :price))
        desc (get details :desc)
        is-available (= (get details :status) "available")]
    [:li {:class "menu-fish"}
     [:img {:src image :alt name}]
     [:h3 {:class "fish-name"} name
      [:span {:class "price"} price]]
     [:p desc]
     [:button {:disabled (not is-available) :onClick #(add-to-order index)} (if is-available "Add To Cart" "Sold Out")]]))
