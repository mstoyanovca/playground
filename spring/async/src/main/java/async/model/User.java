package async.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String name;
    private String blog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getName(), user.getName()) && Objects.equals(getBlog(), user.getBlog());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBlog());
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", blog=" + blog + "]";
    }
}
