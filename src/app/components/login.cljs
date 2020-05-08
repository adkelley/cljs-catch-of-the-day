(ns app.components.login
  (:require
   [helix.core :refer [defnc]]
   [helix.dom :as d]
   [clojure.spec.alpha :as s]))

(defnc Login [{:keys [authenticate]}]
  {:pre [(s/valid? fn? authenticate)]}

  (d/nav {:class "login"}
         (d/h2 "Inventory Login")
         (d/p "Sign in to manage your store's inventory.")
         (d/button {:class "github"
                    :onClick #(authenticate)}
                   "Login with Github")))
