package com.dataart.task1.threads;

public abstract class Part extends Thread {

    private Part antecedent;

    public Part(){}

    public Part(Part antecedent){
        this.antecedent = antecedent;
    }

    @Override
    public void run() {
        build();

        if(antecedent != null){
            try {
                antecedent.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        finish();
    }

    protected abstract void build();

    protected abstract void finish();

    public void setAntecedent(Part antecedent) {
        this.antecedent = antecedent;
    }
}
