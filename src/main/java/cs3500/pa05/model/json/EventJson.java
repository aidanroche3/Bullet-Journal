package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.enumerations.Day;
import java.time.LocalTime;

/**
 * Record for a JSON event
 */
public record EventJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("day") Day day,
    @JsonProperty("start") String start,
    @JsonProperty("duration") Double duration) {
}
