(ns game-of-life-clojure.core)

(declare will-survive)
(declare will-revive)
(declare next-cell-state)
(declare dead-neighbours)
(declare get-live-neighbours-count)
(declare is-alive?)
(declare is-dead?)
(declare get-neighbours)

(def world
  '({:x 4, :y 5} {:x 5, :y 5} {:x 6, :y 5}))

(defn tick [world]
  (concat (filter (partial will-survive world)
                  world)
          (filter (partial will-revive world)
                  (dead-neighbours world))))

(defn will-survive [world cell]
  (= (next-cell-state :live (get-live-neighbours-count world cell)) :live))

(defn will-revive [world cell]
  (= (next-cell-state :dead (get-live-neighbours-count world cell)) :live))

(defn next-cell-state [current-state neighbours-count]
  (if (= current-state :live)
    (if (or (= 2 neighbours-count) (= 3 neighbours-count))
      :live
      :dead)
    (if (= 3 neighbours-count)
      :live
      :dead)))

(defn dead-neighbours [world]
  (filter
   (fn [cell] (is-dead? world cell))
   (set (flatten
         (map
          (fn [cell] (get-neighbours cell))
          world)))))

(defn get-live-neighbours-count [world cell]
  (count (filter (fn [neighbour] (is-alive? world neighbour))
                 (get-neighbours cell))))

(defn is-alive? [world cell]
  (some? (some (fn [c] (= c cell)) world)))

(defn is-dead? [world cell]
  (not (is-alive? world cell)))

(defn get-neighbours [{x :x y :y :as current-cell}]
  (filter (fn [cell] (not= cell current-cell))
          (concat (map (fn [i] {:y (+ y i) :x (- x 1)}) (range -1 2))
                  (map (fn [i] {:x x :y (+ y i)}) (range -1 2))
                  (map (fn [i] {:x (+ x 1) :y (+ y i)}) (range -1 2)))))

(count (get-neighbours {:x 6 :y 5}))
(is-alive? world {:x 6 :y 5})
(get-live-neighbours-count world {:x 5 :y 4})
(next-cell-state :dead (get-live-neighbours-count world {:x 2 :y 8}))
