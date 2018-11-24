# CC3002-Tarea2

## Introducción

El proyecto implementado en el repositorio corresponde a la creación de la parte lógica del juego *Breakout*. Para ello, fue necesario dividr el proyecto en 3 partes:

* Controller.
* Facade.
* Logic.

A continuación se explicara la implementación y el por qué de cada una de estas partes, además, se explicará como funciona el algoritmo.

## Breakout

### Logic 

La parte lógica del juego está compuesta de otras dos partes: *Level* y *Brick*. La primera, corresponde a las clases que conforman un nivel del juego, ya sea un nivel no jugable (o *empty*) o uno jugable (o *real*). Por otra parte, la segunda esta conformada por las clases que representan los ladrillos que existen en cada uno de los niveles del juego. Estos pueden ser de vidrios (*glass bricks*), de mandera (*wooden bricks*) o de metal (*metal bricks*).
