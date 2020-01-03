(ns app.components.login
  (:require
   [hx.react :as hx :refer [defnc]]
   [clojure.spec.alpha :as s]))

(defnc Login [{:keys [authenticate]}]
  {:pre [(s/valid? fn? authenticate)]}

  [:nav {:class "login"}
   [:h2 "Inventory Login"]
   [:p "Sign in to manage your store's inventory."]
   [:button {:class "github"
             :onClick #(authenticate "github")}
    "Login with Github"]])
