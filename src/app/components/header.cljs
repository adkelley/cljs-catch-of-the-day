(ns app.components.header
  (:require [hx.react :refer [defnc]]))

(defnc Header [{:keys [tagline]}]
  [:header {:class "top"}
   [:h1 "Catch"
    [:span {:class "ofThe"}
     [:span {:class "of"} "Of"]
     [:span {:class "the"} "The"]]
    "Day"]
   [:h3 {:class "tagline"}
    [:span tagline]]]
  )
