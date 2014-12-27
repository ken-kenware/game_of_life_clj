(require '[game-of-life-clj.core :refer :all]
         '[clojure.test :refer :all])

(Given #"^the following setup$" [table]
       (let [data (map #(into [] %) (.raw table))]
         (reset! grid data)))

(When #"^I evolve the board$" []
      (evolve-game))

(Then #"^the center cell should be dead$" []
      (let [x (/ (grid-width) 2)
            y (/ (grid-height) 2)]
        (is (dead? [x y]))))
