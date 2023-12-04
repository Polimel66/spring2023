package org.myApplication.app.filter;

import lombok.*;
import org.myApplication.domain.enums.Operation;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriteriaModel {
    private String field;
    private Operation operation;
    private String value;
}
