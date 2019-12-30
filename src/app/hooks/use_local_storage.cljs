(ns app.hooks.use-local-storage
  (:require [hx.hooks :refer [useState]]))

(defn use-local-storage
  "A React hook for storing the order in local storage.  See Video 19"
  [{:keys [id initial-state]}]

  (let [[state set-inner-state](useState (try
                                           (let [existing-state (.getItem js/localStorage id)]
                                             (if existing-state
                                               (js->clj (.parse js/JSON existing-state) :keywordize-keys true)
                                               initial-state))
                                           (catch js/Error e
                                             initial-state)))
        setState (fn [state]
                   (set-inner-state state)
                   (.setItem js/localStorage id (.stringify js/JSON (clj->js state))))]
    [state setState]))
