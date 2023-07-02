(ns game-of-life-clojure.gui
  (:require [io.github.humbleui.ui :as ui])
  (:require [game-of-life-clojure.state :as state])
  (:require [game-of-life-clojure.core :refer :all]))

(def app
  " Main app definition. "
  (ui/default-theme ; we must wrap our app in a theme
   {}
   ;; just some random stuff
   (ui/center
    (ui/label " helloooo "))))

;; reset current app state on eval of this ns
(reset! state/*app app)

(defn -main
  " Run once on app start, starting the humble app. "
  [& args]
  (ui/start-app!
   (reset! state/*window
           (ui/window
            {:title    " Editor "
             :bg-color 0xFFFFFFFF}
            state/*app)))
  (state/redraw!))
