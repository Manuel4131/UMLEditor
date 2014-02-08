# UMLEditor 1.0

This application is designed for creating [UML](Unified Modeling Language) diagrams.

UMLEditor Ver 1.0 is created by Java. It adopts the fundamental [OOP] concept: abstraction, encapsulation 

,inheritance and polymorphism. Also, some design patterns are applied to this project, like [state design pattern].

There are lots of tools in UMLeditor and each one corresponds to a state. For instance, the selection tool is to
 
select serveral class or use-case diagrams. And the generalization tool is to create a 'is-a' relationship between
 
classes by drawing a line. To complete this functionality, typically we need to use lots of **switch** **case**. 
 
However, this makes the code hard to be read and extended. Thus I created an abstract class called **Mouse_Mode.java**, 
 
it included several typical evnets like **mousePressed**,  **mouseDragged**, **mouseReleased** and **mouseClicked**. 

But they are all empty method. As a new state is created, it can override these methods to perform particular tasks.





[UML]:http://en.wikipedia.org/wiki/Unified_Modeling_Language
[OOP]:http://en.wikipedia.org/wiki/Object-oriented_programming
[Design Pattern]:http://en.wikipedia.org/wiki/Software_design_pattern
[state design pattern]:http://en.wikipedia.org/wiki/State_pattern

