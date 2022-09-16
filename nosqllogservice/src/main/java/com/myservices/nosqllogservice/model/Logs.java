package com.myservices.nosqllogservice.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Table
public class Logs {
    @PrimaryKey
    private Integer id;
    @NotBlank(message="loginfo cannot be blank")
    @Size(min=10,message="loginfo must be more than 10 characters long")
    private String loginfo;

    private String logobject;

    public Integer getId() {
        return id;
    }

    public String getLoginfo() {
        return loginfo;
    }

    public String getLogobject() {
        return logobject;
    }

    public Logs(Integer id, String loginfo, String logobject) {
        this.id = id;
        this.loginfo = loginfo;
        this.logobject = logobject;
    }


}
