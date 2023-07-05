(ns game-of-life-clojure.core-test
  (:require [clojure.test :refer :all]
            [game-of-life-clojure.core :refer :all]))

(deftest get-neighbours-test
  (testing "return 8 neighbours"
    (is (= (count (get-neighbours {:x 6 :y 5})) 8)))
  (testing "return expected neighbours"
    (is (=
         (get-neighbours {:x 6 :y 5})
         '({:y 4, :x 5}
           {:y 5, :x 5}
           {:y 6, :x 5}
           {:x 6, :y 4}
           {:x 6, :y 6}
           {:x 7, :y 4}
           {:x 7, :y 5}
           {:x 7, :y 6})))))

(deftest is-alive-test
  (testing "returns true when cell is in the world collection"
    (is (= (is-alive world {:x 6 :y 5}) true)))
  (testing "returns false when cell is not in the world collection"
    (is (= (is-alive world {:x 1 :y 1}) false))))

(deftest get-live-neighbours-count-test
  (testing "returns 3 for cell at 5 4"
    (is (= (get-live-neighbours-count world {:x 5 :y 4}) 3)))
  (testing "returns 2 for cell at 4 4"
    (is (= (get-live-neighbours-count world {:x 4 :y 4}) 2))))

(deftest next-cell-state-test
  (testing "when cell is :live and 2 neighbours return :live"
    (is (= (next-cell-state :live 2) :live)))
  (testing "when cell is :live and 3 neighbours return :live"
    (is (= (next-cell-state :live 2) :live)))
  (testing "when cell is :live and less then 2 neighbours return :dead"
    (is (= (next-cell-state :live 1) :dead)))
  (testing "when cell is :live and more then 3 neighbours return :dead"
    (is (= (next-cell-state :live 4) :dead))))

(deftest dead-neighbours-test
  (testing "returns all neighbours for all living cells in the world"
    (is (= (count (dead-neighbours world)) 12))))

(deftest tick-test
  (testing "returns all neighbours for all living cells in the world"
    (is (= (tick world) '({:x 5, :y 5} {:x 5, :y 6} {:x 5, :y 4})))))
