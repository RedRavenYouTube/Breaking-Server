package com.mite.breakingeverything.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Basic dedicated server for MiTE Breaking Everything 0.9.0
 * This is a placeholder implementation. The actual server logic needs to be implemented
 * based on the MiTE mod requirements, using hooks to patch the vanilla Minecraft server.
 */
public class Server {

    private ServerSocket serverSocket;
    private boolean running = false;

    public Server(int port) throws IOException {
        // Bind to 0.0.0.0 to allow connections from any IP
        serverSocket = new ServerSocket(port, 50, InetAddress.getByName("0.0.0.0"));
        System.out.println("Server started on port " + port);
    }

    public void start() {
        running = true;
        new Thread(this::run).start();
    }

    private void run() {
        while (running) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                // Handle client connection
                // TODO: Implement Minecraft protocol handling with MiTE modifications
                handleClient(clientSocket);
            } catch (IOException e) {
                if (running) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void handleClient(Socket socket) {
        // TODO: Implement client handling
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 25565; // Default Minecraft port
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port number, using default 25565");
            }
        }

        try {
            Server server = new Server(port);
            server.start();

            // Add shutdown hook
            Runtime.getRuntime().addShutdownHook(new Thread(server::stop));

            System.out.println("Press Ctrl+C to stop the server");
        } catch (IOException e) {
            System.err.println("Failed to start server: " + e.getMessage());
        }
    }
}