(require '[game-of-life-clj.core :refer :all]
         '[clojure.test :refer :all]
         '[clojure.math.numeric-tower :as math])

(def gr (atom []))

(Given #"^the following setup$" [table]
       (let [data (map #(into [] %) (.raw table))]
         (reset! gr (vec data))))

(When #"^I evolve the board$" []
      (evolve-game @gr))

(Then #"^the center cell should be \"([^\"]*)\"$" [state]
      (let [x (math/floor (/ (grid-width @next-state) 2.0))
            y (math/floor (/ (grid-height @next-state) 2.0))]
        (if (= state "dead")
          (is (dead? @next-state [x y]))
          (is (alive? @next-state [x y]))))
      )
