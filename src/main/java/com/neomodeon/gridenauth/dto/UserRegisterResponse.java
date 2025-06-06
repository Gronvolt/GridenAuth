package com.neomodeon.gridenauth.dto;

import java.util.UUID;

public record UserRegisterResponse(UUID uuid, String username, String email, String role) {
}
