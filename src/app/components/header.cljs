(ns app.components.header
  (:require [hx.react :refer [defnc]]
            [clojure.spec.alpha :as s]))

(defnc Header [{:keys [tagline]}]
  {:pre [s/explain (s/valid? string? tagline)]}

  [:header {:class "top"}
   [:h1 "Catch"
    [:span {:class "ofThe"}
     [:span {:class "of"} "Of"]
     [:span {:class "the"} "The"]]
    "Day"]
   [:h3 {:class "tagline"}
    [:span tagline]]]
  )
