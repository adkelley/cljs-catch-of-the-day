(ns app.hooks.use-firebase
  (:require [clojure.string :as str]
            [hx.hooks :as hooks]
            ["firebase/app" :as firebase-app]
            ["firebase/auth"]
            ["firebase/database"]))



(defn init-firebase
  "Initialize the firebase app using your configuration.  The configuration is assumed
   to be converted to JavaScript object format"
  [firebaseConfig]
  (if (= 0 firebase-app/apps.length)
    (firebase-app/initializeApp firebaseConfig)
    (nth firebase-app/apps 0)))

(defn- join-path [path]
  (str/join "/" (clj->js path)))

(defn- fb-ref [path]
  (.ref (.database firebase-app)
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

(defn- auth-handler
  [auth-data]
  (js/console.log "Auth Data:" auth-data))

(defn- oauth-sign-in
  [auth-provider]

  (-> (firebase-app/auth)
      (.signInWithPopup auth-provider)
      (.then #(auth-handler %))
      (.catch (println "Hello it didn't work"))))


  (defn create-auth-provider []
    (oauth-sign-in (firebase-app/auth.GithubAuthProvider.)))



    ;; (defn use-auth
    ;;   "Creates a react hook that authorizes a user to access a firebase database
    ;;    This logic was copied from "

    ;;   )
