# UMLEditor 1.0

## Introduction

This application is designed for creating [UML](Unified Modeling Language) diagrams.

UMLEditor Ver 1.0 is created by Java. It adopts the fundamental [OOP] concept: abstraction, encapsulation 

,inheritance and polymorphism. Also, some design patterns are applied to this project, like [state design pattern].

There are lots of tools in UMLeditor and each one corresponds to a state. For instance, the selection tool is to
 
select serveral class or use-case diagrams. And the generalization tool is to create a 'is-a' relationship between
 
classes by drawing a line. To complete this functionality, typically we need to use lots of **switch** **case**. 
 
However, this makes the code hard to be read and extended. Thus I created an abstract class called **Mouse_Mode.java**, 
 
it included several typical evnets like **mousePressed**,  **mouseDragged**, **mouseReleased** and **mouseClicked**. 

But they are all empty method. As a new state is created, it can override these methods to perform particular tasks.

By adopting this pattern, code is much easier to be maintianed.

## Agent

You can use some IDE tools like [Eclipse] to run this application.
 
### How to run?
 
* Download an IDE like **Eclipse**.
* Create a new project and include these source code.
* Run. (Ctr+F11) for Eclipse.
 
### How to edit?
 
* Utilizing the add-on [Window Builder] can save you lots of time in GUI design.
 
## Future release
 
The current version is implemented by Java. Nevertheless, the future version will be created by C++.
  
Creating the same application by various language can force you rethink your design and you will

compare C++ and Java OOP concept. They are still something very different!                         
  
I will open a new project to the next version

[UML]:http://en.wikipedia.org/wiki/Unified_Modeling_Language
[OOP]:http://en.wikipedia.org/wiki/Object-oriented_programming
[Design Pattern]:http://en.wikipedia.org/wiki/Software_design_pattern
[state design pattern]:http://en.wikipedia.org/wiki/State_pattern
[Eclipse]:http://www.eclipse.org/downloads/
[Window Builder]:http://download.eclipse.org/windowbuilder/WB/release/R201309271200/4.2/
