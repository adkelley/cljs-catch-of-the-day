(ns app.components.notfound
  (:require [helix.core :refer [defnc]]
            [helix.dom :as d]))

(defnc NotFound []
  (d/div
   (d/h2 "Not Found")))
