package com.solvd.multithreading;

import java.util.concurrent.*;

public class ConnectionPool {
    private static ConnectionPool instance;
    private final BlockingQueue<Connection> pool;
    private final ExecutorService executor;

    private ConnectionPool(int poolSize) {
        this.pool = new LinkedBlockingQueue<>(poolSize);
        this.executor = Executors.newFixedThreadPool(poolSize);

        for (int i = 0; i < poolSize; i++) {
            pool.add(new Connection("Connection " + (i + 1)));
        }
    }

    public static synchronized ConnectionPool getInstance(int poolSize) {
        if (instance == null) {
            instance = new ConnectionPool(poolSize);
        }
        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        return pool.take();
    }

    public void releaseConnection(Connection connection) {
        pool.offer(connection);
    }

    public void shutdown() {
        executor.shutdown();
    }

    public CompletableFuture<Connection> getConnectionAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return getConnection();
            } catch (InterruptedException e) {
                throw new RuntimeException("Failed to get connection asynchronously", e);
            }
        }, executor);
    }
}
