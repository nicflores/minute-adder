# Minute Adder
This is an incomplete code sample for candidates to finish.

# Problem Statement
> Without using any built-in date or time functions, write a function or method that accepts two mandatory arguments: the first argument is a 12-hour time string with the format "[H]H:MM {AM|PM}", and the second argument is a (signed) integer. The second argument is the number of minutes to add to the time of day represented by the first argument. The return value should be a string of the same format as the first argument. For example, AddMinutes("9:13 AM", 200) would return "12:33 PM".

# Instructions
Clone this repo and fill in the implementation for `Main.scala` and `MinuteAdder.addMinutes`. Feel free to add whatever you need, but please leave the signature of `MinuteAdder.addMinutes` the same.

To turn in your implementation, compress the root directory of this repo (including the `.git` directory), put your name in the name of the compressed file, and return it to us.

# Usage
To run the application using sbt, run the main class from the `impl` project: `impl/run "5:02 PM" 1`

# How to Test
To test the applications using sbt, call `test` from the root of the project.


