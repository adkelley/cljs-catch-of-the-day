(ns app.components.inventory
  (:require [hx.react :refer [defnc]]
            [app.components.add-fish-form :refer [AddFishForm]]
            [app.components.edit-fish-form :refer [EditFishForm]]))

(defnc Inventory [{:keys [store-id add-fish
                          load-sample-fishes fishes
                          update-fish delete-fish]}]
  [:div {:class "inventory"}
   [:h2 "Inventory"]
   (for [x fishes]
     [EditFishForm {:fish (val x) :index (key x)
                    :update-fish update-fish :delete-fish delete-fish}])
   [AddFishForm {:add-fish add-fish}]
   [:button {:on-click load-sample-fishes} "Load Sample Fishes"]
   ])
