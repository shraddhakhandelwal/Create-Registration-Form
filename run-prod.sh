#!/bin/bash
# Run with MySQL Database (Production Mode)

export JAVA_HOME=/usr/local/sdkman/candidates/java/21.0.9-ms
export PATH=$JAVA_HOME/bin:$PATH

echo "Starting application with MySQL database..."
echo "Access at: http://localhost:8080"
echo ""
echo "Make sure you have:"
echo "  1. Created database: CREATE DATABASE registration_db;"
echo "  2. Updated credentials in src/main/resources/application.properties"
echo ""

mvn spring-boot:run -Dspring-boot.run.profiles=prod
