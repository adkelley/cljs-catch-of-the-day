(ns app.components.edit-fish-form
  (:require
   [clojure.spec.alpha :as s]
   [hx.react :refer [defnc]]))

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

    [:div {:class "fish-edit"}
     [:input {:type "text" :name "name" :onChange #(handleChange %) :value (fish :name)}]
     [:input {:type "text" :name "price" :onChange #(handleChange %) :value (fish :price)}]
     [:select {:type "text" :name "status" :onChange #(handleChange %) :value (fish :status)}
      [:option {:onChange #(handleChange %) :value "available"} "Fresh!"]
      [:option {:onChange #(handleChange %) :value "unavailable"} "Sold Out!"]]
     [:textarea {:name "desc" :onChange #(handleChange %) :value (fish :desc)}]
     [:input {:type "text" :name "image" :onChange #(handleChange %) :value (fish :image)}]
     [:button {:onClick #(delete-fish index)} "Remove Fish"]])
  )
