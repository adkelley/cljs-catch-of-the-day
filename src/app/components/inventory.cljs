(ns app.components.inventory
  (:require [hx.react :refer [defnc]]
            [clojure.spec.alpha :as s]
            [app.components.add-fish-form :refer [AddFishForm]]
            [app.components.edit-fish-form :refer [EditFishForm]]))

(defnc Inventory [{:keys [add-fish load-sample-fishes fishes
                          update-fish delete-fish]}]

  {:pre [s/explain (s/valid? fn? add-fish)
         s/explain (s/valid? fn? load-sample-fishes)
         s/explain (s/valid? fn? update-fish)
         s/explain (s/valid? fn? delete-fish)
         s/explain (s/valid? map? fishes)
         ]}


  [:div {:class "inventory"}
   [:h2 "Inventory"]
   (for [x fishes]
     [EditFishForm {:fish (val x) :index (key x)
                    :update-fish update-fish :delete-fish delete-fish}])
   [AddFishForm {:add-fish add-fish}]
   [:button {:on-click load-sample-fishes} "Load Sample Fishes"]
   ])
