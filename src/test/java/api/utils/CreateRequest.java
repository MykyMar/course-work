package api.utils;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRequest<T> {
    private String jsonrpc;
    private String method;
    private int id;
    private T params;
}
