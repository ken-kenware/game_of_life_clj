(require '[game-of-life-clj.core :refer :all]
         '[clojure.test :refer :all]
         '[clojure.math.numeric-tower :as math])

(def gr (atom []))

(Given #"^the following setup$" [board]
       (let [data (vec (map #(into [] %) (.raw board)))]
         (reset! gr data)))

(When #"^I evolve the board$" []
      (evolve-game @gr)
      (reset! gr @next-state))

(Then #"^the center cell should be \"([^\"]*)\"$" [state]
      (let [x (math/floor (/ (grid-width @next-state) 2.0))
            y (math/floor (/ (grid-height @next-state) 2.0))]
        (if (= state "dead")
          (is (dead? @next-state [x y]))
          (is (alive? @next-state [x y]))))
      )

(Then #"^I should see the following board$" [board]
      (let [expected (vec (map #(into [] %) (.raw board)))]
        (is (= expected @next-state))))
