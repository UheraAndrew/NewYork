package newyork.organisational;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Role}.
 *
 * @author Developers
 *
 */
public interface IRole extends IEntityDao<Role> {

    static final IFetchProvider<Role> FETCH_PROVIDER = EntityUtils.fetch(Role.class).with(
            "name", "desc");
}
