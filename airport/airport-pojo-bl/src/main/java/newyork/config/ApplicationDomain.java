package newyork.config;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import newyork.personnel.Person;
import ua.com.fielden.platform.basic.config.IApplicationDomainProvider;
import ua.com.fielden.platform.domain.PlatformDomainTypes;
import ua.com.fielden.platform.entity.AbstractEntity;
import newyork.tablescodes.assets.AssetClass;
import newyork.tablescodes.assets.AssetOperation;
import newyork.tablescodes.assets.ServiceStatus;
import newyork.tablescodes.assets.AssetType;
import newyork.tablescodes.assets.AssetTypeOperation;
import newyork.tablescodes.assets.ConditionRating;
import newyork.assets.Asset;
import newyork.tablescodes.assets.ui_actions.OpenAssetClassMasterAction;
import newyork.tablescodes.assets.master.menu.actions.AssetClassMaster_OpenMain_MenuItem;
import newyork.tablescodes.assets.master.menu.actions.AssetClassMaster_OpenAssetType_MenuItem;
import newyork.assets.AssetFinDet;
import newyork.projects.Project;
import newyork.organisational.Role;
import newyork.organisational.BusinessUnit;
import newyork.organisational.Organisation;
import newyork.tablescodes.assets.AssetTypeOwnership;
import newyork.tablescodes.assets.AssetOwnership;
import newyork.tablescodes.assets.AssetManagement;
import newyork.tablescodes.assets.AssetTypeManagement;

/**
 * A class to register domain entities.
 * 
 * @author TG Team
 * 
 */
public class ApplicationDomain implements IApplicationDomainProvider {
    private static final Set<Class<? extends AbstractEntity<?>>> entityTypes = new LinkedHashSet<>();
    private static final Set<Class<? extends AbstractEntity<?>>> domainTypes = new LinkedHashSet<>();

    private static void add(final Class<? extends AbstractEntity<?>> domainType) {
        entityTypes.add(domainType);
        domainTypes.add(domainType);
    }

    /**
                                	                             * This is a static initialisation block where all entity types should be registered.
                                	                             */
    static {
        entityTypes.addAll(PlatformDomainTypes.types);
        add(Person.class);
        add(AssetClass.class);
        add(AssetType.class);
        add(ServiceStatus.class);
        add(ConditionRating.class);
        add(Asset.class);
        add(OpenAssetClassMasterAction.class);
        add(AssetClassMaster_OpenMain_MenuItem.class);
        add(AssetClassMaster_OpenAssetType_MenuItem.class);
        add(AssetFinDet.class);
        add(Project.class);
        add(Role.class);
        add(BusinessUnit.class);
        add(Organisation.class);
        add(AssetTypeOwnership.class);
        add(AssetOwnership.class);
        add(AssetOperation.class);
        add(AssetTypeOperation.class);
        add(AssetManagement.class);
        add(AssetTypeManagement.class);

    }

    @Override
    public List<Class<? extends AbstractEntity<?>>> entityTypes() {
        return Collections.unmodifiableList(entityTypes.stream().collect(Collectors.toList()));
    }

    public List<Class<? extends AbstractEntity<?>>> domainTypes() {
        return Collections.unmodifiableList(domainTypes.stream().collect(Collectors.toList()));
    }
}
