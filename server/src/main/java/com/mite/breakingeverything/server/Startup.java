package com.mite.breakingeverything.server;

import java.io.File;
import java.util.logging.Logger;

/**
 * Startup class for MiTE Breaking Everything dedicated server.
 * Handles initialization of server directories and environment setup.
 */
public class Startup {

    private static final Logger F = Logger.getLogger("Minecraft-Server");

    private static final String MITE_FOLDER = "MiTE";
    private static final String WORLDS_FOLDER = "worlds";
    private static final String CONFIG_FOLDER = "config";
    private static final String LOGS_FOLDER = "logs";
    private static final String PLUGINS_FOLDER = "plugins"; // For compatibility

    /**
     * Initializes the server environment by creating necessary directories.
     */
    public static void initializeDirectories() {
        File currentDir = new File(System.getProperty("user.dir"));

        // Create MiTE-specific directory
        createDirectory(currentDir, MITE_FOLDER);

        // Create standard server directories
        createDirectory(currentDir, WORLDS_FOLDER);
        createDirectory(currentDir, CONFIG_FOLDER);
        createDirectory(currentDir, LOGS_FOLDER);
        createDirectory(currentDir, PLUGINS_FOLDER);

        // Create default world directory
        createDirectory(new File(currentDir, WORLDS_FOLDER), "world");

        System.out.println("MiTE server directories initialized successfully.");
    }

    /**
     * Creates a directory if it doesn't exist.
     * @param parent The parent directory
     * @param name The directory name
     */
    private static void createDirectory(File parent, String name) {
        File dir = new File(parent, name);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Created directory: " + dir.getAbsolutePath());
            } else {
                System.err.println("Failed to create directory: " + dir.getAbsolutePath());
            }
        }
    }

    /**
     * Called by any launcher to perform server startup work.
     * Using a dedicated startServer method ensures the logic can be invoked
     * programmatically (for example from a patcher or custom launcher).
     */
    public static void startServer() {
        // log a custom MiTE startup notice; hosting services watch for this log entry
        this.F.a("Starting MiTE Breaking Everything dedicated server v0.9.0");

        System.out.println("Initializing MiTE Breaking Everything 0.9.0...");

        // set up folders used by the server
        initializeDirectories();

        // TODO: Load configuration
        // TODO: Apply MiTE transformations to a vanilla server jar
        // TODO: Launch the patched vanilla server

        // after the patched server launches, the vanilla code will emit "Done!" when ready
        System.out.println("Preparation finished, launching server jar...");
    }
    /**
     * Entry point for command-line launching; delegates to startServer.
     */
    public static void main(String[] args) {
        startServer();
    }
}