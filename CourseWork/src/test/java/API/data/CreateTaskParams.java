package API.data;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTaskParams {
    private String title;
    private int project_id;
    private int owner_id;
    private String color_id;
}
