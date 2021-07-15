package com.automobile.assistance.otto;

import com.automobile.assistance.data.remote.pojo.Job;

public class AssistanceEvent {

    String type;
    Job job;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public AssistanceEvent(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
