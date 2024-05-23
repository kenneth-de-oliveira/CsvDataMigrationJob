package com.springbatch.datamigration.domain;

import lombok.Data;
import org.apache.logging.log4j.util.Strings;

@Data
public class Student {

    private int id;
    private String document;
    private String name;
    private String email;

    public boolean isValid() {
        return !Strings.isBlank(document) && !Strings.isBlank(name) && !Strings.isBlank(email);
    }

}