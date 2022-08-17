package org.issue;

import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.SecurityIdentityAugmentor;
import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IdentitySecurityIdentityAugmentor implements SecurityIdentityAugmentor {

  @Override
  public Uni<SecurityIdentity> augment(
      SecurityIdentity securityIdentity,
      AuthenticationRequestContext authenticationRequestContext) {
    System.out.println("SecurityIdentityAugmentor called");
    return Uni.createFrom().item(securityIdentity);
  }
}
