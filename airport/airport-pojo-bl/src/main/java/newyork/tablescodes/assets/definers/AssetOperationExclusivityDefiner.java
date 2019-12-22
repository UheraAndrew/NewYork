package newyork.tablescodes.assets.definers;

import static ua.com.fielden.platform.utils.CollectionUtil.setOf;

import java.util.Set;

import newyork.tablescodes.assets.AssetOperation;
import ua.com.fielden.platform.entity.meta.IAfterChangeEventHandler;
import ua.com.fielden.platform.entity.meta.MetaProperty;

public class AssetOperationExclusivityDefiner implements IAfterChangeEventHandler<Object> {

    private final static Set<String> operationPropNames = setOf("role", "bu", "org");
    
    @Override
    public void handle(final MetaProperty<Object> prop, final Object value) {
        final AssetOperation operation = prop.getEntity();
        final boolean allEmpty = operation.getRole() == null && operation.getBu() == null && operation.getOrg() == null;
        
        operationPropNames.stream()
                .map(name -> operation.getProperty(name))
                .filter(p -> p.getValue() == null)
                .forEach(p -> p.setRequired(allEmpty));
        
        if (value != null) {
            operationPropNames.stream()
                    .filter(name -> !name.equalsIgnoreCase(prop.getName()))
                    .map(name -> operation.getProperty(name))
                    .forEach(p -> {p.setRequired(false); p.setValue(null);});
            prop.setRequired(true);
        }
    }
}
