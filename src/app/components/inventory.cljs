(ns app.components.inventory
  (:require
   [hx.react :refer [defnc]]
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

  (let [authenticate #(js/alert %)
        provider (create-auth-provider {:app fb-app })]
    [Login {:authenticate authenticate}])


  ;; [:div {:class "inventory"}
  ;;  [:h2 "Inventory"]
  ;;  (for [x fishes]
  ;;    [EditFishForm {:fish (val x) :index (key x)
  ;;                   :update-fish update-fish :delete-fish delete-fish}])
  ;;  [AddFishForm {:add-fish add-fish}]
  ;;  [:button {:on-click load-sample-fishes} "Load Sample Fishes"]]
  )
