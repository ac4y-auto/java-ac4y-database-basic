# java-ac4y-database-basic

Lightweight JDBC Connection wrapper adapter for the ac4y framework.

## Coordinates

- **GroupId**: `ac4y`
- **ArtifactId**: `ac4y-database-basic`
- **Version**: `1.0.0`

## Description

This library provides `Ac4yDBAdapter`, a simple wrapper class for JDBC `Connection` objects. It allows database connections to be managed and passed around within the ac4y framework without coupling to specific connection acquisition strategies.

## Usage

```java
// Create adapter with existing connection
Connection conn = DriverManager.getConnection(url, user, password);
Ac4yDBAdapter adapter = new Ac4yDBAdapter(conn);

// Or create empty and set later
Ac4yDBAdapter adapter = new Ac4yDBAdapter();
adapter.setConnection(conn);

// Retrieve connection when needed
Connection connection = adapter.getConnection();
```

## Build

```bash
mvn clean package
```

## Origin

Extracted from `IJAc4yDatabaseModule/Basic`.
