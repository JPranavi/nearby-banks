Tech Stack: Java, Spring Boot, Google Maps API, Maven, Git.

Microservices:

Bank Service: REST API endpoint /api/banks?zipcode=XXXXX.

Geo Service: Connects to Google Maps Places, Geocoding, and Distance Matrix APIs.

Flow:

User sends zipcode to /api/banks.
Bank Service calls Geo Service.
Geo Service gets coordinates, finds banks, and calculates distances.
Bank Service returns JSON with bank details.

Dependencies: Spring Web, Lombok, Google Maps API client library.