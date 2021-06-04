package data;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperatingHistory {
   //Дата, когда было опробование двигателе или летная смена
   private Date dateOperating;
   //Информация о газовке или летной смене
    private String information;

}
