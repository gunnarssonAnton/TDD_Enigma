# Project
## Environment & Tools
####Ruben
* `OS: Windows 10`

* `IDE: Jetbrains IntelliJ`

* `Git: v2.35.1.windows.2`


####Anton
* `OS: Windows 11`

* `IDE: Jetbrains IntelliJ`

* `Git: v2.35.1.windows.2`

## Purpose
The purpose of this project is to create a digital replica of the German, World War II encryption machine, The Enigma.
The application should work almost identically with a few differences due to the conversion to a digital application.
The user will be able to choose from different encryption Reels and set a start position. The original Enigma had three
encryption Reels and a Reflector and so will this one. Although, the user will not be able to set the Reflector but
the code will be implemented with the possibility. As a user enters text, the text will be encrypted on the fly. A reset
and a backspace button will be implemented. As the original, when a character is typed, an encryption keyboard will
light up showing what the typed character was encrypted to.
The original Enigma grouped encrypted characters into groups of four, the digital enigma will not. The digital Enigma will
include the german alphabet, as the original did, but also include a blank space as a character and this will make it
unclear to differentiate between a grouping blank space and an encrypted character.
The code for this project will be driven forward by creating test for how the code should work and implementing
code to accommodate the tests (Test Driven Development). Git and bitbucket will be used for source control and
feature branching for structuring and Trello do visualize structure flow.


### Using the Enigma
For each of the three Rotor dropdowns the user picks a Rotor to use and uses the up/down arrow on each Rotor to choose
a start position for the given Rotor. These "settings" is what is used at a later point for decrypting the message.
The user now enters text via the hardware keyboard or the on-screen keyboard.
As characters are typed the encrypted message is displayed in the output field and the Rotors "counts" as characters are encrypted.
The encrypted text can be copied and sent to another user and on their side, the Rotors and start positions needs to
correspond to the settings used to encrypt. Given this the message can be converted back into decrypted form.

## Procedures
The Reel class was implemented as an abstract class to facilitate the Rotor and Reflector subclasses. The abstract `withReel()` method
should be called from the Reel class upon construction as when using template pattern, so a Reel number needs to be passed in the
constructor for the subclasses and the list of values corresponding to the id used as a return for `withReel()`.
The Rotor and Reflector acts similarly but has different structure when it comes to its values.
The methods `passForward()` and `passBackwards()` are used for the encryption.
A Reel houses a `List<Integer> values` and `passForwards()` returns the value for an index passed in. `passBackwards()`
returns the index for where the value passed in is located. The index passed in, is added to the offset in the Reel.
The values in a Rotor are arbitrarily placed at an index. For a Reflector the index/value are set in pairs.
E.g. if the value for 1 is 10 then the value for 10 must be 1. Hence the name Reflector.

The EnigmaMachine was implemented to house a set of Rotors and a Reflector. In the constructor the valid characters used are
passed in and stored. When encrypting a message each character is translated by checking the index of the character in said
string. The retrieved number will be passed forward through the first Rotor and the return value passed to the next Rotor using
the passForward method. There are basically three steps to the encryption process.

* `passForwards()` gets called on each of the Rotors where the input is the previous output
* `passForwards()` gets called on the Reflector inputting the output of the last Rotor
* `passBackwards()` gets called on each of the Rotors in reverse order where input is the output of the previous.

In the `getEncoded(String message)`method, these three steps must be made for each of the characters in the message. Each time
this is done the baseN counter increments and the Rotors' offsets are updated.

The BaseN class is used to keep track of the Rotor offsets and it does so by having a given amount of "digits" each representing
a Rotor offset. Each having a number base equal to the length of valid characters string.



## Discussion
The purpose has been completed and fulfilled and the Enigma works as intended. There are ten Rotors to choose via drop downs,
from and doing so changes the way the message is encrypted. The lights on the output board lights up when a key is pressed.
The offsets can be adjusted for a unique starting point and the message encrypted on "another" Enigma.

Although test driven development is a bit tedious it is a good way of forcing the project t progress. It is difficult
to create tests for the gui, but we took advice from "Uncle Bob" and moved as much logic as possible from the view to the controller.
Regarding feature branches. A good way to chop up a bigger project into smaller part, although difficult to determine "where to chop".
Linking feature branches to a visual workflow in Trello is a suiting complement. Some issues arose with Trello since our custom fields
stopped working, but this was solved using, for example, the card cover color was set to blue by default and during the review part,
changing the color to red/green to indicate disapproval/approval. A few things where invented during the development. For
instance, all commits should start with the branch name, making it much easier to follow and find errors. Also, the
discovery of the importance of pulling the dev branch before committing code was made clear. This moves the task of
merge conflicts to the person who made the changes or additions to the code.









