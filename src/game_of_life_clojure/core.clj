(ns game-of-life-clojure.core)

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

(defn get-neighbours [x y]
  (filter (fn [cell] (not (= cell {:x x :y y})))
          (concat (map (fn [i] {:y (+ y i) :x (- x 1)}) (range -1 2))
                  (map (fn [i] {:x x :y (+ y i)}) (range -1 2))
                  (map (fn [i] {:x (+ x 1) :y (+ y i)}) (range -1 2)))))

(defn is-alive [world x y]
  (some? (some (fn [item] (= item {:x x :y y})) world)))

(defn get-live-neighbours-count [world {x :x y :y}]
  (count (filter (fn [{x :x y :y}] (is-alive world x y))
                 (get-neighbours x y))))

(count (get-neighbours 6 5))
(is-alive world 6 5)
(get-live-neighbours-count world {:x 5 :y 4})
(next-cell-state :dead (get-live-neighbours-count world {:x 2 :y 8}))

(defn dead-neighbours [world]
  (filter
   (fn [{x :x y :y}] (not (is-alive world x y)))
   (set (flatten
         (map
          (fn [{x :x y :y}] (get-neighbours x y))
          world)))))

(defn tick [world]
  (concat (filter (fn [cell] (= (next-cell-state :live (get-live-neighbours-count world cell)) :live))
                  world)
          (filter (fn [cell] (= (next-cell-state :dead (get-live-neighbours-count world cell)) :live))
                  (dead-neighbours world))))
