(ns app.components.inventory
  (:require
   [helix.core :refer [defnc $]]
   [helix.dom :as d]
   [clojure.spec.alpha :as s]
   [app.hooks.use-firebase :refer [create-auth-provider]]
   [app.components.add-fish-form :refer [AddFishForm]]
   [app.components.edit-fish-form :refer [EditFishForm]]
   [app.components.login :refer [Login]]))

(defnc Inventory [{:keys [add-fish load-sample-fishes fishes
                          update-fish delete-fish fb-app]}]

  {:pre [s/explain (s/valid? fn? add-fish)
         s/explain (s/valid? fn? load-sample-fishes)
         s/explain (s/valid? fn? update-fish)
         s/explain (s/valid? fn? delete-fish)
         (s/conform (s/or :map map? :nil nil?) fishes)
         ]}

  ($ Login {:authenticate #(create-auth-provider)})

  (d/div {:class "inventory"}
         (d/h2 "Inventory")
         (for [x fishes]
           ($ EditFishForm {:fish (val x) :index (key x)
                            :update-fish update-fish :delete-fish delete-fish}))
         ($ AddFishForm {:add-fish add-fish})
         (d/button {:on-click load-sample-fishes} "Load Sample Fishes"))
  )
