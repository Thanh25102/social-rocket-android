
package tech.mobile.socialrocket.data.remote.model;

public class TodoApiRes {
    private Long id;
    private Boolean completed;
    private String title;
    private Long userId;

    public Long getId() { return id; }
    public void setId(long value) { this.id = value; }

    public Boolean getCompleted() { return completed; }
    public void setCompleted(boolean value) { this.completed = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public Long getUserId() { return userId; }
    public void setUserId(long value) { this.userId = value; }
}
