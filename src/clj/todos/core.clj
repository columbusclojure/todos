(ns todos.core
  (:require [cemerick.piggieback :refer [cljs-repl]]
            [cljs.repl.browser :refer [repl-env]]
            cljs.repl))

(cljs-repl :repl-env (doto (repl-env :port 9000) cljs.repl/-setup))
