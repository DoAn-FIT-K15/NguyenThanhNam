package org.example.doantotnghiep.Payload.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchDoctorResponse {

    private String avata;

    private String name;

    private String position;

    private String specialistName;

    private String slugDoctor;
}
