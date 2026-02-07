package ac4y.base.database;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for Ac4yDBAdapter class.
 * Tests the basic connection wrapper functionality.
 */
public class Ac4yDBAdapterTest {

    @Mock
    private Connection mockConnection;

    private Ac4yDBAdapter adapter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        adapter = new Ac4yDBAdapter();
    }

    /**
     * Test default constructor creates an instance with null connection.
     */
    @Test
    public void testDefaultConstructor() {
        Ac4yDBAdapter newAdapter = new Ac4yDBAdapter();
        assertNotNull("Adapter should be created", newAdapter);
        assertNull("Connection should be null by default", newAdapter.getConnection());
    }

    /**
     * Test parameterized constructor sets the connection.
     */
    @Test
    public void testParameterizedConstructor() {
        Ac4yDBAdapter newAdapter = new Ac4yDBAdapter(mockConnection);
        assertNotNull("Adapter should be created", newAdapter);
        assertEquals("Connection should be set", mockConnection, newAdapter.getConnection());
    }

    /**
     * Test parameterized constructor with null connection.
     */
    @Test
    public void testParameterizedConstructorWithNull() {
        Ac4yDBAdapter newAdapter = new Ac4yDBAdapter(null);
        assertNotNull("Adapter should be created", newAdapter);
        assertNull("Connection should be null", newAdapter.getConnection());
    }

    /**
     * Test setConnection method sets the connection.
     */
    @Test
    public void testSetConnection() {
        adapter.setConnection(mockConnection);
        assertEquals("Connection should be set", mockConnection, adapter.getConnection());
    }

    /**
     * Test setConnection can replace existing connection.
     */
    @Test
    public void testSetConnectionReplacement() throws SQLException {
        Connection firstConnection = mock(Connection.class);
        Connection secondConnection = mock(Connection.class);

        adapter.setConnection(firstConnection);
        assertEquals("First connection should be set", firstConnection, adapter.getConnection());

        adapter.setConnection(secondConnection);
        assertEquals("Second connection should replace first", secondConnection, adapter.getConnection());
    }

    /**
     * Test setConnection with null clears the connection.
     */
    @Test
    public void testSetConnectionWithNull() {
        adapter.setConnection(mockConnection);
        assertEquals("Connection should be set", mockConnection, adapter.getConnection());

        adapter.setConnection(null);
        assertNull("Connection should be null after setting to null", adapter.getConnection());
    }

    /**
     * Test getConnection returns the set connection.
     */
    @Test
    public void testGetConnection() {
        adapter.setConnection(mockConnection);
        Connection result = adapter.getConnection();
        assertSame("Should return the same connection instance", mockConnection, result);
    }

    /**
     * Test getConnection returns null when no connection is set.
     */
    @Test
    public void testGetConnectionWhenNull() {
        Connection result = adapter.getConnection();
        assertNull("Should return null when no connection is set", result);
    }

    /**
     * Test that adapter works with closed connection.
     */
    @Test
    public void testWithClosedConnection() throws SQLException {
        when(mockConnection.isClosed()).thenReturn(true);

        adapter.setConnection(mockConnection);
        Connection result = adapter.getConnection();

        assertEquals("Should return the connection even if closed", mockConnection, result);
        assertTrue("Connection should be closed", result.isClosed());
    }

    /**
     * Test that adapter works with open connection.
     */
    @Test
    public void testWithOpenConnection() throws SQLException {
        when(mockConnection.isClosed()).thenReturn(false);

        adapter.setConnection(mockConnection);
        Connection result = adapter.getConnection();

        assertEquals("Should return the connection", mockConnection, result);
        assertFalse("Connection should be open", result.isClosed());
    }

    /**
     * Test multiple get operations return same connection.
     */
    @Test
    public void testMultipleGetOperations() {
        adapter.setConnection(mockConnection);

        Connection result1 = adapter.getConnection();
        Connection result2 = adapter.getConnection();
        Connection result3 = adapter.getConnection();

        assertSame("All gets should return same instance", result1, result2);
        assertSame("All gets should return same instance", result2, result3);
        assertSame("All gets should return same instance", result1, result3);
    }

    /**
     * Test that adapter can be reused after clearing connection.
     */
    @Test
    public void testAdapterReuse() {
        // Set first connection
        adapter.setConnection(mockConnection);
        assertEquals("First connection should be set", mockConnection, adapter.getConnection());

        // Clear connection
        adapter.setConnection(null);
        assertNull("Connection should be cleared", adapter.getConnection());

        // Set new connection
        Connection newConnection = mock(Connection.class);
        adapter.setConnection(newConnection);
        assertEquals("New connection should be set", newConnection, adapter.getConnection());
    }
}
