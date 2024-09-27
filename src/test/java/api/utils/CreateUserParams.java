package api.utils;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserParams {
    private String username;
    private String password;
    private String role;
}
