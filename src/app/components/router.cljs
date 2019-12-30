(ns app.components.router
  (:require [hx.react :refer [defnc]]
            [react-router-dom :refer [BrowserRouter Route Switch]]
            [app.components.store-picker :refer [StorePicker]]
            [app.components.app :refer [App]]
            [app.components.notfound :refer [NotFound]]))


(defnc Router []
  [BrowserRouter
   [Switch
    [Route {:exact true :path "/" :component StorePicker}]
    [Route {:path "/store/:storeId" :component App}]
    [Route {:component NotFound}]]])
