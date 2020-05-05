# From Java 13 to Java 14
This kata will help you to discover and experiment new features appeared in Java 14.

In this pandemic times, the subject is about hospital.

You will have to follow the FIXME present into the code which show you where you can use Java 14 new features.

## What do I need for this kata ?
* Git to checkout this code
* Maven for dependencies
* Java 14 of course (look here for new release [OpenJDK](https://adoptopenjdk.net/))
* An up to date IDE for easier life (the 2020.1 release of [IntelliJ](https://www.jetbrains.com/idea/download/) works perfectly for example)

Some new features in Java 14 you will have to play with :

(All showed examples are mostly from OpenJDK website)

## Improved switch
It's now stable and fully usable in your programs :)

Example:
```java
//Before
switch (day) {
    case MONDAY:
    case FRIDAY:
    case SUNDAY:
        System.out.println(6);
        break;
    case TUESDAY:
        System.out.println(7);
        break;
    case THURSDAY:
    case SATURDAY:
        System.out.println(8);
        break;
    case WEDNESDAY:
        System.out.println(9);
        break;
}

// From Java 14
switch (day) {
    case MONDAY, FRIDAY, SUNDAY -> System.out.println(6);
    case TUESDAY                -> System.out.println(7);
    case THURSDAY, SATURDAY     -> System.out.println(8);
    case WEDNESDAY              -> System.out.println(9);
}

// Other case with variable assignment
// Before
int numLetters;
switch (day) {
    case MONDAY:
    case FRIDAY:
    case SUNDAY:
        numLetters = 6;
        break;
    case TUESDAY:
        numLetters = 7;
        break;
    case THURSDAY:
    case SATURDAY:
        numLetters = 8;
        break;
    case WEDNESDAY:
        numLetters = 9;
        break;
    default:
        throw new IllegalStateException("Wat: " + day);
}

// From Java 14
int numLetters = switch (day) {
    case MONDAY, FRIDAY, SUNDAY -> 6;
    case TUESDAY                -> 7;
    case THURSDAY, SATURDAY     -> 8;
    case WEDNESDAY              -> 9;
};

// And if you have a block of code
int j = switch (day) {
    case MONDAY  -> 0;
    case TUESDAY -> 1;
    default      -> {
        int k = day.toString().length();
        int result = f(k);
        yield result;
    }
};

// And also with the new yield reserved word if you don't want to use the ->
int result = switch (s) {
    case "Foo": 
        yield 1;
    case "Bar":
        yield 2;
    default:
        System.out.println("Neither Foo nor Bar, hmmm...");
        yield 0;
};
```

## Text block
It's the second preview in this release of Java. It has been improved by adding two new escape sequences.

It just allows you to display nicely in your code long strings or formatted strings as JSON or HTML.
```java
// Before
"line 1\n" +
"line 2\n" +
"line 3\n"

// From Java 14
"""
line 1
line 2
line 3
"""

// Before
String html = "<html>\n" +
              "    <body>\n" +
              "        <p>Hello, world</p>\n" +
              "    </body>\n" +
              "</html>\n";

// From Java 14
String html = """
              <html>
                  <body>
                      <p>Hello, world</p>
                  </body>
              </html>
              """;
```

## Pattern matching for instanceOf
It's still a preview in this release of Java but it sounds great !

```java
// Before
if (obj instanceof String) {
    String s = (String) obj;
    // use s
}

// From Java 14
if (obj instanceof String s) {
    // use s
}
```

## Record
It's also a preview and a good start for immutable objects.

```java
// Before
public final class ReceptionFile {
    public final String firstName;
    public final String lastName;
    public final String socialSecurityNumber;

    public ReceptionFile(String firstName, String lastName, String socialSecurityNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }
}

// From Java 14
public record ReceptionFile(String firstName,
                            String lastName,
                            String socialSecurityNumber) {}
```

As you understand, you will have to enable preview features in your IDE and the POM file to play with all the visible new things brought by Java 14.

Enjoy !
