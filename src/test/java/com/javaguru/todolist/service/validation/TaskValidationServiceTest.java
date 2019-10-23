package com.javaguru.todolist.service.validation;

import com.javaguru.todolist.dto.TaskDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TaskValidationServiceTest {

    @Mock
    private TaskUniqueNameValidationRule uniqueNameValidationRule;

    @Mock
    private TaskNameValidationRule taskNameValidationRule;

    @Captor
    private ArgumentCaptor<TaskDto> captor;

    private TaskValidationService victim;

    private TaskDto taskDto = taskDto();

    @Before
    public void setUp() {
        Set<TaskValidationRule> rules = new HashSet<>();
        rules.add(uniqueNameValidationRule);
        rules.add(taskNameValidationRule);

        victim = new TaskValidationService(rules);
    }

    @Test
    public void shouldValidate() {
        victim.validate(taskDto);

        verify(uniqueNameValidationRule).validate(captor.capture());
        verify(taskNameValidationRule).validate(captor.capture());

        List<TaskDto> resultList = captor.getAllValues();
        assertThat(resultList).containsOnly(taskDto);
    }

    private TaskDto taskDto() {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(100L);
        taskDto.setDescription("TEST_DESCRIPTION");
        taskDto.setName("TEST_NAME");
        return taskDto;
    }
}