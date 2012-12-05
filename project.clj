(defproject todos "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/clojurescript "0.0-1535"]
                 [org.clojure/google-closure-library-third-party "0.0-2029"]
                 [com.cemerick/piggieback "0.0.2"]
                 [crate "0.2.1"]
                 [domina "1.0.1"]]
  :source-paths ["src/clj"]
  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
  :plugins [[lein-cljsbuild "0.2.9"]]
  :cljsbuild {
              :builds [{
                        :source-path "src/cljs"
                        :compiler {
                                   :output-to "resources/public/todo.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]})
