package co2124.part1.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class PatientCreationDTO {
    @NotBlank(message = "Name is required")
    public String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    public String email;
    @NotBlank(message = "Phone number is required")
    public String phoneNumber;

    public @NotBlank(message = "Name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") String name) {
        this.name = name;
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

    public @NotBlank(message = "Address is required") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Address is required") String address) {
        this.address = address;
    }

    @NotBlank(message = "Address is required")
    public String address;
}
