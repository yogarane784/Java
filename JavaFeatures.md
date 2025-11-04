## Java new features

### Major features added in Java 8

- Optional
- Streams API
- Lambdas
- Default and static methods in interface
```
Interfaces can also define static utility methods (just like in classes).

✅ Example
interface MathUtils {
    static int add(int a, int b) {
        return a + b;
    }
}


Interfaces can now provide method implementations using the default keyword.
Before Java 8, interfaces could only declare abstract methods.

✅ Example
interface Vehicle {
    void start();

    default void stop() {
        System.out.println("Vehicle stopped");
    }
}
```
- Method references


### Major features from Java 9-17

- var keyword
```
Allows the compiler to infer the type of a local variable.

var list = new ArrayList<String>(); // infers ArrayList<String>
var name = "Yoga";                  // infers String

Benefits:
✅ Reduces boilerplate (Map<String, List<Integer>> → just var).
✅ Improves readability when the type is obvious.
✅ Helps when using generics or builders.

Note:
Type is inferred at compile-time, not dynamically — it’s still strongly typed.
Bad practice:

var x = getSomething(); // unclear what type!
```
- Sealed classes
```
They let you control which classes can extend or implement a class or interface.

public sealed class Vehicle permits Car, Truck { }

public final class Car extends Vehicle { }
public final class Truck extends Vehicle { }
```

- Records
- Pattern matching for instance of
```
Before Java 16:

if (obj instanceof String) {
    String s = (String) obj; // explicit cast needed
    System.out.println(s.toLowerCase());
}


Now (Java 16+):

if (obj instanceof String s) {
    System.out.println(s.toLowerCase()); // auto-cast + binding
}
```
- toList() instead of .collect(Collectors.toList())
