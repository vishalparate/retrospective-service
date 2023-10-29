# Retrospective Service - Spring Boot

This is a simple RESTful web service built with Spring Boot for managing and querying retrospective (SCRUM ceremony) outcomes. The service allows you to create retrospectives, add feedback items, update feedback, and search retrospectives. It also provides basic validation and error handling.

## Features

- Create a new retrospective.
- Add feedback items to retrospectives.
- Update feedback items.
- Search retrospectives with pagination.
- Search retrospectives by date.
- Basic validation and error handling.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- H2 Database (in-memory database)
- JUnit and Mockito (for testing)
- Maven (for project build)

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Maven (for building the project)

### Build and Run

1. Clone this repository:

   ```bash
   git clone https://github.com/vishalparate/retrospective-service.git
   cd retrospective-service 
2. Build the project using Maven:
   ```bash
   mvn clean install
3. Run the application:
   ```
   java -jar target/retrospective-service-1.0.0.jar

The application will start and be accessible at http://localhost:8080.

### API Endpoints
- POST `/retrospectives` : Create a new retrospective.
- POST `/retrospectives/{retrospectiveId}/feedback` : Add feedback items to a retrospective.
- PUT `/retrospectives/{retrospectiveId}/feedback/{feedbackId}` : Update feedback items.
- GET `/retrospectives?page={page}&pageSize={pageSize}` : Search retrospectives with pagination.
- GET `/retrospectives/search?date={date}&page={page}&pageSize={pageSize}` : Search retrospectives by date.

Make HTTP requests to these endpoints using your favorite API testing tool (e.g., Postman)
or swagger running at http://localhost:8080/swagger-ui/index.html.

### Unit Testing
  Unit tests are included in the `src/test` directory. You can run the tests using Maven: 
  ```bash
     mvn test
  ```    
### Error Handling
The application includes error handling for various scenarios, including validation errors and general exceptions. When an error occurs, appropriate error responses are returned with details.

### Custom Validation
Custom validation rules have been applied to ensure data integrity. For example, the service validates that retrospectives have a date and at least one participant.

### Logging
Basic logging is implemented for debugging and error handling purposes.
