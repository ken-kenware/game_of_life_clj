(ns game-of-life-clj.core
  (:gen-class))

(def grid (atom []))

(defn grid-height []
  (count @grid))

(defn grid-width []
  (count (nth @grid 0)))

(defn- at [x y]
  (-> @grid (nth x) (nth y)))

(defn alive? [[x y]]
  (= (at x y) "x"))

(defn dead? [[x y]]
  (not (alive? [x y])))

(defn- neighbors [[x y]]
  (for [dx [-1 0 1] dy [-1 0 1]
        :let [tx (+ dx x) ty (+ dy y)]
        :when (and
               (not= 0 dx dy)
               (not (neg? tx))
               (not (neg? ty))
               (< tx (grid-width))
               (< ty (grid-height)))]
    [tx ty]))

(defn- live-neighbors [[x y]]
  (for [n (neighbors [x y])
        :when (alive? n)]
    n))

(defn- evolve-dead [[x y]]
  (swap! grid assoc-in [x y] (cond
                              (= (count (live-neighbors [x y])) 3) "x"
                              :else "."
                              )))

(defn- evolve [[x y]]
  (cond
   (dead? [x y]) (evolve-dead [x y])
   ))

(defn evolve-game []
  (for [x (range (grid-width))
        y (range (grid-height))]
    (evolve [x y])))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
