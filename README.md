# Modulo
Make a custom modulo function (Something I've learned while doing this is Java doesn't have a real modulus function, instead % is really remainder)

## Why I made this
Looking online, specifically on reddit at r/learnprogramming, I've seen multiple people request aid in dealing with recursion.
I never commented on these threads but I they got me thinking about what a could recursive function problem would look like that wasn't one of the well known ones such as fibbonaci.

I thought that making a modulus operator would work well since in my head I can see the modulus function in a recursive context.

However, I didn't want to only do recursion since I also knew modulus could probably be done in other ways, my first guess was through some multiplication, so I also wanted to try making other functions that would work.

## Is this done yet?
No, I've started working on my non-recursive methods but haven't made them all yet.

## What I've coded so far
I use a public, "outer" as I call it, function that can be called which takes in a divisor and dividend which can then call some private functions based on the positive or negative nature of the dividend and divisor. Because I wanted to do multiple versions of the same "inner" functions, to run the different ones an if statement has to have function calls commented out, all of this is to say that you can't run every function that does the same thing at the same time.

I've completed my recursive functions and done some basic testing on them. I haven't tried anything crazy, but my results align with Google so far. (Note that I'm basing my correctness off of Google when I type "x mod y" in the address bar) They work for all dividend/divisor combinations: ++,--,-+,+-

I've started working on functions using while loops. My original idea of multiplication was based off the concept of while loops.
I made a subtraction based one first just to get started.
Then I made a multiplication one which works for the ++ and -- dividend divisor combinations, I haven't started the +- or -+ ones yet

Finally I also have one which uses integer division for ++ and --. I didn't think of this until I asked GPT about the modulus function in Java where it told me % was actually a remainder function. I asked it to review my code to see if there were any mistakes (There weren't!) but it mentioned how integer division could be used, while I was a bit upset that I hadn't thought about using integer division I decided to code it out and call it a learning experience.
