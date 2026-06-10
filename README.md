# math-expression-adt

A Java library implementing an immutable Abstract Data Type (ADT) for mathematical expressions, built around a recursive composite pattern.

## Overview

This project models algebraic expressions as an immutable tree structure. Each node in the tree is one of four types — a number literal, a named variable, an addition, or a multiplication — and every type is fully immutable and structurally equal by value.

## Suggested Name

`math-expression-adt` — replacing the uninformative `Lab12` label.

## Project Structure

```
src/
├── Expression.java       # Core interface / ADT definition
├── NumberExpr.java       # Leaf: numeric literal (e.g. 3, 1.5)
├── VariableExpr.java     # Leaf: named variable (e.g. x, y)
├── AddExpr.java          # Internal node: n-ary addition
├── MultiplyExpr.java     # Internal node: n-ary multiplication
└── ExpressionTest.java   # JUnit 4 test suite
```

## ADT Definition

```
Expression = Number(double value)
           + Variable(String name)
           + Add(List<Expression> terms)       -- at least 2 terms
           + Multiply(List<Expression> factors) -- at least 2 factors
```

## Design Principles

- **Immutability** — all implementations use `List.copyOf()` and final fields; no mutation is possible after construction.
- **Rep safety** — defensive copies prevent rep exposure; `checkRep()` assertions guard invariants.
- **Structural equality** — `equals()` and `hashCode()` are value-based and consistent across all four types.
- **Recursive structure** — `toString()`, `equals()`, and `hashCode()` all delegate to sub-expressions naturally, leveraging the composite pattern.

## Usage

```java
// 1 + x
Expression e = new AddExpr(List.of(new NumberExpr(1), new VariableExpr("x")));
System.out.println(e); // "1 + x"

// 2 * 3
Expression m = new MultiplyExpr(List.of(new NumberExpr(2), new NumberExpr(3)));
System.out.println(m); // "2 * 3"

// Equality is structural
Expression a1 = new AddExpr(List.of(new NumberExpr(1), new VariableExpr("x")));
Expression a2 = new AddExpr(List.of(new NumberExpr(1.000), new VariableExpr("x")));
a1.equals(a2); // true — doubles compared within 1e-9 tolerance
```

## Representation Invariants

| Class          | Invariant                                              |
|----------------|--------------------------------------------------------|
| `NumberExpr`   | Any valid `double`                                     |
| `VariableExpr` | Name matches `[a-zA-Z]+`                               |
| `AddExpr`      | `terms.size() >= 2`, no nulls, unmodifiable list       |
| `MultiplyExpr` | `factors.size() >= 2`, no nulls, unmodifiable list     |

## Running Tests

The project uses **JUnit 4** and targets **Java 21**.

In Eclipse: right-click `ExpressionTest.java` → *Run As* → *JUnit Test*.

Via command line:
```bash
javac -cp .:junit-4.x.jar src/*.java
java  -cp .:junit-4.x.jar org.junit.runner.JUnitCore ExpressionTest
```

## Requirements

- Java 21+
- JUnit 4
- Eclipse IDE (optional — `.classpath` / `.project` included)

## Known Limitation

`ExpressionTest` uses JUnit 5-style annotations (`@Test` from `org.junit.jupiter`) but imports `org.junit.Assert` from JUnit 4. The class also lacks a `public` modifier. To run cleanly, either migrate fully to JUnit 5 or add `public` to the class declaration.
