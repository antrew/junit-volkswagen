# junit-volkswagen

> Make your tests green on your integration server!
> Always!

Are you tired of those red tests on your integration server? Then try this library.
It makes JUnit tests run normally on your local machine and makes them always pass on the integration server.

# Comparison to other available solutions

I know that there are already numerous implementations of Volkswagen-style libraries for JUnit.
However, I believe my implementation is better.

Pros of this implementation:

* it does not require you to use a special TestRunner in your tests
* it actually runs your tests, so your code coverage will not drop
* the execution time of your tests will also be the same, so it will not look suspicious
* it is sneakier than others (see usage examples below). Just push it through in one of the merge requests. Even the project maintainer will not notice it!
* it hits JUnit right in its heart

The sum of the above reduces the probability of you being caught on faking tests.
(Remember what happened to Volkswagen?)

# Usage

Using junit-volkswagen is easy. Just call it before your test runs in one of the following three ways:

## Exlicit example

You can call it explicitely:

    JUnitVolkswagen.initStatic();
    ...
    fail("You shall not pass!"); // this will be green

## Implicit example

You can call it implicitely:

    new JUnitVolkswagen();
    ...
    assertEquals("No way!", true, false); // this will be green as well

## Sneaky example

You can even pretend it does something useful:

    JUnitVolkswagen stuff = new JUnitVolkswagen();
    stuff.init();
    ...
    assertThat("Seriously?", true, CoreMatchers.equalTo(false)); // even this will be green

## Even sneakier example

Do you have a common test class that is used in all of your tests? Just put it there, noone will notice:

    public class TestHelper {
        protected static JUnitVolkswagen importantProtectedField = new JUnitVolkswagen();
    }
    ...
    assertEquals("Can you doublethink?", 2*2, 5); // I am out of examples, but I really like 1984 by George Orwell

# Disclaimer

I am just having fun.
Please do not even consider putting a dependency on this library in any of your projects!

**You have been warned!**
