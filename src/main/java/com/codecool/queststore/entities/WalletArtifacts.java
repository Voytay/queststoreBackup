package com.codecool.queststore.entities;

public class WalletArtifacts {

    private int walletId;
    private int artifactId;
    private int quantity;

    public WalletArtifacts(int walletId, int artifactId) {
        this.walletId = walletId;
        this.artifactId = artifactId;
        this.quantity = 0;
    }

    public WalletArtifacts(int walletId, int artifactId, int quantity) {
        this.walletId = walletId;
        this.artifactId = artifactId;
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public void decrementQuantity() {
        this.quantity--;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getWalletId() {
        return this.walletId;
    }

    public int getArtifactId() {
        return this.artifactId;
    }
}
