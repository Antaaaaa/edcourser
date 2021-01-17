package anta.project.edcourser.config.rest.model;

import anta.project.edcourser.config.rest.resources.Statuses;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class MvcResponse {
    @Getter
    @Setter
    @JsonProperty("status")
    protected int status;

    public MvcResponse(int status) {
        this.status = status;
    }

    public MvcResponse(Statuses status) {
        this.status = status.getStatus();
    }
}
