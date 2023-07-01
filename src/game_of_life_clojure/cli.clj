(ns game-of-life-clojure.cli
  (:gen-class)
  (:require [game-of-life-clojure.core :refer :all]))

(defn render [world]
  (print "   ")
  (dotimes [n 10] (print n " "))
  (println)
  (dotimes [y 10]
    (print y "")
    (dotimes [x 10]
      (if (is-alive world x y)
        (print " x ")
        (print " o ")))
    (println)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (render world)
  (render (tick world)))
