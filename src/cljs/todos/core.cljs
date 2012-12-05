(ns todos.core
  (:require [crate.core :refer [html]]
            [domina :refer [append! by-id by-class set_html! log attrs value]]
            [domina.css :refer [sel]]
            [domina.events :refer [listen! prevent-default target]])
  (:use-macros [crate.def-macros :only [defpartial]]))

(def id-seq (atom 0))

(defn make-task [description]
  {:id (swap! id-seq inc) :task description :state "pending"})

(def tasks (atom [(make-task "Learn ClojureScript")]))

(defpartial task-html [task]
  [:li {:id (:id task)
                  :class (:state task)}
             (:task task)])

(defpartial task-list-html [tasks]
  [:ul (map task-html tasks)])

(defn render-all [tasks]
  (html [:div#content
         [:form#new-task {:action "#"}
          [:label {:for "description"} "Add A Task"]
          [:input {:name "description" :watch :newtask}]]
         (task-list-html tasks)]))

(defn ^:export launch []
  (log "launching todos app")
  (append! (by-id "content") (render-all @tasks)))
