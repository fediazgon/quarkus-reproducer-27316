package org.issue;

import io.quarkus.security.identity.IdentityProviderManager;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.AuthenticationRequest;
import io.quarkus.vertx.http.runtime.security.ChallengeData;
import io.quarkus.vertx.http.runtime.security.HttpAuthenticationMechanism;
import io.quarkus.vertx.http.runtime.security.HttpSecurityUtils;
import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.RoutingContext;
import java.util.Optional;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApiKeyAuthenticationMechanism implements HttpAuthenticationMechanism {

  private static final String API_KEY_HEADER = "X-API-Key";

  private static final Uni<SecurityIdentity> UNAUTHENTICATED = Uni.createFrom().nullItem();

  @Override
  public Uni<SecurityIdentity> authenticate(
      final RoutingContext context, final IdentityProviderManager identityProviderManager) {
    System.out.println("ApiKeyAuthenticationMechanism called");
    return Optional.ofNullable(context.request().headers().get(API_KEY_HEADER))
        .map(ApiKeyAuthenticationRequest::new)
        .map(authRequest -> HttpSecurityUtils.setRoutingContextAttribute(authRequest, context))
        .map(identityProviderManager::authenticate)
        .orElse(UNAUTHENTICATED);
  }

  @Override
  public Set<Class<? extends AuthenticationRequest>> getCredentialTypes() {
    return Set.of(ApiKeyAuthenticationRequest.class);
  }

  @Override
  public Uni<ChallengeData> getChallenge(final RoutingContext context) {
    return Uni.createFrom().nullItem();
  }

  @Override
  public Uni<Boolean> sendChallenge(final RoutingContext context) {
    return Uni.createFrom().item(false);
  }
}
