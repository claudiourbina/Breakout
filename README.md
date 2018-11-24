# CC3002-Tarea2

## Introducción

El proyecto implementado en el repositorio corresponde a la creación de la parte lógica del juego *Breakout*. Para ello, fue necesario dividr el proyecto en 3 partes:

* Controller.
* Facade.
* Logic.

A continuación se explicara la implementación y el por qué de cada una de estas partes, además, se explicará como funciona el algoritmo.

## Breakout

### Logic 

La parte lógica del juego está compuesta de otras dos partes: *Level* y *Brick*. La primera, corresponde a las clases que conforman un nivel del juego, ya sea un nivel no jugable (o *empty*) o uno jugable (o *real*), en esta clase esta toda la información relevante en un nivel, como los ladrillos que contiene, el puntaje total del nivel, el puntaje del jugador en el nivel, y cual es el nivel que le sigue. Por otra parte, la segunda esta conformada por las clases que representan los ladrillos que existen en cada uno de los niveles del juego. Estos pueden ser de vidrios (*glass bricks*), de mandera (*wooden bricks*) o de metal (*metal bricks*).

### Controller

El controlador es el juego en sí, es por esto que la clase que compone esta parte es *game*. El controlador se encarga de cambiar los niveles cuando sea necesario y como bien dice su nombre, controlar lo que ocurre en el juego. Dentro de sus funciones esta verificar cuantas vidas (*balls*) le quedan al jugador, si perdio o no, si paso de nivel, etc.

### Facade

Facade es una clase que existe para ocultar la complejidad del controlador, es decir, puede realizar las mismas funciones que este llamandolo, pero es mas entendible.

## Implementación del Juego

El juego implementa distintos patrones de diseño que permiten que funcione de manera correcta y entendible.

### Observadores, Observados y Visitantes

Los patrones de diseño mas importantes y que permiten que el juego funcione de manera correcta son los de observabilidad y visitor. A continuación se explicarán como estos se utilizan dentro del algoritmo:

#### Level y Brick

Como bien se dijo en un comienzo, level contiene la lista de ladrillos que posee. Es en esta parte (cuando esta lista de ladrillos es creada) que se implementa el patron de observabilidad, cada ladrillo que es agregado en la lista de ladrillos del nivel suscribe al nivel como su observador. La necesidad de que el nivel observe al ladrillo es porque necesita saber cuando este muere para aumentar el puntaje del jugador. Es por esto, que cuando el ladrillo recibe un golpe, si con ese golpe el ladrillo muere, le notifica al nivel que murio. 

Es importante destacar que el Nivel si bien sabe que un ladrillo murió, no sabe que tipo ladrillo fue el que murio, es por esto que el nivel debe visitar al ladrillo que notifico su muerte y así poder sumar el puntaje correspondiente utilizando *Double Dispacht*.

#### Game y Brick

La relación entre game y brick es identica que a la relación entre level y brick, debido a que game contiene el puntaje total del jugador en el juego, este tambien debe aumentar el puntaje cuando un brick muere.

#### Game y Level

Similar a la relación entre game y brick. Game contiene el nivel actual del juego, por lo que, al momento de *setear* el nivel actual, este suscribe al juego como un observador de él. La necesidad de esta observabilidad es que el juego necesita saber cuando pasar al siguiente nivel, es por esto, que cuando un brick es destruido y le notifica al nivel de su muerte, el nivel aumenta los puntos del jugador *en el nivel*, comparandolos con los puntos totales que deberia obtener. Si estos son iguales es porque termino el nivel, es en este momento en que el nivel le notifica al Game que el jugador completo el nivel y debe pasar al siguiente. El game no necesita visitar el nivel como el nivel al brick.

### Proxy

