(ns app.main
  (:require [helix.core :refer [defnc $]]
            [react-dom :as react-dom]
            [app.components.router :refer [Router]]
            ))


(defn main! []
  (react-dom/render
   ($ Router)
   (. js/document getElementById "main")))
