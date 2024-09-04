package core.dtos;

import java.util.Optional;

public class AdaptedWebResponse {
    public int statusCode;
    public Optional<Object> body;

    private AdaptedWebResponse(int statusCode, Optional<Object> body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public static AdaptedWebResponse of(int statusCode, Optional<Object> body) {
        return new AdaptedWebResponse(statusCode, body);
    }

    public static AdaptedWebResponse of(int statusCode, Object body) {
        return new AdaptedWebResponse(statusCode, Optional.of(body));
    }

    public static AdaptedWebResponse of(int statusCode) {
        return new AdaptedWebResponse(statusCode, null);
    }
}
