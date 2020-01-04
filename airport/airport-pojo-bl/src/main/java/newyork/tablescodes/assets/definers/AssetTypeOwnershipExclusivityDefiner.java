package newyork.tablescodes.assets.definers;

import static ua.com.fielden.platform.utils.CollectionUtil.setOf;

import java.util.Set;

import newyork.tablescodes.assets.AssetOwnership;
import newyork.tablescodes.assets.AssetTypeOwnership;
import ua.com.fielden.platform.entity.AbstractEntity;
import ua.com.fielden.platform.entity.meta.IAfterChangeEventHandler;
import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.error.Result;

public class AssetTypeOwnershipExclusivityDefiner implements IAfterChangeEventHandler<Object> {

    private final static Set<String> ownershipPropNames = setOf("role", "bu", "org");
    
    @Override
    public void handle(final MetaProperty<Object> prop, final Object value) {
        if (!(prop.getEntity() instanceof AssetOwnership) && !(prop.getEntity() instanceof AssetTypeOwnership)) {
            throw Result.failure("Stringly entitles of type AssetOwnership or AssetTypeOwnership are expected");
            
        }
        final AbstractEntity<?> ownership = prop.getEntity();
        final boolean allEmpty = ownership.get("role") == null && ownership.get("bu") == null && ownership.get("org") == null;
        
        ownershipPropNames.stream()
                .map(name -> ownership.getProperty(name))
                .filter(p -> p.getValue() == null)
                .forEach(p -> p.setRequired(allEmpty));
        
        if (value != null) {
            ownershipPropNames.stream()
                    .filter(name -> !name.equalsIgnoreCase(prop.getName()))
                    .map(name -> ownership.getProperty(name))
                    .forEach(p -> {p.setRequired(false); p.setValue(null);});
            prop.setRequired(true);
        }
    }
}
