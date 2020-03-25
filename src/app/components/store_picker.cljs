(ns app.components.store-picker
  (:require [helix.core :refer [defnc]]
            [helix.dom :as d]
            ["../helpers.js" :refer (getFunName)]))

(defn goto-store [e fun-name history]
  ;; 1. Stop the form from submitting
  (.preventDefault e)
  ;; 2. Change the page to /store/whatever-they-entered
  (.push history (str "/store/" fun-name))
  )

(defnc StorePicker [{:keys [history]}]
  (let [fun-name (getFunName)]
    (d/form {:class "store-selector" :onSubmit (fn [e] (goto-store e fun-name history))}
            (d/h2 "Enter A Store")
            (d/input {:type "text" :required true :placeholder "Store Name" :default-value fun-name})
            (d/button {:type "submit"} "Visit Store ->"))))
