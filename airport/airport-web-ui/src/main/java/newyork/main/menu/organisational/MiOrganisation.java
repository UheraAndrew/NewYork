package newyork.main.menu.organisational;

import ua.com.fielden.platform.entity.annotation.EntityType;
import ua.com.fielden.platform.ui.menu.MiWithConfigurationSupport;
import newyork.organisational.Organisation;
/**
 * Main menu item representing an entity centre for {@link Organisation}.
 *
 * @author Developers
 *
 */
@EntityType(Organisation.class)
public class MiOrganisation extends MiWithConfigurationSupport<Organisation> {

}
