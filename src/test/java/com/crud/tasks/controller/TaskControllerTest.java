package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    DbService dbService;
    @MockBean
    TaskMapper taskMapper;

    @Test
    void shouldGetTasksTest() throws Exception {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1l,"test 1","testing"));
        taskList.add(new Task(2L,"test2", "testing"));
        List<TaskDto> taskDtos = new ArrayList<>();
        taskDtos.add(new TaskDto(1l,"test 1","testing"));
        taskDtos.add(new TaskDto(2L,"test2", "testing"));
        when(dbService.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtos);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));


    }

    @Test
    void shouldGetSingleTaskTest() throws Exception{
        //Given
        Task task = new Task(1l,"test", "testing");
        when(dbService.getTask(any(long.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(new TaskDto(1l,"test", "testing"));
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("testing")));
    }
    @Test
    void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(0L,"test", "testing");
        Task task = new Task(0l,"test", "testing");
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1l, "test", "testing");
        Task savingTask = new Task(1l, "test", "testing");
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(savingTask);
        when(taskMapper.mapToTaskDto(savingTask)).thenReturn(taskDto);
        when(dbService.saveTask(savingTask)).thenReturn(savingTask);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("testing")));

    }

    @Test
    void shouldTryDelete() throws Exception {
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/tasks/67"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(dbService,times(1)).deleteTask(67);
    }



}