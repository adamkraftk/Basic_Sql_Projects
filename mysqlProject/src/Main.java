import java.sql.*;
public class Main {
    public static void main(String[] args) {
            try {
// Connect to the library_db database, via the jdbc:mysql: channel on localhost (this PC)
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/library_db",
                        "adamkraftk",
                        "johnadam1@RSA");
// Create a direct line to the database for running our queries
                Statement statement = connection.createStatement();
                ResultSet results;
                int rowsAffected;
// Set up finished, do some stuff:
// executeQuery: runs a SELECT statement and returns the results.
                        results = statement.executeQuery("SELECT title, qty FROM books");
// Loop over the results, printing them all.
                while (results.next()) {System.out.println(results.getString("title") + ", "
                        +results.getInt("qty"));
                }
// Add a new book:
                rowsAffected = statement.executeUpdate("INSERT INTO books VALUES (3001, 'Programming 101', 'Jane Doe', 1)");
                System.out.println("Query complete, " + rowsAffected + " rows added.");
                        printAllFromTable(statement);

// Edit qty for 'Introduction to Java':
                rowsAffected = statement.executeUpdate("update books set qty = 0 where title = 'Introduction to Java';");

                System.out.println("Query complete, " + rowsAffected + " rows updated.");
                printAllFromTable(statement);

// Change a book:
                rowsAffected = statement.executeUpdate("UPDATE books SET qty=500 WHERE id=1001");

                System.out.println("Query complete, " + rowsAffected + " rows updated.");
                        printAllFromTable(statement);
// Clear a book:
                rowsAffected = statement.executeUpdate("DELETE FROM books WHERE id=3001");

                System.out.println("Query complete, " + rowsAffected + " rows removed.");
                        printAllFromTable(statement);

// Delete all books with id > 8000:
                rowsAffected = statement.executeUpdate("delete from books where id > 8000;");
                System.out.println("Query complete, " + rowsAffected + " rows added.");
                printAllFromTable(statement);


// Add new books:
                rowsAffected = statement.executeUpdate("INSERT INTO books VALUES (8001, 'Java ABC', 'Kevin Jones', 3);");
                System.out.println("Query complete, " + rowsAffected + " rows added.");
                printAllFromTable(statement);

// Add new books:
                rowsAffected = statement.executeUpdate("INSERT INTO books VALUES (8002, 'Java XYZ', 'Kevin Jones', 5);");
                System.out.println("Query complete, " + rowsAffected + " rows added.");
                printAllFromTable(statement);

// Close up our connections
                results.close();
                statement.close();
                connection.close();
            }
            catch (SQLException e) {
// We only want to catch a SQLException - anything else is off-limits for now.
                        e.printStackTrace();
            }
        }
        /*
         * Method printing all values in all rows.
         * Takes a statement to try to avoid spreading DB access too far.
         *
         * @param a statement on an existing connection
         * @throws SQLException
         */
        public static void printAllFromTable(Statement statement) throws SQLException
        {
            ResultSet results = statement.executeQuery("SELECT id, title, author, qty FROM books");
            while (results.next()) {
                System.out.println(
                        results.getInt("id") + ", " + results.getString("title") + ", " + results.getString("author") + ", " + results.getInt("qty"));
            }
        }
    }
