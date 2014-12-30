(ns game-of-life-clj.core
  (:gen-class)
  (:require [clojure.set :as set]))

(def next-state (atom []))

(defn grid-height [grid]
  (count grid))

(defn grid-width [grid]
  (count (first grid)))

(defn- at [grid x y]
  (-> grid (nth x) (nth y)))

(defn alive? [grid [x y]]
  (= (at grid x y) "x"))

(defn dead? [grid [x y]]
  (not (alive? grid [x y])))

(defn- neighbors [grid [x y]]
  (for [dx [-1 0 1] dy [-1 0 1]
        :let [tx (+ dx x) ty (+ dy y)]
        :when (and
               (not= 0 dx dy)
               (not (neg? tx))
               (not (neg? ty))
               (< tx (grid-width grid))
               (< ty (grid-height grid))
               )]
    [tx ty]))

(defn- live-neighbors [grid [x y]]
  (for [n (neighbors grid [x y])
        :when (alive? grid n)]
    n))

(defn evolve-dead [grid [x y]]
  (swap! next-state assoc-in [x y] (if (= (count (live-neighbors grid [x y])) 3)
                                     "x"
                                     "."
                                     )))

(defn evolve-alive [grid [x y]]
  (swap! next-state assoc-in [x y]
         (cond
          (< (count (live-neighbors grid [x y])) 2) "."
          :else "x"
          )))

(defn evolve [grid [x y]]
  (if (dead? grid [x y])
    (evolve-dead grid [x y])
    (evolve-alive grid [x y])
    ))

(defn evolve-game [grid]
  (reset! next-state grid)
  (vec
   (for [x (range (count (first grid)))
         y (range (count grid))]
     (evolve grid [x y]))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
