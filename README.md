# London Location API

This **Java** API returns people who are listed as either living in London, or whose current coordinates are within 50 miles of London.

Two circles are used to find users who are within 50 miles of London. The green circle covers the area of London with a radius of 16.5 miles and the red circle covers both the area of London and 50 miles around London with a radius of 66.5 miles.

Coordinates that are inside the red circle and are not inside the green circle are considered to be 50 miles within London.

Due to London not being the shape of a perfect circle, this model is not 100% accurate.

![London](https://user-images.githubusercontent.com/48014118/86022962-f2009400-ba22-11ea-9f45-7c9bb8e92dfb.PNG)

## Libraries Used

GeoTools – Open source Java library that provides tools for geospatial data.

## Testing

This project uses JUnit to run unit tests. A folder containing all tests is included.

The conditions tested:

-	Returning all users that meet the main requirements.
-	API calls.

## Built With
- Java 11
