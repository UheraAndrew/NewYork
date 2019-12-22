package newyork.main.menu.organisational;

import ua.com.fielden.platform.entity.annotation.EntityType;
import ua.com.fielden.platform.ui.menu.MiWithConfigurationSupport;
import newyork.organisational.BusinessUnit;
/**
 * Main menu item representing an entity centre for {@link BusinessUnit}.
 *
 * @author Developers
 *
 */
@EntityType(BusinessUnit.class)
public class MiBusinessUnit extends MiWithConfigurationSupport<BusinessUnit> {

}
