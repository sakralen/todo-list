package com.example.todolist.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValidationExceptionDto {

    private List<Violation> violations;

    public record Violation(String fieldName,
                            String message) {
    }

}
