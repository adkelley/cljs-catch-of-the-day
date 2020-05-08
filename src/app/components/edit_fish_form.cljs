(ns app.components.edit-fish-form
  (:require [clojure.spec.alpha :as s]
            [helix.core :refer [defnc]]
            [helix.dom :as d]))

(defnc EditFishForm [{:keys [index fish update-fish delete-fish]}]

  {:pre [s/explain (s/valid? map? fish)
         s/explain (s/valid? keyword? index)
         s/explain (s/valid? fn? update-fish)
         s/explain (s/valid? fn? delete-fish)]}

  (let [handleChange (fn [e]
                       (let [name_ (.. e -currentTarget -name)
                             value (.. e -currentTarget -value)
                             updated-fish (assoc fish name_ value)]
                         (update-fish index updated-fish)))]

    (d/div {:class "fish-edit"}
           (d/input {:type "text" :name "name" :onChange #(handleChange %) :value (fish :name)})
           (d/input {:type "text" :name "price" :onChange #(handleChange %) :value (fish :price)})
           (d/select {:type "text" :name "status" :onChange #(handleChange %) :value (fish :status)}
                     (d/option {:onChange #(handleChange %) :value "available"} "Fresh!")
                     (d/option {:onChange #(handleChange %) :value "unavailable"} "Sold Out!"))
           (d/textarea {:name "desc" :onChange #(handleChange %) :value (fish :desc)})
           (d/input {:type "text" :name "image" :onChange #(handleChange %) :value (fish :image)})
           (d/button {:onClick #(delete-fish index)} "Remove Fish"))))
