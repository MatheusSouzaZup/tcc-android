package br.com.bluedot.redevalor.data.model;

import br.com.bluedot.redevalor.custom.status.Status;

public class Task {

    private String id;
    private Status status;

    public Task(String id, Status status) {
        this.id = id;
        this.status = status;
    }

    public Task() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
