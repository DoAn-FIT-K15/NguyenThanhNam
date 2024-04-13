package org.example.doantotnghiep.Payload.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExaminationServiceResponse {
    private int id;

    private String img;

    private String name;
}
