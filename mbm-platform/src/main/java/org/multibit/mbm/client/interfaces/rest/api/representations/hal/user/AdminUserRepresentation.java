package org.multibit.mbm.client.interfaces.rest.api.representations.hal.user;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.theoryinpractise.halbuilder.api.Representation;
import org.multibit.mbm.client.domain.model.model.User;

/**
 * <p>Representation to provide the following to {@link org.multibit.mbm.client.domain.model.model.User}:</p>
 * <ul>
 * <li>Creates a representation of a single {@link org.multibit.mbm.client.domain.model.model.User} update for an administrator</li>
 * </ul>
 *
 * @since 0.0.1
 */
public class AdminUserRepresentation {

  private final CustomerUserRepresentation customerUserRepresentation = new CustomerUserRepresentation();

  public Representation get(User user, Optional<User> principal) {
    Preconditions.checkNotNull(user, "user");
    Preconditions.checkNotNull(user.getId(), "id");

    // Build on the Customer representation
    return customerUserRepresentation.get(user, principal)
      // Must use individual property entries due to collections
      // The admin must have full visibility
      .withProperty("staff_member", user.isStaffMember())
      .withProperty("locked",user.isLocked())
      .withProperty("created_at",user.getCreatedAt())
      .withProperty("password_reset_at",user.getPasswordResetAt());

  }

  public Representation get(User user) {
    return get(user, Optional.<User>absent());
  }

}
