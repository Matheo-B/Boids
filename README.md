# Boids
Implementation of the Boids algorithm in java

# Goal
The goal of the project was just to learn Java.

# How to run it
To run the project, you need to launch Main.java

# What is the Boids Algorithm?
[Here](https://people.ece.cornell.edu/land/courses/ece4760/labs/s2021/Boids/Boids.html)
you can find the Website I based my implementation on. The Boids algorithm is well explained.

# Settings
### In Main.java :
    - WIDTH and HEIGHT of the window (Default 700x700)
    - The FrameRate (Default 60)

### In Boids.java :
    - The number of Birds (Default 150)
    - The Pixel Margin (Default 100)
        The distance from the side in which the birds is pulled to the center
    - The Border Factor (Default 400)
        The strength of the force in the border
    - The Separation Distance (Default 30)
        The radius of the cirlce in which a Bird flee the others
    - The Follow Distance (Default 90)
        The radius of the cirlce in which a Bird can see other Bird
    - The Separation Factor (Default 1)
        The strength of the separation force
    - The Alignment Factor (Default 0.7)
        The strength of the Alignment Force
    - The Cohesion Factor (Default 0.7)
        The strength of the Cohesion Factor
    - The Min and Max Velocities of the Bird (Default 100 and 150)
    - The size of the Bird Rendering (Default 10)