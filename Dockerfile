FROM markhobson/maven-chrome:latest

# Set working directory
WORKDIR /app

# Copy the entire project
COPY . /app

# Navigate to selenium-tests and run tests
WORKDIR /app/selenium-tests

# Run Maven tests
CMD ["mvn", "clean", "test"]
