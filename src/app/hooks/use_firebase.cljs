(ns app.hooks.use-firebase
  (:require [clojure.string :as str]
            [hx.hooks :as hooks]
            ["firebase/app" :as firebase-app :refer [initializeApp database]]
            ["firebase/auth" :as firebase-auth]
            ["firebase/database" :as firebase-database]
            ["firebase/database"]))



(defn init-firebase
  "Initialize the firebase app using your configuration.  The configuration is assumed
   to be converted to JavaScript object format"
  [firebaseConfig]
  (if (= 0 firebase-app/apps.length)
    (initializeApp firebaseConfig)
    (nth firebase-app/apps 0)))

(defn- join-path [path]
  (str/join "/" (clj->js path)))

(defn- fb-ref [path]
  (.ref (database)
        (join-path path)))

(defn use-value
  "Creates a React Hook that synchronizes a firebase database with the host app.
   The logic was copied from https://github.com/donavon/use-firebase-database"

  [{:keys [app path init-value]}]
  (let [[value set-value] (hooks/useState init-value)
        ref (hooks/useMemo #(fb-ref path) (clj->js [app (join-path path)]))
        set (hooks/useCallback (fn [newValue]
                                 (.set ref (clj->js newValue))) (clj->js [ref]))]

    (hooks/useEffect #(let [handler (fn [snapshot]
                                      (set-value
                                       (js->clj (.val snapshot)
                                                :keywordize-keys true)))]
                        (.on ref "value" handler)
                        (fn [] (.off ref "value" handler)))
                     (clj->js [ref]))
    [value set])
  )


(defn create-auth-provider [{:keys [app]}]
  (let [provider (js/firebase.auth.GithubAuthProvider)]
    provider)
  )



;; (defn use-auth
;;   "Creates a react hook that authorizes a user to access a firebase database
;;    This logic was copied from "

;;   )
