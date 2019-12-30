

# Catch of the Day (CLJS)


## What is it?

Catch of the Day (CLJS) is a port of Wes Boss' [React for Beginners](<https://reactforbeginners.com/>) code from JavaScript to ClojureScript

-   Build Tool

-   See [shadow-cljs](<https://github.com/thheller/shadow-cljs>).

I'm using it as a replacment for [create-react-app](<https://github.com/facebook/create-react-app>) when programming in CLJS/React.  It offers Live Reload, a CLJS Repl, and code splitting, etc.


## CLJS/React Framework

[hx](<https://github.com/Lokeh/hx>) which offers a thinner wrapper than [reagent](<https://github.com/reagent-project/reagent>) for ClojureScript development in React; See [Why not Reagent](<https://github.com/Lokeh/hx/blob/master/docs/why-not-reagent.md>) as to why, in 2019, React makes a thin wrapper in ClojureScript perfectly viable.


## Installation

Pick your favorite package manager (i.e., npm or yarn).  I've created several
scripts to help (see package.json) using yarn.

`yarn install`


## Develop Mode

`yarn start`


## Release Mode

yarn release


## Notes

TBD

