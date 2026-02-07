package ac4y.base.database;

import java.sql.Connection;

/**
 * A lightweight wrapper class for JDBC Connection objects.
 *
 * <p>This adapter provides a simple abstraction layer for database connectivity,
 * allowing Connection objects to be managed and passed around more easily within
 * the ac4y framework.</p>
 *
 * <p><strong>Usage Example:</strong></p>
 * <pre>{@code
 * // Create adapter with existing connection
 * Connection conn = DriverManager.getConnection(url, user, password);
 * Ac4yDBAdapter adapter = new Ac4yDBAdapter(conn);
 *
 * // Or create empty and set later
 * Ac4yDBAdapter adapter = new Ac4yDBAdapter();
 * adapter.setConnection(conn);
 *
 * // Retrieve connection when needed
 * Connection connection = adapter.getConnection();
 * }</pre>
 *
 * <p><strong>Important Notes:</strong></p>
 * <ul>
 *   <li>This class does NOT manage the lifecycle of the Connection</li>
 *   <li>Callers are responsible for closing connections to prevent leaks</li>
 *   <li>This class is NOT thread-safe</li>
 * </ul>
 *
 * @author ac4y
 * @version 1.20190505.1
 * @since 1.0
 */
public class Ac4yDBAdapter {

	/**
	 * Default constructor that creates an adapter with no connection.
	 */
	public Ac4yDBAdapter(){
	}

	/**
	 * Creates an adapter with the specified connection.
	 *
	 * @param connection the JDBC connection to wrap (may be null)
	 */
	public Ac4yDBAdapter(Connection connection){
		setConnection(connection);
	}

	/**
	 * Retrieves the wrapped connection.
	 *
	 * @return the current Connection, or null if none is set
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Sets or replaces the wrapped connection.
	 *
	 * <p>Note: This method does not close any previously set connection.
	 * Callers must manage connection lifecycle explicitly.</p>
	 *
	 * @param connection the connection to wrap (may be null to clear)
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * The wrapped JDBC connection.
	 */
	private Connection connection;

} // Ac4yDBAdapter
