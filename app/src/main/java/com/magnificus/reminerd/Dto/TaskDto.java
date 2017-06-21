package com.magnificus.reminerd.Dto;

import com.magnificus.reminerd.Entities.TaskEntity;

import java.util.List;

/**
 * Created by allysson on 12/06/17.
 */

public class TaskDto {

    private List<TaskEntity> tasks;

    public List<TaskEntity> getTasks() {
        return this.tasks;
    }
}
