#!/bin/bash
# Run with H2 Database (Development Mode)

export JAVA_HOME=/usr/local/sdkman/candidates/java/21.0.9-ms
export PATH=$JAVA_HOME/bin:$PATH

echo "Starting application with H2 database..."
echo "Access at: http://localhost:8080"
echo ""

mvn spring-boot:run -Dspring-boot.run.profiles=dev
