(ns game-of-life-clojure.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Helloooo, World!"))

(-main)

(defn next-cell-state [current-state neighbours-count]
  (if (= current-state :live)
    (if (or (= 2 neighbours-count) (= 3 neighbours-count))
      :live
      :dead)
    (if (= 3 neighbours-count)
      :live
      :dead)))

(next-cell-state :dead 1)

(def world
  '({:x 4, :y 5} {:x 5, :y 5} {:x 6, :y 5}))

(defn get-live-neighbours-count [world cell])

(defn get-neighbours [x y]
  (concat (map (fn [i] {:y (+ y i) :x (- x 1)}) (range -1 2))
          (map (fn [i] {:x x :y (+ y i)}) (range -1 2))
          (map (fn [i] {:x (+ x 1) :y (+ y i)}) (range -1 2))))

(count (get-neighbours 4 5))

(defn is-alive [world x y]
  (some (fn [item] (= item {:x x :y y})) world))

(is-alive world 5 5)

(defn render [world]
  (print "   ")
  (dotimes [n 10] (print n " "))
  (println)
  (dotimes [x 10]
    (print x "")
    (dotimes [y 10]
      (if (is-alive world x y)
        (print " x ")
        (print " o ")))
    (println)))

(render world)
(render (concat (get-neighbours 4 5) world))

