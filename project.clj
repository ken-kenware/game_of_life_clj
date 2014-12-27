(defproject game_of_life_clj "0.1.0-SNAPSHOT"
  :description "Conway's Game of Life'"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :plugins [[lein-cucumber "1.0.2"]]
  :cucumber-feature-paths ["features/"]
  :main ^:skip-aot game-of-life-clj.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
