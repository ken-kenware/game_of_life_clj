;; (ns step-definitions.game-of-life-steps)
(use 'clojure.test)
;; (require game_of_life_clj.core :refer :all)

(require '[game-of-life-clj.core :refer :all])

(Given #"^nothing$" [])

(Then #"^I should get hello world$" []
      (is (= "hello_world" (hello_world))))
