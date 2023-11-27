package org.myApplication.app.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.myApplication.domain.enums.Operation;

@Getter
@Setter
@AllArgsConstructor
public class CriteriaModel {
    private String field;
    private Operation operation;
    private String value;
}
