(ns app.main
  (:require [hx.react :as hx]
            [react-dom :as react-dom]
            [app.components.router :refer [Router]]
            ))


(defn main! []
  (react-dom/render
   (hx/f [Router])
   (. js/document getElementById "main")))
