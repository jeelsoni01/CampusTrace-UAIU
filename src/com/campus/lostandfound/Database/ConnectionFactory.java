package com.campus.lostandfound.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class ConnectionFactory {

    private static final String SAMPLE_PROFILE = "uaiu-india-v1";
    private static final String[] OLD_SAMPLE_ITEMS = {
            "Student ID Card", "Nike Sneaker", "Calculus Textbook", "Pink Stanley Tumbler",
            "Apple Watch SE", "Xiaomi Power Bank", "Campus Card", "White Dress",
            "Razer Mechanical Keyboard", "Lenovo Laptop Charger", "Black Folding Umbrella",
            "National ID Card", "AirPods Pro", "Jansport Backpack", "Casio Digital Watch",
            "Black Framed Glasses", "Kingston USB Drive", "Bear Keychain", "Black A4 Notebook",
            "Green Canvas Tote", "Kindle E-Reader", "Red Scarf", "Lab Report Folder",
            "Huawei Phone", "Clear Lunch Box", "Calculator", "White Baseball Cap",
            "Sony Earbuds Case", "Silver Necklace"
    };

    private static final String[][] UNIVERSITY_SAMPLE_DATA = {
            {"UAU Student ID Card", "Identification", "Academic Block A Lobby", "2026-10-19 09:15:00", "Aditi Patil", "9876500101", "B.Tech AI & ML student card in a transparent holder.", "Unclaimed", null, null, null, "user", null},
            {"Blue Nike Sports Shoe", "Clothing & Accessories", "Sports Complex Entrance", "2026-10-19 12:30:00", "Rohan Mehta", "9876500102", "Single blue left shoe, size 42, with white laces.", "Claimed", "Kabir Shah", "9876500121", "2026-10-19 13:45:00", "admin", "admin"},
            {"Data Structures Notebook", "Study Supplies", "Central Library, Second Floor", "2026-10-18 10:45:00", "Sneha Kulkarni", "9876500103", "Black notebook with Java and data-structures notes.", "Unclaimed", null, null, null, "user", null},
            {"Green Insulated Bottle", "Everyday Items", "University Cafeteria", "2026-10-18 15:20:00", "Arjun Deshmukh", "9876500104", "Green one-litre bottle with initials A.D. on the base.", "Claimed", "Priya Nair", "9876500122", "2026-10-18 17:05:00", "admin", "user"},
            {"Apple Watch SE", "Valuables", "School of AI, Room 108", "2026-10-18 08:50:00", "Priya Nair", "9876500105", "Midnight case with a navy strap and a small screen scratch.", "Unclaimed", null, null, null, "admin", null},
            {"Ambrane Power Bank", "Electronics", "Innovation Hub", "2026-10-17 18:40:00", "Kabir Shah", "9876500106", "Black 10,000 mAh power bank with a green charging cable.", "Claimed", "Ananya Iyer", "9876500123", "2026-10-17 20:15:00", "admin", "user"},
            {"Hostel Access Card", "Identification", "Boys Hostel Reception", "2026-10-17 11:55:00", "Ananya Iyer", "9876500107", "Blue UAU hostel access card in a clear sleeve.", "Unclaimed", null, null, null, "user", null},
            {"White Laboratory Coat", "Clothing & Accessories", "Life Sciences Lab", "2026-10-17 16:10:00", "Vivaan Joshi", "9876500108", "White lab coat with a UAU patch and size M label.", "Claimed", "Meera Rao", "9876500124", "2026-10-17 19:30:00", "admin", "admin"},
            {"Mechanical Keyboard", "Electronics", "Robotics Lab", "2026-10-16 19:45:00", "Meera Rao", "9876500109", "Compact black RGB keyboard with blue switches.", "Unclaimed", null, null, null, "user", null},
            {"Lenovo Laptop Charger", "Electronics", "Central Library, Third Floor", "2026-10-16 14:35:00", "Ishaan Singh", "9876500110", "Original Lenovo 65 W USB-C charger with a name tag.", "Claimed", "Diya Gupta", "9876500125", "2026-10-16 15:50:00", "admin", "admin"},
            {"Black Folding Umbrella", "Everyday Items", "Admin Block Entrance", "2026-10-16 17:20:00", "Diya Gupta", "9876500111", "Automatic black umbrella with a wooden handle.", "Unclaimed", null, null, null, "user", null},
            {"Aadhaar Card Holder", "Identification", "Student Activity Centre", "2026-10-15 10:15:00", "Rahul Verma", "9876500112", "Transparent card holder containing an identification photocopy.", "Claimed", "Neha Kapoor", "9876500126", "2026-10-15 11:40:00", "admin", "user"},
            {"AirPods Pro Case", "Electronics", "Academic Block B, Room 315", "2026-10-15 13:55:00", "Neha Kapoor", "9876500113", "White charging case with a green silicone cover.", "Unclaimed", null, null, null, "admin", null},
            {"Grey College Backpack", "Clothing & Accessories", "Academic Block C, Room 201", "2026-10-15 09:30:00", "Aditya Menon", "9876500114", "Grey backpack with a UAU fest badge and two notebooks.", "Claimed", "Sanvi Jain", "9876500127", "2026-10-15 14:20:00", "admin", "user"},
            {"Casio Digital Watch", "Valuables", "University Cafeteria, First Floor", "2026-10-14 12:05:00", "Sanvi Jain", "9876500115", "Black Casio watch with a square digital display.", "Unclaimed", null, null, null, "user", null},
            {"Black Framed Glasses", "Everyday Items", "Seminar Hall 2", "2026-10-14 15:40:00", "Kunal Chavan", "9876500116", "Thin black prescription glasses in a blue case.", "Claimed", "Riya Bhosale", "9876500128", "2026-10-14 18:10:00", "admin", "admin"},
            {"SanDisk USB Drive", "Electronics", "Central Library, First Floor", "2026-10-14 20:25:00", "Riya Bhosale", "9876500117", "Metal 128 GB USB drive with a red thread attached.", "Unclaimed", null, null, null, "user", null},
            {"Ganpati Keychain", "Everyday Items", "University Cafeteria Entrance", "2026-10-13 18:50:00", "Omkar Sawant", "9876500118", "Silver keyring with a small Ganpati charm and two keys.", "Claimed", "Isha Mishra", "9876500129", "2026-10-13 21:30:00", "admin", "user"},
            {"Blue Project Journal", "Study Supplies", "School of AI, Room 205", "2026-10-13 14:10:00", "Isha Mishra", "9876500119", "Blue project journal labelled Machine Learning Lab.", "Unclaimed", null, null, null, "user", null},
            {"Green Canvas Tote", "Everyday Items", "Design Studio", "2026-10-13 10:10:00", "Siddharth Roy", "9876500130", "Green tote printed with the UAU cultural fest logo.", "Unclaimed", null, null, null, "user", null},
            {"Kindle E-Reader", "Electronics", "Girls Hostel Common Room", "2026-10-12 19:25:00", "Tara Fernandes", "9876500131", "Black Kindle with a blue fabric cover and low battery.", "Claimed", "Yash Patel", "9876500132", "2026-10-13 09:00:00", "admin", "user"},
            {"Green UAU Scarf", "Clothing & Accessories", "Main Auditorium", "2026-10-12 08:45:00", "Yash Patel", "9876500133", "Green knitted scarf with blue stripes from a university event.", "Unclaimed", null, null, null, "admin", null},
            {"Physics Lab Record", "Study Supplies", "Physics Lab, Room 208", "2026-10-12 16:30:00", "Nisha Yadav", "9876500134", "Transparent folder containing a first-year physics lab record.", "Handed Over", null, null, null, "admin", null},
            {"Samsung Galaxy Phone", "Valuables", "Amphitheatre", "2026-10-11 20:05:00", "Varun Sethi", "9876500135", "Blue phone case with a mountain wallpaper on the lock screen.", "Claimed", "Kavya Reddy", "9876500136", "2026-10-12 12:10:00", "admin", "user"},
            {"Steel Lunch Box", "Everyday Items", "University Cafeteria Patio", "2026-10-11 12:20:00", "Kavya Reddy", "9876500137", "Three-tier steel lunch box inside a navy carry bag.", "Unclaimed", null, null, null, "user", null},
            {"Scientific Calculator", "Study Supplies", "Engineering Lab, Room 304", "2026-10-11 14:00:00", "Manav Malhotra", "9876500138", "Casio fx-991ES Plus with an M.M. label on the back.", "Claimed", "Pooja Shetty", "9876500139", "2026-10-11 16:30:00", "admin", "admin"},
            {"White Baseball Cap", "Clothing & Accessories", "Cricket Ground Pavilion", "2026-10-10 17:45:00", "Pooja Shetty", "9876500140", "White cap with blue UAU lettering on the front.", "Unclaimed", null, null, null, "user", null},
            {"Sony Earbuds Case", "Electronics", "Media Lab, Room 102", "2026-10-10 18:35:00", "Dev Agarwal", "9876500141", "Black Sony charging case without the earbuds.", "Claimed", "Aisha Khan", "9876500142", "2026-10-11 09:25:00", "admin", "user"},
            {"Silver Pendant Chain", "Valuables", "Main Gate Security Desk", "2026-10-10 13:15:00", "Aisha Khan", "9876500143", "Thin silver chain with a small star-shaped pendant.", "Unclaimed", null, null, null, "admin", null}
    };

    static final String driver = "com.mysql.cj.jdbc.Driver";
    static final String url = config("LOSTFOUND_DB_URL",
            "jdbc:mysql://localhost:3306/LostFoundDB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&characterEncoding=utf8");
    static final String username = config("LOSTFOUND_DB_USER", "root");
    static final String password = config("LOSTFOUND_DB_PASSWORD", "");

    Connection conn = null;
    Statement statement = null;
    ResultSet resultSet = null;

    private static String config(String key, String fallback) {
        String property = System.getProperty(key);
        if (property != null && !property.trim().isEmpty()) return property.trim();
        String env = System.getenv(key);
        if (env != null && !env.trim().isEmpty()) return env.trim();
        return fallback;
    }

    public ConnectionFactory() {
        try {
            Class.forName(driver);
            conn = openConnection();
            statement = conn.createStatement();
            initializeDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName(driver);
                conn = openConnection();
                statement = conn.createStatement();
                initializeDatabase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    private Connection openConnection() throws SQLException {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException initialError) {
            if (!isMissingDatabase(initialError)) throw initialError;

            String databaseName = databaseName(url);
            String serverUrl = serverUrl(url);
            if (databaseName == null || serverUrl == null) throw initialError;

            try (Connection serverConnection = DriverManager.getConnection(serverUrl, username, password);
                 Statement createStatement = serverConnection.createStatement()) {
                createStatement.executeUpdate("CREATE DATABASE IF NOT EXISTS `" + databaseName
                        + "` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci");
            }
            return DriverManager.getConnection(url, username, password);
        }
    }

    private boolean isMissingDatabase(SQLException error) {
        String message = error.getMessage();
        return error.getErrorCode() == 1049
                || (message != null && message.toLowerCase().contains("unknown database"));
    }

    private String databaseName(String jdbcUrl) {
        int schemeEnd = jdbcUrl.indexOf("//");
        int pathStart = schemeEnd < 0 ? -1 : jdbcUrl.indexOf('/', schemeEnd + 2);
        if (pathStart < 0 || pathStart == jdbcUrl.length() - 1) return null;
        int queryStart = jdbcUrl.indexOf('?', pathStart);
        String name = jdbcUrl.substring(pathStart + 1, queryStart < 0 ? jdbcUrl.length() : queryStart);
        return name.matches("[A-Za-z0-9_]+") ? name : null;
    }

    private String serverUrl(String jdbcUrl) {
        int schemeEnd = jdbcUrl.indexOf("//");
        int pathStart = schemeEnd < 0 ? -1 : jdbcUrl.indexOf('/', schemeEnd + 2);
        if (pathStart < 0) return null;
        int queryStart = jdbcUrl.indexOf('?', pathStart);
        String query = queryStart < 0 ? "" : jdbcUrl.substring(queryStart);
        return jdbcUrl.substring(0, pathStart + 1) + query;
    }

    private void initializeDatabase() throws SQLException {
        statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS users ("
                        + "id int NOT NULL AUTO_INCREMENT,"
                        + "name varchar(45) NOT NULL,"
                        + "location varchar(45) NOT NULL,"
                        + "phone varchar(20) NOT NULL,"
                        + "username varchar(20) NOT NULL,"
                        + "password varchar(200) NOT NULL,"
                        + "usertype varchar(45) NOT NULL,"
                        + "PRIMARY KEY (id)"
                        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"
        );
        statement.executeUpdate("ALTER TABLE users MODIFY phone varchar(20) NOT NULL");
        addUniqueUsernameIndexIfMissing();

        statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS userlogs ("
                        + "username varchar(45) NOT NULL,"
                        + "in_time varchar(45) NOT NULL,"
                        + "out_time varchar(45) NOT NULL"
                        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"
        );

        statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS app_settings ("
                        + "setting_key varchar(50) NOT NULL PRIMARY KEY,"
                        + "setting_value varchar(100) NOT NULL"
                        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"
        );

        statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS lost_items ("
                        + "id int NOT NULL AUTO_INCREMENT,"
                        + "item_name varchar(100) NOT NULL,"
                        + "category varchar(50) NOT NULL,"
                        + "pickup_location varchar(100) NOT NULL,"
                        + "pickup_time datetime NOT NULL,"
                        + "pickup_person varchar(50) NOT NULL,"
                        + "contact varchar(50) NOT NULL,"
                        + "description text,"
                        + "status varchar(20) NOT NULL DEFAULT 'Unclaimed',"
                        + "claimant varchar(50),"
                        + "claim_contact varchar(50),"
                        + "claim_time datetime,"
                        + "submitter_username varchar(20),"
                        + "claimant_username varchar(20),"
                        + "PRIMARY KEY (id)"
                        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"
        );
        statement.executeUpdate("ALTER TABLE lost_items MODIFY category varchar(50) NOT NULL");
        statement.executeUpdate("ALTER TABLE lost_items MODIFY status varchar(20) NOT NULL DEFAULT 'Unclaimed'");
        addColumnIfMissing("lost_items", "submitter_username", "varchar(20)");
        addColumnIfMissing("lost_items", "claimant_username", "varchar(20)");


        ensureUser("UAU Lost & Found Administrator", "Universal AI University, Karjat", "9876500001", "admin", "admin", "ADMIN");
        ensureUser("Aarav Sharma", "Universal AI University, Karjat", "9876500002", "user", "user", "USER");
        personalizeUniversityData();
        seedLostItems();
    }

    private void addUniqueUsernameIndexIfMissing() {
        try {
            statement.executeUpdate("ALTER TABLE users ADD UNIQUE KEY uk_username (username)");
        } catch (SQLException ignored) {
        }
    }

    private void addColumnIfMissing(String table, String column, String definition) {
        try {
            statement.executeUpdate("ALTER TABLE " + table + " ADD COLUMN " + column + " " + definition);
        } catch (SQLException ignored) {
        }
    }

    private void ensureUser(String name, String location, String phone, String user, String pass, String userType) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM users WHERE username=? LIMIT 1")) {
            ps.setString(1, user);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return;
            }
        }

        String insertSql = "INSERT INTO users (name,location,phone,username,password,usertype) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
            ps.setString(1, name);
            ps.setString(2, location);
            ps.setString(3, phone);
            ps.setString(4, user);
            ps.setString(5, pass);
            ps.setString(6, userType);
            ps.executeUpdate();
        }
    }

    private void personalizeUniversityData() throws SQLException {
        personalizeDefaultUser("admin", "Administrator", "UAU Lost & Found Administrator", "9876500001");
        personalizeDefaultUser("user", "Standard User", "Aarav Sharma", "9876500002");

        try (PreparedStatement check = conn.prepareStatement(
                "SELECT setting_value FROM app_settings WHERE setting_key='sample_data_profile'");
             ResultSet rs = check.executeQuery()) {
            if (rs.next() && SAMPLE_PROFILE.equals(rs.getString(1))) return;
        }

        String sql = "UPDATE lost_items SET item_name=?, category=?, pickup_location=?, pickup_time=?, "
                + "pickup_person=?, contact=?, description=?, status=?, claimant=?, claim_contact=?, "
                + "claim_time=?, submitter_username=?, claimant_username=? WHERE id=? AND item_name=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < UNIVERSITY_SAMPLE_DATA.length; i++) {
                String[] item = UNIVERSITY_SAMPLE_DATA[i];
                ps.setString(1, item[0]);
                ps.setString(2, item[1]);
                ps.setString(3, item[2]);
                ps.setTimestamp(4, Timestamp.valueOf(item[3]));
                ps.setString(5, item[4]);
                ps.setString(6, item[5]);
                ps.setString(7, item[6]);
                ps.setString(8, item[7]);
                ps.setString(9, item[8]);
                ps.setString(10, item[9]);
                ps.setTimestamp(11, item[10] == null ? null : Timestamp.valueOf(item[10]));
                ps.setString(12, item[11]);
                ps.setString(13, item[12]);
                ps.setInt(14, i + 1);
                ps.setString(15, OLD_SAMPLE_ITEMS[i]);
                ps.addBatch();
            }
            ps.executeBatch();
        }
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO app_settings (setting_key, setting_value) VALUES ('sample_data_profile', ?) "
                        + "ON DUPLICATE KEY UPDATE setting_value=VALUES(setting_value)")) {
            ps.setString(1, SAMPLE_PROFILE);
            ps.executeUpdate();
        }
    }

    private void personalizeDefaultUser(String usernameValue, String oldName, String newName, String phone) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE users SET name=?, location='Universal AI University, Karjat', phone=? "
                        + "WHERE username=? AND (name=? OR name=?)")) {
            ps.setString(1, newName);
            ps.setString(2, phone);
            ps.setString(3, usernameValue);
            ps.setString(4, oldName);
            ps.setString(5, newName);
            ps.executeUpdate();
        }
    }

    private void seedLostItems() throws SQLException {
        try (ResultSet rs = statement.executeQuery(
                "SELECT setting_value FROM app_settings WHERE setting_key='sample_items_seeded'")) {
            if (rs.next()) return;
        }
        boolean hasItems;
        try (ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM lost_items")) {
            hasItems = rs.next() && rs.getInt(1) > 0;
        }
        if (hasItems) {
            markSamplesInitialized();
            return;
        }

        for (String[] item : UNIVERSITY_SAMPLE_DATA) {
            insertLostItem(item[0], item[1], item[2], item[3], item[4], item[5], item[6], item[7],
                    item[8], item[9], item[10], item[11], item[12]);
        }
        markSamplesInitialized();
    }

    private void markSamplesInitialized() throws SQLException {
        statement.executeUpdate("INSERT IGNORE INTO app_settings (setting_key, setting_value) "
                + "VALUES ('sample_items_seeded', 'true')");
    }

    private void insertLostItem(String itemName, String category, String location, String pickupTime,
                                String pickupPerson, String contact, String description, String status,
                                String claimant, String claimContact, String claimTime, String submitter,
                                String claimantUsername) throws SQLException {
        String sql = "INSERT INTO lost_items "
                + "(item_name, category, pickup_location, pickup_time, pickup_person, contact, description, status, claimant, claim_contact, claim_time, submitter_username, claimant_username) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, itemName);
            ps.setString(2, category);
            ps.setString(3, location);
            ps.setTimestamp(4, Timestamp.valueOf(pickupTime));
            ps.setString(5, pickupPerson);
            ps.setString(6, contact);
            ps.setString(7, description);
            ps.setString(8, status);
            ps.setString(9, claimant);
            ps.setString(10, claimContact);
            ps.setTimestamp(11, claimTime == null ? null : Timestamp.valueOf(claimTime));
            ps.setString(12, submitter);
            ps.setString(13, claimantUsername);
            ps.executeUpdate();
        }
    }

    public boolean checkLogin(String username, String password, String userType) {
        String query = "SELECT * FROM users WHERE username=? AND password=? AND usertype=? LIMIT 1";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username.trim());
            ps.setString(2, password.trim());
            ps.setString(3, userType.trim());
            resultSet = ps.executeQuery();
            if (resultSet.next()) return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
