package co2124.part1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class DoctorCreationDTO {
    @NotBlank(message = "Name is required")
    String name;
    @NotBlank(message = "Specialisation is required")
    String specialisation;
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    String email;
    @NotBlank(message = "Phone number is required")
    @JsonProperty("phone_number")
    String phoneNumber;

    public @NotBlank(message = "Name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Specialisation is required") String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(@NotBlank(message = "Specialisation is required") String specialisation) {
        this.specialisation = specialisation;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Phone number is required") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "Phone number is required") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
