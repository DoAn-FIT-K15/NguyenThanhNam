package org.example.doantotnghiep.Payload.Response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleResponse {
    private String dayofWeek;
    private String date;
    private String time;
    private boolean isEmpty;
}
