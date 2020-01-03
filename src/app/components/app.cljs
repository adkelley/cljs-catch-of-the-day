(ns app.components.app
  (:require [hx.react :as hx :refer [defnc]]
            [hx.hooks :as hooks :refer [useState]]
            [app.components.header :refer [Header]]
            [app.components.inventory :refer [Inventory]]
            [app.components.order :refer [Order]]
            [app.components.fish :refer [Fish]]
            [app.hooks.use-firebase :refer [init-firebase use-value]]
            [app.hooks.use-local-storage :refer [use-local-storage]]
            ["../sample-fishes.js" :refer [sampleFishes]]
            ["../credentials.js" :refer [firebaseConfig]]
            ))

(defnc App [{:keys [match]}]
  (let [store-id (.. match -params -storeId)
        path [store-id "fishes"]
        fb-app (init-firebase firebaseConfig)
        [fishes set-fishes] (use-value {:app fb-app :path path :init-value {}})
        [order set-order] (use-local-storage {:id store-id :initial-value {}})
        add-fish #(set-fishes (conj fishes %))
        update-fish #(set-fishes (assoc fishes %1 %2))
        load-sample-fishes #(set-fishes (js->clj sampleFishes :keywordize-keys true))
        add-to-order #(set-order (assoc order % (inc (get order % 0))))
        remove-from-order #(set-order (dissoc order %))
        delete-fish #(set-fishes (dissoc fishes %))]


    [:div {:class "catch-of-the-day"}
     [:div {:class "menu"}
      [Header {:tagline "Fresh Seafood Market"}]
      (for [x fishes]
        [Fish {:details (val x) :index (key x) :add-to-order add-to-order}])]
     [Order {:fishes fishes :order order :remove-from-order remove-from-order}]
     [Inventory {:add-fish add-fish
                 :load-sample-fishes load-sample-fishes
                 :fishes fishes
                 :update-fish update-fish
                 :delete-fish delete-fish
                 :fb-app fb-app}]]))
