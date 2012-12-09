# todos

A ClojureScript todo app created for and by the 12/5 meeting of the Columbus Clojure user group.

## Usage

Using the todo app is not the point. Hacking it is the point.

## Hacking

The lein plugin cljsbuild helps us out a ton by wrapping up the messy Google Clojure Compiler stuff.
Take a look at project.clj to see how it's configured. All you need to know to start hacking is
to start the compiler
```bash
lein deps
lein cljsbuild auto
```

The auto option tells lein to recompile your todos.js file when any of your cljs files change.
After compilation finishes, open the file resources/public/index.html in your browser.


## Hacking with the Brepl

To use the browser repl you'll need a to run a webserver to serve up the static files.

This is because single-origin policy will prevent an html file opened from the filesystem from interacting with localhost:9000 (which is the port we'll be running the repl on)

There is an http server included with the project that will serve up our index.html file. To start it simply run this command from the root of our project directory.

```bash
lein ring server
```
The clj code for the webserver is in src/clj/todos/core.clj

Next you do ```lein cljsbuild repl-listen```. This starts your repl server. Now load the page http://localhost:3000/index.html and the browser will connect to your repl server.

The cljs code that causes this to happen is in src/cljs/todos/connect.cljs

Now you can type something in the repl ```(js/alert "hello")```

## Ultimate Hacking in Emacs

OK that's cool but the repl isn't as nice as editing in my editor. Well the answer,
thanks to cemerick is called piggyback. It allows you to start a browser repl inside
an already running ```lein repl```

So here are the steps in detail. Start a new terminal.

```bash
$ lein repl
nREPL server started on port 64435
REPL-y 0.1.0-beta10
Clojure 1.4.0
...
```

Now make note of that port number 64435 (it will be different for you).
Switch to emacs. ```M-x nrepl``` Enter your port number when prompted.
After that you should be in a buffer called ```*nrepl*```
now just ```(require 'todos.brepl)``` to drop into a cljs repl. You'll know
that you've succeeded when you see the prompt change to ```cljs.user>```

## Questions?

Please email the ColumbusClojure user group with any questions you have. Pull requests are welcome!

## License

Copyright © 2012 ColumbusClojure

Distributed under the Eclipse Public License, the same as Clojure.
