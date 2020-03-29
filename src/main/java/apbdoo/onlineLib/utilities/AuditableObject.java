package apbdoo.onlineLib.utilities;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public abstract class AuditableObject implements Auditable<String> {

    private Date dateCreated;
    private String createdByUser;
    private Date dateLastModified;
    private String lastModifyingUser;


    @Override
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    @Override
    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    @Override
    public String getLastModifyingUser() {
        return lastModifyingUser;
    }

    public void setLastModifyingUser(String lastModifyingUser) {
        this.lastModifyingUser = lastModifyingUser;
    }
}
