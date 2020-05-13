package com.andrewd.libs.reading.api.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddReadingBookRequest {
    @JsonProperty("userId")
    @NotNull
    private long userId;

    @JsonProperty("bookId")
    @NotNull
    private long bookId;
}
