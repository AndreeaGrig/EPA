package apbdoo.onlineLib.utilities;

import java.util.Date;

public interface Auditable<T> {
    Date getDateCreated();
    T getCreatedByUser();
    Date getDateLastModified();
    T getLastModifyingUser();
}
