package org.example.doantotnghiep.Payload.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorResponse {
    private String avata;
    private String name;
    private String position;
    private String description;
    private double price;
}
