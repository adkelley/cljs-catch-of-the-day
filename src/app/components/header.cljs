(ns app.components.header
  (:require [helix.core :refer [defnc]]
            [helix.dom :as d]
            [clojure.spec.alpha :as s]))

(defnc Header [{:keys [tagline]}]
  {:pre [s/explain (s/valid? string? tagline)]}

  (d/header {:class "top"}
            (d/h1 "Catch"
                  (d/span {:class "ofThe"}
                          (d/span {:class "of"} "Of")
                          (d/span {:class "the"} "The"))
                  "Day")
            (d/h3 {:class "tagline"}
                  (d/span tagline))))
