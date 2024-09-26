package API.data;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddProjectUserParams {
   private  int project_id;
   private int user_id;
   private String role;
}
