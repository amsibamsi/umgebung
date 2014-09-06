# Umgebung

Create an application configuration and read properties from the system and the environment.

## Installation

Add the following to your dependencies:

```clojure
:dependencies [[umgebung "x.y.z"]]
```

Where `x.y.z` is the version you want. The lastest version is distributed on http://clojars.org.

## Usage

The simplest form is to use `umgebung.core/cfg`, e.g.:

```clojure
(require '[umgebung.core :as u])
(def cfg (u/cfg [:path :java-version]))
(cfg :java-version)
```

That should return something like `"1.8.0_05"`. Keys are automatically translated from `:a-key` to `"A_KEY"` for environment variables and to `"a.key"` for system properties. Values in the system properties overwrite the environment.

There are also more complicated forms using `umgebung.core/prop` to create properties and `umgebung.core/prop-cfg`, `umgebung.core/prop-cfg-all` to return full property maps that can be used to annotate settings with more information or dump a configuration.

To change the way keys are translated or values are looked up see `umgebung.convert` and `umgebung.source`.

## License

Copyright 2014 (c) Anselm Strauss.

Distributed under the 2-Clause BSD License. See the file LICENSE.
