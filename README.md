# game-of-life-clojure

Conwey's game of life implemented in Clojure. There is not GUI yet, I played around with Humble UI but wasn't able to make any progress so far. You can run it in command line though.

## Run CLI

```sh
clj -M -m game-of-life-clojure.cli
```

## Run Tests

```sh
clj -X:test
```

## Run REPL

I don't know how to run repl server without running the user namespace that opens the window as the following command. `clj -M:dev` opens just regular repl I'm not able to connect to from editor.

```sh
clj -M:dev -m user
```

## License

Copyright © 2023 Jakub Koci

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
