#!/bin/bash
# Quick Start Script for Registration System

echo "========================================="
echo "User Registration System - Quick Start"
echo "========================================="
echo ""

# Use Java 21
export JAVA_HOME=/usr/local/sdkman/candidates/java/21.0.9-ms
export PATH=$JAVA_HOME/bin:$PATH

echo "Using Java version:"
java -version
echo ""

echo "Step 1: Cleaning previous builds..."
mvn clean

echo ""
echo "Step 2: Building the project..."
mvn install -DskipTests

if [ $? -eq 0 ]; then
    echo ""
    echo "========================================="
    echo "BUILD SUCCESSFUL!"
    echo "========================================="
    echo ""
    echo "To run the application:"
    echo "  Option 1 (H2 Database - No setup required):"
    echo "    ./run-dev.sh"
    echo "    OR"
    echo "    JAVA_HOME=/usr/local/sdkman/candidates/java/21.0.9-ms mvn spring-boot:run -Dspring-boot.run.profiles=dev"
    echo ""
    echo "  Option 2 (MySQL Database):"
    echo "    1. Create database: CREATE DATABASE registration_db;"
    echo "    2. Update credentials in src/main/resources/application.properties"
    echo "    3. Run: ./run-prod.sh"
    echo ""
    echo "Access the application at: http://localhost:8080"
    echo "========================================="
else
    echo ""
    echo "========================================="
    echo "BUILD FAILED!"
    echo "========================================="
    echo "Please check the error messages above."
fi
