package my.goals.application.adapters;

public class AdaptedWebResponse {

    public int status;
    public Object body;

    private AdaptedWebResponse(int status, Object body) {
        this.status = status;
        this.body = body;
    }

    public static AdaptedWebResponse of(int status) {
        return new AdaptedWebResponse(status, null);
    }

    public static AdaptedWebResponse of(int status, Object body) {
        return new AdaptedWebResponse(status, body);
    }

}
