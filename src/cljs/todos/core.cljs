(ns todos.core
  (:require [crate.core :refer [html]]
            [domina :refer [append! by-id by-class set-html! log attrs value get-data set-value!]]
            [domina.css :refer [sel]]
            [domina.events :refer [listen! prevent-default target]])
  (:use-macros [crate.def-macros :only [defpartial]]))

(def id-seq (atom 0))

(defn make-task
  ([description]
     (make-task description false))
  ([description done]
     {:id (swap! id-seq inc) :task description :done done}))

(def tasks (atom [(make-task "Learn ClojureScript" true) (make-task "Profit")]))

(defn state-class [task]
  (if (:done task) "done" "pending"))

(defpartial task-html [task]
  [:li {:id (:id task)
        :class (state-class task)}
   [:a.task {:href "#" :data-task-id (:id task)} (:task task)]])

(defn render-all [tasks]
  (html [:div#content
         [:form#new-task {:action "#"}
          [:label {:for "description"} "Add A Task"]
          [:input {:name "description"}]]
         [:ul (map task-html tasks)]]))

(defn render-tasks [tasks]
  (set-html! (sel "ul")
             (map task-html tasks)))

(defn add-task [ev]
  (prevent-default ev)
  (let [input (sel "input[name=description]")]
    (swap! tasks conj (make-task (value input)))
    (set-value! input "")))

(defn toggle-task [ev]
  (prevent-default ev)
  (let [id (js/parseInt (:data-task-id (attrs (target ev))))]
    (swap! tasks (fn [tasks]
                   (map (fn [task]
                          (if (= id (:id task))
                            (update-in task [:done] not)
                            task))
                        tasks)))))

(defn update-tasks-dom [key reference old-state new-state]
  (render-tasks @tasks))

(defn ^:export launch []
  (log "launching todos app")
  (append! (by-id "content") (render-all @tasks))
  (listen! :submit add-task)
  (listen! :click  toggle-task)
  (add-watch tasks :task-watcher update-tasks-dom))
