(ns game-of-life-clojure.gui
  (:require [io.github.humbleui.ui :as ui])
  (:require [io.github.humbleui.window :as window])
  (:require [game-of-life-clojure.core :refer :all]))

(def *window
  " State of the main window. Gets set on app startup. "
  (atom nil))

(def *app
  " Current state of what's drawn in the main app window.
Gets set any time we want to draw something new. "
  (atom nil))

(defn redraw!
  " Redraws the window with the current app state. "
  []
  ;; we redraw only when window state has been set.
  ;; this lets us call the function on ns eval and will only
  ;; redraw if the window has already been created in either
  ;; user/-main or the app -main
  (some-> *window deref window/request-frame))

(def app
  " Main app definition. "
  (ui/default-theme ; we must wrap our app in a theme
   {}
   ;; just some random stuff
   (ui/center
    (ui/label " heellooo "))))

;; reset current app state on eval of this ns
(reset! *app app)

(defn -main
  " Run once on app start, starting the humble app. "
  [& args]
  (ui/start-app!
   (reset! *window
           (ui/window
            {:title    " Editor "
             :bg-color 0xFFFFFFFF}
            *app)))
  (redraw!))
