package org.issue;

import io.quarkus.security.identity.request.BaseAuthenticationRequest;

public class ApiKeyAuthenticationRequest extends BaseAuthenticationRequest {

  private String apiKey;

  public ApiKeyAuthenticationRequest(final String apiKey) {
    System.out.println("ApiKeyAuthenticationRequest created");
    this.apiKey = apiKey;
  }
}
