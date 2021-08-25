# decathlon
Java program that calculates the results of a Decathlon competition.

A starting point for implementing Hexagonal architecture. You will also find it named clean architecture, ports-and-adapters, or onion architecture.

The basic idea is to make the domain layer independent of any library and other layers such as service and infrastructure layers. It is easy visible by looking at import section, there are only Java SDK imports.
This can come handy when you need to replace repository layer (for instance, for scaling) and remain domain layer untouched.     

Used patterns: Exporter builder and hexagonal architecture
