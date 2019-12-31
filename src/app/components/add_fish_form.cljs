(ns app.components.add-fish-form
  (:require [hx.react :refer [defnc]]
            [hx.hooks :refer [useIRef]]
            [clojure.spec.alpha :as s]
            ["../helpers.js" :refer (parsePrice)]))

(defnc AddFishForm [{:keys [add-fish]}]

  {:pre [s/explain (s/valid? fn? add-fish)]}

  (let [name-ref  (useIRef nil)
        price-ref (useIRef nil)
        status-ref (useIRef nil)
        desc-ref (useIRef nil)
        image-ref (useIRef nil)
        create-fish (fn [e]
                      (.preventDefault e)
                      (add-fish
                       (let [fish-id (keyword (str "fish" (.now js/Date)))]
                         {fish-id {:name   (.. name-ref -current -value)
                                   :price (parsePrice (.. price-ref -current -value))
                                   :status (.. status-ref -current -value)
                                   :desc   (.. desc-ref -current -value)
                                   :image (.. image-ref -current -value)
                                   }
                          }
                         ))
                      (.reset (.-currentTarget e)))
        ]
    [:form {:class "fish-edit" :onSubmit (fn [e] (create-fish e))}
     [:input {:name "name" :ref name-ref :type "text" :placeholder "Name"}]
     [:input {:name "price" :ref price-ref :type "text" :placeholder "Price"}]
     [:select {:name "status" :ref status-ref}
      [:option {:value "available"} "Fresh!"]
      [:option {:value "unavailable"} "Sold Out!"]]
     [:textarea {:name "desc" :ref desc-ref :placeholder "Desc"}]
     [:input {:name "image" :ref image-ref :type "text" :placeholder "Image"}]
     [:button {:type "submit"} "+ Add Fish"]]))
