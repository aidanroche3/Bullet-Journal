package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.enumerations.Meridiem;

public record BujoTimeJson(
    @JsonProperty("hour") int hour,
    @JsonProperty("minute") int minute,
    @JsonProperty("meridiem") Meridiem meridiem) {
}
