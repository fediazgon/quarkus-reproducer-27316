package org.issue;

import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.IdentityProvider;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.mutiny.Uni;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class APiKeyIdentityProvider implements IdentityProvider<ApiKeyAuthenticationRequest> {

  @Override
  public Class<ApiKeyAuthenticationRequest> getRequestType() {
    return ApiKeyAuthenticationRequest.class;
  }

  @Override
  public Uni<SecurityIdentity> authenticate(
      ApiKeyAuthenticationRequest apiKeyAuthenticationRequest,
      AuthenticationRequestContext authenticationRequestContext) {
    System.out.println("APiKeyIdentityProvider called");
    return Uni.createFrom()
        .item(
            QuarkusSecurityIdentity.builder().setAnonymous(true).addRoles(Set.of("USER")).build());
  }
}
