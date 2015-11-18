# scala_utils

Library with some useful things for my Scala needs

## Get the library

Simply download the file <code>scala_utils.jar</code> in the <code>out</code> folder, or click [here](https://github.com/HiinoFW/scala_utils/blob/master/out/scala_utils.jar?raw=true).

## Contents

- util - main package, contains various useful classes and objects
    - Odds [mutable] - implementation of Pondered with a fetching method that uses RNG as ponderation
    - Pondered [mutable] - trait that contains a Seq of tuples (element, ponderation) that can be fetched according to a certain ponderation rule
    - Random - static object version of the scala.util.Random class
    - Sequence [mutable] - class describing a simple mathematical sequence with an initial value and a function that is applied everytime <code>next</code> or <code>skip</code> is called
    - collection - contains immutable collection classes
        - Ring [immutable] - class describing a zipper-list-like structure with both ends linked together
        - Zipper [immutable] - class describing an immutable list that you can browse forwards or backwards. [to be extended into a tree version]
    - exception - contains custom exceptions
        - EmptyRingException - thrown when trying to create a Ring with no values inside
        - EndOfZipperException - (unused yet) thrown when trying to read past the end points of a zipper
        - BadCoordSystemException - thrown when trying to create a CoordSystem with parallel or null vectors
    - geometry - contains basic classes for geometry
        - CoordSystem [immutable] - class describing a cartesian coordinate system for the plane
        - BaseCoordSystem [immutable] - object describing the default coordinate system, origin (0, 0) and vectors (1, 0) and (0, 1)
        - Point [immutable] - point on the plane, described relatively to a coordinate system (base system by default)
        - Vector [immutable] - vector on the plane, described relatively to a coordinate system (base system by default)
    - tool - contains various, more specific functions
        - DeclineZipper - very specific function that returns a list of all possible zipper states from a list passed as an argument
        - IO - functions for reading and using files
        - Shuffle - very simple function that shuffles a list of arguments (O(n))
