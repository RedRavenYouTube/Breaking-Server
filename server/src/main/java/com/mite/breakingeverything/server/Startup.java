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
     * Main method for server startup.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Print the startup message as done in BES via this.F.a
        F.info("Starting not a integrated minecraft server version 1.6.4");

        System.out.println("Starting MiTE Breaking Everything 0.9.0 dedicated server...");

        // Initialize directories
        initializeDirectories();

        // TODO: Load configuration
        // TODO: Patch vanilla server jar with MiTE hooks
        // TODO: Launch the patched server

        // Note: The "Done!" message will be printed by the vanilla server startup process
        // after the patched server jar is launched and fully initialized
        System.out.println("MiTE server preparation complete. Launching patched server...");
    }
}