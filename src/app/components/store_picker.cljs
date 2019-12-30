(ns app.components.store-picker
  (:require [hx.react :refer [defnc]]
            ["../helpers.js" :refer (getFunName)]))

(defn goto-store [e fun-name history]
  ;; 1. Stop the form from submitting
  (.preventDefault e)
  ;; 2. Change the page to /store/whatever-they-entered
  (.push history (str "/store/" fun-name))
  )

(defnc StorePicker [{:keys [history]}]
  (let [fun-name (getFunName)]
    [:form {:class "store-selector" :onSubmit (fn [e] (goto-store e fun-name history))}
     [:h2 "Enter A Store"]
     [:input {:type "text" :required true :placeholder "Store Name" :default-value fun-name}]
     [:button {:type "submit"} "Visit Store ->"]]))
