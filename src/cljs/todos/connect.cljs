(ns todos.connect
  (:require [clojure.browser.repl :as repl])

  )

(.log js/console "hello")

(repl/connect "http://localhost:9000/repl")
