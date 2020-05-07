(ns app.components.add-fish-form
  (:require [helix.core :refer [defnc]]
            [helix.dom :as d]
            [helix.hooks :refer [use-ref]]
            [clojure.spec.alpha :as s]
            ["../helpers.js" :refer (parsePrice)]))

(defnc AddFishForm [{:keys [add-fish]}]

  {:pre [s/explain (s/valid? fn? add-fish)]}

  (let [name-ref  (use-ref nil)
        price-ref (use-ref nil)
        status-ref (use-ref nil)
        desc-ref (use-ref nil)
        image-ref (use-ref nil)
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
    (d/form {:class "fish-edit" :onSubmit (fn [e] (create-fish e))}
            (d/input {:name "name" :ref name-ref :type "text" :placeholder "Name"})
            (d/input {:name "price" :ref price-ref :type "text" :placeholder "Price"})
            (d/select {:name "status" :ref status-ref}
                      (d/option {:value "available"} "Fresh!")
                      (d/option{:value "unavailable"} "Sold Out!"))
            (d/textarea {:name "desc" :ref desc-ref :placeholder "Desc"})
            (d/input {:name "image" :ref image-ref :type "text" :placeholder "Image"})
            (d/button {:type "submit"} "+ Add Fish"))))
